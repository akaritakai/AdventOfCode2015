package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

@Test
public class TestPuzzle08 extends BasePuzzleTest {
    @Test
    public void testParse() {
        Assert.assertEquals("\"\"".length(), 2);
        Assert.assertEquals("\"abc\"".length(), 5);
        Assert.assertEquals("\"aaa\\\"aaa\"".length(), 10);
        Assert.assertEquals("\"\\x27\"".length(), 6);

        Assert.assertEquals(Puzzle08.parse("\"\""), "");
        Assert.assertEquals(Puzzle08.parse("\"abc\""), "abc");
        Assert.assertEquals(Puzzle08.parse("\"aaa\\\"aaa\""), "aaa\"aaa");
        Assert.assertEquals(Puzzle08.parse("\"\\x27\""), "'");

        Assert.assertEquals(Puzzle08.parse("\"\"").length(), 0);
        Assert.assertEquals(Puzzle08.parse("\"abc\"").length(), 3);
        Assert.assertEquals(Puzzle08.parse("\"aaa\\\"aaa\"").length(), 7);
        Assert.assertEquals(Puzzle08.parse("\"\\x27\"").length(), 1);
    }

    @Test
    public void testEscape() {
        Assert.assertEquals("\"\"".length(), 2);
        Assert.assertEquals("\"abc\"".length(), 5);
        Assert.assertEquals("\"aaa\\\"aaa\"".length(), 10);
        Assert.assertEquals("\"\\x27\"".length(), 6);

        Assert.assertEquals(Puzzle08.escape("\"\""), "\"\\\"\\\"\"");
        Assert.assertEquals(Puzzle08.escape("\"abc\""), "\"\\\"abc\\\"\"");
        Assert.assertEquals(Puzzle08.escape("\"aaa\\\"aaa\""), "\"\\\"aaa\\\\\\\"aaa\\\"\"");
        Assert.assertEquals(Puzzle08.escape("\"\\x27\""), "\"\\\"\\\\x27\\\"\"");

        Assert.assertEquals(Puzzle08.escape("\"\"").length(), 6);
        Assert.assertEquals(Puzzle08.escape("\"abc\"").length(), 9);
        Assert.assertEquals(Puzzle08.escape("\"aaa\\\"aaa\"").length(), 16);
        Assert.assertEquals(Puzzle08.escape("\"\\x27\"").length(), 11);
    }

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle08("""
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """);
        Assert.assertEquals(puzzle.solvePart1(), "12");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle08(getStoredInput(8));
        Assert.assertEquals(puzzle.solvePart1(), "1342");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle08("""
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """);
        Assert.assertEquals(puzzle.solvePart2(), "19");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle08(getStoredInput(8));
        Assert.assertEquals(puzzle.solvePart2(), "2074");
    }
}
