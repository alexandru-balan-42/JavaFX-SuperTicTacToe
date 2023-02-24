module alex {
    requires javafx.controls;
    requires javafx.fxml;

    opens MainPackage to javafx.fxml;
    exports MainPackage;
}
