package net.exkazuu.gameaiarena.gui.builder;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.exkazuu.gameaiarena.gui.Environment;
import net.exkazuu.gameaiarena.gui.GamePanels;
import net.exkazuu.gameaiarena.gui.JGamePanel;

public class GameGuiBuilder {
  private String _title;
  private Dimension _windowSize;
  private Dimension _panelSize;
  private Integer _imageType;
  private Integer _fps;
  private WindowCreator _windowCreator;
  private int _defaultCloseOperation;

  public GameGuiBuilder() {
    _windowCreator = new WindowCreator();
    _defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE;
  }

  public <Env extends Environment> GameGuiBuildResult<Env> buildForCui(Class<Env> clazz) {
    return buildForCui(EnvironmentCreator.defaultCreator(clazz));
  }

  public <Env extends Environment> GameGuiBuildResult<Env> buildForCui(EnvironmentCreator<Env> envCreator) {
    Env env = envCreator.createEnvironment(null, null);
    if (_fps != null) {
      env.getSceneManager().setFps(_fps);
    }
    return new GameGuiBuildResult<Env>(env, null, null);
  }

  public <Env extends Environment> GameGuiBuildResult<Env> buildForGui(Class<Env> clazz) {
    return buildForGui(EnvironmentCreator.defaultCreator(clazz));
  }

  public <Env extends Environment> GameGuiBuildResult<Env> buildForGui(EnvironmentCreator<Env> envCreator) {
    JGamePanel gamePanel =
        _imageType == null ? GamePanels.newWithDefaultImage() : GamePanels
            .newWithBufferedImage(_imageType);
    JFrame window = _windowCreator.createWindow(gamePanel);

    window.setTitle(_title);
    window.setSize(_windowSize);
    gamePanel.setPreferredSize(_panelSize);
    window.setDefaultCloseOperation(_defaultCloseOperation);

    window.setVisible(true);
    gamePanel.initializeAfterShowing();
    Env env = envCreator.createEnvironment(window, gamePanel);
    if (_fps != null) {
      env.getSceneManager().setFps(_fps);
    }
    env.getSceneManager().addWindowListenerForTerminating(window);
    return new GameGuiBuildResult<Env>(env, window, gamePanel);
  }

  public GameGuiBuilder setFps(int fps) {
    _fps = fps;
    return this;
  }

  public GameGuiBuilder setPanelSize(int width, int height) {
    _panelSize = new Dimension(width, height);
    return this;
  }

  public GameGuiBuilder setPanelSize(Dimension size) {
    _panelSize = size;
    return this;
  }

  public GameGuiBuilder setImageType(int imageType) {
    _imageType = imageType;
    return this;
  }

  public GameGuiBuilder setWindowSize(int width, int height) {
    _windowSize = new Dimension(width, height);
    return this;
  }

  public GameGuiBuilder setWindowSize(Dimension size) {
    _windowSize = size;
    return this;
  }

  public GameGuiBuilder setTitle(String windowTitle) {
    _title = windowTitle;
    return this;
  }

  public GameGuiBuilder setWindowCreator(WindowCreator windowCreator) {
    _windowCreator = windowCreator;
    return this;
  }

  public GameGuiBuilder setDefaultCloseOperation(int defaultCloseOperation) {
    _defaultCloseOperation = defaultCloseOperation;
    return this;
  }
}