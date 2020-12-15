package net.akaritakai.aoc2015;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Puzzle16 extends AbstractPuzzle {
    public Puzzle16(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 16;
    }

    @Override
    public String solvePart1() {
        var mfcsam = new MFCSAM(getPuzzleInput(), false);
        for (var i = 1; i <= 500; i++) {
            if (mfcsam.matchesEvidence(i)) {
                return String.valueOf(i);
            }
        }
        throw new IllegalStateException("Unable to solve puzzle given input");
    }

    @Override
    public String solvePart2() {
        var mfcsam = new MFCSAM(getPuzzleInput(), true);
        for (var i = 1; i <= 500; i++) {
            if (mfcsam.matchesEvidence(i)) {
                return String.valueOf(i);
            }
        }
        throw new IllegalStateException("Unable to solve puzzle given input");
    }

    private static class MFCSAM {
        private final Map<Integer, Map<String, Integer>> database = new HashMap<>();
        private final boolean hasOutdatedRetroencabulator;

        public MFCSAM(String input, boolean hasOutdatedRetroencabulator) {
            var pattern = Pattern.compile("^Sue (\\d+): (\\S+): (\\d+), (\\S+): (\\d+), (\\S+): (\\d+)$");
            for (var line : input.split("\n")) {
                var matcher = pattern.matcher(line);
                if (matcher.find()) {
                    var auntDb = database.computeIfAbsent(Integer.parseInt(matcher.group(1)), s -> new HashMap<>());
                    auntDb.put(matcher.group(2), Integer.parseInt(matcher.group(3)));
                    auntDb.put(matcher.group(4), Integer.parseInt(matcher.group(5)));
                    auntDb.put(matcher.group(6), Integer.parseInt(matcher.group(7)));
                }
            }
            this.hasOutdatedRetroencabulator = hasOutdatedRetroencabulator;
        }

        public boolean matchesEvidence(int aunt) {
            var db = database.get(aunt);
            var evidence = Map.of(
                    "children", 3,
                    "cats", 7,
                    "samoyeds", 2,
                    "pomeranians", 3,
                    "akitas", 0,
                    "vizslas", 0,
                    "goldfish", 5,
                    "tress", 3,
                    "cars", 2,
                    "perfumes", 1);
            for (var e : evidence.entrySet()) {
                if (hasOutdatedRetroencabulator) {
                    if (e.getKey().equals("cats") || e.getKey().equals("trees")) {
                        if (db.containsKey(e.getKey()) && db.get(e.getKey()) <= e.getValue()) {
                            return false;
                        }
                    } else if (e.getKey().equals("pomeranians") || e.getKey().equals("goldfish")) {
                        if (db.containsKey(e.getKey()) && db.get(e.getKey()) >= e.getValue()) {
                            return false;
                        }
                    } else {
                        if (db.containsKey(e.getKey()) && !db.get(e.getKey()).equals(e.getValue())) {
                            return false;
                        }
                    }
                } else {
                    if (db.containsKey(e.getKey()) && !db.get(e.getKey()).equals(e.getValue())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
