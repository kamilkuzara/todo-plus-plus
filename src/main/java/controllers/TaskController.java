package controllers;

import ui.TaskUI;
import model.TaskModel;
import javafx.scene.layout.BorderPane;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.control.Label;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.paint.Paint;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable{
  @FXML private BorderPane root;
  @FXML private JFXRippler taskCheckbox;
  @FXML private JFXRippler deleteTask;
  @FXML private JFXTextField taskName;

    private boolean taskCompleted=false;
    private TaskModel taskModel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        taskCheckbox.setMaskType(JFXRippler.RipplerMask.CIRCLE);
    }

    @FXML
    public void deleteTask()
    {
        TaskUI taskToDelete = (TaskUI) root;

        taskToDelete.getList().getController().getListModel().deleteTask(taskModel);
        taskToDelete.getList().getController().deleteTask(taskToDelete);
    }

    @FXML
    public void tickAsCompleted()
    {
        Label checkboxLabel = (Label)taskCheckbox.getChildren().get(0);
        FontIcon icon = (FontIcon)checkboxLabel.getGraphic();

        if(taskCompleted)
        {
            icon.setIconLiteral("mdi-checkbox-blank-outline");
            root.setStyle("-fx-background-color: transparent;");
            taskCompleted = false;
            taskModel.setCompleted(false);
            //root.setBackground(new Background(new BackgroundFill(Paint.valueOf("rgba(78, 227, 73, 0.3)"), null, null)));
        }
        else
        {
            icon.setIconLiteral("mdi-checkbox-marked");
            root.setStyle("-fx-background-color: rgba(78, 227, 73, 0.3);");
            taskCompleted = true;
            taskModel.setCompleted(true);
        }
    }

    public void initCompleted()
    {
      Label checkboxLabel = (Label)taskCheckbox.getChildren().get(0);
      FontIcon icon = (FontIcon)checkboxLabel.getGraphic();


          icon.setIconLiteral("mdi-checkbox-marked");
          root.setStyle("-fx-background-color: rgba(78, 227, 73, 0.3);");
          taskCompleted = true;

          System.out.println("Task background changed");
    }

    @FXML
    public void mouseEntered()
    {
        deleteTask.setVisible(true);
    }

    @FXML
    public void mouseExited()
    {
        deleteTask.setVisible(false);
    }

    public void setName(String name)
    {
        taskName.setText(name);
    }

    public void setTaskModel(TaskModel taskModel)
    {
        this.taskModel = taskModel;
    }

    public void setNameChangeListener()
    {
        taskName.textProperty().addListener((observable, oldValue, newValue) -> {
            taskModel.setName(newValue);
        });
    }
}
