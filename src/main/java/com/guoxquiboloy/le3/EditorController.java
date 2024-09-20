package com.guoxquiboloy.le3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController {
    @FXML TextArea typeArea;
    @FXML Button saveButton;
    @FXML Button fontButton; 
    @FXML Button fontSizeButton;
    @FXML Button textColorButton;

    private String filePath;

    @FXML
    private void Save() throws IOException{
        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("TEXT", "*.txt")
        );

        String text = typeArea.getText();
        saveFile.setTitle("Hatdog");
        File file = saveFile.showSaveDialog(null);
        FileWriter writer = new FileWriter(file);
        if (file != null) {
            writer.write(text);
            writer.close();
        }
    }
    public void setEditorText(String text){
        typeArea.setText(text);
    }

    public void setFilePath(String path){
        filePath = path;
    }
}