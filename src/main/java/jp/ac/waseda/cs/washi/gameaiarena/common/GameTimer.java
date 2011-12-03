package jp.ac.waseda.cs.washi.gameaiarena.common;

/**
 * 時間に関するクラス
 */
public class GameTimer {

	private GameTime gameTime;
	private int span;
	private int endTime;

	public GameTimer(GameTime gameTime, int span) {
		this.gameTime = gameTime;
		this.span = span;
		this.endTime = gameTime.getCurrentTime() + span;
	}

	public GameTime getGameTime() {
		return gameTime;
	}

	public int getSpan() {
		return span;
	}

	/**
	 * ゲームが終了しているかどうかを取得します
	 */
	public boolean isOver() {
		return gameTime.getCurrentTime() >= endTime;
	}

	/**
	 * ゲームの残り時間を取得します
	 */
	public int getRumainingTime() {
		return isOver() ? 0 : endTime - gameTime.getCurrentTime();
	}
}
