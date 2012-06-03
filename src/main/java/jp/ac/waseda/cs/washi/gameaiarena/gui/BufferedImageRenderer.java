package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class BufferedImageRenderer extends OriginalBufferedRenderer<BufferedImage> {
  Graphics graphics;
  private WritableRaster raster;

  BufferedImageRenderer(JGamePanel panel, BufferedImage image) {
    super(panel, image);
  }

  @Override
  public void drawPixel(int x, int y, Color c) {
    raster.setPixel(x, y, c.getComponents(null));
  }

  @Override
  protected void updateImage(BufferedImage image) {
    super.updateImage(image);
    this.raster = image.getRaster();
  }
}
