package com.fatih.game.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;
import com.fatih.game.configuration.Configuration;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HumanInputTaker.class)
public class HumanInputTakerTest {

	private Configuration configuration;
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		configuration = new Configuration();
		configuration.setBoardLength(3);

		gameBoard = new GameBoard(configuration.getBoardLength());
	}

	@Test
	public void shouldGetNextInputWhenInputDoesntMatchRegEx() throws Exception {
		HumanInputTaker target = new HumanInputTaker(configuration.getRegularExpression());
		HumanInputTaker spy = PowerMockito.spy(target);
		PowerMockito.doReturn("4,4", "3,1").when(spy, "getInputString");
		Coordinate actual = spy.getInput(gameBoard);
		System.out.println(actual.getPrettyFormat());
		assertThat(actual, equalTo(new Coordinate(3, 1)));
	}

	@Test
	public void shouldGetNextInputWhenInputMatchesRegEx() throws Exception {
		HumanInputTaker target = new HumanInputTaker(configuration.getRegularExpression());
		HumanInputTaker spy = PowerMockito.spy(target);
		PowerMockito.doReturn("1,1", "3,1").when(spy, "getInputString");
		Coordinate actual = spy.getInput(gameBoard);
		System.out.println(actual.getPrettyFormat());
		assertThat(actual, equalTo(new Coordinate(1, 1)));
	}
}
