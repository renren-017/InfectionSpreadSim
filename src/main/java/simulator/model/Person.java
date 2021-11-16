package simulator.model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Person {

    public static int rad = 5;
    public static int healingtime = 400;
    private State state;
    private Position loc;
    private Orientation orientation;
    private Circle circle;
    private Pane environment;
    private int sickTime = 0;

    public Person(State state, Pane environment) {
        this.state = state;
        this.orientation = new Orientation();
        this.loc = new Position(environment, rad);
        this.circle = new Circle(rad, state.getColor());
        this.environment = environment;
        environment.getChildren().add(circle);
    }

    public void draw() {
        circle.setRadius(rad);
        circle.setTranslateX(loc.getX());
        circle.setTranslateY(loc.getY());
    }

    public void move() {
        loc.move(orientation, environment);
    }

    public void getInfection(Person other) {
        if (loc.collision(other.loc)) {
            if(other.getState() == State.infected && state == State.healthy) {
                setState(State.infected);
            }
        }
    }

    public void heal() {
        if (state == State.infected) {
            sickTime++;
            if (sickTime > healingtime) {
                setState(State.immune);
            }
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        circle.setFill(state.getColor());
    }
}
