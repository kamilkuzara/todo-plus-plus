package controllers;

import ui.TaskUI;
import ui.ListUI;
import model.ListModel;
import model.TaskModel;
import model.Model;
import utils.ComponentFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPopup;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

public class ListController implements Initializable{
  @FXML private BorderPane root;
  @FXML private JFXTextField listName;
  @FXML private JFXRippler listMenuButton;
  @FXML private VBox tasks;

    private JFXPopup listMenu;
    private JFXRippler listLink;
    private ListModel listModel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listMenuButton.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        listMenu = ComponentFactory.createListMenu(this);
    }

    @FXML
    public void addNewTask()
    {
        String taskName = "New Task";
        TaskModel taskModel = new TaskModel(taskName);

          System.out.println("Creating task");
        addNewTask(taskModel, taskName);
    }

    public void addNewTask(TaskModel taskModel, String taskName)
    {
        try
        {
            TaskUI taskUI = new TaskUI((ListUI)root);



            taskUI.getController().setTaskModel(taskModel);
            taskUI.getController().setName(taskName);
            taskUI.getController().setNameChangeListener();

            tasks.getChildren().add(taskUI);

            if(!listModel.contains(taskModel))
            {
                listModel.addTask(taskModel);
            }

            System.out.println("Task completed: " + taskModel.isCompleted());
          if(taskModel.isCompleted())
          {
                System.out.println("This task is completed");
              taskUI.getController().initCompleted();
          }
          

                System.out.println("The task was created: " + taskName);
        }
        catch(IOException exception)
        {
            System.out.println("The task could not be created");
            exception.printStackTrace();
        }
    }

    @FXML
    public void openListMenu()
    {
        listMenu.show(listMenuButton, JFXPopup.PopupVPosition.TOP,
                      JFXPopup.PopupHPosition.RIGHT, 0, listMenuButton.getHeight());
    }

    public void deleteList(MouseEvent event)
    {
        Model.instance().deleteList(listModel);

        if(listMenu.isShowing())
            listMenu.hide();

        BorderPane parent = (BorderPane)root.getParent();
        parent.setCenter(ComponentFactory.createContentPlaceholder());

        VBox links = (VBox)listLink.getParent();
        links.getChildren().remove(listLink);

        ((ListUI)root).getMainPage().getController().setCurrentList(null);
    }

    public void deleteTask(TaskUI taskToDelete)
    {
        tasks.getChildren().remove(taskToDelete);
    }

    public void setListLink(JFXRippler listLink)
    {
        this.listLink = listLink;
    }

    public void setName(String name)
    {
        listName.setText(name);
    }

    public void setListModel(ListModel listModel)
    {
        this.listModel = listModel;
    }

    public ListModel getListModel()
    {
        return listModel;
    }

    public void setNameChangeListener()
    {
        listName.textProperty().addListener((observable, oldValue, newValue) -> {
            Label linkLabel = (Label)listLink.getChildren().get(0);
            linkLabel.setText(newValue);

            listModel.setName(newValue);
        });
    }
}
