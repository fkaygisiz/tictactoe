package com.fatih.game.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;

public class ComputerPlayerTest {

	private GameBoard gameBoard;

	@Before
	public void createBoard() {
		gameBoard = new GameBoard(3);
	}

	@Test
	public void getEmptyInput() throws Exception {
		// only one cell will be empty
		IntStream.range(0, 8).forEach(e -> gameBoard.setValue(new Coordinate(e / 3 + 1, e % 3 + 1), 'a'));
		gameBoard.print();
		Character symbol = 'c';
		ComputerPlayer target = new ComputerPlayer(symbol);
		Coordinate actual = target.getInput(gameBoard);
		Coordinate expected = new Coordinate(3, 3);
		assertThat(actual, is(equalTo(expected)));
	}

}
