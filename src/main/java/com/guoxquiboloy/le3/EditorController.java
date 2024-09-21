package com.guoxquiboloy.le3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController {
    @FXML TextArea typeArea;
    @FXML Button saveButton;
    @FXML MenuButton fontButtons;
    @FXML MenuItem fontStyle1; 
    @FXML MenuItem fontStyle2; 
    @FXML MenuItem fontStyle3; 
    @FXML MenuItem fontStyle4; 
    @FXML MenuItem fontStyle5; 
    @FXML Button fontSizePlusButton;
    @FXML Button fontSizeMinButton;
    @FXML Button colorButtonOne;
    @FXML Button colorButtonTwo;
    @FXML Button colorButtonThree;
    @FXML Button colorButtonFour;
    @FXML Button colorButtonFive;
        
    private Stage stage;

    private static TextArea staticTypeArea;
    private static String originalText = "";
    private File file;
    private String filePath = "";

    @FXML
    public void initialize() 
    {
        staticTypeArea = typeArea;
        fontButtons.setText(typeArea.getFont().getName());
        Platform.runLater(() -> {
            stage = (Stage) typeArea.getScene().getWindow();
            stage.setOnCloseRequest(event -> handleCloseRequest(event));
        });
    }

    private void handleCloseRequest(WindowEvent event) {
        if (this.CheckIfChanged() || (filePath.isEmpty() && !typeArea.getText().isEmpty())) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText("You have unsaved changes.");
            alert.setContentText("Would you like to save or discard your changes?");

            ButtonType saveButton = new ButtonType("Save");
            ButtonType discardButton = new ButtonType("Discard");
            ButtonType cancelButton = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(saveButton, discardButton, cancelButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == saveButton) {
                    try {
                        Save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (response == discardButton) {
                    BackupandRestore.backupUserWork(typeArea.getText());
                    ((Stage)this.typeArea.getScene().getWindow()).close();
                } else {
                    event.consume(); // Cancel the close request
                }
            });
        }
    }

    public static TextArea getTypeArea() 
    {
        return staticTypeArea;
    }

    @FXML
    private void Save() throws IOException{
        BackupandRestore.clearBackup();
        String text = typeArea.getText();
        if (filePath.isEmpty()) {
            FileChooser saveFile = new FileChooser();
            saveFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("TEXT", "*.txt")
            );
    
            saveFile.setTitle("Save File");
            file = saveFile.showSaveDialog(null);   
            filePath = file.getAbsolutePath();         
        }
        else file = new File(filePath);

        FileWriter writer = new FileWriter(file);

        if (file != null) {
            writer.write(text);
            writer.close();
            originalText = text;
            RecentFileManager.addRecentFile(file);
        }       
    }
    public void setEditorText(String text){
        typeArea.setText(text);
    }

    public void setFile(File file) throws IOException {
        this.file = file;

        FileReader reader = new FileReader(file);

        String textFromFile = "";

        int i;
        while ((i = reader.read()) != -1) {
            textFromFile += (char) i;
        }
        typeArea.setText(textFromFile);
        originalText = textFromFile;

        if (!file.getPath().equals(BackupandRestore.BACKUP_FILE)) {
            ((Stage)typeArea.getScene().getWindow()).setTitle(file.getName());
        }
        reader.close();
        BackupandRestore.clearBackup();
    }

    @FXML
    private void styleOne() throws IOException{
        Font font = typeArea.getFont(); 
        double size = font.getSize(); 
        Font newFont = Font.font("Arial", size); 
        typeArea.setFont(newFont);
        fontButtons.setText("Arial");
    }

    @FXML
    private void styleTwo() throws IOException{
        Font font = typeArea.getFont(); 
        double size = font.getSize(); 
        Font newFont = Font.font("Calibri", size); 
        typeArea.setFont(newFont);
        fontButtons.setText("Calibri");
    }

    @FXML
    private void styleThree() throws IOException{
        Font font = typeArea.getFont(); 
        double size = font.getSize(); 
        Font newFont = Font.font("Times New Roman", size); 
        typeArea.setFont(newFont);
        fontButtons.setText("Times New Roman");
    }

    @FXML
    private void styleFour() throws IOException{
        Font font = typeArea.getFont(); 
        double size = font.getSize(); 
        Font newFont = Font.font("Roboto", size); 
        typeArea.setFont(newFont);
        fontButtons.setText("Roboto");
    }

    @FXML
    private void styleFive() throws IOException{
        Font font = typeArea.getFont(); 
        double size = font.getSize(); 
        Font newFont = Font.font("Georgia", size); 
        typeArea.setFont(newFont);
        fontButtons.setText("Georgia");
    }

    @FXML
    private void fontSizePlus() throws IOException{
        Font font = typeArea.getFont();
        double size = font.getSize();
        typeArea.setFont(Font.font(font.getName(), size + 5));
    }

    @FXML
    private void fontSizeMin() throws IOException{
        Font font = typeArea.getFont();
        double size = font.getSize();

        if(size > 7)
            typeArea.setFont(Font.font(font.getName(), size - 5));
    }

    @FXML
    private void colorOne() throws IOException{
        typeArea.setStyle("-fx-text-fill:  #FFC0CB;"); 
    }

    @FXML
    private void colorTwo() throws IOException{
        typeArea.setStyle("-fx-text-fill:  #C3B1E1;"); 
    }

    @FXML
    private void colorThree() throws IOException{
        typeArea.setStyle("-fx-text-fill:    #F89880;"); 
    }

    @FXML
    private void colorFour() throws IOException{
        typeArea.setStyle("-fx-text-fill:  #ADD8E6;"); 
    }

    @FXML
    private void colorFive() throws IOException{
        typeArea.setStyle("-fx-text-fill:  #000000;"); 
    }


    @FXML private void BackToMenu() throws IOException{
        App.switchToMenu((Stage)typeArea.getScene().getWindow());
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public boolean CheckIfChanged(){
        return !originalText.equals(typeArea.getText());
    }
}