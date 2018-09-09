package com.fatih.game.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fatih.game.configuration.Configuration;

public class PlayerFactoryTest {

	private Configuration configuration;

	@Before
	public void beforeTest() {
		configuration = new Configuration();
		configuration.setBoardLength(4);
		configuration.setFirstPlayerSymbol('a');
		configuration.setSecondPlayerSymbol('b');
		configuration.setThirdPlayerSymbol('c');
		configuration.setValid(true);
	}

	@Test
	public void shouldProduce3PlayersFromConfiguration() throws Exception {
		PlayerFactory target = new PlayerFactory();
		List<Player> actual = target.createPlayers(configuration);
		assertThat(actual.size(), equalTo(3));
	}

}
