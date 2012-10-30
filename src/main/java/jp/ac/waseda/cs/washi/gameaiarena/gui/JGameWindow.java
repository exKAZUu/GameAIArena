package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class JGameWindow extends JFrame {
  private static final long serialVersionUID = -4977886675727461795L;

  public JGameWindow() {
    super();
  }

  public JGameWindow(String title) {
    super(title);
  }

  public static Point getCenterLocation(int width, int height) {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle desktopRect = env.getMaximumWindowBounds();
    return new Point((desktopRect.width - width) / 2, (desktopRect.height - height) / 2);
  }

  public JGamePanel showAtCenter(int width, int height) {
    return show(GamePanels.newWithDefaultImage(), getCenterLocation(width, height), width, height);
  }

  public JGamePanel showAtCenter(JGamePanel panel, int width, int height) {
    return show(panel, getCenterLocation(width, height), width, height);
  }

  public JGamePanel show(Point location, int width, int height) {
    return show(GamePanels.newWithDefaultImage(), location, width, height);
  }

  public JGamePanel show(JGamePanel panel, Point location, int width, int height) {
    panel.setPreferredSize(new Dimension(width, height));
    getContentPane().add(panel, BorderLayout.CENTER);
    pack();
    setVisible(true);
    setLocation(location);
    panel.initializeAfterShowing();
    return panel;
  }

  public void addWindowListenerForTerminating(SceneManager sceneManager) {
    sceneManager.addWindowListenerForTerminating(this);
  }
}
