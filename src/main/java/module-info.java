module com.guoxquiboloy.le3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.guoxquiboloy.le3 to javafx.fxml;
    exports com.guoxquiboloy.le3;
}
