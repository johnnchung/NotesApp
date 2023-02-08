module com.example.dragdroptargets {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    opens com.example.dragdroptargets to javafx.fxml;
    exports com.example.dragdroptargets;
}