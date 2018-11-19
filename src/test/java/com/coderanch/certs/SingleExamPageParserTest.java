package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SingleExamPageParserTest {

	private SingleExamPageParser parser;

	@BeforeEach
	public void setUp() {
		parser = new SingleExamPageParser();
	}

	private String readJsonToParse() throws IOException {
		Path path = Paths.get("src/test/resources/sample-808-json.txt");
		List<String> lines = Files.readAllLines(path);
		// the first line is a comment so get the second line
		return lines.get(1);
	}

	@Test
	public void convert() throws Exception {
		String expected = "Duration: 150\n" +
				"# Questions: 70\n" +
				"Passing Score: 65%\n" +
				"US exam cost: US$ 245\n" +
				"\n" +
				"Java Basics\n" +
				"- Define the scope of variables\n";
		String json = readJsonToParse();
		String actual = parser.convertToTextFormat(json);

		assertEquals(expected, actual);
	}

}
