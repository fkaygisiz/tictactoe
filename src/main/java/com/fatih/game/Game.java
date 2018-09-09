package com.fatih.game;

import java.util.List;

import com.fatih.game.configuration.Configuration;
import com.fatih.game.configuration.ConfigurationReader;
import com.fatih.game.player.Player;
import com.fatih.game.player.PlayerFactory;

public class Game {

	public static void main(String[] args) {
		Game game = new Game();
		if (args.length > 0) {
			game.start(args[0]);
		} else {
			System.out.println("Please provide configuration path!");
		}
	}

	public void start(String configurationPath) {
		ConfigurationReader configurationReader = new ConfigurationReader();
		Configuration configuration = configurationReader.getConfigurationFromPath(configurationPath);
		if (!configuration.isValid()) {
			configuration.getValidationIssues().stream().forEach(System.out::println);
			System.out.println("******");
		} else {
			PlayerFactory playerFactory = new PlayerFactory();
			List<Player> playerList = playerFactory.createPlayers(configuration);

			GameBoard gameBoard = new GameBoard(configuration.getBoardLength());
			gameBoard.print();
			this.play(playerList, gameBoard);
		}
	}

	private void play(List<Player> playerList, GameBoard gameBoard) {
		boolean isGameOver = false;
		while (!isGameOver) {
			for (Player player : playerList) {
				System.out.println(player.getSymbol() + "'s turn. Please enter a value(eg 3,2)");
				Coordinate input = player.getInput(gameBoard);
				System.out.println(player.getSymbol() + " fills " + input.getPrettyFormat());
				gameBoard.setValue(input, player.getSymbol());
				gameBoard.print();
				boolean somebodyWon = gameBoard.hasSomebodyWon();
				if (somebodyWon) {
					System.out.println("!!!!! " + player.getSymbol() + " won the game!!!!!");
					isGameOver = true;
					break;
				}
				boolean isBoardFull = !gameBoard.isThereAnyEmptyCell();
				if(isBoardFull) {
					System.out.println("!!!!! Great game, Tie !!!!!");
					isGameOver = true;
					break;
				}
			}
		}
	}

}
