package com.guoxquiboloy.le3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Main{

    @FXML Button newFileButton;
    @FXML Button loadButton;
    @FXML Button closeButton;

    @FXML
    private void OpenNewFile() throws IOException{
        System.out.println("Pinindot mo New File");
    }

    @FXML
    private void LoadFile() throws IOException{
        System.out.println("Pinindot mo Load File");
    }

    @FXML
    private void Close() throws IOException{
        System.out.println("Pinindot mo Close");
    }
}
