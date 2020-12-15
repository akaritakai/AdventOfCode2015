package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle19 extends AbstractPuzzle {
    public Puzzle19(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 19;
    }

    @Override
    public String solvePart1() {
        var plant = new Plant(getPuzzleInput());
        return String.valueOf(plant.countReplacements());
    }

    @Override
    public String solvePart2() {
        var plant = new Plant(getPuzzleInput());
        return String.valueOf(plant.findMinDistance());
    }

    @VisibleForTesting
    static class Plant {
        private String input;
        private final Grammar grammar;
        private final Map<String, Set<String>> replacements = new HashMap<>();

        public Plant(String plantInput) {
            for (var line : plantInput.split("\n")) {
                if (line.contains(" => ")) {
                    var lhs = line.split(" => ")[0];
                    var rhs = line.split(" => ")[1];
                    replacements.computeIfAbsent(lhs.trim(), s -> new HashSet<>()).add(rhs.trim());
                } else if (!line.isBlank()) {
                    this.input = line.trim();
                }
            }
            grammar = new Grammar(plantInput);
        }

        public long countReplacements() {
            var set = new HashSet<String>();
            replacements().forEach(replacement -> {
                var matcher = Pattern.compile(replacement.from).matcher(input);
                while (matcher.find()) {
                    set.add(input.substring(0, matcher.start()) + replacement.to + input.substring(matcher.end()));
                }
            });
            return set.size();
        }

        public int findMinDistance() {
            var input = tokenize(SymbolType.NON_TERMINAL, this.input);
            var cyk = new Cyk(grammar, input);
            return cyk.findDistance();
        }

        private Set<Replacement> replacements() {
            return replacements.entrySet()
                    .stream()
                    .flatMap(e -> e.getValue().stream().map(v -> new Replacement(e.getKey(), v)))
                    .collect(Collectors.toSet());
        }
    }

    private record Replacement(String from, String to) {
    }

    private static class Grammar {
        private static final Symbol START_SYMBOL = new Symbol(SymbolType.NON_TERMINAL, null);
        private final Set<Symbol> originalLhs = new HashSet<>();
        private final Map<Symbol, Set<List<Symbol>>> rules = new HashMap<>();

        public Grammar(String rulesString) {
            for (var line : rulesString.split("\n")) {
                generateOriginalLhs(line);
                generateRule(line);
            }
        }

        private Map<List<Symbol>, Set<Symbol>> reverseRules() {
            var map = new HashMap<List<Symbol>, Set<Symbol>>();
            rules.forEach((k, values) -> values.forEach(v -> map.computeIfAbsent(v, s -> new HashSet<>()).add(k)));
            return map;
        }

        private void generateOriginalLhs(String ruleString) {
            if (ruleString.contains(" => ")) {
                originalLhs.add(joinSymbols(tokenize(SymbolType.NON_TERMINAL, ruleString.split(" => ")[0])));
            }
        }

        private void generateRule(String ruleString) {
            if (ruleString.contains(" => ")) {
                var lhsT = tokenize(SymbolType.TERMINAL, ruleString.split(" => ")[0]);
                var rhsT = tokenize(SymbolType.TERMINAL, ruleString.split(" => ")[1]);
                var lhsNT = tokenize(SymbolType.NON_TERMINAL, ruleString.split(" => ")[0]);
                var rhsNT = tokenize(SymbolType.NON_TERMINAL, ruleString.split(" => ")[1]);

                for (var terminal : lhsT) {
                    rules.computeIfAbsent(new Symbol(SymbolType.NON_TERMINAL, terminal.symbol),
                            s -> new HashSet<>()).add(List.of(terminal));
                }
                for (var terminal : rhsT) {
                    rules.computeIfAbsent(new Symbol(SymbolType.NON_TERMINAL, terminal.symbol),
                            s -> new HashSet<>()).add(List.of(terminal));
                }

                if (lhsNT.size() > 1) {
                    var newLhs = joinSymbols(lhsNT);
                    generateRule(newLhs, lhsNT);
                    generateRule(newLhs, rhsNT);
                } else {
                    generateRule(lhsNT.get(0), rhsNT);
                }
            }
        }

        private void generateRule(Symbol lhs, List<Symbol> rhs) {
            if (rhs.size() <= 2) {
                rules.computeIfAbsent(lhs, s -> new HashSet<>()).add(rhs);
            } else {
                var nextRhs = rhs.subList(1, rhs.size());
                var nextLhs = joinSymbols(nextRhs);
                rules.computeIfAbsent(lhs, s -> new HashSet<>()).add(List.of(rhs.get(0), nextLhs));
                generateRule(nextLhs, nextRhs);
            }
        }

        private static Symbol joinSymbols(List<Symbol> symbols) {
            var sb = new StringBuilder();
            for (var symbol : symbols) {
                sb.append(symbol.symbol);
            }
            return new Symbol(SymbolType.NON_TERMINAL, sb.toString());
        }


    }

    private record Symbol(SymbolType type, String symbol) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Symbol symbol1 = (Symbol) o;
            return type == symbol1.type &&
                    Objects.equals(symbol, symbol1.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, symbol);
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    private enum SymbolType {
        TERMINAL,
        NON_TERMINAL
    }

    private static List<Symbol> tokenize(SymbolType type, String input) {
        if (input.equals("e") && type == SymbolType.TERMINAL) {
            return List.of();
        }
        if (input.equals("e") && type == SymbolType.NON_TERMINAL) {
            return List.of(Grammar.START_SYMBOL);
        }
        var tokens = new ArrayList<Symbol>();
        var matcher = Pattern.compile("([A-Z][a-z]*)").matcher(input);
        while (matcher.find()) {
            tokens.add(new Symbol(type, matcher.group(1)));
        }
        return tokens;
    }

    private static class Cyk {
        private final Map<Integer, Map<Integer, Set<Symbol>>> cyk = new HashMap<>();
        private final Map<Integer, Map<Integer, Set<Ref>>> backref = new HashMap<>();
        private final List<Symbol> input;
        private final Grammar grammar;

        public Cyk(Grammar grammar, List<Symbol> input) {
            this.input = input;
            this.grammar = grammar;
            var rules = grammar.reverseRules();
            for (var x = 0; x < input.size(); x++) {
                cyk(0, x).add(input.get(x));
            }
            for (var y = 1; y < input.size(); y++) {
                for (var x = 0; x < input.size() - y; x++) {
                    for (var i = 0; i < y; i++) {
                        for (var need : Sets.cartesianProduct(cyk(i,x), cyk(y-i-1, x+i+1))) {
                            for (var rule : rules.getOrDefault(need, Set.of())) {
                                cyk(y, x).add(rule);
                                backref(y, x).add(new Ref(x, i, x+i+1, y-i-1, need));
                            }
                        }
                    }
                }
            }
        }

        private Set<Symbol> cyk(int y, int x) {
            return cyk.computeIfAbsent(y, s -> new HashMap<>()).computeIfAbsent(x, s -> new HashSet<>());
        }

        private Set<Ref> backref(int y, int x) {
            return backref.computeIfAbsent(y, s -> new HashMap<>()).computeIfAbsent(x, s -> new HashSet<>());
        }

        public int findDistance() {
            return 1 + findDistance(0, input.size() - 1);
        }

        private int findDistance(int x, int y) {
            if (y == 0) {
                return 0;
            }
            var ref = backref(y, x).stream().findAny().orElse(null);
            if (ref == null) {
                return 0;
            }
            if (cyk(y, x).equals(Set.of(Grammar.START_SYMBOL))) {
                return findDistance(ref.x1, ref.y1) + findDistance(ref.x2, ref.y2);
            } else if (cyk(y, x).stream().noneMatch(grammar.originalLhs::contains)) {
                return findDistance(ref.x1, ref.y1) + findDistance(ref.x2, ref.y2);
            } else {
                return 1 + findDistance(ref.x1, ref.y1) + findDistance(ref.x2, ref.y2);
            }
        }
    }

    private record Ref(int x1, int y1, int x2, int y2, List<Symbol> n) {
    }
}
