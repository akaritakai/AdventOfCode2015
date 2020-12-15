package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle11 extends BasePuzzleTest {
    @Test
    public void testIsPassword() {
        Assert.assertFalse(Puzzle11.isPassword("hijklmmn"));
        Assert.assertFalse(Puzzle11.isPassword("abbceffg"));
        Assert.assertFalse(Puzzle11.isPassword("abbcegjk"));
        Assert.assertTrue(Puzzle11.isPassword("abcdffaa"));
        Assert.assertTrue(Puzzle11.isPassword("ghjaabcc"));
    }

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle11("abcdefgh");
        Assert.assertEquals(puzzle.solvePart1(), "abcdffaa");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle11("ghijklmn");
        Assert.assertEquals(puzzle.solvePart1(), "ghjaabcc");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle11(getStoredInput(11));
        Assert.assertEquals(puzzle.solvePart1(), "vzbxxyzz");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle11(getStoredInput(11));
        Assert.assertEquals(puzzle.solvePart2(), "vzcaabcc");
    }
}
