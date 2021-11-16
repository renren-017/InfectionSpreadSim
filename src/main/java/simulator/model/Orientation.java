package simulator.model;

public class Orientation {
    private static final double SPEED = 2;
    private double dx;
    private double dy;

    public Orientation() {
        double direction = 2 * Math.random() * Math.PI;
        dx = Math.sin(direction);
        dy = Math.cos(direction);
    }

    public Orientation(double dx, double dy) {
        this.dy = dy;
        this.dx = dx;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void bounceX() {
        dx *= -1;
    }

    public void bounceY() {
        dy *= -1;
    }
}
