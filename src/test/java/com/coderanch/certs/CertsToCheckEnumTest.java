package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

public class CertsToCheckEnumTest {

	@Test
	public void noDuplicateExamNumbers() {
		Set<String> set = CertsToCheckEnum.getSetOfExamNumbers();
		int expected = CertsToCheckEnum.values().length;
		assertEquals(expected, set.size(), "exam numbers must be unique. copy/paste error?");
	}

	@Test
	public void stringFormat() {
		String expected = "OCPJP_JAVA_8 (1Z0-809)";
		String actual = CertsToCheckEnum.OCPJP_JAVA_8.toString();
		assertEquals(expected, actual, "to string should include exam # so easy to find file");
	}
}
