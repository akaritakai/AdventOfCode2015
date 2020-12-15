package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle10 extends BasePuzzleTest {
    @Test
    public void testExpand() {
        Assert.assertEquals(Puzzle10.expand("211"), "1221");
        Assert.assertEquals(Puzzle10.expand("111"), "31");
        Assert.assertEquals(Puzzle10.expand("1"), "11");
        Assert.assertEquals(Puzzle10.expand("11"), "21");
        Assert.assertEquals(Puzzle10.expand("21"), "1211");
        Assert.assertEquals(Puzzle10.expand("1211"), "111221");
        Assert.assertEquals(Puzzle10.expand("111221"), "312211");
    }

    @Test
    public void testPart1Example1() {
        var input = "1";
        for (var i = 0; i < 5; i++) {
            input = Puzzle10.expand(input);
        }
        Assert.assertEquals(input, "312211");
        Assert.assertEquals(input.length(), 6);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle10(getStoredInput(10));
        Assert.assertEquals(puzzle.solvePart1(), "329356");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle10(getStoredInput(10));
        Assert.assertEquals(puzzle.solvePart2(), "4666278");
    }
}
