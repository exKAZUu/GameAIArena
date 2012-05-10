package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public interface Renderer {

  public abstract void clear(Color c);

  public abstract Image createImage(int width, int height);

  public abstract Image createImage(int width, int height, Color c);

  public abstract void drawImage(Image img, int x, int y);

  public abstract void drawPoint(int x, int y, Color c);

  public abstract void drawRectangle(int x, int y, int width, int height, Color c);

  public abstract void fillRectangle(int x, int y, int width, int height, Color c);

  public abstract void forceRepaint();

  public abstract Graphics getGraphics();

  public abstract ImageObserver getImageObserver();

  public abstract GamePanel getPanel();

  public abstract Image loadImage(String path);

  public abstract void waitLoadImage();

}
