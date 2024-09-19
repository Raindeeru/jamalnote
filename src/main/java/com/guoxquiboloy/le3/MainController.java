package com.guoxquiboloy.le3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController{
    

    @FXML Button newFileButton;
    @FXML Button loadButton;
    @FXML Button closeButton;

    Stage stage;
    

    @FXML
    private void OpenNewFile() throws IOException{
        System.out.println("Pinindot mo New File");
        App.switchToEditor();
    }

    @FXML
    private void LoadFile() throws IOException{
        System.out.println("Pinindot mo Load File");
    }

    @FXML
    private void Close() throws IOException{
        System.out.println("Pinindot mo Close");
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
