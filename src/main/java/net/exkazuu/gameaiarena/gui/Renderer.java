package net.exkazuu.gameaiarena.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.util.HashMap;


public abstract class Renderer {
  protected final JGamePanel panel;
  protected final ImageObserver imageObserver;
  protected final MediaTracker mediaTracker;
  protected int imageCount;
  private PrintStream stream;
  private HashMap<Image, Integer> image2Ids;

  public Renderer(JGamePanel panel) {
    this.panel = panel;
    this.imageObserver = panel;
    this.mediaTracker = new MediaTracker(panel);
    this.image2Ids = new HashMap<Image, Integer>();
  }

  public abstract void clear(Color c);

  public final Image createImage(int width, int height) {
    if (enabledLogging()) log("createImage", width + "," + height + "," + imageCount);
    Image image = panel.createEmptyImage(width, height);
    image2Ids.put(image, imageCount++);
    return image;
  }

  public final Image createImage(int width, int height, Color c) {
    if (enabledLogging())
      log("createImage", width + "," + height + "," + logColor(c) + "," + imageCount);
    Image image = panel.createEmptyImage(width, height);
    Graphics g = image.getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, width, height);
    g.dispose();
    image2Ids.put(image, imageCount++);
    return image;
  }

  public final void drawImage(Image img, int x, int y) {
    if (enabledLogging()) log("drawImage", image2Ids.get(img) + "," + x + "," + y);
    Graphics g = getGraphics();
    g.drawImage(img, x, y, imageObserver);
  }

  public void drawPixel(int x, int y, Color c) {
    if (enabledLogging()) log("drawPixel", x + "," + y + "," + logColor(c));
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawLine(x, y, x, y);
  }

  public final void drawRect(int x, int y, int width, int height, Color c) {
    if (enabledLogging())
      log("drawRect", x + "," + y + "," + width + "," + height + "," + logColor(c));
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawRect(x, y, width, height);
  }

  public final void fillRect(int x, int y, int width, int height, Color c) {
    if (enabledLogging())
      log("fillRect", x + "," + y + "," + width + "," + height + "," + logColor(c));
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
  }

  public final ImageObserver getImageObserver() {
    return imageObserver;
  }

  public final Image loadImage(String path) {
    if (enabledLogging()) log("loadImage", path + "," + imageCount);
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
    if (enabledLogging()) log("drawString", str + "," + x + "," + y);
    drawString(str, x, y, Color.BLACK);
  }

  public final void drawString(String str, int x, int y, Color c) {
    if (enabledLogging()) log("drawString", str + "," + x + "," + y + "," + logColor(c));
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawString(str, x, y);
  }

  public final void drawString(String str, int x, int y, Font f) {
    if (enabledLogging()) log("drawString", str + "," + x + "," + y);
    drawString(str, x, y, Color.BLACK, f);
  }

  public final void drawString(String str, int x, int y, Color c, Font f) {
    if (enabledLogging()) log("drawString", str + "," + x + "," + y + "," + logColor(c));
    Graphics g = getGraphics();
    g.setColor(c);
    g.setFont(f);
    g.drawString(str, x, y);
  }

  protected final void forceRepaint() {
    if (enabledLogging()) log("forceRepaint");
    panel.forceRepaint();
    panel.tryResizeBufferImage();
  }

  protected abstract Graphics getGraphics();

  public final JGamePanel getPanel() {
    return panel;
  }

  public final boolean enabledLogging() {
    return stream != null;
  }

  protected final void log(String name) {
    if (stream != null) {
      stream.println("['" + name + "'],");
    }
  }

  protected final void log(String name, String args) {
    if (stream != null) {
      stream.println("['" + name + "'," + args + "],");
    }
  }

  protected final String logColor(Color c) {
    return c.getAlpha() + "," + c.getRed() + "," + c.getGreen() + "," + c.getBlue();
  }

  public final void startLogging(PrintStream stream) {
    this.stream = stream;
    stream.println("setLogData({");
    stream.println("logs: [");
  }

  public final PrintStream finishLogging() {
    if (stream != null) {
      stream.println("],");
      stream.println("});");
    }
    PrintStream stream = this.stream;
    this.stream = null;
    return stream;
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      finishLogging();
    } finally {
      super.finalize();
    }
  }
}
