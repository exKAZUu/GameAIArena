package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;

import jp.ac.waseda.cs.washi.gameaiarena.key.AwtKeyMemorizer;

public interface GamePanel {
  Image getBufferImage();

  Image createEmptyImage(int width, int height);

  Image loadImage(String path);

  void forceRepaint();

  AwtKeyMemorizer getKeyMemorizer();

  ImageObserver getObserver();

  void setSize(Dimension dimension);

  void setSize(int width, int height);

  Renderer createRenderer();

  void initialize();
}
