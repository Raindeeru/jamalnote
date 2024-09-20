package com.guoxquiboloy.le3;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController{
    

    @FXML Button newFileButton;
    @FXML Button loadButton;
    @FXML Button closeButton;
    @FXML Button recentFileButton;
    @FXML VBox recentFilesBar;

    Stage stage;
    
    @FXML
    public void initialize(){
        recentFilesBar.getChildren().add(new Button());
        System.out.println(recentFileButton.getProperties());
    }

    @FXML
    private void OpenNewFile() throws IOException{
        App.switchToEditor();
    }

    @FXML
    private void LoadFile() throws IOException{
        FileChooser loader = new FileChooser();
        loader.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileToLoad = loader.showOpenDialog(stage);
        RecentFileManager.addRecentFile(fileToLoad);
        if (fileToLoad != null) {
            App.switchToEditor(fileToLoad);
        }
    }

    @FXML
    private void Close() throws IOException{
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML void LoadRecentFile() throws IOException{

    }
}
