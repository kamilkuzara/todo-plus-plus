package model;

import utils.JSONParser;
import ui.MainPageUI;
import java.util.List;
import java.util.ArrayList;

public class Model{
    private static Model instance;

    private List<ListModel> lists;
    private User user;
    private MainPageUI mainPageUI;

    public static Model instance()
    {
        if(instance == null)
          instance = new Model();

        return instance;
    }

    private Model()
    {
        lists = new ArrayList<>();
        user = null;
    }

    public void setMainPageUI(MainPageUI mainPageUI)
    {
        this.mainPageUI = mainPageUI;
    }

    public void addList(ListModel list)
    {
        lists.add(list);
        //System.out.println("The list was added. No. of lists: " + lists.size());
    }

    public void deleteList(ListModel list)
    {
        lists.remove(list);
        //System.out.println("The list was deleted. No. of lists: " + lists.size());
    }

    public void loadFromFile()
    {
        ArrayList<ListModel> newLists = JSONParser.loadFromJSON();

        if(newLists == null || newLists.isEmpty())
            return;

        for(ListModel list : newLists)
            mainPageUI.getController().createNewList(list, list.getName());
    }

    public void saveToFile()
    {
        JSONParser.saveToJSON(lists);
    }

    public void logUserIn(User user)
    {
        this.user = user;
        mainPageUI.getController().logUserIn(user.getUsername());
          System.out.println("Successfully logged in");
    }

    public void logUserOut()
    {
        // TODO: here the database records of the user should be updated

        if(user != null)
            user = null;

        mainPageUI.getController().logUserOut();
          System.out.println("Successfully logged out");
    }
}
