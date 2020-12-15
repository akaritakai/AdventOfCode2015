package net.akaritakai.aoc2015;

public class Puzzle01 extends AbstractPuzzle {
    public Puzzle01(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public String solvePart1() {
        var sum = getPuzzleInput().chars()
                .map(i -> switch (i) {
                    case '(' -> 1;
                    case ')' -> -1;
                    default -> 0;
                })
                .sum();
        return String.valueOf(sum);
    }

    @Override
    public String solvePart2() {
        var floor = 0;
        var position = 0;
        while (floor >= 0) {
            switch (getPuzzleInput().charAt(position++)) {
                case '(' -> floor++;
                case ')' -> floor--;
            }
        }
        return String.valueOf(position);
    }
}
