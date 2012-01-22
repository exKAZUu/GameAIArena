package jp.ac.waseda.cs.washi.gameaiarena.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import jp.ac.waseda.cs.washi.gameaiarena.io.InputStreams;

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
		private final BufferedInputStream stream;

		/**
		 * Constructs an instance with the specified stream.
		 * 
		 * @param stream
		 *            the stream to construct the instance
		 * @throws JavaLayerException
		 *             if fails to an instance of initialize
		 *             javazoom.jl.player.Player
		 */
		public RunnablePlayer(BufferedInputStream stream)
				throws JavaLayerException {
			super(stream);
			this.stream = stream;
		}

		@Override
		public void close() {
			super.close();
			finished = true;
		}

		/**
		 * Stops the audio player.
		 */
		public void stop() {
			stopping = true;
		}

		/**
		 * Resumes the stopped audio player. If the audio player are playing,
		 * does nothing.
		 */
		public synchronized void resume() {
			stopping = false;
			notify();
		}

		@Override
		public void run() {
			while (!finished) {
				checkStopping();
				try {
					finished |= !decodeFrame();
				} catch (JavaLayerException e) {
				}
			}
			if (loop) {
				restart();
			}
		}

		synchronized private void checkStopping() {
			if (stopping) {
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
		}

		/**
		 * Resets and restarts the audio player.
		 * 
		 * @throws JavaLayerException
		 *             if it fails to re-construct the audio player
		 */
		private void restart() {
			// Stop this thread
			finished = true;
			resume();

			int priority = playingThread.getPriority();
			try {
				try {
					stream.reset();
				} catch (IOException e) {
				}
				player = new RunnablePlayer(stream);
				playingThread = new Thread(player, "MP3-Player");
				playingThread.setPriority(priority);
				playingThread.start();
			} catch (JavaLayerException e) {
			}
		}
	}

	/**
	 * An audio player for thread.
	 */
	private RunnablePlayer player;

	/**
	 * A thread to execute the audio player.
	 */
	private Thread playingThread;

	/**
	 * A boolean whether the audio player restart when playing is completed.
	 */
	private boolean loop;

	public AudioPlayer(String resourcePath) throws JavaLayerException,
			IOException {
		this(InputStreams.openResourceOrFile(resourcePath));
	}

	public AudioPlayer(File file) throws FileNotFoundException,
			JavaLayerException {
		this(new FileInputStream(file));
	}

	public AudioPlayer(InputStream stream) throws JavaLayerException {
		this(stream, 8 * 1024 * 1024);
	}

	public AudioPlayer(String resourcePath, int bufferSize)
			throws JavaLayerException, IOException {
		this(InputStreams.openResourceOrFile(resourcePath), bufferSize);
	}

	public AudioPlayer(File file, int bufferSize) throws FileNotFoundException,
			JavaLayerException {
		this(new FileInputStream(file), bufferSize);
	}

	public AudioPlayer(InputStream stream, int bufferSize)
			throws JavaLayerException {
		BufferedInputStream bufferedStream = new BufferedInputStream(stream);
		bufferedStream.mark(bufferSize);
		player = new RunnablePlayer(bufferedStream);
		playingThread = new Thread(player, "MP3-Player");
		playingThread.setPriority(8);
	}

	public void play() {
		if (player == null)
			return;
		playingThread.start();
		player.resume();
	}

	public void restart() {
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

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean enable) {
		loop = enable;
	}
}
