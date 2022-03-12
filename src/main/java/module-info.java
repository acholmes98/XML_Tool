module tools.xml_tool {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens tools.xml_tool to javafx.fxml;
    exports tools.xml_tool;
}