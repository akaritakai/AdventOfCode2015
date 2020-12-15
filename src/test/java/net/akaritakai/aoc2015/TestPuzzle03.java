package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle03 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle03(">");
        Assert.assertEquals(puzzle.solvePart1(), "2");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle03("^>v<");
        Assert.assertEquals(puzzle.solvePart1(), "4");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle03("^v^v^v^v^v");
        Assert.assertEquals(puzzle.solvePart1(), "2");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle03(getStoredInput(3));
        Assert.assertEquals(puzzle.solvePart1(), "2081");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle03("^v");
        Assert.assertEquals(puzzle.solvePart2(), "3");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle03("^>v<");
        Assert.assertEquals(puzzle.solvePart2(), "3");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle03("^v^v^v^v^v");
        Assert.assertEquals(puzzle.solvePart2(), "11");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle03(getStoredInput(3));
        Assert.assertEquals(puzzle.solvePart2(), "2341");
    }
}
