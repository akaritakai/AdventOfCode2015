package net.akaritakai.aoc2015;

import com.google.common.collect.Collections2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Puzzle09 extends AbstractPuzzle {
    public Puzzle09(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public String solvePart1() {
        var locations = new Locations(getPuzzleInput());
        return String.valueOf(locations.getShortestTripLength());
    }

    @Override
    public String solvePart2() {
        var locations = new Locations(getPuzzleInput());
        return String.valueOf(locations.getLongestTripLength());
    }

    private static class Locations {
        private final Set<String> cities = new HashSet<>();
        private final Map<Trip, Integer> distances = new HashMap<>();

        public Locations(String input) {
            var pattern = Pattern.compile("^(\\S+) to (\\S+) = (\\d+)$");
            for (var line : input.split("\n")) {
                var matcher = pattern.matcher(line);
                if (matcher.find()) {
                    var source = matcher.group(1);
                    var destination = matcher.group(2);
                    var distance = Integer.parseInt(matcher.group(3));
                    cities.add(source);
                    cities.add(destination);
                    distances.put(new Trip(source, destination), distance);
                    distances.put(new Trip(destination, source), distance);
                }
            }
        }

        public int getShortestTripLength() {
            return getTripLength(Integer.MAX_VALUE, Math::min);
        }

        public int getLongestTripLength() {
            return getTripLength(Integer.MIN_VALUE, Math::max);
        }

        @SuppressWarnings("UnstableApiUsage")
        private int getTripLength(int startingLength, BiFunction<Integer, Integer, Integer> merger) {
            var bestTripLength = startingLength;
            for (var permutation : Collections2.permutations(cities)) {
                var currentTripLength = 0;
                for (var i = 0; i < permutation.size() - 1; i++) {
                    var source = permutation.get(i);
                    var destination = permutation.get(i + 1);
                    currentTripLength += distances.get(new Trip(source, destination));
                }
                bestTripLength = merger.apply(bestTripLength, currentTripLength);
            }
            return bestTripLength;
        }
    }

    private record Trip(String source, String destination) {
    }
}
