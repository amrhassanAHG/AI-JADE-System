package projectai;

import jade.core.Agent;
import javafx.scene.control.TextField;

public class Explain extends Agent {

    static TextField explain1;

    @Override
    protected void setup() {
        Object[] ob = getArguments();
        explain1 = (TextField) (ob[0]);

        addBehaviour(new ExplainBehaviour());

    }

    @Override
    protected void takeDown() {
    }
}
