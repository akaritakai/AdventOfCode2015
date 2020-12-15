package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle12 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle12("[1,2,3]");
        Assert.assertEquals(puzzle.solvePart1(), "6");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle12("{\"a\":2,\"b\":4}");
        Assert.assertEquals(puzzle.solvePart1(), "6");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle12("[[[3]]]");
        Assert.assertEquals(puzzle.solvePart1(), "3");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle12("{\"a\":{\"b\":4},\"c\":-1}");
        Assert.assertEquals(puzzle.solvePart1(), "3");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle12("{\"a\":[-1,1]}");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example6() {
        var puzzle = new Puzzle12("[-1,{\"a\":1}]");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example7() {
        var puzzle = new Puzzle12("[]");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example8() {
        var puzzle = new Puzzle12("{}");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle12(getStoredInput(12));
        Assert.assertEquals(puzzle.solvePart1(), "156366");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle12("[1,2,3]");
        Assert.assertEquals(puzzle.solvePart2(), "6");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle12("[1,{\"c\":\"red\",\"b\":2},3]");
        Assert.assertEquals(puzzle.solvePart2(), "4");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle12("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}");
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle12("[1,\"red\",5]");
        Assert.assertEquals(puzzle.solvePart2(), "6");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle12(getStoredInput(12));
        Assert.assertEquals(puzzle.solvePart2(), "96852");
    }
}
