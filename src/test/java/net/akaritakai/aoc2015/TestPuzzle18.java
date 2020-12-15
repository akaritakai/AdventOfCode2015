package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle18 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var lights = new Puzzle18.Lights("""
                .#.#.#
                ...##.
                #....#
                ..#...
                #.#..#
                ####..
                """, false);
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                ..##..
                ..##.#
                ...##.
                ......
                #.....
                #.##..
                """, false));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                ..###.
                ......
                ..###.
                ......
                .#....
                .#....
                """, false));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                ...#..
                ......
                ...#..
                ..##..
                ......
                ......
                """, false));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                ......
                ......
                ..##..
                ..##..
                ......
                ......
                """, false));
        Assert.assertEquals(lights.numLightsOn(), 4);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle18(getStoredInput(18));
        Assert.assertEquals(puzzle.solvePart1(), "821");
    }

    @Test
    public void testPart2Example1() {
        var lights = new Puzzle18.Lights("""
                ##.#.#
                ...##.
                #....#
                ..#...
                #.#..#
                ####.#
                """, true);
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                #.##.#
                ####.#
                ...##.
                ......
                #...#.
                #.####
                """, true));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                #..#.#
                #....#
                .#.##.
                ...##.
                .#..##
                ##.###
                """, true));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                #...##
                ####.#
                ..##.#
                ......
                ##....
                ####.#
                """, true));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                #.####
                #....#
                ...#..
                .##...
                #.....
                #.#..#
                """, true));
        lights.nextState();
        Assert.assertEquals(lights, new Puzzle18.Lights("""
                ##.###
                .##..#
                .##...
                .##...
                #.#...
                ##...#
                """, true));
        Assert.assertEquals(lights.numLightsOn(), 17);
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle18(getStoredInput(18));
        Assert.assertEquals(puzzle.solvePart2(), "886");
    }
}
