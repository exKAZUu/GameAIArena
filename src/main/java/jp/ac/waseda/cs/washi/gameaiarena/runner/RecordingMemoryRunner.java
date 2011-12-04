package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;
import java.util.List;

public class RecordingMemoryRunner<Arg, Result extends Serializable, Plyaer>
		extends AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private final List<Result> oos;

	public RecordingMemoryRunner(AbstractRunner<Arg, Result, Plyaer> player,
			List<Result> oos) {
		this.player = player;
		this.oos = oos;
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
		player.runProcessing();
	}

	@Override
	public Result runPostProcessing() {
		Result act = player.runPostProcessing();
		oos.add(act);
		return act;
	}
}
