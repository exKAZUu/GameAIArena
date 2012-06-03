package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class JGamePanelWithBufferedImage extends JGamePanel {
  private static final long serialVersionUID = 6798239655884559261L;

  private final int imageType;
  protected BufferedImage bufferImage;
  private BufferedImageRenderer renderer;

  public JGamePanelWithBufferedImage() {
    this(BufferedImage.TYPE_INT_RGB, false);
  }

  public JGamePanelWithBufferedImage(int imageType) {
    this(imageType, false);
  }

  public JGamePanelWithBufferedImage(int imageType, boolean isDoubleBuffered) {
    super(isDoubleBuffered);
    this.imageType = imageType;
  }

  @Override
  public BufferedImage createEmptyImage(int width, int height) {
    return new BufferedImage(width, height, imageType);
  }

  @Override
  public Renderer createRenderer() {
    if (renderer == null) {
      setDoubleBuffered(false);
      Dimension d = getPreferredSize();
      bufferImage = this.createEmptyImage(d.width, d.height);
      renderer = new BufferedImageRenderer(this, bufferImage);
    }
    return renderer;
  }

  @Override
  public Image updateRendererImage(int width, int height) {
    BufferedImage newImage = this.createEmptyImage(width, height);
    renderer.updateImage(newImage);
    bufferImage = newImage;
    return newImage;
  }

  @Override
  public Image getBufferImage() {
    return bufferImage;
  }
}
