package model;

import java.util.List;
import java.util.ArrayList;

public class ListModel{
    private String listName;
    private List<TaskModel> tasks;

    public ListModel(String name)
    {
        listName = name;
        tasks = new ArrayList<>();
        //System.out.println("The list was created");
    }

    public void addTask(TaskModel task)
    {
        tasks.add(task);
        //System.out.println("The task was added. No. of tasks: " + tasks.size());
    }

    public void deleteTask(TaskModel task)
    {
        tasks.remove(task);
        //System.out.println("The task was deleted. No. of tasks: " + tasks.size());
    }

    public void setName(String name)
    {
        listName = name;
        //System.out.println("New list name: " + listName);
    }

    public String getName()
    {
        return listName;
    }

    public boolean hasTasks()
    {
        return !tasks.isEmpty();
    }

    public List<TaskModel> getTasks()
    {
        return tasks;
    }

    public boolean contains(TaskModel task)
    {
        return tasks.contains(task);
    }
}
