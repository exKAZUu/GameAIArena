package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
import java.awt.image.ImageObserver;

import jp.ac.waseda.cs.washi.gameaiarena.key.AwtKeyMemorizer;
import jp.ac.waseda.cs.washi.gameaiarena.scene.SceneManager;

public interface GamePanel {
  Image createImage(int width, int height);

  void forceRepaint();

  AwtKeyMemorizer getKeyMemorizer();

  ImageObserver getObserver();

  Image loadImage(String path);

  void setSize(Dimension dimension);

  void setSize(int width, int height);

  Renderer createStandardDoubleBufferedRenderer();

  Renderer createOriginalDoubleBufferedRenderer();

  Renderer createDuplicateDoubleBufferedRenderer();

  void initialize();

  void addWindowListenerOfTerminateSceneManager(Window window, SceneManager sceneManager);
}
