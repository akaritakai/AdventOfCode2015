package net.akaritakai.aoc2015;

import com.google.common.annotations.VisibleForTesting;

public class Puzzle18 extends AbstractPuzzle {
    public Puzzle18(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 18;
    }

    @Override
    public String solvePart1() {
        var lights = new Lights(getPuzzleInput(), false);
        for (var i = 0; i < 100; i++) {
            lights.nextState();
        }
        return String.valueOf(lights.numLightsOn());
    }

    @Override
    public String solvePart2() {
        var lights = new Lights(getPuzzleInput(), true);
        for (var i = 0; i < 100; i++) {
            lights.nextState();
        }
        return String.valueOf(lights.numLightsOn());
    }

    @VisibleForTesting
    static class Lights {
        private final int size;
        private final boolean stuck;
        private boolean[][] lights;
        private boolean[][] scratch;

        public Lights(String input, boolean stuck) {
            var lines = input.split("\n");
            size = lines.length;
            lights = new boolean[size][size];
            scratch = new boolean[size][size];
            for (var y = 0; y < size; y++) {
                var line = lines[y];
                for (var x = 0; x < size; x++) {
                    if (line.charAt(x) == '#') lights[x][y] = true;
                }
            }
            this.stuck = stuck;
            if (stuck) {
                lights[0][0] = true;
                lights[0][size - 1] = true;
                lights[size - 1][0] = true;
                lights[size - 1][size - 1] = true;
            }
        }

        public int numLightsOn() {
            var count = 0;
            for (var x = 0; x < size; x++) {
                for (var y = 0; y < size; y++) {
                    if (lights[x][y]) count++;
                }
            }
            return count;
        }

        public void nextState() {
            // Calculate next state into scratch
            for (var x = 0; x < size; x++) {
                for (var y = 0; y < size; y++) {
                    scratch[x][y] = nextState(x, y);
                }
            }
            if (stuck) {
                scratch[0][0] = true;
                scratch[0][size - 1] = true;
                scratch[size - 1][0] = true;
                scratch[size - 1][size - 1] = true;
            }

            // Swap references
            var tmp = lights;
            lights = scratch;
            scratch = tmp;
        }

        private boolean nextState(int x, int y) {
            int numNeighborsOn = 0;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i == x && j == y) continue;
                    if (inBounds(i, j) && lights[i][j]) numNeighborsOn++;
                }
            }
            return lights[x][y] ? numNeighborsOn == 2 || numNeighborsOn == 3 : numNeighborsOn == 3;
        }

        private boolean inBounds(int x, int y) {
            return x >= 0 && x < size && y >= 0 && y < size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lights l = (Lights) o;
            if (size != l.size) return false;
            for (var x = 0; x < size; x++) {
                for (var y = 0; y < size; y++) {
                    if (lights[x][y] != l.lights[x][y]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public String toString() {
            var sb = new StringBuilder();
            for (var y = 0; y < size; y++) {
                for (var x = 0; x < size; x++) {
                    if (lights[x][y]) {
                        sb.append('#');
                    } else {
                        sb.append('.');
                    }
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
