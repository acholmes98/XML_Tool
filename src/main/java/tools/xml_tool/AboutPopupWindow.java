package tools.xml_tool;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutPopupWindow {
    Stage stage;


    public void CreateAboutPopup() {
        Label label = new Label();
        String message =  "DYNAC XML Tool\nVersion 0.4\n\nCreated by: Andrew Holmes\n\n\n\nTetra Tech\nIBRA-RMAC";
        label.setText(message);
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_CENTER);
        label.setStyle("-fx-font-size: 14");

        Button OK_Button = new Button("OK");
        OK_Button.setOnAction(event -> stage.close());
        OK_Button.setAlignment(Pos.CENTER);
        OK_Button.setPrefSize(90,30);

        VBox layout = new VBox(50);
        layout.setPadding(new Insets(15,10,15,10));
        layout.getChildren().addAll(label,OK_Button);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout);
        stage = new Stage();
        stage.setScene(scene);
        double scale_pct = 0.4;
        stage.setWidth(800*scale_pct);
        stage.setHeight(900*scale_pct);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("About");
        stage.showAndWait();


    }
}
