package tools.xml_tool;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutPopupWindow {
    Stage stage;


    public void CreateAboutPopup() {
        Label label = new Label();
        String message =  "DYNAC XML Tool\nVersion 0.4\n\nCreated by: Andrew Holmes\n\n\n\nTetra Tech\nIBRA-RMAC";
        label.setText(message);
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_CENTER);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(7,7,5,7));
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout);
        stage = new Stage();
        stage.setScene(scene);
        double scale_pct = 0.3;
        stage.setWidth(800*scale_pct);
        stage.setHeight(800*scale_pct);
        stage.show();
        stage.setTitle("About");


    }
}
