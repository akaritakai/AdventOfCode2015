package net.akaritakai.aoc2015;

public class Puzzle02 extends AbstractPuzzle {
    public Puzzle02(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String solvePart1() {
        var amount = getPuzzleInput()
                .lines()
                .map(Box::new)
                .mapToInt(box -> box.surfaceArea() + box.areaOfSmallestSide())
                .sum();
        return String.valueOf(amount);
    }

    @Override
    public String solvePart2() {
        var amount = getPuzzleInput()
                .lines()
                .map(Box::new)
                .mapToInt(box -> box.perimeterOfSmallestFace() + box.volume())
                .sum();
        return String.valueOf(amount);
    }

    private record Box(int length, int width, int height) {
        public Box(String input) {
            this(Integer.parseInt(input.split("x")[0]),
                    Integer.parseInt(input.split("x")[1]),
                    Integer.parseInt(input.split("x")[2]));
        }

        public int surfaceArea() {
            return 2 * length * width + 2 * length * height + 2 * width * height;
        }

        public int areaOfSmallestSide() {
            return Math.min(length * width, Math.min(length * height, width * height));
        }

        public int perimeterOfSmallestFace() {
            return Math.min(2 * (length + width), Math.min(2 * (length + height), 2 * (width + height)));
        }

        public int volume() {
            return length * width * height;
        }
    }
}
