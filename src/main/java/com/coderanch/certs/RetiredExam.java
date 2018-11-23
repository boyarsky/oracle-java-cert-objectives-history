package com.coderanch.certs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Collectors;

public class RetiredExam {

    private String examNumber;
    private LocalDate expirationDate;

    private RetiredExam(String examNumber, LocalDate expirationDate) {
        this.examNumber = examNumber;
        this.expirationDate = expirationDate;
    }

    public static RetiredExam parseFromWeb(String examText, String expiresDescription) {
        String number = parseExamNumberFromWeb(examText);
        LocalDate date = parseRetiresDateFromWeb(expiresDescription.trim());
        return new RetiredExam(number, date);
    }

    private static String parseExamNumberFromWeb(String examText) {
        if (examText.startsWith("1Z0")) {
            return examText.replaceFirst(" .*$", "");
        }
        // remove everything prior to exam number
        String endingPart = examText.replaceFirst("^.*1Z0", "1Z0");
        // remove trailing close paren
        return endingPart.replace(")", "");
    }

    private static LocalDate parseRetiresDateFromWeb(String expiresDescription) {
        DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        try {
            return LocalDate.parse(expiresDescription, shortFormatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(expiresDescription, longFormatter);
        }
    }

    public static Set<RetiredExam> parseFromCsv(String csvFileLocation) throws IOException {
        Path path = Paths.get(csvFileLocation);
        return Files.readAllLines(path)
                .stream()
                // skip header line
                .skip(1)
                // skip commented lines
                .filter(line -> ! line.startsWith("#"))
                .map(line -> line.split(","))
                .map(arr -> new RetiredExam(arr[0], LocalDate.parse(arr[1])))
                .collect(Collectors.toSet());

    }

    public boolean isExpired() {
        LocalDate now = LocalDate.now();
        return now.isAfter(expirationDate);
    }

    public LocalDate getExpiration() {
        return expirationDate;
    }

    public String getExamNumber() {
        return examNumber;
    }

    public boolean isExamInCertsToCheckEnum() {
        Set<String> checkedExamNumbers = CertsToCheckEnum.getSetOfExamNumbers();
        return checkedExamNumbers.contains(examNumber);
    }

    @Override
    public String toString() {
        return examNumber;
    }
}
