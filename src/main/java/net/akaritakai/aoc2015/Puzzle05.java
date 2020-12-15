package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;

import java.util.regex.Pattern;

public class Puzzle05 extends AbstractPuzzle {
    public Puzzle05(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public String solvePart1() {
        var count = getPuzzleInput().lines()
                .filter(line -> containsAtLeastThreeVowels(line)
                        && containsAtLeastOneLetterRepeatedTwiceInARow(line)
                        && doesNotContainSpecialStrings(line))
                .count();
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        var count = getPuzzleInput().lines()
                .filter(line -> containsPairThatDoesNotOverlap(line)
                        && containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem(line))
                .count();
        return String.valueOf(count);
    }

    @VisibleForTesting
    static boolean containsAtLeastThreeVowels(String s) {
        return Pattern.compile("[aeiou].*[aeiou].*[aeiou]").asPredicate().test(s);
    }

    @VisibleForTesting
    static boolean containsAtLeastOneLetterRepeatedTwiceInARow(String s) {
        return Pattern.compile("(\\S)\\1").asPredicate().test(s);
    }

    @VisibleForTesting
    static boolean doesNotContainSpecialStrings(String s) {
        return !s.contains("ab") && !s.contains("cd") && !s.contains("pq") && !s.contains("xy");
    }

    @VisibleForTesting
    static boolean containsPairThatDoesNotOverlap(String s) {
        return Pattern.compile("(\\S\\S).*\\1").asPredicate().test(s);
    }

    @VisibleForTesting
    static boolean containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem(String s) {
        return Pattern.compile("(\\S)\\S\\1").asPredicate().test(s);
    }
}
