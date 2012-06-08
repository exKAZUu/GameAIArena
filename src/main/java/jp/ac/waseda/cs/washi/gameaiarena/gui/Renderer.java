package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;

public abstract class Renderer {
  protected final JGamePanel panel;
  protected final ImageObserver imageObserver;
  protected final MediaTracker mediaTracker;
  protected int imageCount;

  public Renderer(JGamePanel panel) {
    this.panel = panel;
    this.imageObserver = panel;
    this.mediaTracker = new MediaTracker(panel);
  }

  public abstract void clear(Color c);

  public Image createImage(int width, int height) {
    return panel.createEmptyImage(width, height);
  }

  public Image createImage(int width, int height, Color c) {
    final Image image = panel.createEmptyImage(width, height);
    final Graphics g = image.getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, width, height);
    g.dispose();
    return image;
  }

  public void drawImage(Image img, int x, int y) {
    Graphics g = getGraphics();
    g.drawImage(img, x, y, imageObserver);
  }

  public void drawPixel(int x, int y, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawLine(x, y, x, y);
  }

  public void drawRect(int x, int y, int width, int height, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawRect(x, y, width, height);
  }

  public void fillRect(int x, int y, int width, int height, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
  }

  public ImageObserver getImageObserver() {
    return imageObserver;
  }

  public Image loadImage(String path) {
    final Image image = panel.loadImage(path);
    mediaTracker.addImage(image, imageCount++);
    return image;
  }

  public void waitLoadImage() {
    try {
      mediaTracker.waitForAll();
    } catch (final InterruptedException e) {}
  }

  public void drawString(String str, int x, int y, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawString(str, x, y);
  }

  protected void forceRepaint() {
    panel.forceRepaint();
    panel.tryResizeBufferImage();
  }

  protected abstract Graphics getGraphics();

  public JGamePanel getPanel() {
    return panel;
  }
}
