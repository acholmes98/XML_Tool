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
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @FXML
    private MenuItem CloseMenuItem;



    private String myButtonStyle;
    private String inputSelButtonStyle;
    private String outputSelButtonStyle;
    private boolean BeachStyleToggle = false;
    private String out_InitialDir = "C:\\";
    private String in_InitialDir = "C:\\";
    private String in_InitialPath = in_InitialDir;
    private int template_num = -1;
    private boolean filledOutputDir = false;
    private boolean filledInputDir = false;
    GenericPopup_SingleMsg genericPopupSingleMsg = new GenericPopup_SingleMsg();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Combo Box 1
        ObservableList<String> list1 = FXCollections.observableArrayList("Empty Template","Create All","Create DIO (1-Bit)",
                "Create DIO (2-Bit)","Create AIO (Int)","Create AIO (Float)");
        Combo1.setItems(list1);
        Combo1.setEditable(false);
        Combo1.setOnAction(event -> getTemplateNum());

        //Main Button
        myButton.setOnAction(event -> {
            if (filledOutputDir && (template_num>0 || filledInputDir)) {
                myButtonClick();
                genericPopupSingleMsg.CreatePopup("Output generated successfully!","Files Generated");

            } else if (!filledOutputDir) {
                genericPopupSingleMsg.CreatePopup("Designate the output directory by clicking the select " +
                        "button on the right side and then choosing a valid directory.","Designate Output Directory");
            } else {
                genericPopupSingleMsg.CreatePopup("Designate the input file by clicking the select " +
                        "button on the left side and then choosing a valid Excel template file. If you do not have a " +
                        "template, select \"Empty Template\" from the drop down (for this, you do not need to specify a file).",
                        "Designate Input File");
            }
        });
        myButton.setOnMouseEntered(event -> myButtonMouseEnter());
        myButton.setOnMouseExited(event -> myButtonMouseExit());

        //Input file selection button
        inputSelButton.setOnAction(event -> inputButtonClick());
        inputSelButton.setOnMouseEntered(event -> inputButtonMouseEnter());
        inputSelButton.setOnMouseExited(event -> inputButtonMouseExit());
        inputPathTxt.setOnAction(event ->{
            Path path = Paths.get(inputPathTxt.getText());
            if(Files.exists(path)) {
                in_InitialPath = inputPathTxt.getText();
                filledInputDir = true;
            } else {
                inputPathTxt.setText(in_InitialPath);
            }

        });
        
        //output file selection button
        outputSelButton.setOnAction(event ->{
            outputButtonClick();
            filledOutputDir = true;
        });
        outputSelButton.setOnMouseEntered(event -> outputButtonMouseEnter());
        outputSelButton.setOnMouseExited(event -> outputButtonMouseExit());
        outputDirTxt.setOnAction(event ->{
            Path path = Paths.get(outputDirTxt.getText());
            if (Files.exists(path)) {
                out_InitialDir = outputDirTxt.getText();
                filledOutputDir = true;
            } else {
                outputDirTxt.setText(out_InitialDir);
            }
        });

        //Help Pop-ups
        AlertBox helpPopup = new AlertBox();
        Help1.setOnMouseEntered(event -> helpPopup.display("Excel Template Selection Help", "Select the Excel template containing DYNAC" +
                " point information. If you do not have the template, select \"Empty Template\" from the combo box to the right, set " +
                "the Output Directory, click the \"Generate\" button, and fill in the resulting template.\nIf manually entering a directory to the text box, press enter after typing text.",event.getScreenX()+30,event.getScreenY()-110));
        Help1.setOnMouseExited(event -> helpPopup.close());

        Help2.setOnMouseEntered(event -> helpPopup.display("Output Type Help", "Select from the drop down menu the type of points" +
                " that you wish to generate (D = Digital, A = Analog, I = Input, O = Output).\n" +
                "If you need a fresh template to fill out, select \"Empty Template\".",event.getScreenX()+30,event.getScreenY()-110));
        Help2.setOnMouseExited(event -> helpPopup.close());

        Help3.setOnMouseEntered(event -> helpPopup.display("Output Directory Help", "Select the directory in which you would like the tool " +
                "to place the XML files or empty Excel template.\nIf manually entering a directory to the text box, press enter after typing text.",event.getScreenX()+30,event.getScreenY()-110));
        Help3.setOnMouseExited(event -> helpPopup.close());


        MenuItemAbout.setOnAction(event -> {
            AboutPopupWindow aboutWindow = new AboutPopupWindow();
            aboutWindow.CreateAboutPopup();
        });

        CloseMenuItem.setOnAction(event -> ((Stage)inputSelButton.getScene().getWindow()).close());


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
        PointCreation xml_points = new PointCreation();
        System.out.println(template_num);
        if (template_num == 1) {
            for (int i = 2; i <= 5; i++) {
                xml_points.ReadExcelGenXML(in_InitialPath, out_InitialDir, i);
            }
        } else {
            xml_points.ReadExcelGenXML(in_InitialPath, out_InitialDir,template_num);
        }
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
            FileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel Spreadsheet","*.xlsx"));
            File file = FileChooser.showOpenDialog(inputSelButton.getScene().getWindow());
            if(file.getPath() != null) {
                in_InitialPath = file.getPath();
                in_InitialDir = file.getParent();
                inputPathTxt.setText(file.getPath());
                filledInputDir = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    private void inputButtonMouseEnter() {
        inputSelButton.setStyle("-fx-background-color: grey; -fx-font-size: 20px;");
    }
    private void inputButtonMouseExit() {
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
    private void outputButtonMouseExit() {
        outputSelButton.setStyle(outputSelButtonStyle);
    }

    private void getTemplateNum() {
        String template_Str = (String) Combo1.getValue();
        switch (template_Str) {
            case "Empty Template":
                template_num = 0;
                break;
            case "Create All":
                template_num = 1;
                break;
            case "Create DIO (1-Bit)":
                template_num = 2;
                break;
            case "Create DIO (2-Bit)":
                template_num = 3;
                break;
            case "Create AIO (Int)":
                template_num = 4;
                break;
            case "Create AIO (Float)":
                template_num = 5;
                break;
            default:
                template_num = -1;
                break;
        }
    }
}