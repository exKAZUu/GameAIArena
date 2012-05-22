package jp.ac.waseda.cs.washi.gameaiarena.common;

import java.util.Collections;
import java.util.List;

import jp.ac.waseda.cs.washi.gameaiarena.gui.Renderer;
import jp.ac.waseda.cs.washi.gameaiarena.gui.SceneManager;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;

import com.google.common.collect.Lists;

public abstract class Environment extends TypeSafeCloneable<Environment> {
  private final SceneManager _sceneManager;
  private final Renderer _renderer;
  private final List<Renderer> _renderers;
  private final MappedInputer _inputer;
  protected GameTime _time;
  protected GameTimer _timer;

  public Environment(SceneManager sceneManager, Renderer renderer, MappedInputer inputer) {
    this(sceneManager, renderer, inputer, new GameTime());
  }

  public Environment(SceneManager sceneManager, Renderer renderer, MappedInputer inputer,
      GameTime time) {
    _sceneManager = sceneManager;
    _renderer = renderer;
    _renderers = Lists.newArrayList();
    _inputer = inputer;
    _time = time;
    _timer = new GameTimer(_time, 0);
  }

  public void initializeTimer(int roundTime) {
    _timer = new GameTimer(_time, roundTime);
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

  public void addSubRenderer(Renderer renderer) {
    _renderers.add(renderer);
  }

  public void removeSubRenderer(Renderer renderer) {
    _renderers.remove(renderer);
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
}
