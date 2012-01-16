package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public class IgnoringExceptionRunner<Arg, Result extends Serializable, Plyaer>
		extends AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;

	public IgnoringExceptionRunner(AbstractRunner<Arg, Result, Plyaer> player) {
		this.player = player;
	}

	@Override
	public Plyaer getPlyaer() {
		return player.getPlyaer();
	}

	@Override
	public void runPreProcessing(Arg input) {
		player.runPreProcessing(input);
	}

	@Override
	public void runProcessing() {
		try {
			player.runProcessing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Result runPostProcessing() {
		return player.runPostProcessing();
	}

	@Override
	public String toString() {
		return player.toString();
	}
}
