package com.fatih.game;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	private GameBoard gameBoard;

	@Before
	public void createBoard() {
		gameBoard = new GameBoard(3);
	}

	@Test
	public void test_getBoardCopy() throws Exception {
		Coordinate coordinate = new Coordinate(1, 1);
		gameBoard.setValue(coordinate, 'x');
		Map<Coordinate, Character> actual = gameBoard.getBoardCopy();
		assertThat(actual.size(), equalTo(9));
		assertThat(actual.get(coordinate), equalTo('x'));
	}

	@Test
	public void test_isEmpty() throws Exception {
		boolean actual = gameBoard.isEmpty(new Coordinate(1, 1));
		boolean expected = true;
		assertThat(actual, equalTo(expected));
	}

	@Test
	public void test_isNotEmpty() throws Exception {
		Coordinate coordinate = new Coordinate(1, 1);
		gameBoard.setValue(coordinate, 'x');
		boolean actual = gameBoard.isEmpty(coordinate);
		boolean expected = false;
		assertThat(actual, equalTo(expected));
	}

	@Test
	public void setValue_toCoordinate() throws Exception {

		Coordinate coordinate = new Coordinate(1, 1);
		Character value = 'x';
		gameBoard.setValue(coordinate, value);
		assertThat(gameBoard.getBoardCopy().get(coordinate), equalTo(value));
	}

	@Test
	public void isGameFinished_byWinningGame() throws Exception {
		Character value = 'x';
		IntStream.range(1, 4).forEach(e -> gameBoard.setValue(new Coordinate(1, e), value));
		boolean actual = gameBoard.hasSomebodyWon();
		boolean expected = true;
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void isGameFinished_byWinningGame_crossComplete() throws Exception {
		Character value = 'x';
		IntStream.range(1, 4).forEach(e -> gameBoard.setValue(new Coordinate(e, e), value));

		boolean actual = gameBoard.hasSomebodyWon();
		boolean expected = true;
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void isGameFinished_byWinningGame_reverseCrossComplete() throws Exception {
		Character value = 'x';
		IntStream.range(1, 4).forEach(e -> gameBoard.setValue(new Coordinate(e, 4 - e), value));
		boolean actual = gameBoard.hasSomebodyWon();
		boolean expected = true;
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void isGameFinished_byFullGameBoard() throws Exception {
		Character[] characters = new Character[] { 'a', 'b', 'c', 'd', 'e' };
		int length = characters.length;
		int charactersIndex = 0;
		for (int i = 1; i < 4; i++) {
			gameBoard.setValue(new Coordinate(i, 1), characters[charactersIndex++ % length]);
			gameBoard.setValue(new Coordinate(i, 2), characters[charactersIndex++ % length]);
			gameBoard.setValue(new Coordinate(i, 3), characters[charactersIndex++ % length]);
		}
		gameBoard.print();
		boolean actual = !gameBoard.isThereAnyEmptyCell();
		boolean expected = true;
		assertThat(actual, is(equalTo(expected)));
	}

}
