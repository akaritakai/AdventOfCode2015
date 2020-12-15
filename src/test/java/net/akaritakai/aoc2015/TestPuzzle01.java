package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle01 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle01("(())");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle01("()()");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle01("(((");
        Assert.assertEquals(puzzle.solvePart1(), "3");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle01("(()(()(");
        Assert.assertEquals(puzzle.solvePart1(), "3");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle01("))(((((");
        Assert.assertEquals(puzzle.solvePart1(), "3");
    }

    @Test
    public void testPart1Example6() {
        var puzzle = new Puzzle01("())");
        Assert.assertEquals(puzzle.solvePart1(), "-1");
    }

    @Test
    public void testPart1Example7() {
        var puzzle = new Puzzle01("))(");
        Assert.assertEquals(puzzle.solvePart1(), "-1");
    }

    @Test
    public void testPart1Example8() {
        var puzzle = new Puzzle01(")))");
        Assert.assertEquals(puzzle.solvePart1(), "-3");
    }

    @Test
    public void testPart1Example9() {
        var puzzle = new Puzzle01(")())())");
        Assert.assertEquals(puzzle.solvePart1(), "-3");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart1(), "232");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle01(")");
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle01("()())");
        Assert.assertEquals(puzzle.solvePart2(), "5");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle01(getStoredInput(1));
        Assert.assertEquals(puzzle.solvePart2(), "1783");
    }
}
