package com.fatih.game.player;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fatih.game.Coordinate;
import com.fatih.game.GameBoard;
import com.fatih.game.configuration.Configuration;


public class HumanPlayerTest {

	private Configuration configuration;
	private GameBoard gameBoard;

	//@Mock
    private HumanInputTaker humanInputTaker;
	
	@Before
	public void setUp() {
		configuration = new Configuration();
		configuration.setBoardLength(3);
		configuration.setFirstPlayerSymbol('a');
		configuration.setSecondPlayerSymbol('b');
		configuration.setThirdPlayerSymbol('c');
		configuration.setValid(true);
		
		gameBoard = new GameBoard(configuration.getBoardLength());
	//	MockitoAnnotations.initMocks(this);
		//humanInputTaker = new HumanInputTaker(configuration.getRegularExpression());
		//PowerMockito.mock(HumanInputTaker.class);
		
		humanInputTaker = mock(HumanInputTaker.class);
		PowerMockito.when(humanInputTaker.getInput(gameBoard)).thenReturn(new Coordinate(3,1));
	}

	@Test
	public void instantiation() throws Exception {
		//HumanInputTaker inputTaker = new HumanInputTaker(configuration.getRegularExpression());
		HumanPlayer target = new HumanPlayer(configuration.getThirdPlayerSymbol(), humanInputTaker);
		Coordinate input = target.getInput(gameBoard);
		//target.

	}

}
