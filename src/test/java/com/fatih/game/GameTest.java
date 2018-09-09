package com.fatih.game;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Game.class)
public class GameTest {

	private static final String METHOD_NAME_PLAY = "play";

	@Test
	public void start_withInvalidConfiguration() throws Exception {
		Game target = PowerMockito.spy(new Game());
		String configurationPath = "fsdfsdfdsf";
		target.start(configurationPath);
		PowerMockito.verifyPrivate(target, never()).invoke(METHOD_NAME_PLAY, ArgumentMatchers.any(),
				ArgumentMatchers.any());
	}

	@Test
	public void start_withValidConfiguration() throws Exception {
		URL resource = GameTest.class.getResource("/tictactoe.txt");
		Path path = Paths.get(resource.toURI());
		String configurationPath = path.toString();

		Game target = PowerMockito.spy(new Game());
		PowerMockito.doNothing().when(target, METHOD_NAME_PLAY, ArgumentMatchers.any(List.class),
				ArgumentMatchers.any(GameBoard.class));

		target.start(configurationPath);
		PowerMockito.verifyPrivate(target, times(1)).invoke(METHOD_NAME_PLAY, ArgumentMatchers.any(),
				ArgumentMatchers.any());
	}

}
