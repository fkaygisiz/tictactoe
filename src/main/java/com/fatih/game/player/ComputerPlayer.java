package com.fatih.game.player;

import java.util.Map.Entry;
import java.util.Optional;

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
		Optional<Entry<Coordinate, Character>> anyEmptyCell = gameBoard.getBoardCopy().entrySet().stream()
				.filter(e -> e.getValue().equals(Configuration.EMPTY_CELL_CHAR)).findAny();
		return anyEmptyCell.isPresent() ? anyEmptyCell.get().getKey() : null;
	}

}
