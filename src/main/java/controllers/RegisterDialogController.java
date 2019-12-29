package controllers;

import ui.RegisterDialogLayout;
import model.Model;
import model.User;
import utils.ComponentFactory;
import utils.LoginManager;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterDialogController implements Initializable{
  @FXML private JFXDialogLayout root;
  @FXML private JFXButton cancelButton;
  @FXML private JFXButton registerButton;
  @FXML private JFXTextField username;
  @FXML private JFXPasswordField password;
  @FXML private JFXPasswordField confirmPassword;
  @FXML private VBox formContainer;

    private FlowPane unsuccessfulRegistrationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cancelButton.setOnAction(event -> ((RegisterDialogLayout)root).getDialog().close());
        unsuccessfulRegistrationLabel = ComponentFactory.createUnsuccessfulRegistrationLabel();
    }

    @FXML
    public void registerUser()
    {
        if(LoginManager.registerUser(username.getText(), password.getText(), confirmPassword.getText()))
        {
            Model.instance().logUserIn(new User(username.getText()));
            ((RegisterDialogLayout)root).getDialog().close();
        }
        else
        {
            if(!formContainer.getChildren().contains(unsuccessfulRegistrationLabel))
                formContainer.getChildren().add(0, unsuccessfulRegistrationLabel);
        }
    }
}
