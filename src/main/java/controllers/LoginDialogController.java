package controllers;

import ui.LoginDialogLayout;
import utils.LoginManager;
import utils.ComponentFactory;
import model.Model;
import model.User;

import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginDialogController implements Initializable{
  @FXML private JFXDialogLayout root;
  @FXML private JFXButton cancelButton;
  @FXML private JFXButton loginButton;
  @FXML private JFXTextField username;
  @FXML private JFXPasswordField password;
  @FXML private VBox formContainer;

    private FlowPane unsuccessfulLoginLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cancelButton.setOnAction(event -> ((LoginDialogLayout)root).getDialog().close());
        unsuccessfulLoginLabel = ComponentFactory.createUnsuccessfulLoginLabel();
    }

    @FXML
    public void logIn()
    {
        if(LoginManager.logUserIn(username.getText(), password.getText()))
        {
            Model.instance().logUserIn(new User(username.getText()));
            ((LoginDialogLayout)root).getDialog().close();
        }
        else
        {
            if(!formContainer.getChildren().contains(unsuccessfulLoginLabel))
                formContainer.getChildren().add(0, unsuccessfulLoginLabel);
        }
    }
}
