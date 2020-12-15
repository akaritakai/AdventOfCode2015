package net.akaritakai.aoc2015;

import com.google.common.collect.Collections2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Puzzle13 extends AbstractPuzzle {
    public Puzzle13(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 13;
    }

    @Override
    public String solvePart1() {
        var table = new Table(getPuzzleInput(), false);
        return String.valueOf(table.findOptimalHappiness());
    }

    @Override
    public String solvePart2() {
        var table = new Table(getPuzzleInput(), true);
        return String.valueOf(table.findOptimalHappiness());
    }

    private static class Table {
        private final Set<String> participants = new HashSet<>();
        private final Map<Preference, Integer> preferences = new HashMap<>();

        public Table(String input, boolean includeSelf) {
            var pattern = Pattern.compile("^(\\S+) would (\\S+) (\\d+) happiness units by sitting next to (\\S+).$");
            for (var line : input.split("\n")) {
                var matcher = pattern.matcher(line);
                if (matcher.find()) {
                    var person = matcher.group(1);
                    var sign = matcher.group(2).equals("gain") ? 1 : -1;
                    var amount = Integer.parseInt(matcher.group(3)) * sign;
                    var partner = matcher.group(4);
                    participants.add(person);
                    participants.add(partner);
                    preferences.put(new Preference(person, partner), amount);
                }
            }
            if (includeSelf) {
                for (var participant : participants) {
                    preferences.put(new Preference("", participant), 0);
                    preferences.put(new Preference(participant, ""), 0);
                }
                participants.add("");
            }
        }

        @SuppressWarnings("UnstableApiUsage")
        public int findOptimalHappiness() {
            var mostHappiness = Integer.MIN_VALUE;
            for (var permutation : Collections2.permutations(participants)) {
                var currentHappiness = 0;
                for (var i = 0; i < permutation.size(); i++) {
                    var person = permutation.get(i);
                    var partner = permutation.get((i + 1) % permutation.size());
                    currentHappiness += preferences.get(new Preference(person, partner));
                }
                for (var i = permutation.size() - 1; i >= 0; i--) {
                    var person = permutation.get(i);
                    var partner = i == 0 ? permutation.get(permutation.size() - 1) : permutation.get(i - 1);
                    currentHappiness += preferences.get(new Preference(person, partner));
                }
                mostHappiness = Math.max(mostHappiness, currentHappiness);
            }
            return mostHappiness;
        }
    }

    private record Preference(String person, String partner) {
    }
}
