package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RecordingStream<Arg, Result extends Serializable, Plyaer> extends
		AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private final ObjectOutputStream oos;

	public RecordingStream(AbstractRunner<Arg, Result, Plyaer> player,
			ObjectOutputStream oos) {
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
		try {
			oos.writeObject(act);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return act;
	}
}
