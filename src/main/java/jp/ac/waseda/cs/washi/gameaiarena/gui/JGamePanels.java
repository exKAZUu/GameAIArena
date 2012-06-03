package jp.ac.waseda.cs.washi.gameaiarena.gui;


public class JGamePanels {
  private JGamePanels() {

  }

  public JGamePanel createWithBufferedImage() {
    return new JGamePanelWithBufferedImage();
  }

  public JGamePanel createWithBufferedImage(int imageType, boolean isDoubleBuffered) {
    return new JGamePanelWithBufferedImage(imageType, isDoubleBuffered);
  }

  public JGamePanel createWithBufferedImage(int imageType) {
    return new JGamePanelWithBufferedImage(imageType);
  }

  public JGamePanel createWithDefaultImage() {
    return new JGamePanelWithDefaultImage();
  }

  public JGamePanel createWithDefaultImage(boolean isDoubleBuffered) {
    return new JGamePanelWithDefaultImage(isDoubleBuffered);
  }

  public JGamePanel createWithSwingDoubleBuffer() {
    return new JGamePanelWithSwingDoubleBuffer();
  }
}
