package bird.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Integer taskCount = 0;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void addTaskCount() {
        this.taskCount += 1;
    }

    public void SubtractTaskCount() {
        this.taskCount -= 1;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getTaskCount() {
        return taskCount;
    }
}
