package com.guoxquiboloy.le3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController {
    @FXML TextArea typeArea;
    @FXML Button saveButton;
    @FXML MenuButton fontButton; 
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

    //Changes mad by Payor, Matthew Josh G.
    private static TextArea staticTypeArea;
    private File file;

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
        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("TEXT", "*.txt")
        );

        String text = typeArea.getText();
        saveFile.setTitle("Save File");
        file = saveFile.showSaveDialog(null);
        FileWriter writer = new FileWriter(file);
        if (file != null) {
            writer.write(text);
            writer.close();
        }
    }
    public void setEditorText(String text){
        typeArea.setText(text);
    }

    public void setFile(File file) throws IOException{
        this.file = file;
        
        FileReader reader = new FileReader(file);
        
        String textFromFile = "";

        int i;
        while ((i = reader.read()) != -1) {
            textFromFile += (char)i;
        }
        typeArea.setText(textFromFile);
    }

    @FXML
    private void fontChange() throws IOException{
    }

    @FXML
    private void styleOne() throws IOException{
        Font newFont = Font.font("Arial"); 
        typeArea.setFont(newFont);
    }

    @FXML
    private void styleTwo() throws IOException{
        Font newFont = Font.font("Calibri"); 
        typeArea.setFont(newFont);
    }

    @FXML
    private void styleThree() throws IOException{
        Font newFont = Font.font("Times New Roman"); 
        typeArea.setFont(newFont);
    }

    @FXML
    private void styleFour() throws IOException{
        Font newFont = Font.font("Roboto"); 
        typeArea.setFont(newFont);
    }

    @FXML
    private void styleFive() throws IOException{
        Font newFont = Font.font("Open Sans"); 
        typeArea.setFont(newFont);
    }

    @FXML
    private void fontSizePlus() throws IOException{
        Font font = typeArea.getFont();
        double size = font.getSize();
        typeArea.setFont(Font.font(size + 1));
    }

    @FXML
    private void fontSizeMin() throws IOException{
        Font font = typeArea.getFont();
        double size = font.getSize();

        if(size > 7)
            typeArea.setFont(Font.font(size - 1));
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

}