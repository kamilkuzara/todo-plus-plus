package model;

public class TaskModel{
    private String taskName;
    private boolean isCompleted;

    public TaskModel(String name)
    {
        taskName = name;
        isCompleted = false;
        //System.out.println("The task was created");
    }

    public void setName(String name)
    {
        taskName = name;
        //System.out.println("Task new name: " + taskName);
    }

    public String getName()
    {
        return taskName;
    }

    public void setCompleted(boolean isCompleted)
    {
        this.isCompleted = isCompleted;
        //System.out.println("The task was marked: " + isCompleted);
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }
}
