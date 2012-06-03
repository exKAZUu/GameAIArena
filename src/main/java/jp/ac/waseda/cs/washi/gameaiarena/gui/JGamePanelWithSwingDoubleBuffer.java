package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Graphics;
import java.awt.Image;

public class JGamePanelWithSwingDoubleBuffer extends JGamePanel {
  private static final long serialVersionUID = -1326447066743126992L;

  public JGamePanelWithSwingDoubleBuffer() {
    this(false);
  }

  public JGamePanelWithSwingDoubleBuffer(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  @Override
  public Renderer createRenderer() {
    setDoubleBuffered(true);
    return new RawRenderer(this);
  }

  @Override
  public Image createEmptyImage(int width, int height) {
    return super.createImage(width, height);
  }

  @Override
  public void updateRendererImage() {}

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
