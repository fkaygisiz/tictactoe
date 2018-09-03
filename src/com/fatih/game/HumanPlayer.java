package com.fatih.game;

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
	public int[] getInput(GameBoard gameBoard) {
		int[] input = inputTaker.getInput(gameBoard);
		boolean isCellAvailable = gameBoard.isEmpty(input[0], input[1]);
		if (!isCellAvailable) {
			System.out.println("That cell is already filled!");
			return getInput(gameBoard);
		}else {
			return input;
		}
	}
	
}
