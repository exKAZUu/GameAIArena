package net.exkazuu.gameaiarena.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;


public class JGameWindow extends JFrame {
  private static final long serialVersionUID = -4977886675727461795L;

  public JGameWindow() {
    super();
  }

  public JGameWindow(String title) {
    super(title);
  }

  public JGamePanel showAtCenter(int width, int height) {
    return show(GamePanels.newWithDefaultImage(), null, width, height);
  }

  public JGamePanel showAtCenter(JGamePanel panel, int width, int height) {
    return show(panel, null, width, height);
  }

  public JGamePanel show(Point location, int width, int height) {
    return show(GamePanels.newWithDefaultImage(), location, width, height);
  }

  public JGamePanel show(JGamePanel panel, Point location, int width, int height) {
    panel.setPreferredSize(new Dimension(width, height));
    getContentPane().add(panel, BorderLayout.CENTER);
    pack();
    setVisible(true);
    if (location == null) {
      setLocationRelativeTo(null);
    } else {
      setLocation(location);
    }
    panel.initializeAfterShowing();
    return panel;
  }

  public void addWindowListenerForTerminating(SceneManager sceneManager) {
    sceneManager.setWindowForTerminating(this);
  }
}
