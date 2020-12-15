package net.akaritakai.aoc2015;

import org.apache.commons.codec.digest.DigestUtils;

public class Puzzle04 extends AbstractPuzzle {
    public Puzzle04(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(findHash("00000"));
    }

    @Override
    public String solvePart2() {
        return String.valueOf(findHash("000000"));
    }

    private int findHash(String prefix) {
        var i = 1;
        while (true) {
            String input = getPuzzleInput().trim() + i;
            String hash = DigestUtils.md5Hex(input);
            if (hash.startsWith(prefix)) {
                break;
            }
            i++;
        }
        return i;
    }
}
