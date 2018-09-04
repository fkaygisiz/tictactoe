package com.fatih.game.player;

import java.util.Arrays;
import java.util.Scanner;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;

public class HumanInputTaker {

	private String userInputRegEx;

	public HumanInputTaker(String userInputRegEx) {
		this.userInputRegEx = userInputRegEx;
	}

	private Scanner in = new Scanner(System.in);

	public Coordinate getInput(GameBoard gameBoard) {
		String userInput = in.next();
		boolean isRegularInput = userInput.matches(userInputRegEx);
		if (!isRegularInput) {
			System.out.println("Please enter a valid value:");
			return getInput(gameBoard);
		}
		int[] inputArray = Arrays.asList(userInput.split(",")).stream().map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
		return new Coordinate(inputArray[0], inputArray[1]);
	}

}
