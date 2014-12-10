package com.coderanch.certs;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class CertsToCheckEnumTest {

	@Test
	public void noDuplicateExamNumbers() {
		Set<String> set = new HashSet<String>();
		for (CertsToCheckEnum element : CertsToCheckEnum.values()) {
			set.add(element.examNumber);
		}
		int expected = CertsToCheckEnum.values().length;
		assertEquals("exam numbers must be unique. copy/paste error?",
				expected, set.size());
	}
}
