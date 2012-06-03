package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class RawRenderer extends Renderer {

  RawRenderer(JGamePanel panel) {
    super(panel);
    panel.getGraphics().setColor(Color.WHITE);
  }

  @Override
  public Graphics getGraphics() {
    return panel.getGraphics();
  }

  @Override
  public void clear(Color c) {
    Dimension d = panel.getPreferredSize();
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillRect(0, 0, d.width, d.height);
  }
}
