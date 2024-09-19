package com.guoxquiboloy.le3;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController {
    @FXML TextArea typeArea;
    @FXML Button saveButton;

    @FXML
    private void Save() throws IOException{
        System.out.println("Pinindot Mo Save");
        FileChooser saveFile = new FileChooser();
        String text = typeArea.getText();
        saveFile.setTitle("Hatdog");
        File file = saveFile.showSaveDialog(null);
        file
    }
}