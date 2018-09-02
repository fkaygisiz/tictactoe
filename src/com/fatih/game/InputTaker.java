package com.fatih.game;

import java.util.Arrays;
import java.util.Scanner;

public class InputTaker {

	private Configuration configuration;

	public InputTaker(Configuration configuration) {
		this.configuration = configuration;
	}

	private Scanner in = new Scanner(System.in);

	public int[] getInput() {
		String userInput = in.next();
		boolean isRegularInput = userInput.matches(configuration.getRegularExpression());
		if (!isRegularInput) {
			System.out.println("Please enter a valid value:");
			return getInput();
		}
		return Arrays.asList(userInput.split(",")).stream().map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
	}

}
