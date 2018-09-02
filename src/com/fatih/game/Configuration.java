package com.fatih.game;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private char firstPlayerSign;
	private char secondPlayerSign;
	private char thirdPlayerSign;

	private int boardLength;

	private boolean valid;

	private List<String> validationIssues = new ArrayList<>();

	public char getFirstPlayerSign() {
		return firstPlayerSign;
	}

	public void setFirstPlayerSign(char firstPlayerSign) {
		this.firstPlayerSign = firstPlayerSign;
	}

	public char getSecondPlayerSign() {
		return secondPlayerSign;
	}

	public void setSecondPlayerSign(char secondPlayerSign) {
		this.secondPlayerSign = secondPlayerSign;
	}

	public char getThirdPlayerSign() {
		return thirdPlayerSign;
	}

	public void setThirdPlayerSign(char thirdPlayerSign) {
		this.thirdPlayerSign = thirdPlayerSign;
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
