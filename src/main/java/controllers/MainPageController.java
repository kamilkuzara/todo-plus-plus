package controllers;

import ui.MainPageUI;
import ui.ListUI;
import model.Model;
import model.ListModel;
import model.TaskModel;
import utils.ComponentFactory;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.events.JFXDrawerEvent;
import org.kordamp.ikonli.javafx.*;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.util.List;

public class MainPageController implements Initializable{
  @FXML private AnchorPane root;
  @FXML private BorderPane mainContent;
  @FXML private JFXRippler menuButton;
  @FXML private JFXDrawer menu;
//  @FXML private JFXRippler searchButton;
//  @FXML private StackPane search;
  @FXML private VBox listOuterContainer;
  @FXML private VBox listContainer;
  @FXML private JFXRippler gitHubLink;
  @FXML private JFXRippler linkedInLink;

    private JFXRippler currentList;
    private JFXPopup clipboardPopup;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        menuButton.setMaskType(JFXRippler.RipplerMask.CIRCLE);
//        searchButton.setMaskType(JFXRippler.RipplerMask.CIRCLE);

        menu.setOverLayVisible(true);

        menu.setOnDrawerClosed(new EventHandler<JFXDrawerEvent>(){
            public void handle(JFXDrawerEvent event) {
                menu.setVisible(false);
                AnchorPane.setLeftAnchor(mainContent, 0.0);
            };
        });

        clipboardPopup = ComponentFactory.createClipboardPopup();
    }

    @FXML
    public void menuOpenClose()
    {
        if(menu.isClosed() || menu.isClosing())
        {
            AnchorPane.setLeftAnchor(mainContent, menu.getDefaultDrawerSize());
            menu.setVisible(true);
            menu.open();
        }
        else
        {
            menu.close();
            menu.setVisible(false);
            AnchorPane.setLeftAnchor(mainContent, 0.0);
        }
    }

/*    @FXML
    public void searchOpenClose()
    {
        if(!search.isVisible())
        {
            AnchorPane.setTopAnchor(listOuterContainer, 105.0);
            search.setVisible(true);
        }
        else
        {
            search.setVisible(false);
            AnchorPane.setTopAnchor(listOuterContainer, 55.0);
        }
    }*/

    @FXML
    public void createNewList()
    {
        String listName = "New List";
        ListModel listModel = new ListModel(listName);

        createNewList(listModel, listName);
    }

    public void createNewList(ListModel listModel, String listName)
    {
        try
        {
            ListUI todoListUI = new ListUI((MainPageUI)root);

            Label listNameLabel = new Label(listName);
            JFXRippler listLink = ComponentFactory.createListLink(listNameLabel);
            listLink.setOnMouseClicked(event -> {
                mainContent.setCenter(todoListUI);

                if(currentList != null)
                    currentList.setStyle("-fx-background-color: transparent;");

                listLink.setStyle("-fx-background-color: #bfbfbf;");
                currentList = listLink;
            });

            todoListUI.getController().setListModel(listModel);
            todoListUI.getController().setName(listName);
            todoListUI.getController().setListLink(listLink);
            todoListUI.getController().setNameChangeListener();

            listContainer.getChildren().add(listLink);

            Model.instance().addList(listModel);

            if(listModel.hasTasks())
                createNewTasks(listModel, todoListUI);

                System.out.println("The list was created: " + listName);
        }
        catch(IOException exception)
        {
            System.out.println("The list could not be created");
            exception.printStackTrace();
        }
    }

    private void createNewTasks(ListModel listModel, ListUI listUI)
    {
            System.out.println("Creating tasks");
        List<TaskModel> tasks = listModel.getTasks();
            System.out.println("The size of the list: " + tasks.size());
        for(TaskModel newTask : tasks){
            listUI.getController().addNewTask(newTask, newTask.getName());
                System.out.println("Task number: ");
        }

        /*for(int i = 0; i < tasks.size(); i++){
            listUI.getController().addNewTask(tasks.get(i), tasks.get(i).getName());
                System.out.println("Task number: " + i);
        }*/
    }

    public void setCurrentList(JFXRippler currentList)
    {
        this.currentList = currentList;
    }

    @FXML
    public void openGitHub()
    {
        copyToClipboard("github.com/kamilkuzara");
    }

    @FXML
    public void openLinkedIn()
    {
        copyToClipboard("linkedin.com/in/kamil-kuzara");
    }

    @FXML
    public void showClipboardPopup(MouseEvent event)
    {
        clipboardPopup.show( (JFXRippler)event.getSource(), JFXPopup.PopupVPosition.BOTTOM,
                          JFXPopup.PopupHPosition.LEFT, 0, ((JFXRippler)event.getSource()).getHeight() );
    }

    @FXML
    public void hideClipboardPopup()
    {
        clipboardPopup.hide();
    }

    private void copyToClipboard(String address)
    {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(address);
        clipboard.setContent(content);
    }
}
