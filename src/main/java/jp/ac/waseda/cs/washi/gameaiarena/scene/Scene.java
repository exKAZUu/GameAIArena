package jp.ac.waseda.cs.washi.gameaiarena.scene;

import jp.ac.waseda.cs.washi.gameaiarena.common.Environment;

public abstract class Scene<Env extends Environment<Env>> {
	public void initialize(Env env) {
	}

	public void release(Env env) {
	}

	public Scene<Env> run(Env env) {
		return this;
	}

	public void draw(Env env) {
	}
}