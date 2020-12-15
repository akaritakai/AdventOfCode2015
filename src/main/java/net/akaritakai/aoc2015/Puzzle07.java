package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Puzzle07 extends AbstractPuzzle {
    public Puzzle07(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public String solvePart1() {
        var circuit = new Circuit(getPuzzleInput());
        return String.valueOf(circuit.wire("a"));
    }

    @Override
    public String solvePart2() {
        var circuit = new Circuit(getPuzzleInput());
        var a = circuit.wire("a");
        circuit.wires.clear();
        circuit.wires.put("b", a);
        circuit.compute();
        return String.valueOf(circuit.wire("a"));
    }

    @VisibleForTesting
    static class Circuit {
        // Contains the result of the wire
        private final Map<String, Integer> wires = new HashMap<>();

        // Contains the calculator
        private final Map<String, Supplier<Integer>> gates = new HashMap<>();

        private static final Pattern PASSTHROUGH_PATTERN = Pattern.compile("^(\\S+) -> (\\S+)$");
        private static final Pattern AND_PATTERN = Pattern.compile("^(\\S+) AND (\\S+) -> (\\S+)$");
        private static final Pattern LSHIFT_PATTERN = Pattern.compile("^(\\S+) LSHIFT (\\S+) -> (\\S+)$");
        private static final Pattern NOT_PATTERN = Pattern.compile("^NOT (\\S+) -> (\\S+)$");
        private static final Pattern OR_PATTERN = Pattern.compile(("^(\\S+) OR (\\S+) -> (\\S+)$"));
        private static final Pattern RSHIFT_PATTERN = Pattern.compile("^(\\S+) RSHIFT (\\S+) -> (\\S+)$");

        public Circuit(String instructions) {
            for (var instruction : instructions.split("\n")) {
                if (PASSTHROUGH_PATTERN.asPredicate().test(instruction)) {
                    var matcher = PASSTHROUGH_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(2), operand(matcher.group(1)));
                    }
                } else if (AND_PATTERN.asPredicate().test(instruction)) {
                    var matcher = AND_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(3), () -> {
                            var input1 = operand(matcher.group(1)).get();
                            var input2 = operand(matcher.group(2)).get();
                            return (input1 == null || input2 == null) ? null : input1 & input2;
                        });
                    }
                } else if (LSHIFT_PATTERN.asPredicate().test(instruction)) {
                    var matcher = LSHIFT_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(3), () -> {
                            var input1 = operand(matcher.group(1)).get();
                            var input2 = operand(matcher.group(2)).get();
                            return (input1 == null || input2 == null) ? null : input1 << input2;
                        });
                    }
                } else if (NOT_PATTERN.asPredicate().test(instruction)) {
                    var matcher = NOT_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(2), () -> {
                            var input = operand(matcher.group(1)).get();
                            return (input == null) ? null : ~input;
                        });
                    }
                } else if (OR_PATTERN.asPredicate().test(instruction)) {
                    var matcher = OR_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(3), () -> {
                            var input1 = operand(matcher.group(1)).get();
                            var input2 = operand(matcher.group(2)).get();
                            return (input1 == null || input2 == null) ? null : input1 | input2;
                        });
                    }
                } else if (RSHIFT_PATTERN.asPredicate().test(instruction)) {
                    var matcher = RSHIFT_PATTERN.matcher(instruction);
                    if (matcher.find()) {
                        gates.put(matcher.group(3), () -> {
                            var input1 = operand(matcher.group(1)).get();
                            var input2 = operand(matcher.group(2)).get();
                            return (input1 == null || input2 == null) ? null : input1 >>> input2;
                        });
                    }
                }
            }
            compute();
        }

        public Integer wire(String name) {
            return Short.toUnsignedInt(wires.get(name).shortValue());
        }

        private void compute() {
            // Check if there are wires we don't know the results for
            while (wires.size() < gates.size()) {
                // There are still some wires we don't know the results of, so add them if possible
                for (var name : Sets.difference(gates.keySet(), wires.keySet())) {
                    var result = gates.get(name).get();
                    if (result != null) {
                        wires.put(name, result);
                    }
                }
            }
        }

        private Supplier<Integer> operand(String s) {
            try {
                var i = Integer.parseInt(s);
                return () -> i;
            } catch (Exception e) {
                return () -> wires.get(s);
            }
        }
    }
}
