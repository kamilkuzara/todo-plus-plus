package controllers;

import ui.RegisterDialogLayout;

import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterDialogController implements Initializable{
  @FXML private JFXDialogLayout root;
  @FXML private JFXButton cancelButton;
  @FXML private JFXButton registerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cancelButton.setOnAction(event -> ((RegisterDialogLayout)root).getDialog().close());
    }
}
