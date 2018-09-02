package com.fatih.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationReader {

	public static void main(String[] args) {
		ConfigurationReader cr = new ConfigurationReader();
		Configuration configurationFromPath = cr.getConfigurationFromPath("c://tictactoe.txt");
		if(!configurationFromPath.isValid()) {
			configurationFromPath.getValidationIssues().stream().forEach(System.out::println);
		}
	}

	public Configuration getConfigurationFromPath(String resourceFileName) {
		Configuration configuration = new Configuration();
		try {
			List<String> configurationList = getResourceFileAsString(resourceFileName);
			System.out.println(configurationList);
			System.out.println("*******");
			Set<String> configurationSet = new HashSet<>(configurationList);
			if (configurationSet.size() != 4) {
				configuration.setValid(false);
				configuration.addValidationIssue(
						"Player signs should be different and there should only 4 configuration options exist.");
				return configuration;
			}
			
			String firstPlayerSign = configurationList.get(0);
			String secondPlayerSign = configurationList.get(1);
			String computerSign = configurationList.get(2);
			checkPlayerSignLength(firstPlayerSign, configuration);
			checkPlayerSignLength(secondPlayerSign, configuration);
			checkPlayerSignLength(computerSign, configuration);
			configuration.setFirstPlayerSign(firstPlayerSign.charAt(0));
			configuration.setSecondPlayerSign(secondPlayerSign.charAt(0));
			configuration.setThirdPlayerSign(computerSign.charAt(0));

			
			checkBoardSize(configurationList.get(3), configuration);
			configuration.setBoardLength(Integer.valueOf(configurationList.get(3)));
		} catch (IOException e) {
			configuration.addValidationIssue("An exception occured while reading comfiguration file. File path is " + resourceFileName);
			configuration.setValid(false);
		}
		return configuration;

	}

	private void checkBoardSize(String boardSizeString, Configuration configuration) {
		int boardSize;
		try {
			boardSize = Integer.valueOf(boardSizeString);
			if (boardSize < 3 || boardSize > 10) {
				configuration.setValid(false);
				configuration.addValidationIssue("Board size should be between 3 and 10.");
			}
		} catch (NumberFormatException e) {
			configuration.setValid(false);
			configuration.addValidationIssue("Board size is not valid: " + e.getMessage());
		}

	}

	private void checkPlayerSignLength(String playerSign, Configuration configuration) {
		if (playerSign.length() != 1) {
			configuration.setValid(false);
			configuration.addValidationIssue(playerSign + " should be one character!");
		}
	}

	private List<String> getResourceFileAsString(String fileName) throws IOException {
		Stream<String> stream = Files.lines(Paths.get(fileName));
		List<String> result = stream.map(e -> e.trim()).filter(e -> e.length() > 0).collect(Collectors.toList());
		stream.close();
		return result;

	}

}