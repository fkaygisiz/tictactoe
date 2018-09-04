package com.fatih.game.configuration;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private char firstPlayerSymbol;
	private char secondPlayerSymbol;
	private char thirdPlayerSymbol;

	private int boardLength;

	private boolean valid;

	private List<String> validationIssues = new ArrayList<>();

	public char getFirstPlayerSymbol() {
		return firstPlayerSymbol;
	}

	public void setFirstPlayerSymbol(char firstPlayerSymbol) {
		this.firstPlayerSymbol = firstPlayerSymbol;
	}

	public char getSecondPlayerSymbol() {
		return secondPlayerSymbol;
	}

	public void setSecondPlayerSymbol(char secondPlayerSymbol) {
		this.secondPlayerSymbol = secondPlayerSymbol;
	}

	public char getThirdPlayerSymbol() {
		return thirdPlayerSymbol;
	}

	public void setThirdPlayerSymbol(char thirdPlayerSymbol) {
		this.thirdPlayerSymbol = thirdPlayerSymbol;
	}

	public int getBoardLength() {
		return boardLength;
	}

	public void setBoardLength(int boardLength) {
		this.boardLength = boardLength;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<String> getValidationIssues() {
		return validationIssues;
	}

	public void addValidationIssue(String issue) {
		validationIssues.add(issue);
	}

	public String getRegularExpression() {
		if (boardLength == 10) {
			return "^([1-9]|10),([1-9]|10)$";
		} else {
			return "^([1-" + boardLength + "]),([1-" + boardLength + "])$";
		}
	}
}
