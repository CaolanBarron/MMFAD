module com.mmfad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mmfad to javafx.fxml;
    exports com.mmfad;
}