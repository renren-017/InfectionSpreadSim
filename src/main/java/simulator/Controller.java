package simulator;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import simulator.model.Person;
import simulator.model.Simulation;

public class Controller {

    Simulation sim;
    private Movement clock;
    private int count = 5;

    @FXML
    Pane environment;

    @FXML
    Button StartButton;

    @FXML
    Button StopButton;

    @FXML
    Button StepButton;

    @FXML
    Slider sizeSlider;

    @FXML
    TextField countInput;

    @FXML
    public void Initialize() {
        sizeSlider.valueProperty().addListener((observableValue, number, t1) -> setSize());
    }

    @FXML
    public void onEnter(ActionEvent event){
        try{
            String str = countInput.getText();
            count = Integer.parseInt(str);
            sim.draw();

        }
        catch (NumberFormatException ex){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Input should be a positive integer value");
            errorAlert.showAndWait();
        }
    }

    @FXML
    public void start() {
        clock = new Movement();
        clock.start();
        disableBtn(false, false, true);
    }

    @FXML
    public void stop() {
        clock.stop();
        disableBtn(false, true, false);
    }

    @FXML
    public void reset() {
        clock = new Movement();
        clock.stop();
        environment.getChildren().clear();
        sim = new Simulation(environment, count);
        Initialize();
        disableBtn(false, true, false);
    }

    @FXML
    public void step() {
        sim.move();
        sim.heal();
        sim.infectionSpread();
        sim.draw();
    }

    @FXML
    public void setSize() {
        Person.rad = (int)sizeSlider.getValue();
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