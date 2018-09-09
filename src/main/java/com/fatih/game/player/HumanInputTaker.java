package com.fatih.game.player;

import java.util.Arrays;
import java.util.Scanner;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;

public class HumanInputTaker {

	private String userInputRegEx;
	private Scanner in = new Scanner(System.in);

	public HumanInputTaker(String userInputRegEx) {
		this.userInputRegEx = userInputRegEx;
	}

	public Coordinate getInput(GameBoard gameBoard) {
		String userInput = getInputString();
		boolean isValid = userInput.matches(userInputRegEx);
		if (!isValid) {
			System.out.println("Please enter a valid value:");
			return getInput(gameBoard);
		}
		int[] inputArray = Arrays.asList(userInput.split(",")).stream().map(Integer::valueOf)
				.mapToInt(Integer::intValue).toArray();
		return new Coordinate(inputArray[0], inputArray[1]);
	}

	private String getInputString() {
		return in.next();
	}

}
