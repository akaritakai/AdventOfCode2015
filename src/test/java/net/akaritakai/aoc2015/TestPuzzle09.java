package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle09 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle09("""
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """);
        Assert.assertEquals(puzzle.solvePart1(), "605");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle09(getStoredInput(9));
        Assert.assertEquals(puzzle.solvePart1(), "141");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle09("""
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """);
        Assert.assertEquals(puzzle.solvePart2(), "982");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle09(getStoredInput(9));
        Assert.assertEquals(puzzle.solvePart2(), "736");
    }
}
