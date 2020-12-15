package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle06 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle06("turn on 0,0 through 999,999");
        Assert.assertEquals(puzzle.solvePart1(), "1000000");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle06("toggle 0,0 through 999,0");
        Assert.assertEquals(puzzle.solvePart1(), "1000");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle06("turn off 499,499 through 500,500");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle06(getStoredInput(6));
        Assert.assertEquals(puzzle.solvePart1(), "377891");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle06("turn on 0,0 through 0,0");
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle06("toggle 0,0 through 999,999");
        Assert.assertEquals(puzzle.solvePart2(), "2000000");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle06(getStoredInput(6));
        Assert.assertEquals(puzzle.solvePart2(), "14110788");
    }
}
