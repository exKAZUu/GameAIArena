package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Image;

public class JGamePanelWithDefaultImage extends JGamePanel {
  private static final long serialVersionUID = 5631225031394211975L;

  private Image bufferImage;
  private DefaultImageRenderer renderer;

  public JGamePanelWithDefaultImage() {
    this(false);
  }

  public JGamePanelWithDefaultImage(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  @Override
  public Renderer createRenderer() {
    if (renderer == null) {
      setDoubleBuffered(false);
      Dimension d = getPreferredSize();
      bufferImage = this.createEmptyImage(d.width, d.height);
      renderer = new DefaultImageRenderer(this, bufferImage);
    }
    return renderer;
  }

  @Override
  public Image updateRendererImage(int width, int height) {
    Image newImage = this.createEmptyImage(width, height);
    renderer.updateImage(newImage);
    bufferImage = newImage;
    return newImage;
  }

  @Override
  public Image createEmptyImage(int width, int height) {
    return super.createImage(width, height);
  }

  @Override
  public Image getBufferImage() {
    return bufferImage;
  }
}
