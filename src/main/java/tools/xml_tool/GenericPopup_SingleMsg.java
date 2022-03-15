package tools.xml_tool;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GenericPopup_SingleMsg {
    Stage stage;


    public void CreatePopup(String message,String title) {
        Label label = new Label();
        label.setText(message);
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_LEFT);
        label.setStyle("-fx-font-size: 14");

        Button OK_Button = new Button("OK");
        OK_Button.setOnAction(event -> stage.close());
        OK_Button.setAlignment(Pos.CENTER);
        OK_Button.setPrefSize(90,30);

        VBox layout = new VBox(55);
        layout.setPadding(new Insets(15,10,15,10));
        layout.getChildren().addAll(label,OK_Button);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout);
        stage = new Stage();
        stage.setScene(scene);
        double scale_pct = 0.32;
        stage.setWidth(1280*scale_pct);
        stage.setHeight(800*scale_pct);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();



    }
}
