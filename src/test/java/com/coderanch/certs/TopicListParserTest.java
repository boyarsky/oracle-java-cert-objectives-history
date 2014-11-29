package com.coderanch.certs;

import static org.junit.Assert.*;

import org.junit.*;

public class TopicListParserTest {

	@Test
	public void htmlFormat() throws Exception {
		String topics = "<div><strong>Objective 1&nbsp;</strong></div><ul><li>Subobjective 1&nbsp;</li>"
				+ "<div><strong>&quot;Objective&quot; 2&nbsp;</strong></div><ul><li>Subobjective 2&nbsp;</li>";
		String expected = "\nObjective 1\n- Subobjective 1\n\n\"Objective\" 2\n- Subobjective 2\n";
		TopicListParser parser = new TopicListParser(topics);
		String actual = parser.convertToTextFormat();
		assertEquals(expected, actual);

	}

}
