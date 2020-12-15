package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle15 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle15("""
                Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
                """);
        Assert.assertEquals(puzzle.solvePart1(), "62842880");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle15(getStoredInput(15));
        Assert.assertEquals(puzzle.solvePart1(), "21367368");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle15("""
                Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
                """);
        Assert.assertEquals(puzzle.solvePart2(), "57600000");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle15(getStoredInput(15));
        Assert.assertEquals(puzzle.solvePart2(), "1766400");
    }
}
