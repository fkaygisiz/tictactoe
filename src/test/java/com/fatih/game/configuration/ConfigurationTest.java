package com.fatih.game.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void addValidationIssue_A$String() throws Exception {
		Configuration target = new Configuration();
		String issue = "issue";
		target.addValidationIssue(issue);
		assertThat(issue, equalTo(target.getValidationIssues().get(0)));
	}

	@Test
	public void getRegularExpressionFor4x4Board() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setBoardLength(4);
		configuration.setFirstPlayerSymbol('a');
		configuration.setSecondPlayerSymbol('b');
		configuration.setThirdPlayerSymbol('c');
		configuration.setValid(true);

		assertThat(configuration.getRegularExpression(), equalTo("^([1-4]),([1-4])$"));
	}

	@Test
	public void getRegularExpressionFor10x10Board() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setBoardLength(10);
		configuration.setFirstPlayerSymbol('a');
		configuration.setSecondPlayerSymbol('b');
		configuration.setThirdPlayerSymbol('c');
		configuration.setValid(true);

		assertThat(configuration.getRegularExpression(), equalTo("^([1-9]|10),([1-9]|10)$"));
	}

}
