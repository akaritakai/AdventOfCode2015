package net.akaritakai.aoc2015;

import com.google.common.primitives.Booleans;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Puzzle06 extends AbstractPuzzle {
    public Puzzle06(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 6;
    }

    @Override
    public String solvePart1() {
        boolean[][] lights = new boolean[1000][1000];
        processInstructions((instruction, x, y) -> {
            switch (instruction) {
                case "turn on" -> lights[x][y] = true;
                case "turn off" -> lights[x][y] = false;
                case "toggle" -> lights[x][y] = !lights[x][y];
            }
        });
        var count = Arrays.stream(lights)
                .flatMap(row -> Booleans.asList(row).stream())
                .filter(light -> light)
                .count();
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        int[][] lights = new int[1000][1000];
        processInstructions((instruction, x, y) -> {
            switch (instruction) {
                case "turn on" -> lights[x][y] += 1;
                case "turn off" -> lights[x][y] = Math.max(0, lights[x][y] - 1);
                case "toggle" -> lights[x][y] += 2;
            }
        });
        var brightness = Arrays.stream(lights).flatMapToInt(Arrays::stream).sum();
        return String.valueOf(brightness);
    }

    private void processInstructions(InstructionHandler handler) {
        for (var line : getPuzzleInput().split("\n")) {
            var format = Pattern.compile("^(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)$");
            var matcher = format.matcher(line);
            if (!matcher.find()) {
                throw new IllegalStateException("Unable to process line: " + line);
            }
            var instruction = matcher.group(1);
            var x1 = Integer.parseInt(matcher.group(2));
            var y1 = Integer.parseInt(matcher.group(3));
            var x2 = Integer.parseInt(matcher.group(4));
            var y2 = Integer.parseInt(matcher.group(5));
            for (var x = x1; x <= x2; x++) {
                for (var y = y1; y <= y2; y++) {
                    handler.handle(instruction, x, y);
                }
            }
        }
    }

    private interface InstructionHandler {
        void handle(String instruction, int x, int y);
    }
}
