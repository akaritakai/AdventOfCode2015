package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle17 extends AbstractPuzzle {
    public Puzzle17(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 17;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(numCombinations(containers(), 150));
    }

    @Override
    public String solvePart2() {
        var i = 1;
        while (true) {
            var combinations = numCombinations(containers(), 150, i++);
            if (combinations > 0) {
                return String.valueOf(combinations);
            }
        }
    }

    @VisibleForTesting
    static long numCombinations(Set<Container> containers, int amount) {
        // First let's find out the limit on how many containers we would need in the worst case
        var orderedSizes = containers.stream().map(Container::size).sorted().collect(Collectors.toList());
        var upperBound = 0;
        var sum = 0;
        do {
            sum += orderedSizes.get(upperBound++);
        } while (sum <= amount);

        // So we don't need to check any combinations larger than upperBound
        // Now just iterate through all the combinations until we find what we're looking for:
        return IntStream.range(1, upperBound)
                .mapToLong(size -> numCombinations(containers, amount, size))
                .sum();
    }

    @VisibleForTesting
    @SuppressWarnings("UnstableApiUsage")
    static long numCombinations(Set<Container> containers, int amount, int size) {
        return Sets.combinations(containers, size)
                .stream()
                .filter(set -> set.stream().mapToInt(Container::size).sum() == amount)
                .count();
    }

    private Set<Container> containers() {
        var containers = new HashSet<Container>();
        var lines = getPuzzleInput().split("\n");
        for (var i = 0; i < lines.length; i++) {
            containers.add(new Container(i, Integer.parseInt(lines[i])));
        }
        return containers;
    }

    @VisibleForTesting
    record Container(int position, int size) {
    }
}
