package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class CertsToCheckEnumTest {

	@Test
	public void noDuplicateExamNumbers() {
		Set<String> set = new HashSet<>();
		for (CertsToCheckEnum element : CertsToCheckEnum.values()) {
			set.add(element.examNumber);
		}
		int expected = CertsToCheckEnum.values().length;
		assertEquals(expected, set.size(), "exam numbers must be unique. copy/paste error?");
	}
}
