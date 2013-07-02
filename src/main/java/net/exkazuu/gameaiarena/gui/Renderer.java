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

  public void clear(Color c) {
    if (stream != null) {
      stream.println("[clear," + logColor(c) + "]");
    }
  }

  public Image createImage(int width, int height) {
    if (stream != null) {
      stream.println("[createImage," + width + "," + height + "," + imageCount + "]");
    }
    Image image = panel.createEmptyImage(width, height);
    image2Ids.put(image, imageCount++);
    return image;
  }

  public Image createImage(int width, int height, Color c) {
    if (stream != null) {
      stream.println("[createImage," + width + "," + height + "," + logColor(c) + "," + imageCount
          + "]");
    }
    Image image = panel.createEmptyImage(width, height);
    Graphics g = image.getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, width, height);
    g.dispose();
    image2Ids.put(image, imageCount++);
    return image;
  }

  public void drawImage(Image img, int x, int y) {
    if (stream != null) {
      stream.println("[drawImage," + image2Ids.get(img) + "," + x + "," + y + "]");
    }
    Graphics g = getGraphics();
    g.drawImage(img, x, y, imageObserver);
  }

  public void drawPixel(int x, int y, Color c) {
    if (stream != null) {
      stream.println("[drawPixel," + x + "," + y + "," + logColor(c) + "]");
    }
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawLine(x, y, x, y);
  }

  public void drawRect(int x, int y, int width, int height, Color c) {
    if (stream != null) {
      stream.println("[drawRect," + x + "," + y + "," + width + "," + height + "," + logColor(c)
          + "]");
    }
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawRect(x, y, width, height);
  }

  public void fillRect(int x, int y, int width, int height, Color c) {
    if (stream != null) {
      stream.println("[fillRect," + x + "," + y + "," + width + "," + height + "," + logColor(c)
          + "]");
    }
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
  }

  public ImageObserver getImageObserver() {
    return imageObserver;
  }

  public Image loadImage(String path) {
    if (stream != null) {
      stream.println("[loadImage," + path + "," + imageCount + "]");
    }
    final Image image = panel.loadImage(path);
    mediaTracker.addImage(image, imageCount);
    image2Ids.put(image, imageCount++);
    return image;
  }

  public void waitLoadImage() {
    try {
      mediaTracker.waitForAll();
    } catch (final InterruptedException e) {}
  }

  public void drawString(String str, int x, int y) {
    if (stream != null) {
      stream.println("[drawString," + str + "," + x + "," + y + "]");
    }
    drawString(str, x, y, Color.BLACK);
  }

  public void drawString(String str, int x, int y, Color c) {
    if (stream != null) {
      stream.println("[drawString," + str + "," + x + "," + y + "," + logColor(c) + "]");
    }
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawString(str, x, y);
  }

  public void drawString(String str, int x, int y, Font f) {
    if (stream != null) {
      stream.println("[drawString," + str + "," + x + "," + y + "]");
    }
    drawString(str, x, y, Color.BLACK, f);
  }

  public void drawString(String str, int x, int y, Color c, Font f) {
    if (stream != null) {
      stream.println("[drawString," + str + "," + x + "," + y + "," + logColor(c) + "]");
    }
    Graphics g = getGraphics();
    g.setColor(c);
    g.setFont(f);
    g.drawString(str, x, y);
  }

  protected void forceRepaint() {
    if (stream != null) {
      stream.println("[forceRepaint]");
    }
    panel.forceRepaint();
    panel.tryResizeBufferImage();
  }

  protected abstract Graphics getGraphics();

  public JGamePanel getPanel() {
    return panel;
  }

  public void startLogging(PrintStream stream) {
    this.stream = stream;
    stream.println("var log = [");
  }

  public PrintStream finishLogging() {
    if (stream != null) {
      stream.println("];");
    }
    PrintStream stream = this.stream;
    this.stream = null;
    return stream;
  }

  private String logColor(Color c) {
    return c.getAlpha() + "," + c.getRed() + "," + c.getGreen() + "," + c.getBlue();
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
