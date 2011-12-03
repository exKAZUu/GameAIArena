package jp.ac.waseda.cs.washi.gameaiarena.scene;

import jp.ac.waseda.cs.washi.gameaiarena.common.TypeSafeCloneable;
import jp.ac.waseda.cs.washi.gameaiarena.gui.Renderer;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;

public abstract class Environment<T extends Environment<T>> extends
		TypeSafeCloneable<Environment<T>> {
	private final SceneManager<T> _sceneManager;
	private final Renderer _renderer;
	private final MappedInputer _inputer;

	public Environment(SceneManager<T> sceneManager, Renderer renderer,
			MappedInputer inputer) {
		_sceneManager = sceneManager;
		_renderer = renderer;
		_inputer = inputer;
	}

	public MappedInputer getInputer() {
		return _inputer;
	}

	public Renderer getRenderer() {
		return _renderer;
	}

	public SceneManager<T> getSceneManager() {
		return _sceneManager;
	}
}
