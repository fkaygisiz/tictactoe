package com.fatih.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePlay {

	public static void main(String[] args) {

		ConfigurationReader configurationReader = new ConfigurationReader();
		Configuration configuration = configurationReader.getConfigurationFromPath("c://temp/tictactoe.txt");
		if (!configuration.isValid()) {
			configuration.getValidationIssues().stream().forEach(System.out::println);
			System.out.println("******");
		}

		HumanInputTaker inputTaker = new HumanInputTaker(configuration.getRegularExpression());

		Player firstPlayer = new HumanPlayer(configuration.getFirstPlayerSign(), inputTaker);
		Player secondPlayer = new HumanPlayer(configuration.getSecondPlayerSign(), inputTaker);
		Player computer = new ComputerPlayer(configuration.getThirdPlayerSign());
		
		List<Player> playerList = new ArrayList<>();
		playerList.add(firstPlayer);
		playerList.add(secondPlayer);
		playerList.add(computer);

		Collections.shuffle(playerList);

		GameBoard gameBoard = new GameBoard(configuration.getBoardLength());
		gameBoard.print();

		boolean gameIsOver = false;
		while (!gameIsOver) {
			for (Player player : playerList) {
				System.out.println(player.getSymbol() + "'s turn. Please enter a value(eg 3,2)");
				int[] input = player.getInput(gameBoard);
				gameBoard.setValue(input[0], input[1], player.getSymbol());
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
