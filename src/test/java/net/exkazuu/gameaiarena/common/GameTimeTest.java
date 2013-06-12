package net.exkazuu.gameaiarena.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GameTimeTest extends GameTime {

	GameTime gameTime;

	@Before
	public final void before() {
		gameTime = new GameTime();
	}

	@Test
	public final void newGameTime() {
		assertThat(gameTime.getCurrentTime(), is(0));
	}

	@Test
	public final void tick() {
		gameTime.tick();
		assertThat(gameTime.getCurrentTime(), is(1));
		gameTime.tick(5);
		assertThat(gameTime.getCurrentTime(), is(6));
	}
}
