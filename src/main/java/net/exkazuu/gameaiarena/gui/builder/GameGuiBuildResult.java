package net.exkazuu.gameaiarena.gui.builder;

import javax.swing.JFrame;

import net.exkazuu.gameaiarena.gui.Environment;
import net.exkazuu.gameaiarena.gui.JGamePanel;

public class GameGuiBuildResult<Env extends Environment> {
  private final Env _env;
  private final JFrame _window;
  private final JGamePanel _panel;

  public GameGuiBuildResult(Env env, JFrame window, JGamePanel panel) {
    _env = env;
    _window = window;
    _panel = panel;
  }

  public Env getEnvironment() {
    return _env;
  }

  public JFrame getWindow() {
    return _window;
  }

  public JGamePanel getPanel() {
    return _panel;
  }
}
