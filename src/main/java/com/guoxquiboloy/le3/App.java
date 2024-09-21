package com.guoxquiboloy.le3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        if (BackupandRestore.backupChecker())
        {
            promptrestoreUserWork(stage);
        }
    }

    public static void switchToEditor(Stage oldStage)throws IOException {
        Stage editorStage = new Stage();
        Parent editor = loadFXML("editor");
        Scene editorScene = new Scene(editor);
        editorStage.setScene(editorScene);
        oldStage.close();
        editorStage.show();
    }

    //For Loading Files
    public static void switchToEditor(File file, Stage oldStage)throws IOException{
        Stage editorStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editor.fxml"));
        Parent editor = fxmlLoader.load();
        Scene editorScene = new Scene(editor);
        editorStage.setScene(editorScene);
        EditorController editorController = fxmlLoader.getController();

        editorController.setFile(file);
        if (!file.getPath().equals(BackupandRestore.BACKUP_FILE)) {
            editorController.setFilePath(file.getAbsolutePath());   
        }

        oldStage.close();
        editorStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void switchToMenu(Stage oldStage) throws IOException{
        Stage newStage = new Stage();
        Parent root = loadFXML("main");
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        oldStage.fireEvent(new WindowEvent(oldStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        if (!oldStage.isShowing()) {
            newStage.show();
        }

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
        try{
            boolean backupExist = BackupandRestore.backupChecker();
            if(backupExist){
                Optional<String> result = dialog.showAndWait();
                //Application will restore from backup
                if (result.isPresent() && result.get().equals("Backup Restored")) 
                {
                    try{switchToEditor(new File("backup.txt"), stage);}
                    catch(IOException e){throw new RuntimeException(e);}
                
                }
            }
        }
        catch(IOException e){throw new RuntimeException(e);}
        
    }

    
    public static void main(String[] args) {
        launch();
    }


}