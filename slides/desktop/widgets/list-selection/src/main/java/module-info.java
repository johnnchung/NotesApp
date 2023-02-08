module com.example.listselection {
    requires javafx.controls;
    requires kotlin.stdlib;

    opens com.example.listselection to javafx.fxml;
    exports com.example.listselection;
}