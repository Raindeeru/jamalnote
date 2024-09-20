package com.guoxquiboloy.le3;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController{
    

    @FXML Button newFileButton;
    @FXML Button loadButton;
    @FXML Button closeButton;

    Stage stage;
    

    @FXML
    private void OpenNewFile() throws IOException{
        App.switchToEditor();
    }

    @FXML
    private void LoadFile() throws IOException{
        System.out.println("Pinindot mo Load File");
        FileChooser loader = new FileChooser();
        loader.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileToLoad = loader.showOpenDialog(stage);
        if (fileToLoad != null) {
            String path = fileToLoad.getAbsolutePath();
            System.out.println(path);
        }
    }

    @FXML
    private void Close() throws IOException{
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
