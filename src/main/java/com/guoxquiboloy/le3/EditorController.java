package com.guoxquiboloy.le3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EditorController {
    @FXML TextArea typeArea;
    @FXML Button saveButton;
    @FXML Button fontButton; 
    @FXML Button fontSizeButton;
    @FXML Button textColorButton;

    @FXML
    private void Save() throws IOException{
        System.out.println("Pinindot Mo Save");
    }
}