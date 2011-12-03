package jp.ac.waseda.cs.washi.gameaiarena.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTimerTest {

	private static final int _roundTime = 30;
	private GameTime _time;
	private GameTimer _timer;

	@Before
	public void before() {
		_time = new GameTime();
		_timer = new GameTimer(_time, _roundTime);
	}

	@Test
	public void getGameTime() {
		assertThat(_timer.getGameTime(), is(_time));
	}

	@Test
	public void getCurrentTime() {
		assertThat(_time.getCurrentTime(), is(0));
	}

	@Test
	public void getCurrentTimeUsingTick() {
		_time.tick();
		assertThat(_time.getCurrentTime(), is(1));
		_time.tick(5);
		assertThat(_time.getCurrentTime(), is(6));
	}

	@Test
	public void getSpan() {
		assertThat(_timer.getSpan(), is(_roundTime));
		_time.tick();
		assertThat(_timer.getSpan(), is(_roundTime));
	}

	@Test
	public void isOver() {
		assertThat(_timer.isOver(), is(false));
	}

	@Test
	public void isOverUsingTick() {
		_time.tick(29);
		assertThat(_timer.isOver(), is(false));
		_time.tick(0);
		assertThat(_timer.isOver(), is(false));
		_time.tick();
		assertThat(_timer.isOver(), is(true));
		_time.tick(20);
		assertThat(_timer.isOver(), is(true));
	}

	@Test
	public void getRumainingTime() {
		assertThat(_timer.getRumainingTime(), is(_roundTime));
	}

	@Test
	public void getRumainingTimeUsingTick() {
		_time.tick();
		assertThat(_timer.getRumainingTime(), is(_roundTime - 1));
		_time.tick(5);
		assertThat(_timer.getRumainingTime(), is(_roundTime - 6));
		_time.tick(_roundTime - 6);
		assertThat(_timer.getRumainingTime(), is(0));
		_time.tick();
		assertThat(_timer.getRumainingTime(), is(0));
	}
}
