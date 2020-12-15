package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableListMultimap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Puzzle14 extends AbstractPuzzle {
    public Puzzle14(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 14;
    }

    @Override
    public String solvePart1() {
        var distance = getDistancesTraveled(2503).values().stream().mapToLong(i -> i).max().orElseThrow();
        return String.valueOf(distance);
    }

    @Override
    public String solvePart2() {
        var points = getPointsEarned(2503).values().stream().mapToLong(i -> i).max().orElseThrow();
        return String.valueOf(points);
    }

    @VisibleForTesting
    Map<String, Long> getDistancesTraveled(int totalTime) {
        var distances = new HashMap<String, Long>();
        handleInput(totalTime, (reindeer, speed, speedTime, restTime, total) ->
                distances.put(reindeer, calculateDistance(speed, speedTime, restTime, total)));
        return distances;
    }

    @VisibleForTesting
    Map<String, Long> getPointsEarned(int totalTime) {
        var points = new HashMap<String, Long>();
        for (var i = 1; i <= totalTime; i++) {
            // Get the map of reindeer -> distance traveled for the current time 'i'
            getDistancesTraveled(i).entrySet().stream()
                    // Arrange a mapping of distance -> reindeer at that distance
                    .collect(ImmutableListMultimap.toImmutableListMultimap(Map.Entry::getValue, Map.Entry::getKey))
                    .asMap().entrySet().stream()
                    // Select the largest distance traveled
                    .max(Comparator.comparingLong(Map.Entry::getKey))
                    // Get the reindeer associated with that distance
                    .stream().flatMap(e -> e.getValue().stream())
                    // Give each of them a point
                    .forEach(r -> points.compute(r, (k, v) -> (v == null) ? 1 : v + 1));
        }
        return points;
    }

    @VisibleForTesting
    static long calculateDistance(int speed, int speedTime, int restTime, int totalTime) {
        var fullPeriods = totalTime / (speedTime + restTime);
        var remainingSeconds = totalTime - (fullPeriods * (speedTime + restTime));
        return (long) speed * speedTime * fullPeriods + (long) speed * Math.min(remainingSeconds, speedTime);
    }

    void handleInput(int totalTime, ReindeerEntryHandler handler) {
        var pattern = Pattern.compile("^(\\S+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.$");
        for (var line : getPuzzleInput().split("\n")) {
            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                var reindeer = matcher.group(1);
                var speed = Integer.parseInt(matcher.group(2));
                var speedTime = Integer.parseInt(matcher.group(3));
                var restTime = Integer.parseInt(matcher.group(4));
                handler.handle(reindeer, speed, speedTime, restTime, totalTime);
            }
        }
    }

    private interface ReindeerEntryHandler {
        void handle(String reindeer, int speed, int speedTime, int restTime, int totalTime);
    }
}
