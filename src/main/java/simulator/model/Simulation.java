package simulator.model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<Person> people;

    public Simulation(Pane environ, int count) {
        people = new ArrayList<Person>();

        for (int i = 0; i < count; i++){
            people.add(new Person(State.healthy, environ));
        }

        people.add(new Person(State.infected, environ));

        draw();
    }

    public void move() {
        for (Person person : people) {
            person.move();
        }
    }

    public void infectionSpread() {
        for (Person a: people) {
            for (Person b : people) {
                if (a != b) {
                    a.getInfection(b);
                } else {
                    continue;
                }
            }
        }
    }

    public void heal() {
        for (Person ani: people) {
            ani.heal();
        }
    }

    public void draw() {
        for (Person ani : people) {
            ani.draw();
        }
    }
}
