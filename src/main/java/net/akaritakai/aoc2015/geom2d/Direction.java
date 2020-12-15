package net.akaritakai.aoc2015.geom2d;

public enum Direction {
    NORTH (0, 1),  // UP
    SOUTH (0, -1), // DOWN
    EAST (1, 0),   // LEFT
    WEST (-1, 0);  // RIGHT

    private final long dx;
    private final long dy;

    Direction(long dx, long dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Point move(Point point) {
        return new Point(point.x() + dx, point.y() + dy);
    }
}
