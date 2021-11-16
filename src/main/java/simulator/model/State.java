package simulator.model;

import javafx.scene.paint.Color;

public enum State {
    healthy {
        public Color getColor() {
            return Color.GRAY;
        }
    },
    infected {
        public Color getColor() {
            return Color.RED;
        }
    },
    immune {
        public Color getColor() {
            return Color.GREEN;
        }
    };

    public abstract Color getColor();
}
