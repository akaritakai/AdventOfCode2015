package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle14 extends BasePuzzleTest {
    @Test
    public void testCalculateDistance() {
        // Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        // Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.

        // After one second, Comet has gone 14 km, while Dancer has gone 16 km.
        Assert.assertEquals(Puzzle14.calculateDistance(14, 10, 127, 1), 14);
        Assert.assertEquals(Puzzle14.calculateDistance(16, 11, 162, 1), 16);

        // After ten seconds, Comet has gone 140 km, while Dancer has gone 160 km.
        Assert.assertEquals(Puzzle14.calculateDistance(14, 10, 127, 10), 140);
        Assert.assertEquals(Puzzle14.calculateDistance(16, 11, 162, 10), 160);

        // On the eleventh second, Comet begins resting (staying at 140 km), and Dancer continues on for a total
        // distance of 176 km.
        Assert.assertEquals(Puzzle14.calculateDistance(14, 10, 127, 11), 140);
        Assert.assertEquals(Puzzle14.calculateDistance(16, 11, 162, 11), 176);

        // On the 12th second, both reindeer are resting.
        Assert.assertEquals(Puzzle14.calculateDistance(14, 10, 127, 12), 140);
        Assert.assertEquals(Puzzle14.calculateDistance(16, 11, 162, 12), 176);

        // In this example, after the 1000th second, both reindeer are resting, and Comet is in the lead at 1120 km
        // (poor Dancer has only gotten 1056 km by that point).
        Assert.assertEquals(Puzzle14.calculateDistance(14, 10, 127, 1000), 1120);
        Assert.assertEquals(Puzzle14.calculateDistance(16, 11, 162, 1000), 1056);
    }

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle14("""
                Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """);
        Assert.assertEquals(puzzle.getDistancesTraveled(1000).get("Comet").intValue(), 1120);
        Assert.assertEquals(puzzle.getDistancesTraveled(1000).get("Dancer").intValue(), 1056);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle14(getStoredInput(14));
        Assert.assertEquals(puzzle.solvePart1(), "2640");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle14("""
                Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """);
        Assert.assertEquals(puzzle.getPointsEarned(1000).get("Comet").intValue(), 312);
        Assert.assertEquals(puzzle.getPointsEarned(1000).get("Dancer").intValue(), 689);
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle14(getStoredInput(14));
        Assert.assertEquals(puzzle.solvePart2(), "1102");
    }
}
