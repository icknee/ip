package bird.task;

import bird.ConsoleFormatter;
import bird.exceptions.InvalidCommandException;

import java.util.ArrayList;

public class TaskHandler {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;


    public static void addTodos(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        if (line.length() < 6) {
            throw new InvalidCommandException("todo <task>");
        }
        String description = line.substring(TODO_DESCRIPTION_INDEX);
        ToDos newToDos = new ToDos(description);
        taskList.add(newToDos);
        ConsoleFormatter.printNewTaskAdded(taskCount, newToDos);
    }

    public static void addDeadlines(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        try {
            int byIndex = line.indexOf("/");
            String description = line.substring(DEADLINE_DESCRIPTION_INDEX, byIndex - 1);
            String by = line.substring(byIndex + 4);
            Deadlines newDeadlines = new Deadlines(description, by);
            taskList.add(newDeadlines);
            ConsoleFormatter.printNewTaskAdded(taskCount, newDeadlines);
        } catch (Exception e) {
            throw new InvalidCommandException("deadline <task> /by <time>");
        }
    }

    public static void addEvents(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        try {
            int fromIndex = line.indexOf("/");
            int toIndex = line.indexOf("/", fromIndex + 1);
            String description = line.substring(EVENT_DESCRIPTION_INDEX, fromIndex - 1);
            String from = line.substring(fromIndex + 6, toIndex - 1);
            String to = line.substring(toIndex + 4);
            Events newEvents = new Events(description, from, to);
            taskList.add(newEvents);
            ConsoleFormatter.printNewTaskAdded(taskCount, newEvents);
        } catch (Exception e) {
            throw new InvalidCommandException("event <task> /from <time> /to <time>");
        }
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(line.split(" ")[1]);
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList.get(taskNumber - 1).setDone(true);
        ConsoleFormatter.printTaskAsDone(taskList.get(taskNumber - 1));
    }

    public static void markTaskAsNotDone(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(line.split(" ")[1]);
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList.get(taskNumber - 1).setDone(false);
        ConsoleFormatter.printTaskAsNotDone(taskList.get(taskNumber - 1));
    }

    public static void deleteTask(ArrayList<Task> taskList, String line, int taskCount) throws InvalidCommandException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(line.split(" ")[1]);
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidCommandException("Task does not exist");
        }
        ConsoleFormatter.printTaskAsDeleted(taskList.get(taskNumber - 1), taskCount);
        taskList.remove(taskNumber - 1);
    }
}
