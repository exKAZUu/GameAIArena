package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.util.Collections;
import java.util.List;

import jp.ac.waseda.cs.washi.gameaiarena.common.GameTime;
import jp.ac.waseda.cs.washi.gameaiarena.common.GameTimer;
import jp.ac.waseda.cs.washi.gameaiarena.common.TypeSafeCloneable;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;

import com.google.common.collect.Lists;

public abstract class Environment extends TypeSafeCloneable<Environment> {
  private final SceneManager _sceneManager;
  private final Renderer _renderer;
  private final List<Renderer> _renderers;
  private final MappedInputer _inputer;
  protected GameTime _time;
  protected GameTimer _timer;

  public Environment(GamePanel panel) {
    this(panel, null, 0, new SceneManager(), new MappedInputer(), new GameTime());
  }

  public Environment(GamePanel panel, List<GamePanel> subPanels) {
    this(panel, subPanels, 0, new SceneManager(), new MappedInputer(), new GameTime());
  }

  public Environment(GamePanel panel, int roundTime) {
    this(panel, null, roundTime, new SceneManager(), new MappedInputer(), new GameTime());
  }

  public Environment(GamePanel panel, List<GamePanel> subPanels, int roundTime) {
    this(panel, subPanels, roundTime, new SceneManager(), new MappedInputer(), new GameTime());
  }

  public Environment(GamePanel panel, List<GamePanel> subPanels, int roundTime,
      SceneManager sceneManager, MappedInputer inputer, GameTime time) {
    _sceneManager = sceneManager;
    _renderer = panel != null ? panel.getRenderer() : null;
    _renderers = Lists.newArrayList();
    if (subPanels != null) {
      for (GamePanel subPanel : subPanels) {
        _renderers.add(subPanel.getRenderer());
      }
    }
    _inputer = inputer;
    _time = time;
    _timer = new GameTimer(_time, roundTime);
  }

  @SuppressWarnings("unchecked")
  public <TEnv extends Environment> void start(Scene<TEnv> startScene) {
    _sceneManager.start((TEnv) this, startScene);
  }

  @Override
  public Environment clone() {
    Environment env = super.clone();
    env._timer = _timer.clone();
    env._time = env._timer.getGameTime();
    return env;
  };

  public MappedInputer getInputer() {
    return _inputer;
  }

  public Renderer getRenderer() {
    return _renderer;
  }

  public List<Renderer> getSubRenderers() {
    return Collections.unmodifiableList(_renderers);
  }

  public SceneManager getSceneManager() {
    return _sceneManager;
  }

  public GameTime getTime() {
    return _time;
  }

  public GameTimer getTimer() {
    return _timer;
  }

  public void addSubRenderer(Renderer renderer) {
    _renderers.add(renderer);
  }

  public void removeSubRenderer(Renderer renderer) {
    _renderers.remove(renderer);
  }
}
