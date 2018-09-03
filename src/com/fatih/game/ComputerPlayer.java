package com.fatih.game;

public class ComputerPlayer implements Player {

	private Character symbol;

	public ComputerPlayer(Character symbol) {
		this.symbol = symbol;
	}

	@Override
	public Character getSymbol() {
		return symbol;
	}

	@Override
	public int[] getInput(GameBoard gameBoard) {
		return findEmptyCell(gameBoard);
	}

	private int[] findEmptyCell(GameBoard gameBoard) {
		Character[][] boardCopy = gameBoard.getBoardCopy();
		for (int i = 0; i < boardCopy.length; i++) {
			for (int j = 0; j < boardCopy.length; j++) {
				if (boardCopy[i][j] == GameBoard.EMPTY_CELL_CHAR) {
					return new int[] { i + 1, j + 1 };
				}
			}
		}
		return null;
	}

}
