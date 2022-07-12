package com.app.test;

import java.util.ArrayList;
import java.util.regex.Pattern;

/*
Write a program that parses a sentence and replaces each word with the following:
1) The first letter of the word
2) The number of distinct characters between first and last character
3) The last letter of the word.
For example, Smooth would become S3h.
Words are separated by spaces or non-alphabetic characters and these separators should be maintained in their original form and location in the answer.
A few of the things we will be looking at is accuracy, efficiency, solution completeness.
*/

public class Interview {

  public static void main(String[] args) {
    String output = "Creativity is thinking-up new things. Innovation is doing new things!";
    String response = wordParser(output);
    System.out.println(response);
  }

  public static String wordParser(String input) {
    StringBuilder stringBuilder = new StringBuilder();
    ArrayList<String> formatSentence = new ArrayList<>();

    for (int i = 0; i < input.length(); i++) {
      if (!Character.isAlphabetic(input.charAt(i))) {

        int wordLength = stringBuilder.length();
        stringBuilder = new StringBuilder(wordParserSentence(stringBuilder.toString()));
        while (stringBuilder.length() <= wordLength) {
          stringBuilder.append(" ");
        }
        stringBuilder.setCharAt(wordLength, input.charAt(i));
        formatSentence.add(stringBuilder.toString());
        stringBuilder.setLength(0);

      } else if (Character.isAlphabetic(input.charAt(i))) {
        stringBuilder.append(input.charAt(i));
      }
    }

    return formatSentence(formatSentence);
  }

  private static String wordParserSentence(String input) {
    ArrayList<Character> duplicateCharsList = new ArrayList<>();
    int distinctCharCount = 0;
    if (input.length() <= 2)
      return input;

    for (int i = 1; i < input.length() - 1; i++) {
      if (!duplicateCharsList.contains(input.charAt(i))) {
        duplicateCharsList.add(input.charAt(i));
        distinctCharCount++;
      }
    }
    return (input.charAt(0) + Integer.toString(distinctCharCount) + input.charAt(input.length() - 1));
  }

  private static String formatSentence(ArrayList<String> sentenceList) {
    StringBuilder resultSentence = new StringBuilder();
    for (String word : sentenceList) {
      word = word.replaceAll("[\\s|\\u00A0]+", "");
      resultSentence.append(word);
      if (Pattern.matches("[a-zA-Z\\d]+", word)) {
        resultSentence.append(" ");
      }
    }

    return resultSentence.toString();
  }

}
