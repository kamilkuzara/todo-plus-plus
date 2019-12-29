package ui;

import controllers.LoginDialogController;

import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;

public class LoginDialogLayout extends JFXDialogLayout{
  private LoginDialogController loginDialogController;
  private JFXDialog dialog;

  public LoginDialogLayout(JFXDialog dialog)
  {
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/login_dialog_layout.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
      loginDialogController = fxmlLoader.getController();

      this.dialog = dialog;
    }
    catch(IOException exception)
    {
        exception.printStackTrace();
    }
  }

  public LoginDialogController getController()
  {
      return loginDialogController;
  }

  public JFXDialog getDialog()
  {
      return dialog;
  }
}
