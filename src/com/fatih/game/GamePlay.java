package com.fatih.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlay {

	public static void main(String[] args) {

		ConfigurationReader configurationReader = new ConfigurationReader();
		Configuration configuration = configurationReader.getConfigurationFromPath("c://temp/tictactoe.txt");
		if (!configuration.isValid()) {
			configuration.getValidationIssues().stream().forEach(System.out::println);
			System.out.println("******");
		}

		Map<String, Character> playerSings = new HashMap<>();
		playerSings.put("firstPlayer", configuration.getFirstPlayerSign());
		playerSings.put("secondPlayer", configuration.getSecondPlayerSign());
		playerSings.put("computer", configuration.getThirdPlayerSign());

		List<String> playerList = new ArrayList<>(playerSings.keySet());
		Collections.shuffle(playerList);

		GameBoard gameBoard = new GameBoard(configuration.getBoardLength());
		gameBoard.print();

		InputTaker inputTaker = new InputTaker(configuration);
		boolean gameIsOver = false;
		while (gameIsOver) {
			for (String player : playerList) {
				System.out.println(player + "'s turn. Please enter a value(eg 3,2)");
				getInputFromUserAndPutToTheBoard(gameBoard, inputTaker, playerSings.get(player));
				gameBoard.print();
			}
		}
	}

	private static void getInputFromUserAndPutToTheBoard(GameBoard gameBoard, InputTaker inputTaker,
			Character character) {
		int[] input = inputTaker.getInput();
		boolean isCellAvailable = gameBoard.isEmpty(input[0], input[1]);
		if (!isCellAvailable) {
			System.out.println("That cell is already filled!");
			getInputFromUserAndPutToTheBoard(gameBoard, inputTaker, character);
		} else {
			gameBoard.setValue(input[0], input[1], character);
		}
	}
}
