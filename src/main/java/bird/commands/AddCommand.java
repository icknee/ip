package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.*;

import java.io.IOException;

public class AddCommand extends Command {
    String type;
    String description;
    String by;
    String from;
    String to;

    public AddCommand(String toDoDescription) {
        this.type = "ToDos";
        this.description = toDoDescription;
    }

    public AddCommand(String deadlineDescription, String by) {
        this.type = "Deadlines";
        this.description = deadlineDescription;
        this.by = by;
    }

    public AddCommand(String eventDescription, String eventFrom, String eventTo) {
        this.type = "Events";
        this.description = eventDescription;
        this.from = eventFrom;
        this.to = eventTo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException, InvalidCommandException {
        Task newTask;
        switch (type) {
            case "ToDos":
                newTask = new ToDos(description);
                break;
            case "Deadlines":
                newTask = new Deadlines(description, by);
                break;
            case "Events":
                newTask = new Events(description, from, to);
                break;
            default:
                throw new InvalidCommandException("Error adding new task");
        }
        taskList.add(newTask);
        Ui.printNewTaskAdded(taskList.getTaskCount(), newTask);
        taskList.addTaskCount();
        storage.saveFile(taskList, storage);
    }
}
