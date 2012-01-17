package jp.ac.waseda.cs.washi.gameaiarena.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;

/**
 * 
 * JavaLayerを使用したmp3専用プレイヤーです。再生をスレッドで行えるように改造してあります。
 * 
 */
public class AudioPlayer {
	private class RunnablePlayer extends javazoom.jl.player.Player implements
			Runnable {
		private boolean stopping;
		private boolean finished;

		public RunnablePlayer(final InputStream stream)
				throws JavaLayerException {
			super(stream);
		}

		@Override
		public void close() {
			super.close();
			finished = true;
		}

		public void stop() {
			stopping = true;
		}

		public synchronized void restart() {
			stopping = false;
			notify();
		}

		// javazoom.jl.player.Player#play() と同様の処理です。
		// スレッドの停止、再開処理が可能です。
		@Override
		public void run() {
			while (!super.isComplete() && !finished) {
				synchronized (this) {
					if (stopping) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				try {
					decodeFrame();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private RunnablePlayer player;
	private Thread playingThread;

	public AudioPlayer(String resourcePath) throws JavaLayerException,
			IOException {
		this(AudioPlayer.class.getClassLoader().getResource(resourcePath)
				.openStream());
	}

	public AudioPlayer(File file) throws FileNotFoundException,
			JavaLayerException {
		this(new FileInputStream(file));
	}

	public AudioPlayer(InputStream stream) throws JavaLayerException {
		player = new RunnablePlayer(new BufferedInputStream(stream));
		playingThread = new Thread(player, "MP3-Player");
		playingThread.setPriority(8);
	}

	public void play() {
		if (player == null)
			return;
		playingThread.start();
	}

	public void replay() {
		if (player == null)
			return;
		player.restart();
	}

	public void stop() {
		if (player == null)
			return;
		player.stop();
	}

	public void close() {
		if (player == null)
			return;
		player.close();
	}

	public int getThreadPriority() {
		return playingThread.getPriority();
	}

	public void setThreadPriority(int priority) {
		playingThread.setPriority(priority);
	}
}
