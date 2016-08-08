package gui;

import gui.components.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class application extends Application {

    private Scene mainScene;
    private MainPanel mainPanel;

    private static File file;


    public void start(Stage primaryStage) throws Exception {
        mainPanel = new MainPanel(primaryStage);
        mainScene = new Scene(mainPanel);

        file = null;

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Edge Detector 0.0.1");
        primaryStage.show();
    }

    public static void setFile(File newFile) {
        file = newFile;
    }

    public static File getFile() {
        return file;
    }
}
