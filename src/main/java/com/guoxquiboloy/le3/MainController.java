package com.guoxquiboloy.le3;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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
    List<File> recentFiles = new ArrayList<File>();
    List<Button> recentFileButtons = new ArrayList<Button>();

    @FXML
    public void initialize() throws IOException{
        File recentFile = new File("recentFiles.csv");
        List<String> paths = RecentFileManager.getPaths(recentFile);
        System.out.println(paths);
        for(String path: paths){
            File file = new File(path);
            Button button = new Button(file.getName());
            System.out.println(path);
            button.setPrefWidth(recentFileButton.getPrefWidth());
            button.setPrefHeight(recentFileButton.getPrefHeight());
            button.setStyle(recentFileButton.getStyle());
            button.setTextFill(recentFileButton.getTextFill());
            button.setFont(recentFileButton.getFont());

            recentFileButtons.add(button);
            
            recentFilesBar.getChildren().add(button);
            button.setOnAction(event -> {
                try{LoadRecentFile(path);}
                catch(IOException e){throw new RuntimeException(e);}
            });
            recentFiles.add(file);
        }
        if (recentFileButtons.get(0).getText().isEmpty()) {
            recentFileButtons.get(0).setText("No Recent Files");
            recentFileButtons.get(0).setDisable(true);
        }
        recentFilesBar.getChildren().remove(recentFileButton);
    }

    @FXML
    private void OpenNewFile() throws IOException{
        App.switchToEditor((Stage)closeButton.getScene().getWindow());
    }

    @FXML
    private void LoadFile() throws IOException{
        FileChooser loader = new FileChooser();
        loader.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileToLoad = loader.showOpenDialog(stage);
        RecentFileManager.addRecentFile(fileToLoad);
        if (fileToLoad != null) {
            App.switchToEditor(fileToLoad, (Stage)closeButton.getScene().getWindow());
        }
    }

    @FXML
    private void Close() throws IOException{
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML 
    private void LoadRecentFile(String filePath) throws IOException{
        File fileToLoad = new File(filePath);
        RecentFileManager.addRecentFile(fileToLoad);
        if (fileToLoad != null) {
            App.switchToEditor(fileToLoad, (Stage)closeButton.getScene().getWindow());
        }
    }
}
