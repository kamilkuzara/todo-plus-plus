package ui;

import controllers.TaskController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class TaskUI extends BorderPane{
  private ListUI parent;
  private TaskController taskController;

  public TaskUI(ListUI parent) throws IOException
  {
      this.parent = parent;
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/task_layout.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
      taskController = fxmlLoader.getController();
  }

  public TaskController getController() {
      return taskController;
  }

  public ListUI getList()
  {
      return parent;
  }
}
