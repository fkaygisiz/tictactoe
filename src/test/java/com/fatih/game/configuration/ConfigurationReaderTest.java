package com.fatih.game.configuration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class ConfigurationReaderTest {

	@Test
	public void getConfigurationFromPath() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		URL resource = ConfigurationReaderTest.class.getResource("/tictactoe.txt");
		Path path = Paths.get(resource.toURI());
		String resourceFilePath = path.toString();
		Configuration actual = target.getConfigurationFromPath(resourceFilePath);
		assertThat(actual.isValid(), equalTo(true));
		assertThat(actual.getBoardLength(), equalTo(5));
		assertThat(actual.getFirstPlayerSymbol(), equalTo('e'));
		assertThat(actual.getSecondPlayerSymbol(), equalTo('r'));
		assertThat(actual.getThirdPlayerSymbol(), equalTo('t'));
	}

	@Test
	public void getConfigurationFromPath_trimCharacter() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		URL resource = ConfigurationReaderTest.class.getResource("/tictactoe_trim.txt");
		Path path = Paths.get(resource.toURI());
		String resourceFilePath = path.toString();
		Configuration actual = target.getConfigurationFromPath(resourceFilePath);
		assertThat(actual.isValid(), equalTo(true));
		assertThat(actual.getBoardLength(), equalTo(5));
		assertThat(actual.getFirstPlayerSymbol(), equalTo('e'));
		assertThat(actual.getSecondPlayerSymbol(), equalTo('r'));
		assertThat(actual.getThirdPlayerSymbol(), equalTo('t'));
	}

	@Test
	public void getConfigurationFromPath_missingConfiguration() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		URL resource = ConfigurationReaderTest.class.getResource("/tictactoe_missing_configuration.txt");
		Path path = Paths.get(resource.toURI());
		String resourceFilePath = path.toString();
		Configuration actual = target.getConfigurationFromPath(resourceFilePath);
		assertThat(actual.isValid(), equalTo(false));
		assertThat(actual.getValidationIssues().size(), equalTo(1));
		assertThat(actual.getValidationIssues().get(0),
				equalTo("Player Symbols should be different and there should only 4 configuration options exist."));
	}

	@Test
	public void getConfigurationFromPath_nonexistingFile() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		Configuration actual = target.getConfigurationFromPath("/nonexisting_file.txt");
		assertThat(actual.isValid(), equalTo(false));
		assertThat(actual.getValidationIssues().size(), equalTo(1));
		assertThat(actual.getValidationIssues().get(0),
				containsString("An exception occured while reading comfiguration file. File path is "));
	}
	
	@Test
	public void getConfigurationFromPath_invalidBoardSize() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		URL resource = ConfigurationReaderTest.class.getResource("/tictactoe_invalid_size.txt");
		Path path = Paths.get(resource.toURI());
		String resourceFilePath = path.toString();
		Configuration actual = target.getConfigurationFromPath(resourceFilePath);
		assertThat(actual.isValid(), equalTo(false));
		assertThat(actual.getValidationIssues().stream().filter(e->e.equals("Board size should be between 3 and 10.")).findAny().isPresent(),
				equalTo(true));
	}
	
	@Test
	public void getConfigurationFromPath_reservedSymbol() throws Exception {
		ConfigurationReader target = new ConfigurationReader();
		URL resource = ConfigurationReaderTest.class.getResource("/tictactoe_reserved_symbol.txt");
		Path path = Paths.get(resource.toURI());
		String resourceFilePath = path.toString();
		Configuration actual = target.getConfigurationFromPath(resourceFilePath);
		assertThat(actual.isValid(), equalTo(false));
		assertThat(actual.getValidationIssues().stream().filter(e->e.contains(Configuration.EMPTY_CELL_CHAR + " cannot be used as a symbol!")).findAny().isPresent(),
				equalTo(true));
	}
}
