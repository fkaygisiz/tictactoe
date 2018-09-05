package com.fatih.game.player;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;

public class HumanPlayer implements Player {

	private Character symbol;
	private HumanInputTaker inputTaker;

	public HumanPlayer(Character symbol, HumanInputTaker inputTaker) {
		this.symbol = symbol;
		this.inputTaker = inputTaker;
	}

	@Override
	public Character getSymbol() {
		return symbol;
	}

	@Override
	public Coordinate getInput(GameBoard gameBoard) {
		Coordinate input = inputTaker.getInput(gameBoard);
		boolean isCellAvailable = gameBoard.isEmpty(input);
		if (!isCellAvailable) {
			System.out.println("That cell is already filled!");
			return getInput(gameBoard);
		} else {
			return input;
		}
	}

}
