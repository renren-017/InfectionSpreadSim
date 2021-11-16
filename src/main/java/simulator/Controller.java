package simulator;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import simulator.model.Simulation;

public class Controller {

    Simulation sim;
    private Movement clock;

    @FXML
    Pane environment;

    @FXML
    Button StartButton;

    @FXML
    Button StopButton;

    @FXML
    Button StepButton;

    @FXML
    public void Initialize() {
    }

    @FXML
    public void start() {
        clock = new Movement();
        clock.start();
    }

    @FXML
    public void stop() {
        clock.stop();
    }

    @FXML
    public void reset() {
        clock = new Movement();
        clock.stop();
        environment.getChildren().clear();
        sim = new Simulation(environment, 150);
    }

    @FXML
    public void step() {
        sim.move();
        sim.heal();
        sim.infectionSpread();
        sim.draw();
    }

    private class Movement extends AnimationTimer {
        private long frames_per_s = 50L;
        private long interval = 1000000000L/frames_per_s;
        private long prev = 0;

        @Override
        public void handle(long now) {
            if (now - prev > interval) {
                step();
                prev = now;
            }
        }
    }

    public void disableBtn(boolean startBtn, boolean stopBtn, boolean stepBtn) {
        StartButton.setDisable(startBtn);
        StopButton.setDisable(stopBtn);
        StepButton.setDisable(stepBtn);
    }

}