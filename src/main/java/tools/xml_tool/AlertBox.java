package tools.xml_tool;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {
    Stage window;


    public void display(String title, String message, double x, double y){

        //window.initModality(Modality.APPLICATION_MODAL); //Force user to use this window first
        window= new Stage();
        //window.setTitle(title);
        double scale_pct = 0.20;
        window.setWidth(1280*scale_pct);
        window.setHeight(800*scale_pct);

        window.setX(x);
        window.setY(y);
        window.initStyle(StageStyle.UNDECORATED);


        Label label = new Label();
        label.setText(message);
        label.setWrapText(true);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15,10,15,10));
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }

    public void close() {
        window.close();
    }


}
