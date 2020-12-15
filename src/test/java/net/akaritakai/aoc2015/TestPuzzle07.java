package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle07 extends BasePuzzleTest {
    @Test
    public void testPart1Example1() {
        var circuit = new Puzzle07.Circuit("""
                123 -> x
                456 -> y
                x AND y -> d
                x OR y -> e
                x LSHIFT 2 -> f
                y RSHIFT 2 -> g
                NOT x -> h
                NOT y -> i
                """);
        Assert.assertEquals(circuit.wire("d").intValue(), 72);
        Assert.assertEquals(circuit.wire("e").intValue(), 507);
        Assert.assertEquals(circuit.wire("f").intValue(), 492);
        Assert.assertEquals(circuit.wire("g").intValue(), 114);
        Assert.assertEquals(circuit.wire("h").intValue(), 65412);
        Assert.assertEquals(circuit.wire("i").intValue(), 65079);
        Assert.assertEquals(circuit.wire("x").intValue(), 123);
        Assert.assertEquals(circuit.wire("y").intValue(), 456);
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle07(getStoredInput(7));
        Assert.assertEquals(puzzle.solvePart1(), "46065");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle07(getStoredInput(7));
        Assert.assertEquals(puzzle.solvePart2(), "14134");
    }
}
