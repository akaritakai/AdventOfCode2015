package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle16 extends BasePuzzleTest {
    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart1(), "40");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle16(getStoredInput(16));
        Assert.assertEquals(puzzle.solvePart2(), "241");
    }
}
