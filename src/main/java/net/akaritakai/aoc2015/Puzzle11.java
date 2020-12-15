package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Puzzle11 extends AbstractPuzzle {
    public Puzzle11(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public String solvePart1() {
        return nextPassword(getPuzzleInput().trim());
    }

    @Override
    public String solvePart2() {
        return nextPassword(nextPassword(getPuzzleInput().trim()));
    }

    private static String nextPassword(String s) {
        s = nextString(s);
        while (!isPassword(s)) {
            s = nextString(s);
        }
        return s;
    }

    @VisibleForTesting
    static boolean isPassword(String s) {
        return containsAtLeastThreeIncreasingStraightLetters(s)
                && doesNotContainConfusingLetters(s)
                && containsTwoDifferentNonOverlappingPairsOfLetters(s);
    }

    private static String nextString(String s) {
        var prefix = s.substring(0, s.length() - 1);
        var suffix = s.charAt(s.length() - 1);
        if (suffix == 'z') {
            return nextString(prefix) + 'a';
        } else if (suffix + 1 == 'i' || suffix + 1 == 'o' || suffix + 1 == 'l') {
            return prefix + (char) (suffix + 2);
        } else {
            return prefix + (char) (suffix + 1);
        }
    }

    private static boolean containsAtLeastThreeIncreasingStraightLetters(String s) {
        for (var i = 0; i < s.length() - 2; i++) {
            var c1 = s.charAt(i);
            var c2 = s.charAt(i + 1);
            var c3 = s.charAt(i + 2);
            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2) && Character.isAlphabetic(c3)
                    && c1 + 1 == c2 && c2 + 1 == c3) {
                return true;
            }
        }
        return false;
    }

    private static boolean doesNotContainConfusingLetters(String s) {
        return Pattern.compile("^[^iol]+$").asPredicate().test(s);
    }

    private static boolean containsTwoDifferentNonOverlappingPairsOfLetters(String s) {
        var pattern = Pattern.compile("([a-z])\\1");
        var matcher = pattern.matcher(s);
        var found = new HashSet<>();
        while (matcher.find()) {
            found.add(matcher.group(1));
        }
        return found.size() >= 2;
    }
}
