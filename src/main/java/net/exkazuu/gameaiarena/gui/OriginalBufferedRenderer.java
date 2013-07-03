package net.exkazuu.gameaiarena.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public abstract class OriginalBufferedRenderer<TImage extends Image> extends Renderer {

  protected TImage image;
  protected Graphics graphics;

  public OriginalBufferedRenderer(JGamePanel panel, TImage image) {
    super(panel);
    updateImage(image);
  }

  @Override
  public Graphics getGraphics() {
    return graphics;
  }

  protected void updateImage(TImage image) {
    this.image = image;
    this.graphics = image.getGraphics();
    this.graphics.setColor(Color.WHITE);
  }

  @Override
  public final void clear(Color c) {
    if (enabledLogging()) log("clear", logColor(c));
    graphics.setColor(c);
    graphics.fillRect(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));
  }
}
