package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RecordingStreamRunner<Arg, Result extends Serializable, Controller>
		extends AbstractRunner<Arg, Result, Controller> {

	private final AbstractRunner<Arg, Result, Controller> player;
	private final ObjectOutputStream oos;

	public RecordingStreamRunner(AbstractRunner<Arg, Result, Controller> controller,
			ObjectOutputStream oos) {
		this.player = controller;
		this.oos = oos;
	}

	@Override
	public Controller getController() {
		return player.getController();
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

	@Override
	public String toString() {
		return player.toString();
	}
}
