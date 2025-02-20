package bird;

import bird.task.Task;
import bird.task.TaskHandler;

import java.io.IOException;
import java.util.ArrayList;

public class CommandHandler {
    static int executeCommand(String command, ArrayList<Task> taskList, int taskCount, String line) throws InvalidCommandException, IOException {
        switch (command) {
            case "list":
                ConsoleFormatter.printTaskList(taskList, taskCount);
                break;
            case "mark":
                TaskHandler.markTaskAsDone(taskList, line, taskCount);
                FileHandler.saveFile(taskList);
                break;
            case "unmark":
                TaskHandler.markTaskAsNotDone(taskList, line, taskCount);
                FileHandler.saveFile(taskList);
                break;
            case "todo":
                TaskHandler.addTodos(taskList, line, taskCount);
                taskCount++;
                FileHandler.saveFile(taskList);
                break;
            case "deadline":
                TaskHandler.addDeadlines(taskList, line, taskCount);
                taskCount++;
                FileHandler.saveFile(taskList);
                break;
            case "event":
                TaskHandler.addEvents(taskList, line, taskCount);
                taskCount++;
                FileHandler.saveFile(taskList);
                break;
            case "delete":
                TaskHandler.deleteTask(taskList, line, taskCount);
                taskCount--;
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + line + "\n" + "\t" + "try entering 'list' to see the list of tasks!");
        }
        return taskCount;
    }

}
