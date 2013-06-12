package net.exkazuu.gameaiarena.gui;

import java.awt.Graphics;
import java.awt.Image;


public class JGamePanelWithSwingDoubleBuffer extends JGamePanel {
  private static final long serialVersionUID = -1326447066743126992L;
  private RawRenderer renderer;

  public JGamePanelWithSwingDoubleBuffer() {
    this(false);
  }

  public JGamePanelWithSwingDoubleBuffer(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  @Override
  public Renderer getRenderer() {
    return renderer;
  }

  @Override
  protected void initializeRenderer() {
    if (renderer == null) {
      setDoubleBuffered(true);
      renderer = new RawRenderer(this);
    }
  }

  @Override
  public Image createEmptyImage(int width, int height) {
    return super.createImage(width, height);
  }

  @Override
  public Image updateRendererImage(int width, int height) {
    return null;
  }

  @Override
  public void setSize(int width, int height) {
    super.setSize(width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  @Override
  public Image getBufferImage() {
    return null;
  }
}
