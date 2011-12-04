package jp.ac.waseda.cs.washi.gameaiarena.common;

import jp.ac.waseda.cs.washi.gameaiarena.gui.Renderer;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;
import jp.ac.waseda.cs.washi.gameaiarena.scene.SceneManager;

public abstract class Environment<T extends Environment<T>> extends
		TypeSafeCloneable<T> {
	private final SceneManager<T> _sceneManager;
	private final Renderer _renderer;
	private final MappedInputer _inputer;
	protected GameTime _time;
	protected GameTimer _timer;

	public Environment(SceneManager<T> sceneManager, Renderer renderer,
			MappedInputer inputer) {
		_sceneManager = sceneManager;
		_renderer = renderer;
		_inputer = inputer;
		_time = new GameTime();
		_timer = new GameTimer(_time, 0);
	}

	public void initializeTimer(int roundTime) {
		_timer = new GameTimer(_time, roundTime);
	}

	@Override
	public T clone() {
		T env = super.clone();
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

	public SceneManager<T> getSceneManager() {
		return _sceneManager;
	}

	public GameTime getTime() {
		return _time;
	}

	public GameTimer getTimer() {
		return _timer;
	}
}
