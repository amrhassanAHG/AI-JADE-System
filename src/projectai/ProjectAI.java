package projectai;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amr Hassan
 */
public class ProjectAI extends Application {
    static ContainerController container;
    public static int mobiles = 0, humans=1, cars=2, cats=3, dogs=4;
    public static String [][] category =   {{"samsung", "iphone", "huawei"},
                                            {"ahmed", "maged", "i"},
                                            {"bmw", "kia", "audi"},
                                            {"abby", "ellie", "nala"},
                                            {"max", "charlie", "lucy"}};

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        RunJade();
        launch(args);
    }

    /////////////////////////////// creating containers and agents ///////////////////////////////////
    public static void RunJade() {
        //Create the JADE envioenment
        jade.core.Runtime myRuntime = jade.core.Runtime.instance();
        Profile myProfile = new ProfileImpl();
        container = myRuntime.createMainContainer(myProfile);
    }

    static AgentController[] rma = new AgentController[2];

    public static void createAgent(String agentName, String agentClass, Object t, int idx) {
        //Call the RMA GUI   

        try {
            Object[] ob = new Object[1];
            ob[0] = new Object();
            ob[0] = t;
            rma[idx] = container.createNewAgent(agentName, agentClass, ob);
            rma[idx].start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }

    }

}
