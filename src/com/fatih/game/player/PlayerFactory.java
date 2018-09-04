package com.fatih.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fatih.game.configuration.Configuration;

public class PlayerFactory {

	public List<Player> createPlayers(Configuration configuration) {
		HumanInputTaker inputTaker = new HumanInputTaker(configuration.getRegularExpression());

		Player firstPlayer = new HumanPlayer(configuration.getFirstPlayerSymbol(), inputTaker);
		Player secondPlayer = new HumanPlayer(configuration.getSecondPlayerSymbol(), inputTaker);
		Player computer = new ComputerPlayer(configuration.getThirdPlayerSymbol());

		List<Player> playerList = new ArrayList<>();
		playerList.add(firstPlayer);
		playerList.add(secondPlayer);
		playerList.add(computer);
		Collections.shuffle(playerList);
		return playerList;
	}
}
