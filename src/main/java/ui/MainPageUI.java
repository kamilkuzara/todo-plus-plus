package ui;

import controllers.MainPageController;
import model.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainPageUI extends AnchorPane{
  private MainPageController mainPageController;

  public MainPageUI() throws IOException
  {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/main_page_layout.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
      mainPageController = fxmlLoader.getController();

      Model.instance().setMainPageUI(this);
          System.out.println("The model was initialised");
      Model.instance().loadFromFile();
          System.out.println("Loading from a file completed");
  }

  public MainPageController getController() {
      return mainPageController;
  }
}
