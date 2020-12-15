package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle19 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle19("""
                H => HO
                H => OH
                O => HH
                
                HOH
                """);
        Assert.assertEquals(puzzle.solvePart1(), "4");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle19("""
                H => HO
                H => OH
                O => HH
                
                HOHOHO
                """);
        Assert.assertEquals(puzzle.solvePart1(), "7");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle19("""
                e => H
                e => O
                H => HO
                H => OH
                O => HH
                
                HOH
                """);
        Assert.assertEquals(puzzle.solvePart2(), "3");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle19("""
                e => H
                e => O
                H => HO
                H => OH
                O => HH
                
                HOHOHO
                """);
        Assert.assertEquals(puzzle.solvePart2(), "6");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle19(getStoredInput(19));
        Assert.assertEquals(puzzle.solvePart2(), "207");
    }
}
