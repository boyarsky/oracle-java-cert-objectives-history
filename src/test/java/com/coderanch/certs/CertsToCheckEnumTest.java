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
}
