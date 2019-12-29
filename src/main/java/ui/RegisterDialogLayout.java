package ui;

import controllers.RegisterDialogController;

import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;

public class RegisterDialogLayout extends JFXDialogLayout{
  private RegisterDialogController registerDialogController;
  private JFXDialog dialog;

  public RegisterDialogLayout(JFXDialog dialog)
  {
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/register_dialog_layout.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
      registerDialogController = fxmlLoader.getController();

      this.dialog = dialog;
    }
    catch(IOException exception)
    {
        exception.printStackTrace();
    }
  }

  public RegisterDialogController getController()
  {
      return registerDialogController;
  }

  public JFXDialog getDialog()
  {
      return dialog;
  }
}
