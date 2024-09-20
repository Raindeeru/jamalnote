package com.guoxquiboloy.le3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();

        //Changes made by Payor, Matthew Josh G.
        //Checks for backup file on startup
        if (BackupandRestore.backupChecker())
        {
            promptrestoreUserWork(stage);
        }
    }

    public static void switchToEditor()throws IOException {
        App.setRoot("editor");
    }

    //For Loading Files
    public static void switchToEditor(File file)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editor.fxml"));
        Parent editor = fxmlLoader.load();
        EditorController editorController = fxmlLoader.getController();
        editorController.setFile(file);
        scene.setRoot(editor); 
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //Changes made by Payor, Matthew Josh G.
    // Create a dialog to ask the user if they want to restore from backup
    private void promptrestoreUserWork(Stage stage) 
    {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Restore from Backup");
        dialog.setContentText("Do you want to restore from the previous backup?");
        ButtonType yesRestoreButtonType = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noRestoreButtonType = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(yesRestoreButtonType, noRestoreButtonType);
        dialog.setResultConverter(dialogButton -> 
        {
            if (dialogButton == yesRestoreButtonType) 
            {
                return "Backup Restored";
            } else 
            {
                return "Backup Discarded";
            }
        });
        Optional<String> result = dialog.showAndWait();
        //Application will restore from backup
        if (result.isPresent() && result.get().equals("Yes")) 
        {
            String backupContent = BackupandRestore.restoreUserWork();
            EditorController.getTypeArea().setText(backupContent);
        }
    }
    //Create a backup when the application closes
    @Override
    public void stop() 
    {
        if (EditorController.getTypeArea().getText().trim().length() > 0) 
        {
            BackupandRestore.backupUserWork(EditorController.getTypeArea().getText());
        }
    }
    
    public static void main(String[] args) {
        launch();
    }


}