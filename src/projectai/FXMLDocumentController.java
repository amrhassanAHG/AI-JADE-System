package projectai;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import static projectai.ProjectAI.createAgent;

public class FXMLDocumentController implements Initializable {

    public static String q = "";

    @FXML
    public TextField question;
    public TextField answer1;
    public TextField explain1;

    @FXML
    public void ans(ActionEvent event) {
        q = question.getText();

        createAgent("explain", "projectai.Explain", explain1, 0);
        createAgent("answer", "projectai.Answer", answer1, 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
