package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Image;

public class DefaultImageRenderer extends OriginalBufferedRenderer<Image> {
  DefaultImageRenderer(JGamePanel panel, Image image) {
    super(panel, image);
  }
}
