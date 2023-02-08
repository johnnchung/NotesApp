module com.example.dragdrop {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.dragdrop to javafx.fxml;
    exports com.example.dragdrop;
}