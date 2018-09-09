package com.fatih.game.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;
import com.fatih.game.configuration.Configuration;

public class HumanPlayerTest {

	private Configuration configuration;
	private GameBoard gameBoard;

	private HumanInputTaker humanInputTaker;

	@Before
	public void setUp() {
		configuration = new Configuration();
		configuration.setBoardLength(3);
		configuration.setFirstPlayerSymbol('a');
		configuration.setSecondPlayerSymbol('b');
		configuration.setThirdPlayerSymbol('c');
		configuration.setValid(true);

		gameBoard = new GameBoard(configuration.getBoardLength());
		humanInputTaker = mock(HumanInputTaker.class);
	}

	@Test
	public void shouldGetNextInputWhenCellIsFull() throws Exception {
		Coordinate filledCoordinate = new Coordinate(1, 1);
		gameBoard.setValue(filledCoordinate, configuration.getFirstPlayerSymbol());
		PowerMockito.when(humanInputTaker.getInput(gameBoard)).thenReturn(filledCoordinate, new Coordinate(3, 1));

		HumanPlayer target = new HumanPlayer(configuration.getThirdPlayerSymbol(), humanInputTaker);
		Coordinate actual = target.getInput(gameBoard);
		assertThat(actual, equalTo(new Coordinate(3, 1)));
	}

}
