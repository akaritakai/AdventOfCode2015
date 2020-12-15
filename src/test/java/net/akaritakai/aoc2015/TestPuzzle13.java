package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle13 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle13("""
                Alice would gain 54 happiness units by sitting next to Bob.
                Alice would lose 79 happiness units by sitting next to Carol.
                Alice would lose 2 happiness units by sitting next to David.
                Bob would gain 83 happiness units by sitting next to Alice.
                Bob would lose 7 happiness units by sitting next to Carol.
                Bob would lose 63 happiness units by sitting next to David.
                Carol would lose 62 happiness units by sitting next to Alice.
                Carol would gain 60 happiness units by sitting next to Bob.
                Carol would gain 55 happiness units by sitting next to David.
                David would gain 46 happiness units by sitting next to Alice.
                David would lose 7 happiness units by sitting next to Bob.
                David would gain 41 happiness units by sitting next to Carol.
                """);
        Assert.assertEquals(puzzle.solvePart1(), "330");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle13(getStoredInput(13));
        Assert.assertEquals(puzzle.solvePart1(), "733");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle13(getStoredInput(13));
        Assert.assertEquals(puzzle.solvePart2(), "725");
    }
}
