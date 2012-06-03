package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.BorderLayout;
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

  private Point getCenterPoint(int width, int height) {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle desktopRect = env.getMaximumWindowBounds();
    return new Point((desktopRect.width - width) / 2, (desktopRect.height - height) / 2);
  }

  public void locateCenter(JGamePanel panel, int width, int height) {
    show(panel, getCenterPoint(width, height), width, height);
  }

  public void show(JGamePanel panel, Point location, int width, int height) {
    panel.setSize(width, height);
    getContentPane().add(panel, BorderLayout.CENTER);
    pack();
    setVisible(true);
    setLocation(location);
    panel.initialize();
  }

  public void addWindowListenerForTerminating(SceneManager sceneManager) {
    sceneManager.addWindowListenerForTerminating(this);
  }
}
