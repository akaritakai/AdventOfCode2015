package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class TestPuzzle17 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var containers = Set.of(
                new Puzzle17.Container(0, 20),
                new Puzzle17.Container(1, 15),
                new Puzzle17.Container(2, 10),
                new Puzzle17.Container(3, 5),
                new Puzzle17.Container(4, 5));
        Assert.assertEquals(Puzzle17.numCombinations(containers, 25), 4);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle17(getStoredInput(17));
        Assert.assertEquals(puzzle.solvePart1(), "1304");
    }

    @Test
    public void testPart1Example2() {
        var containers = Set.of(
                new Puzzle17.Container(0, 20),
                new Puzzle17.Container(1, 15),
                new Puzzle17.Container(2, 10),
                new Puzzle17.Container(3, 5),
                new Puzzle17.Container(4, 5));
        Assert.assertEquals(Puzzle17.numCombinations(containers, 25, 2), 3);
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle17(getStoredInput(17));
        Assert.assertEquals(puzzle.solvePart2(), "18");
    }
}
