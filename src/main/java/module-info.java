module com.guoxquiboloy.le3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.guoxquiboloy.le3 to javafx.fxml;
    exports com.guoxquiboloy.le3;
}
