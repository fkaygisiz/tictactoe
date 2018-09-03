package com.fatih.game;

import java.util.Arrays;
import java.util.Scanner;

public class HumanInputTaker {

	private String userInputRegEx;

	public HumanInputTaker(String userInputRegEx) {
		this.userInputRegEx = userInputRegEx;
	}

	private Scanner in = new Scanner(System.in);

	public int[] getInput(GameBoard gameBoard) {
		String userInput = in.next();
		boolean isRegularInput = userInput.matches(userInputRegEx);
		if (!isRegularInput) {
			System.out.println("Please enter a valid value:");
			return getInput(gameBoard);
		}
		return Arrays.asList(userInput.split(",")).stream().map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
	}

}
