package simulator.model;

import javafx.scene.layout.Pane;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(Pane world, int rad) {
        this.x = Math.random() * (world.getWidth() - 2 * rad);
        this.y = Math.random() * (world.getHeight() - 2 * rad);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(Orientation orientation, Pane environment) {
        x += orientation.getDx();
        y += orientation.getDy();
        if (x < 0 || x > environment.getWidth()) {
            orientation.bounceX();
            x += orientation.getDx();
        } else if (y < 0 || y > environment.getHeight()) {
            orientation.bounceY();
            y += orientation.getDy();
        }
    }

    public double distance(Position other) {
        double pos = Math.sqrt(Math.pow(this.x - other.x, 2)) + Math.pow(this.y - other.y, 2);
        return pos;
    }

    public boolean collision(Position other) {
        return distance(other) < 2 * Person.rad;
    }
}
