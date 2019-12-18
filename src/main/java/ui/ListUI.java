package ui;

import controllers.ListController;
import ui.MainPageUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class ListUI extends BorderPane{
  private ListController listController;
  private MainPageUI mainPage;

  public ListUI(MainPageUI mainPage) throws IOException
  {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/todo_list_layout.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
      listController = fxmlLoader.getController();

      this.mainPage = mainPage;
  }

  public ListController getController() {
      return listController;
  }

  public MainPageUI getMainPage()
  {
      return mainPage;
  }
}
