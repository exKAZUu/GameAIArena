package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

import jp.ac.waseda.cs.washi.gameaiarena.scene.SceneManager;

public class JGameWindow extends JFrame {
  private static final long serialVersionUID = -4977886675727461795L;

  private JGamePanel panel;

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

  public JGamePanel getPanel() {
    return panel;
  }

  public Renderer createDefaultDoubleBufferedRenderer() {
    panel.setDoubleBuffered(true);
    return panel.createDefaultDoubleBufferedRenderer();
  }

  public Renderer createSwingDoubleBufferedRenderer() {
    return panel.createSwingDoubleBufferedRenderer();
  }

  public Renderer createDuplicateDoubleBufferedRenderer() {
    return panel.createDuplicateDoubleBufferedRenderer();
  }

  public void showAtCenter(int width, int height) {
    show(getCenterPoint(width, height), width, height);
  }

  public void show(Point location, int width, int height) {
    panel = new JGamePanel();
    panel.setSize(width, height);
    getContentPane().add(panel, BorderLayout.CENTER);
    pack();
    setVisible(true);
    setLocation(location);
    panel.initialize();
  }

  public void addWindowListenerOfTerminateSceneManager(final SceneManager sceneManager) {
    panel.addWindowListenerOfTerminateSceneManager(this, sceneManager);
  }
}
