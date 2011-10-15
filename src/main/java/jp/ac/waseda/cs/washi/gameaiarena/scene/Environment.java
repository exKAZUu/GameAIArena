package jp.ac.waseda.cs.washi.gameaiarena.scene;

import jp.ac.waseda.cs.washi.gameaiarena.gui.Renderer;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;

public interface Environment<T extends Environment<T>> {
	MappedInputer getInputer();

	Renderer getRenderer();

	SceneManager<T> getSceneManager();
}
