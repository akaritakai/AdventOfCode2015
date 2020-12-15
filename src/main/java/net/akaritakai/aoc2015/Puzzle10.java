package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;

import java.util.regex.Pattern;

public class Puzzle10 extends AbstractPuzzle {
    public Puzzle10(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public String solvePart1() {
        var input = getPuzzleInput();
        for (var i = 0; i < 40; i++) {
            input = expand(input);
        }
        return String.valueOf(input.length());
    }

    @Override
    public String solvePart2() {
        var input = getPuzzleInput();
        for (var i = 0; i < 50; i++) {
            input = expand(input);
        }
        return String.valueOf(input.length());
    }

    private static final Pattern PATTERN = Pattern.compile("(\\d)\\1*");

    @VisibleForTesting
    static String expand(String input) {
        var sb = new StringBuilder();
        var matcher = PATTERN.matcher(input);
        while (matcher.find()){
            sb.append(matcher.group().length());
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }
}
