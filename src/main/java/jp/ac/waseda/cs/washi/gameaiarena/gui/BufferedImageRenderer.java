package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;

public class BufferedImageRenderer extends OriginalBufferedRenderer<BufferedImage> {
  Graphics graphics;
  private WritableRaster raster;
  private HashMap<Color, int[]> colors;

  BufferedImageRenderer(JGamePanel panel, BufferedImage image) {
    super(panel, image);
    colors = new HashMap<Color, int[]>();
  }

  @Override
  public void drawPixel(int x, int y, Color c) {
    int[] argb = colors.get(c);
    if (argb == null) {
      argb = new int[] {c.getRed(), c.getGreen(), c.getBlue()};
      colors.put(c, argb);
    }
    raster.setPixel(x, y, argb);
  }

  @Override
  protected void updateImage(BufferedImage image) {
    super.updateImage(image);
    this.raster = image.getRaster();
  }
}
