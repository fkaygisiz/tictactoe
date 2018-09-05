package com.fatih.game.player;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;
import com.fatih.game.configuration.Configuration;

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
	public Coordinate getInput(GameBoard gameBoard) {
		return findEmptyCell(gameBoard);
	}

	private Coordinate findEmptyCell(GameBoard gameBoard) {
		Character[][] boardCopy = gameBoard.getBoardCopy();
		for (int i = 0; i < boardCopy.length; i++) {
			for (int j = 0; j < boardCopy.length; j++) {
				if (boardCopy[i][j] == Configuration.EMPTY_CELL_CHAR) {
					return new Coordinate(i + 1, j + 1);
				}
			}
		}
		return null;
	}

}
