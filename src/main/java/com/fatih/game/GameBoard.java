package com.fatih.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.IntStream;

import com.fatih.game.configuration.Configuration;

public class GameBoard {

	private int length;
	private Map<Coordinate, Character> board = new HashMap<>();

	public Map<Coordinate, Character> getBoardCopy() {
		return Collections.unmodifiableMap(board);
	}

	public GameBoard(int length) {
		this.length = length;
		initializeBoard(length);
	}

	private void initializeBoard(int length) {
		fillBoard(length);

	}

	private void fillBoard(int length2) {
		for (int l = 1; l < length2 + 1; l++) {
			for (int w = 1; w < length2 + 1; w++) {
				setValue(new Coordinate(l, w), Configuration.EMPTY_CELL_CHAR);
			}
		}

	}

	public boolean isEmpty(Coordinate coordinate) {
		return board.get(coordinate) == Configuration.EMPTY_CELL_CHAR;
	}

	public void setValue(Coordinate coordinate, Character value) {
		setRealValue(coordinate, value);
	}

	private void setRealValue(Coordinate coordinate, Character value) {
		board.put(coordinate, value);
	}

	public void print() {
		IntStream.range(0, this.length + 1).forEach(e -> printPadded(String.valueOf(e)));
		System.out.println("");
		for (int l = 0; l < this.length; l++) {
			System.out.print(getPadded(String.valueOf(l + 1)));
			for (int w = 0; w < this.length; w++) {
				System.out.print(getPadded(String.valueOf(board.get(new Coordinate(l + 1, w + 1)))));
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

	public boolean hasSomebodyWon() {
		Character[] downCross = new Character[this.length];
		Character[] upperCross = new Character[this.length];
		for (int i = 0; i < this.length; i++) {
			int x = i;
			if (isCharactersAllTheSame(IntStream.range(0, this.length)
					.mapToObj(e -> board.get(new Coordinate(x + 1, e + 1))).toArray(Character[]::new))) {
				return true;
			}
			Character[] column = new Character[this.length];
			for (int j = 0; j < this.length; j++) {
				column[j] = board.get(new Coordinate(j + 1, i + 1));
			}
			if (isCharactersAllTheSame(column)) {
				return true;
			}
			downCross[i] = board.get(new Coordinate(i + 1, i + 1));
			upperCross[i] = board.get(new Coordinate(this.length - i, i + 1));
		}
		return isCharactersAllTheSame(upperCross) || isCharactersAllTheSame(downCross);

	}

	private boolean isCharactersAllTheSame(Character[] characters) {
		HashSet<Character> characterSet = new HashSet<>(Arrays.asList(characters));
		return characterSet.size() == 1 && !characterSet.contains(Configuration.EMPTY_CELL_CHAR);
	}

	public boolean isThereAnyEmptyCell() {
		board.values().stream().anyMatch(e -> e.equals(Configuration.EMPTY_CELL_CHAR));
		return board.values().stream().anyMatch(e -> e.equals(Configuration.EMPTY_CELL_CHAR));
	}

}