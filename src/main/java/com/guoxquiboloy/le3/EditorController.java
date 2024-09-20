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
        String text = typeArea.getText();
        saveFile.setTitle("Hatdog");
        File file = saveFile.showSaveDialog(null);
        file;
    }
}