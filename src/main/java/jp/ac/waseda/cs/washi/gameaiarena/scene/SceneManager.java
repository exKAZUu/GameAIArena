package jp.ac.waseda.cs.washi.gameaiarena.scene;

import jp.ac.waseda.cs.washi.gameaiarena.common.Environment;

public class SceneManager<Env extends Environment<Env>> {
	private double fps;
	private double mspf;
	private int maxSkip;
	private boolean ended;

	public SceneManager() {
		this(60);
	}

	public SceneManager(double fps) {
		this(fps, 0);
	}

	public SceneManager(double fps, int maxSkip) {
		this.setFps(fps);
	}

	public double getFps() {
		return this.fps;
	}

	public double getMaxSkip() {
		return this.maxSkip;
	}

	public double getMspf() {
		return this.mspf;
	}

	public void run(final Env env, final Scene<Env> firstScene) {
		Scene<Env> scene = firstScene;
		scene.initialize(env);
		long nextTime = System.currentTimeMillis();

		while (!ended) {
			int maxSkip = this.maxSkip; // 最大で連続に設定した回数(maxSkip回)runを実行
			long nowTime;
			do {
				// キー入力の更新
				if (env.getInputer() != null) {
					env.getInputer().update();
				}
				// シーンの処理
				final Scene<Env> nextScene = scene.run(env);
				if (nextScene != scene) {
					scene.release(env);
					if (nextScene == null) {
						return;
					}
					scene = nextScene;
					scene.initialize(env);
				}

				nextTime += this.mspf;
				nowTime = System.currentTimeMillis();
				if (--maxSkip <= 0) {
					break;
				}
			} while (nextTime <= nowTime);

			if (nextTime > nowTime) {
				try {
					Thread.sleep(nextTime - nowTime);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (env.getRenderer() != null) {
				scene.draw(env);
				env.getRenderer().forceRepaint();
			}
		}
		scene.release(env);
	}

	public void setFps(double fps) {
		this.fps = fps;
		this.mspf = 1000 / fps;
	}

	public void setMaxSkip(int maxSkip) {
		this.maxSkip = maxSkip;
	}

	public void terminate() {
		ended = true;
	}
}
