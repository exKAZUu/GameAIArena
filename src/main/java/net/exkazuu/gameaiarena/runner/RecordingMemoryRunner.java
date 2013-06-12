package net.exkazuu.gameaiarena.runner;

import java.io.Serializable;
import java.util.List;

public class RecordingMemoryRunner<Arg, Result extends Serializable, Controller>
		extends AbstractRunner<Arg, Result, Controller> {

	private final AbstractRunner<Arg, Result, Controller> controller;
	private final List<Result> oos;

	public RecordingMemoryRunner(AbstractRunner<Arg, Result, Controller> controller,
			List<Result> oos) {
		this.controller = controller;
		this.oos = oos;
	}

	@Override
	public Controller getComputerPlayer() {
		return controller.getComputerPlayer();
	}

	@Override
	public void runPreProcessing(Arg input) {
		controller.runPreProcessing(input);
	}

	@Override
	public void runProcessing() {
		controller.runProcessing();
	}

	@Override
	public Result runPostProcessing() {
		Result act = controller.runPostProcessing();
		oos.add(act);
		return act;
	}

	@Override
	public String toString() {
		return controller.toString();
	}
}
