package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public class LimittingTimeRunner<Arg, Result extends Serializable, Plyaer>
		extends AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private final int maxMillisecond;
	private Result result;

	public LimittingTimeRunner(AbstractRunner<Arg, Result, Plyaer> player,
			int maxMillisecond) {
		this.player = player;
		this.maxMillisecond = maxMillisecond;
	}

	@Override
	public Plyaer getPlyaer() {
		return player.getPlyaer();
	}

	@Override
	public void runPreProcessing(Arg input) {
		player.runPreProcessing(input);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void runProcessing() {
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				player.runProcessing();
			}
		});
		thread.start();
		try {
			thread.join(maxMillisecond);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		// 時間制限を超えた時点の結果を保存する
		result = player.runPostProcessing();
		if (thread.isAlive()) {
			System.out.println("terminated the thread because time was exceeded");
			thread.stop();
		}
	}

	@Override
	public Result runPostProcessing() {
		return result;
	}
}
