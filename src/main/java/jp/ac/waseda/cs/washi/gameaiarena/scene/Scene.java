package jp.ac.waseda.cs.washi.gameaiarena.scene;

public abstract class Scene<Env extends Environment<Env>> {
	public void initializeWithoutRenderer(Env env) {
	}

	public void initializeWithRenderer(Env env) {
	}

	public void releaseWithoutRenderer(Env env) {
	}

	public void releaseWithRenderer(Env env) {
	}

	public Scene<Env> run(Env env) {
		return this;
	}

	public void draw(Env env) {
	}
}