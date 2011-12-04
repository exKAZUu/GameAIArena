package jp.ac.waseda.cs.washi.gameaiarena.common;

public class GameTime extends TypeSafeCloneable<GameTime> {
	private int currentTime;

	public GameTime() {
		currentTime = 0;
	}

	/**
	 * ゲーム時間を1だけ進めます
	 */
	public void tick() {
		currentTime++;
	}

	/**
	 * ゲーム時間を指定した時間だけ進めます
	 * 
	 * @param time
	 *            進める時間
	 */
	public void tick(int time) {
		currentTime += time;
	}

	/**
	 * 現在のゲーム時間を返します
	 * 
	 * @return 現在のゲーム時間
	 */
	public int getCurrentTime() {
		return currentTime;
	}
}
