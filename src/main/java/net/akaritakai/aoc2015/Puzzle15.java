package net.akaritakai.aoc2015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Puzzle15 extends AbstractPuzzle {
    public Puzzle15(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 15;
    }

    @Override
    public String solvePart1() {
        var recipe = new Recipe(getPuzzleInput());
        return String.valueOf(recipe.findBestRecipeScore(false));
    }

    @Override
    public String solvePart2() {
        var recipe = new Recipe(getPuzzleInput());
        return String.valueOf(recipe.findBestRecipeScore(true));
    }

    private static class Recipe {
        private final List<String> ingredients = new ArrayList<>();
        private final List<Integer> capacities = new ArrayList<>();
        private final List<Integer> durabilites = new ArrayList<>();
        private final List<Integer> flavors = new ArrayList<>();
        private final List<Integer> textures = new ArrayList<>();
        private final List<Integer> calories = new ArrayList<>();

        public Recipe(String input) {
            var pattern = Pattern.compile(
                    "^(\\S+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)$");
            for (var line : input.split("\n")) {
                var matcher = pattern.matcher(line);
                if (matcher.find()) {
                    var ingredient = matcher.group(1);
                    ingredients.add(ingredient);
                    capacities.add(Integer.parseInt(matcher.group(2)));
                    durabilites.add(Integer.parseInt(matcher.group(3)));
                    flavors.add(Integer.parseInt(matcher.group(4)));
                    textures.add(Integer.parseInt(matcher.group(5)));
                    calories.add(Integer.parseInt(matcher.group(6)));
                }
            }
        }

        public long findBestRecipeScore(boolean limitCalories) {
            AtomicLong bestScore = new AtomicLong(Long.MIN_VALUE);
            searchRecipes(amounts -> {
                if (limitCalories && IntStream.range(0, amounts.size()).mapToLong(i -> (long) amounts.get(i) * calories.get(i)).sum() != 500) {
                    return;
                }
                var capacity = IntStream.range(0, amounts.size()).mapToLong(i -> (long) amounts.get(i) * capacities.get(i)).sum();
                var durability = IntStream.range(0, amounts.size()).mapToLong(i -> (long) amounts.get(i) * durabilites.get(i)).sum();
                var flavor = IntStream.range(0, amounts.size()).mapToLong(i -> (long) amounts.get(i) * flavors.get(i)).sum();
                var texture = IntStream.range(0, amounts.size()).mapToLong(i -> (long) amounts.get(i) * textures.get(i)).sum();
                var score = Math.max(0, capacity) * Math.max(0, durability) * Math.max(0, flavor) * Math.max(0, texture);
                bestScore.getAndUpdate(current -> Math.max(current, score));
            });
            return bestScore.get();
        }

        private void searchRecipes(RecipeHandler handler) {
            var amounts = new ArrayList<Integer>();
            for (var i = 0; i < ingredients.size(); i++) {
                amounts.add(0);
            }
            searchRecipes(amounts, 0, handler);
        }

        private void searchRecipes(List<Integer> amounts, int position, RecipeHandler handler) {
            var used = 0;
            for (var i = 0; i < position; i++) {
                used += amounts.get(i);
            }
            if (position == amounts.size() - 1) {
                amounts.set(position, 100 - used);
                handler.handle(amounts);
                return;
            }
            for (var i = 0; i <= 100 - used; i++) {
                amounts.set(position, i);
                searchRecipes(amounts, position + 1, handler);
            }
        }
    }

    private interface RecipeHandler {
        void handle(List<Integer> amounts);
    }
}
