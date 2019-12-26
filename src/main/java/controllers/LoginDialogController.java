package controllers;

import ui.LoginDialogLayout;

import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginDialogController implements Initializable{
  @FXML private JFXDialogLayout root;
  @FXML private JFXButton cancelButton;
  @FXML private JFXButton loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cancelButton.setOnAction(event -> ((LoginDialogLayout)root).getDialog().close());
    }
}
