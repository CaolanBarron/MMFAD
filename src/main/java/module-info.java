module com.mmfad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mmfad to javafx.fxml;
    exports com.mmfad;
    exports com.mmfad.controllers;
    opens com.mmfad.controllers to javafx.fxml;
    exports com.mmfad.model;
    opens com.mmfad.model to javafx.fxml;
    exports com.mmfad.GUI;
    opens com.mmfad.GUI to javafx.fxml;
}