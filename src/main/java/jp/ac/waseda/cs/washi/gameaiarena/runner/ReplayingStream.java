package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReplayingStream<Arg, Result extends Serializable, Plyaer> extends
		AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private final ObjectInputStream ois;

	public ReplayingStream(AbstractRunner<Arg, Result, Plyaer> player,
			ObjectInputStream ois) {
		this.player = player;
		this.ois = ois;
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

	@SuppressWarnings("unchecked")
	@Override
	public Result runPostProcessing() {
		try {
			return (Result) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
