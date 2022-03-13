package tools.xml_tool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    // FXML annotations
    @FXML
    private TextField inputPathTxt;
    @FXML
    private TextField outputDirTxt;
    @FXML
    private ComboBox Combo1;
    @FXML
    private Button myButton;
    @FXML
    private Button inputSelButton;
    @FXML
    private Button outputSelButton;
    @FXML
    private Group Help1;
    @FXML
    private Group Help2;
    @FXML
    private Group Help3;
    @FXML
    private MenuItem MenuItemAbout;
    @FXML
    private AnchorPane anchorPaneMain;
    @FXML
    private MenuItem EditBeachMenuItem;



    private String myButtonStyle;
    private String inputSelButtonStyle;
    private String outputSelButtonStyle;
    private boolean BeachStyleToggle = false;
    private String out_InitialDir = "C:\\";
    private String in_InitialDir = "C:\\";
    private String in_InitialPath = in_InitialDir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Combo Box 1
        ObservableList<String> list1 = FXCollections.observableArrayList("Empty Template","Create All","Create DIO (1-Bit)",
                "Create DIO (2-Bit)","Create AIO (Int)","Create AIO (Float)");
        Combo1.setItems(list1);
        Combo1.setEditable(false);

        //Main Button
        myButton.setOnAction(event -> myButtonClick());
        myButton.setOnMouseEntered(event -> myButtonMouseEnter());
        myButton.setOnMouseExited(event -> myButtonMouseExit());

        //Input file selection button
        inputSelButton.setOnAction(event -> inputButtonClick());
        inputSelButton.setOnMouseEntered(event -> inputButtonMouseEnter());
        inputSelButton.setOnMouseExited(event -> inputButtonMouseExit());

        //output file selection button
        outputSelButton.setOnAction(event -> outputButtonClick());
        outputSelButton.setOnMouseEntered(event -> outputButtonMouseEnter());
        outputSelButton.setOnMouseExited(event -> outputButtonMouseExit());

        //Help Pop-ups
        AlertBox helpPopup = new AlertBox();
        Help1.setOnMouseEntered(event -> helpPopup.display("Excel Template Selection Help", "Select the Excel template containing DYNAC" +
                " point information. If you do not have the template, select \"Empty Template\" from the combo box to the right, set " +
                "the Output Directory, click the \"Generate\" button, and fill in the resulting template.",event.getScreenX()+30,event.getScreenY()-110));
        Help1.setOnMouseExited(event -> helpPopup.close());

        Help2.setOnMouseEntered(event -> helpPopup.display("Output Type Help", "Select from the drop down menu the type of points" +
                " that you wish to generate (D = Digital, A = Analog, I = Input, O = Output).\n" +
                "If you need a fresh template to fill out, select \"Empty Template\".",event.getScreenX()+30,event.getScreenY()-110));
        Help2.setOnMouseExited(event -> helpPopup.close());

        Help3.setOnMouseEntered(event -> helpPopup.display("Output Directory Help", "Select the directory in which you would like the tool" +
                "to place the XML files or empty Excel template.",event.getScreenX()+30,event.getScreenY()-110));
        Help3.setOnMouseExited(event -> helpPopup.close());


        MenuItemAbout.setOnAction(event -> {
            AboutPopupWindow aboutWindow = new AboutPopupWindow();
            aboutWindow.CreateAboutPopup();
        });


        EditBeachMenuItem.setOnAction(event -> {

            if (!BeachStyleToggle) {
                BeachStyleToggle = true;
                anchorPaneMain.setStyle("-fx-background-color: linear-gradient(to top, #6B7EC7 10%, #BA9E80 45%)");
            }
            else {
                BeachStyleToggle = false;
                anchorPaneMain.setStyle("-fx-background-color: linear-gradient(to bottom, #959595 40%, #4B4B4B 100%)");
            }

        });

        myButtonStyle = myButton.getStyle();
        inputSelButtonStyle = inputSelButton.getStyle();
        outputSelButtonStyle = outputSelButton.getStyle();
    }

    private void myButtonClick() {
        PointCreation xml_DIO2 = new PointCreation();
        xml_DIO2.ReadDIO2_Excel(in_InitialPath, out_InitialDir);
    }

    @FXML
    void myButtonMouseEnter() {
        myButton.setStyle("-fx-background-color: grey; -fx-font-size: 30px;");
    }

    @FXML
    void myButtonMouseExit() {
        myButton.setStyle(myButtonStyle);
    }

    private void inputButtonClick() {
        try {
            FileChooser FileChooser = new FileChooser();
            FileChooser.setTitle("Select Input XLSX File");
            FileChooser.setInitialDirectory(new File(in_InitialDir));
            File file = FileChooser.showOpenDialog(inputSelButton.getScene().getWindow());
            if(file.getPath() != null) {
                in_InitialPath = file.getPath();
                in_InitialDir = file.getParent();
                inputPathTxt.setText(file.getPath());
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    void inputButtonMouseEnter() {
        inputSelButton.setStyle("-fx-background-color: grey; -fx-font-size: 20px;");
    }
    void inputButtonMouseExit() {
        inputSelButton.setStyle(inputSelButtonStyle);
    }

    private void outputButtonClick() {
        try {
            DirectoryChooser FolderChooser = new DirectoryChooser();
            FolderChooser.setTitle("Select Output Folder");
            FolderChooser.setInitialDirectory(new File(out_InitialDir));
            File file = FolderChooser.showDialog(outputSelButton.getScene().getWindow());
            if(file.getPath() != null) {
                out_InitialDir = file.getPath();
                outputDirTxt.setText(file.getPath());
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    void outputButtonMouseEnter() {
        outputSelButton.setStyle("-fx-background-color: grey; -fx-font-size: 20px;");
    }
    void outputButtonMouseExit() {
        outputSelButton.setStyle(outputSelButtonStyle);
    }



}