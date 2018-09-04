package com.fatih.game;

import java.util.List;

import com.fatih.game.configuration.Configuration;
import com.fatih.game.configuration.ConfigurationReader;
import com.fatih.game.player.Player;
import com.fatih.game.player.PlayerFactory;

public class Game {

	public static void main(String[] args) {
		Game game = new Game();
		ConfigurationReader configurationReader = new ConfigurationReader();
		Configuration configuration = configurationReader.getConfigurationFromPath("c://temp/tictactoe.txt");
		if (!configuration.isValid()) {
			configuration.getValidationIssues().stream().forEach(System.out::println);
			System.out.println("******");
		} else {
			PlayerFactory playerFactory = new PlayerFactory();
			List<Player> playerList = playerFactory.createPlayers(configuration);

			GameBoard gameBoard = new GameBoard(configuration.getBoardLength());
			gameBoard.print();
			game.play(playerList, gameBoard);
		}
	}

	public void play(List<Player> playerList, GameBoard gameBoard) {
		boolean gameIsOver = false;
		while (!gameIsOver) {
			for (Player player : playerList) {
				System.out.println(player.getSymbol() + "'s turn. Please enter a value(eg 3,2)");
				Coordinate input = player.getInput(gameBoard);
				gameBoard.setValue(input, player.getSymbol());
				gameBoard.print();
				gameIsOver = gameBoard.isGameFinished();
				if (gameIsOver) {
					System.out.println("!!!!! " + player.getSymbol() + " won the game!!!!!");
					break;
				}
			}
		}
	}

}
