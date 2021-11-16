package simulator;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import simulator.model.Person;
import simulator.model.Simulation;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    Simulation sim;
    private Movement clock;
    private int count;

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
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSize();
            }
        });

        countInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                setCount();
            }
        });
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
        sim = new Simulation(environment, count);
        Initialize();
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

    @FXML
    public void setCount() {
        String str = countInput.getText();
        System.out.println("sjk");
        try{
            int number = Integer.parseInt(str);
            count = number;
            sim.draw();
        }
        catch (NumberFormatException ex){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Input should be a positive integer value");
            errorAlert.showAndWait();
        }
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