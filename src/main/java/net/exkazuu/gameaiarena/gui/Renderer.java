package net.exkazuu.gameaiarena.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.util.HashMap;

public abstract class Renderer {
  protected final JGamePanel panel;
  protected final ImageObserver imageObserver;
  protected final MediaTracker mediaTracker;
  protected int imageCount;
  protected boolean rendered;
  private HashMap<Image, Integer> image2Ids;

  public Renderer(JGamePanel panel) {
    this.panel = panel;
    this.imageObserver = panel;
    this.mediaTracker = new MediaTracker(panel);
    this.image2Ids = new HashMap<Image, Integer>();
    this.rendered = false;
  }

  public abstract void clear(Color c);

  public final Image createImage(int width, int height) {
    Image image = panel.createEmptyImage(width, height);
    image2Ids.put(image, imageCount++);
    return image;
  }

  public final Image createImage(int width, int height, Color c) {
    Image image = panel.createEmptyImage(width, height);
    Graphics g = image.getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, width, height);
    g.dispose();
    image2Ids.put(image, imageCount++);
    return image;
  }

  public final void drawImage(Image img, int x, int y) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.drawImage(img, x, y, imageObserver);
  }

  public final void drawComposedImages(Image img1, Image img2, int x, int y, int alpha) {
    this.rendered = true;
    Graphics g = getGraphics();
    // img1.get
    g.drawImage(img1, x, y, imageObserver);
  }

  public void drawPixel(int x, int y, Color c) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawLine(x, y, x, y);
  }

  public final void drawRect(int x, int y, int width, int height, Color c) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawRect(x, y, width, height);
  }

  public final void fillRect(int x, int y, int width, int height, Color c) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
  }

  public final ImageObserver getImageObserver() {
    return imageObserver;
  }

  public final Image loadImage(String path) {
    final Image image = panel.loadImage(path);
    mediaTracker.addImage(image, imageCount);
    image2Ids.put(image, imageCount++);
    return image;
  }

  public final void waitLoadImage() {
    try {
      mediaTracker.waitForAll();
    } catch (final InterruptedException e) {}
  }

  public final void drawString(String str, int x, int y) {
    this.rendered = true;
    drawString(str, x, y, Color.BLACK);
  }

  public final void drawString(String str, int x, int y, Color c) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawString(str, x, y);
  }

  public final void drawString(String str, int x, int y, Font f) {
    this.rendered = true;
    drawString(str, x, y, Color.BLACK, f);
  }

  public final void drawString(String str, int x, int y, Color c, Font f) {
    this.rendered = true;
    Graphics g = getGraphics();
    g.setColor(c);
    g.setFont(f);
    g.drawString(str, x, y);
  }

  protected final void forceRepaint() {
    if (this.rendered == true) {
      this.rendered = false;
      panel.forceRepaint();
    } else {
      System.out.println("skip repaint");
    }
    panel.tryResizeBufferImage();
  }

  protected abstract Graphics getGraphics();

  public final JGamePanel getPanel() {
    return panel;
  }
}
