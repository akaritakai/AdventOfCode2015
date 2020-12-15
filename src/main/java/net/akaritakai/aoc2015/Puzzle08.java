package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.text.StringEscapeUtils;

public class Puzzle08 extends AbstractPuzzle {
    public Puzzle08(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public String solvePart1() {
        var result = getPuzzleInput().lines()
                .mapToInt(line -> line.length() - parse(line).length())
                .sum();
        return String.valueOf(result);
    }

    @Override
    public String solvePart2() {
        var result = getPuzzleInput().lines()
                .mapToInt(line -> escape(line).length() - line.length())
                .sum();
        return String.valueOf(result);
    }

    @VisibleForTesting
    static String parse(String s) {
        var sb = new StringBuilder();
        s = s.substring(1, s.length() - 1); // The first and last characters are quotes
        for (var i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if (c1 == '\\') {
                // Special handling
                char c2 = s.charAt(i + 1);

                // Case 1: An escaped '\'
                if (c2 == '\\') {
                    sb.append('\\');
                    i++;
                }
                // Case 2: An escaped '"'
                else if (c2 == '"') {
                    sb.append('"');
                    i++;
                }
                // Case 3: A hex character
                else if (c2 == 'x') {
                    sb.append((char) Integer.parseInt(s.substring(i + 2, i + 4), 16));
                    i += 3;
                }
            } else {
                sb.append(c1);
            }
        }
        return sb.toString();
    }

    @VisibleForTesting
    static String escape(String s) {
        return '"' + StringEscapeUtils.escapeJava(s) + '"';
    }
}
