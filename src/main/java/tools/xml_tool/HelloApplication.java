package tools.xml_tool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //ExcelTest excelTest = new ExcelTest();
        //excelTest.test();

        PointCreation xml_DIO2 = new PointCreation();
        String XlsxPath = getClass().getResource("/tools/xml_tool/DIO2_Template_Layout.xlsx").toString().replace("file:/","");
        System.out.println(XlsxPath);
        xml_DIO2.ReadDIO2_Excel(XlsxPath);

    }

    public static void main(String[] args) {
        launch();
    }
}