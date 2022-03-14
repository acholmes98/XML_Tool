package tools.xml_tool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class XML_Tool_Application extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("DYNAC XML Point Tool");
            Parent root = FXMLLoader.load(getClass().getResource("Primary_View.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}