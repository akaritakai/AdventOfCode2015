package net.akaritakai.aoc2015;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPuzzle05 extends BasePuzzleTest {
    @Test
    public void testContainsAtLeastThreeVowels() {
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("aeiou"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("aei"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("xazegov"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("aeiouaeiouaeiou"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("xx"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("abcdde"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("dd"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("aabbccdd"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("aa"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("bb"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("cc"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("dd"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("ab"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("cd"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("pq"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("xy"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("ugknbfddgicrmopn"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("aaa"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("jchzalrnumimnmhp"));
        Assert.assertTrue(Puzzle05.containsAtLeastThreeVowels("haegwjzuvuyypxyu"));
        Assert.assertFalse(Puzzle05.containsAtLeastThreeVowels("dvszwmarrgswjxmb"));
    }

    @Test
    public void testContainsAtLeastOneLetterRepeatedTwiceInARow() {
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aeiou"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aei"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("xazegov"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aeiouaeiouaeiou"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("xx"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("abcdde"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("dd"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aabbccdd"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aa"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("bb"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("cc"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("dd"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("ab"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("cd"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("pq"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("xy"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("ugknbfddgicrmopn"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("aaa"));
        Assert.assertFalse(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("jchzalrnumimnmhp"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("haegwjzuvuyypxyu"));
        Assert.assertTrue(Puzzle05.containsAtLeastOneLetterRepeatedTwiceInARow("dvszwmarrgswjxmb"));
    }

    @Test
    public void testDoesNotContainSpecialStrings() {
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("aeiou"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("aei"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("xazegov"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("aeiouaeiouaeiou"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("xx"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("abcdde")); // contains 'ab' and 'cd'
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("dd"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("aabbccdd")); // contains 'ab'
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("aa"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("bb"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("cc"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("dd"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("ab"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("cd"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("pq"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("xy"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("ugknbfddgicrmopn"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("aaa"));
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("jchzalrnumimnmhp"));
        Assert.assertFalse(Puzzle05.doesNotContainSpecialStrings("haegwjzuvuyypxyu")); // contains 'xy'
        Assert.assertTrue(Puzzle05.doesNotContainSpecialStrings("dvszwmarrgswjxmb"));
    }

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle05("ugknbfddgicrmopn");
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle05("aaa");
        Assert.assertEquals(puzzle.solvePart1(), "1");
    }

    @Test
    public void testPart1Example3() {
        var puzzle = new Puzzle05("jchzalrnumimnmhp");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example4() {
        var puzzle = new Puzzle05("haegwjzuvuyypxyu");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testPart1Example5() {
        var puzzle = new Puzzle05("dvszwmarrgswjxmb");
        Assert.assertEquals(puzzle.solvePart1(), "0");
    }

    @Test
    public void testSolvePart1() throws Exception {
        var puzzle = new Puzzle05(getStoredInput(5));
        Assert.assertEquals(puzzle.solvePart1(), "238");
    }

    @Test
    public void testContainsPairThatDoesNotOverlap() {
        Assert.assertTrue(Puzzle05.containsPairThatDoesNotOverlap("xyxy"));
        Assert.assertTrue(Puzzle05.containsPairThatDoesNotOverlap("aabcdefgaa"));
        Assert.assertFalse(Puzzle05.containsPairThatDoesNotOverlap("aaa"));
        Assert.assertTrue(Puzzle05.containsPairThatDoesNotOverlap("qjhvhtzxzqqjkmpb"));
        Assert.assertTrue(Puzzle05.containsPairThatDoesNotOverlap("xxyxx"));
        Assert.assertTrue(Puzzle05.containsPairThatDoesNotOverlap("uurcxstgmygtbstg"));
        Assert.assertFalse(Puzzle05.containsPairThatDoesNotOverlap("ieodomkazucvgmuy"));
    }

    @Test
    public void testContainsLetterWhichRepeatsWithExactlyOneLetterBetweenThem() {
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("xyx"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("abcdefeghi"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("efe"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("aaa"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("qjhvhtzxzqqjkmpb"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("xxyxx"));
        Assert.assertFalse(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("uurcxstgmygtbstg"));
        Assert.assertTrue(Puzzle05.containsLetterWhichRepeatsWithExactlyOneLetterBetweenThem("ieodomkazucvgmuy"));
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle05("qjhvhtzxzqqjkmpb");
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example2() {
        var puzzle = new Puzzle05("xxyxx");
        Assert.assertEquals(puzzle.solvePart2(), "1");
    }

    @Test
    public void testPart2Example3() {
        var puzzle = new Puzzle05("uurcxstgmygtbstg");
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testPart2Example4() {
        var puzzle = new Puzzle05("ieodomkazucvgmuy");
        Assert.assertEquals(puzzle.solvePart2(), "0");
    }

    @Test
    public void testSolvePart2() throws Exception {
        var puzzle = new Puzzle05(getStoredInput(5));
        Assert.assertEquals(puzzle.solvePart2(), "69");
    }
}
