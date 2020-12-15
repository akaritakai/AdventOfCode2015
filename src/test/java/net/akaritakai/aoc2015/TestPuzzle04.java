package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle04 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle04("abcdef");
        Assert.assertEquals(puzzle.solvePart1(), "609043");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle04("pqrstuv");
        Assert.assertEquals(puzzle.solvePart1(), "1048970");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle04(getStoredInput(4));
        Assert.assertEquals(puzzle.solvePart1(), "117946");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle04(getStoredInput(4));
        Assert.assertEquals(puzzle.solvePart2(), "3938038");
    }
}
