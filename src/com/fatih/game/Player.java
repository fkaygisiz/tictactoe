package com.fatih.game;

public interface Player {

	Character getSymbol();

	int[] getInput(GameBoard gameBoard);

}