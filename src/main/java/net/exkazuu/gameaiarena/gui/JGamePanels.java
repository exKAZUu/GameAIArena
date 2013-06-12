package net.exkazuu.gameaiarena.gui;


public class JGamePanels {
  private JGamePanels() {

  }

  public static JGamePanel newWithBufferedImage() {
    return new JGamePanelWithBufferedImage();
  }

  public static JGamePanel newWithBufferedImage(int imageType, boolean isDoubleBuffered) {
    return new JGamePanelWithBufferedImage(imageType, isDoubleBuffered);
  }

  public static JGamePanel newWithBufferedImage(int imageType) {
    return new JGamePanelWithBufferedImage(imageType);
  }

  public static JGamePanel newWithDefaultImage() {
    return new JGamePanelWithDefaultImage();
  }

  public static JGamePanel newWithDefaultImage(boolean isDoubleBuffered) {
    return new JGamePanelWithDefaultImage(isDoubleBuffered);
  }

  public static JGamePanel newWithSwingDoubleBuffer() {
    return new JGamePanelWithSwingDoubleBuffer();
  }
}
