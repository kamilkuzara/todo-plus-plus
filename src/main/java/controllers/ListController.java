package controllers;

import ui.TaskUI;
import ui.ListUI;
import model.ListModel;
import model.TaskModel;
import model.Model;
import utils.ComponentFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPopup;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
  @FXML private ScrollPane listScrollPane;
  @FXML private VBox tasks;
  @FXML private StackPane listPlaceholder;

    private JFXPopup listMenu;
    private JFXRippler listLink;
    private ListModel listModel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listMenuButton.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        listMenu = ComponentFactory.createListMenu(this);

        tasks.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> change) {
                if(change.next() && change.wasAdded())
                        Platform.runLater(() -> listScrollPane.setVvalue(1.0));
            }
        });
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

            // after adding the task, check if it's the only one in the list
            // if it is, hide the placeholder and make the list itself visible
            if(tasks.getChildren().size() == 1)
            {
                listPlaceholder.setVisible(false);
                listScrollPane.setVisible(true);
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

        ((ListUI)root).getMainPage().getController().deleteList(listLink);

        ((ListUI)root).getMainPage().getController().setCurrentList(null);
    }

    public void deleteTask(TaskUI taskToDelete)
    {
        tasks.getChildren().remove(taskToDelete);

        // after deleting the task, check if it was the last one,
        // if so, then hide the list and show the placeholder
        if(tasks.getChildren().size() == 0)
        {
            listScrollPane.setVisible(false);
            listPlaceholder.setVisible(true);
        }
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
