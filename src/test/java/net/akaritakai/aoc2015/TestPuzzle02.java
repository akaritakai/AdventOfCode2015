package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle02 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle02("2x3x4");
        Assert.assertEquals(puzzle.solvePart1(), "58");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle02("1x1x10");
        Assert.assertEquals(puzzle.solvePart1(), "43");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle02(getStoredInput(2));
        Assert.assertEquals(puzzle.solvePart1(), "1586300");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle02("2x3x4");
        Assert.assertEquals(puzzle.solvePart2(), "34");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle02("1x1x10");
        Assert.assertEquals(puzzle.solvePart2(), "14");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle02(getStoredInput(2));
        Assert.assertEquals(puzzle.solvePart2(), "3737498");
    }
}
