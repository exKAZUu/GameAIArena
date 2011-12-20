package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public class LimittingSumTimeRunner<Arg, Result extends Serializable, Plyaer>
		extends AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private int restExceededMillisecond;
	private final int availableMillisecond;
	private Result result;

	public LimittingSumTimeRunner(AbstractRunner<Arg, Result, Plyaer> player,
			int maxExceededMillisecond, int availableMillisecond) {
		this.player = player;
		this.restExceededMillisecond = maxExceededMillisecond;
		this.availableMillisecond = availableMillisecond;
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
		if (restExceededMillisecond < 0) {
			return;
		}
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				player.runProcessing();
			}
		});
		long currentTimeMillis = System.currentTimeMillis();
		thread.start();
		try {
			thread.join(availableMillisecond + restExceededMillisecond);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		restExceededMillisecond -= System.currentTimeMillis() - currentTimeMillis
				- availableMillisecond;
		// 時間制限を超えた時点の結果を保存する
		result = player.runPostProcessing();
		if (thread.isAlive()) {
			System.out
					.println("terminated the thread because time was exceeded");
			thread.stop();
		}
	}

	@Override
	public Result runPostProcessing() {
		return result;
	}
}
