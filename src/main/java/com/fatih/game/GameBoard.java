package com.fatih.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fatih.game.configuration.Configuration;

public class GameBoard {

	private int length;
	private Character[][] board;

	public Character[][] getBoardCopy() {
		return Arrays.copyOf(board, board.length);
	}

	public GameBoard(int length) {
		this.length = length;
		initializeBoard(length);
	}

	private void initializeBoard(int length) {
		board = new Character[length][length];
		fillBoard(length);

	}

	private void fillBoard(int length2) {
		for (int l = 0; l < length2; l++) {
			for (int w = 0; w < length2; w++) {
				setRealValue(l, w, Configuration.EMPTY_CELL_CHAR);
			}
		}

	}

	public boolean isEmpty(Coordinate coordinate) {
		return board[coordinate.getX() - 1][coordinate.getY() - 1] == Configuration.EMPTY_CELL_CHAR;
	}

	public void setValue(Coordinate coordinate, Character value) {
		setRealValue(coordinate.getX() - 1, coordinate.getY() - 1, value);
	}

	private void setRealValue(int x, int y, Character value) {
		board[x][y] = value;
	}

	public void print() {
		IntStream.range(0, this.length + 1).forEach(e -> printPadded(String.valueOf(e)));
		System.out.println("");
		for (int l = 0; l < this.length; l++) {
			System.out.print(getPadded(String.valueOf(l + 1)));
			for (int w = 0; w < this.length; w++) {
				System.out.print(getPadded(String.valueOf(board[l][w])));
			}
			System.out.println("");
		}
	}

	private void printPadded(String e) {
		System.out.print(getPadded(e));
	}

	private String getPadded(String e) {
		return String.format("%2s ", e);
	}

	public boolean isGameFinished() {
		return !isThereAnyEmptyCell() || hasSomebodyWin();
	}

	private boolean hasSomebodyWin() {
		Character[] downCross = new Character[board.length];
		Character[] upperCross = new Character[board.length];
		for (int i = 0; i < board.length; i++) {
			if (isCharactersAllTheSame(board[i])) {
				return true;
			}
			Character[] column = new Character[board.length];
			for (int j = 0; j < board.length; j++) {
				column[j] = board[j][i];
			}
			if (isCharactersAllTheSame(column)) {
				return true;
			}
			downCross[i] = board[i][i];
			upperCross[i] = board[board.length - 1 - i][i];
		}
		return isCharactersAllTheSame(upperCross) || isCharactersAllTheSame(downCross);

	}

	private boolean isCharactersAllTheSame(Character[] characters) {
		HashSet<Character> characterSet = new HashSet<>(Arrays.asList(characters));
		return characterSet.size() == 1 && !characterSet.contains(Configuration.EMPTY_CELL_CHAR);
	}

	private Boolean isThereAnyEmptyCell() {
		Optional<Character> emptyCell = Arrays.stream(board) // 'array' is two-dimensional
				.flatMap(Arrays::stream).collect(Collectors.toList()).stream().filter(e -> e.equals(Configuration.EMPTY_CELL_CHAR))
				.findAny();
		return emptyCell.isPresent();
	}

}