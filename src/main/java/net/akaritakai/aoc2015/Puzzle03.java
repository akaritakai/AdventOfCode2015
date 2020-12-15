package net.akaritakai.aoc2015;

import com.google.common.collect.Sets;
import net.akaritakai.aoc2015.geom2d.Direction;
import net.akaritakai.aoc2015.geom2d.Point;

import java.util.HashSet;

import static net.akaritakai.aoc2015.geom2d.Direction.*;

public class Puzzle03 extends AbstractPuzzle {
    public Puzzle03(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public String solvePart1() {
        var houses = new HashSet<Point>();
        var santa = new Point(0, 0);
        houses.add(santa);
        for (var instruction : getPuzzleInput().toCharArray()) {
            santa = direction(instruction).move(santa);
            houses.add(santa);
        }
        return String.valueOf(houses.size());
    }

    @Override
    public String solvePart2() {
        var houses1 = new HashSet<Point>();
        var santa = new Point(0, 0);
        houses1.add(santa);

        var houses2 = new HashSet<Point>();
        var roboSanta = new Point(0, 0);
        houses2.add(roboSanta);

        for (var i = 0; i < getPuzzleInput().toCharArray().length; i++) {
            var instruction = getPuzzleInput().toCharArray()[i];
            var direction = direction(instruction);
            if (i % 2 == 0) {
                santa = direction.move(santa);
                houses1.add(santa);
            } else {
                roboSanta = direction.move(roboSanta);
                houses2.add(roboSanta);
            }
        }

        return String.valueOf(Sets.union(houses1, houses2).size());
    }

    private static Direction direction(char instruction) {
        return switch (instruction) {
            case '^' -> NORTH;
            case 'v' -> SOUTH;
            case '>' -> EAST;
            case '<' -> WEST;
            default -> throw new IllegalArgumentException("Not a valid instruction: " + instruction);
        };
    }
}
