package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;

public abstract class AbstractRunner<Arg, Result extends Serializable, Player> {

	public abstract Player getPlyaer();

	public final Result run(Arg input) {
		runPreProcessing(input);
		runProcessing();
		return runPostProcessing();
	}

	protected abstract void runPreProcessing(Arg input);

	protected abstract void runProcessing();

	protected abstract Result runPostProcessing();
}