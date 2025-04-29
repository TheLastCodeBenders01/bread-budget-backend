package com.thelastcodebenders.bread_budget_backend.utils;

import lombok.experimental.UtilityClass;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class CommonUtils {

    public static String extractTextFromPdf(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             PDDocument document = PDDocument.load(inputStream)) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    public static String extractBetweenTags(String input, String tagName) {
        String patternString = "<" + tagName + ">(.*?)</" + tagName + ">";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1); // Group 1 captures inside the tag
        } else {
            return input;
        }
    }

    public static String getStringBetween(String text, String start, String end) {
        int startIndex = text.indexOf(start);
        if (startIndex == -1) {
            return text;
        }
        startIndex += start.length();

        int endIndex = text.indexOf(end, startIndex);
        if (endIndex == -1) {
            return text; // end substring not found
        }

        return text.substring(startIndex, endIndex);
    }
}
