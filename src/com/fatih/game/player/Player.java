package com.fatih.game.player;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;

public interface Player {

	Character getSymbol();

	Coordinate getInput(GameBoard gameBoard);

}