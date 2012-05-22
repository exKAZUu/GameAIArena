package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;

public class RawRenderer extends Renderer {
  private final JGamePanel panel;
  private final ImageObserver imageObserver;
  private final MediaTracker mediaTracker;
  private int imageCount;

  public RawRenderer(JGamePanel panel) {
    this.panel = panel;
    panel.getGraphics().setColor(Color.WHITE);
    imageObserver = panel.getObserver();
    mediaTracker = new MediaTracker(panel);
  }

  @Override
  public void clear(Color c) {
    Dimension d = panel.getPreferredSize();
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, d.width, d.height);
  }

  @Override
  public Image createImage(int width, int height) {
    return panel.createImage(width, height);
  }

  @Override
  public Image createImage(int width, int height, Color c) {
    final Image image = panel.createImage(width, height);
    final Graphics g = image.getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, width, height);
    g.dispose();
    return image;
  }

  @Override
  public void drawImage(Image img, int x, int y) {
    Graphics g = getGraphics();
    g.drawImage(img, x, y, imageObserver);
  }

  @Override
  public void drawPoint(int x, int y, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, 1, 1);
  }

  @Override
  public void drawRectangle(int x, int y, int width, int height, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.drawRect(x, y, width, height);
  }

  @Override
  public void fillRectangle(int x, int y, int width, int height, Color c) {
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
  }

  @Override
  protected void forceRepaint() {
    panel.forceRepaint();
  }

  @Override
  public Graphics getGraphics() {
    return panel.getGraphics();
  }

  @Override
  public ImageObserver getImageObserver() {
    return imageObserver;
  }

  @Override
  public GamePanel getPanel() {
    return panel;
  }

  @Override
  public Image loadImage(String path) {
    final Image image = panel.loadImage(path);
    mediaTracker.addImage(image, imageCount++);
    return image;
  }

  @Override
  public void waitLoadImage() {
    try {
      mediaTracker.waitForAll();
    } catch (final InterruptedException e) {}
  }
}
