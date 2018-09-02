package com.fatih.game;

import java.util.stream.IntStream;

public class GameBoard {

	private static final char EMPTY_CELL_CHAR = '-';
	private int length;
	Character[][] board;

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
				setRealValue(l, w, EMPTY_CELL_CHAR);
			}
		}

	}

	public boolean isEmpty(int x, int y) {
		return board[x-1][y-1] == EMPTY_CELL_CHAR;
	}
	
	public void setValue(int x, int y, Character value) {
		setRealValue(x-1, y-1, value);
	}
	
	public void setRealValue(int x, int y, Character value) {
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
}
