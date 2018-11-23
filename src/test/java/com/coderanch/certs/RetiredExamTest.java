package com.coderanch.certs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RetiredExamTest {

    @Test
    void notYetExpired() {
        LocalDate expectedExpiration = LocalDate.of(3000, Month.DECEMBER, 31);
        RetiredExam exam = RetiredExam.parseFromWeb("", "Dec 31, 3000");
        assertEquals(expectedExpiration, exam.getExpiration(), "expires");
        assertFalse(exam.isExpired(), "exam expiration date not reached");
    }

    @Test
    void alreadyExpired() {
        LocalDate expectedExpiration = LocalDate.of(2015, Month.MARCH, 5);
        RetiredExam exam = RetiredExam.parseFromWeb("", "March 5, 2015");
        assertEquals(expectedExpiration, exam.getExpiration(), "expires");
        assertTrue(exam.isExpired(), "exam expiration date not reached");
    }

    @Test
    void trailingWhitespace() {
        LocalDate expectedExpiration = LocalDate.of(3000, Month.DECEMBER, 31);
        RetiredExam exam = RetiredExam.parseFromWeb("", "Dec 31, 3000   ");
        assertEquals(expectedExpiration, exam.getExpiration(), "expires");
        assertFalse(exam.isExpired(), "exam expiration date not reached");
    }
    // ---------------------------------------------------------------------------
    @Test
    void examNumberFirst() {
        String examText = "1Z0-803 | Java SE 7 Programmer I (retiring Dec 31, 2018)";
        RetiredExam exam = RetiredExam.parseFromWeb(examText, "March 15, 2015");
        assertEquals("1Z0-803", exam.getExamNumber(), "exam #");
        assertTrue(exam.isExamInCertsToCheckEnum(), "in enum");
    }

    @Test
    void examNumberInParensWithPipe() {
        String examText = "Oracle Big Data 2017 Implementation Essentials | (1Z0-449)";
        RetiredExam exam = RetiredExam.parseFromWeb(examText, "March 15, 2015");
        assertEquals("1Z0-449", exam.getExamNumber(), "exam #");
        assertFalse(exam.isExamInCertsToCheckEnum(), "in enum");
    }

    @Test
    void examNumberInParensWithoutPipe() {
        String examText = "Oracle Big Data 2017 Implementation Essentials (1Z0-449)";
        RetiredExam exam = RetiredExam.parseFromWeb(examText, "March 15, 2015");
        assertEquals("1Z0-449", exam.getExamNumber(), "exam #");
        assertFalse(exam.isExamInCertsToCheckEnum(), "in enum");
    }

    @Test
    void examNumberAtEndWithPipe() {
        String examText = "PeopleSoft General Ledger 9 | 1Z0-228";
        RetiredExam exam = RetiredExam.parseFromWeb(examText, "March 15, 2015");
        assertEquals("1Z0-228", exam.getExamNumber(), "exam #");
        assertFalse(exam.isExamInCertsToCheckEnum(), "in enum");
    }
    // ---------------------------------------------------------------------------

    @Test
    void parseFromCsv() throws IOException {
        Set<RetiredExam> actual = RetiredExam.parseFromCsv("src/test/resources/sample-retirement.csv");
        assertEquals(1, actual.size(), "# retired exams");
        RetiredExam actualExam = actual.iterator().next();
        assertEquals("1Z0-803", actualExam.getExamNumber(), "examp #");
        assertEquals(LocalDate.of(2018, Month.DECEMBER, 31), actualExam.getExpiration(), "expires");
    }

    // ---------------------------------------------------------------------------

    @Test
    void asString() {
        LocalDate expectedExpiration = LocalDate.of(2015, Month.MARCH, 5);
        RetiredExam exam = RetiredExam.parseFromWeb("1Z0-803", "March 5, 2015");
        String actual = exam.toString();
        assertEquals("1Z0-803", actual);
    }

    // ---------------------------------------------------------------------------

    @Test
    void noExamsInListPastRetirementDate() throws IOException {
        Set<RetiredExam> allExams = RetiredExam.parseFromCsv("src/main/resources/pending-retirement.csv");
        Set<String> expired = allExams.stream()
                .filter(RetiredExam::isExpired)
                .map((RetiredExam::getExamNumber))
                .collect(Collectors.toSet());
        assertEquals(0, expired.size(), "Remove expired retirements fro pending-retirement.csv: " + expired);

    }

}

