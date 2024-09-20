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
    //Changes mad by Payor, Matthew Josh G.
    private static TextArea staticTypeArea;
    @FXML
    public void initialize() 
    {
        staticTypeArea = typeArea;
    }
    public static TextArea getTypeArea() 
    {
        return staticTypeArea;
    }

    @FXML
    private void Save() throws IOException{
        System.out.println("Pinindot Mo Save");
        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("TEXT", "*.txt")
        );

        String text = typeArea.getText();
        saveFile.setTitle("Hatdog");
        File file = saveFile.showSaveDialog(null);
<<<<<<< HEAD
        file;
=======
        FileWriter writer = new FileWriter(file);
        if (file != null) {
            writer.write(text);
            writer.close();
        }
>>>>>>> 838e4659a618f313a7e462f65c55f57fbed70630
    }
}