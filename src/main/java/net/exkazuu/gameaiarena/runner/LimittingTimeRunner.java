package net.exkazuu.gameaiarena.runner;

import java.io.Serializable;

public class LimittingTimeRunner<Arg, Result extends Serializable, Controller>
		extends AbstractRunner<Arg, Result, Controller> {

	private final AbstractRunner<Arg, Result, Controller> controller;
	private final int maxMillisecond;
	private Result result;

	public LimittingTimeRunner(AbstractRunner<Arg, Result, Controller> controller,
			int maxMillisecond) {
		this.controller = controller;
		this.maxMillisecond = maxMillisecond;
	}

	@Override
	public Controller getComputerPlayer() {
		return controller.getComputerPlayer();
	}

	@Override
	public void runPreProcessing(Arg input) {
		controller.runPreProcessing(input);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void runProcessing() {
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				controller.runProcessing();
			}
		});
		thread.start();
		try {
			thread.join(maxMillisecond);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		// 時間制限を超えた時点の結果を保存する
		result = controller.runPostProcessing();
		if (thread.isAlive()) {
			System.out.println("terminated the thread because time was exceeded");
			thread.stop();
		}
	}

	@Override
	public Result runPostProcessing() {
		return result;
	}

	@Override
	public String toString() {
		return controller.toString();
	}
}
