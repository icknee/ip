package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.*;

import java.io.IOException;

/**
 *  The AddCommand class represents a command that adds a new task to the task list.
 *  It supports creating three types of tasks:
 *  <ul>
 *  <li>ToDo tasks, initialized with a description.</li>
 *  <li>Deadline tasks, initialized with a description and a deadline time.</li>
 *  <li>Event tasks, initialized with a description, a starting time, and an ending time.</li>
 *  </ul>
 *  When executed, this command creates the appropriate task object,
 *  adds it to the task list, displays a confirmation to the user,
 *  updates the task count, and persists the updated list via the storage.
 */
public class AddCommand extends Command {
    String type;
    String description;
    String by;
    String from;
    String to;

    /**
     * Constructs an AddCommand for a ToDo task.
     *
     * @param toDoDescription the description of the ToDo task.
     */

    public AddCommand(String toDoDescription) {
        this.type = "ToDos";
        this.description = toDoDescription;
    }

    /**
     * Constructs an AddCommand for a Deadline task.
     *
     * @param deadlineDescription the description of the deadline task.
     * @param by                  the deadline time for the task.
     */

    public AddCommand(String deadlineDescription, String by) {
        this.type = "Deadlines";
        this.description = deadlineDescription;
        this.by = by;
    }

    /**
     * Constructs an AddCommand for an Event task.
     *
     * @param eventDescription the description of the event task.
     * @param eventFrom        the starting time of the event.
     * @param eventTo          the ending time of the event.
     */

    public AddCommand(String eventDescription, String eventFrom, String eventTo) {
        this.type = "Events";
        this.description = eventDescription;
        this.from = eventFrom;
        this.to = eventTo;
    }

    /**
     * Executes the add command by creating a new task based on the command type and adding it to the task list.
     * The method performs the following steps:
     * <ol>
     *     <li>Determines the task type and creates the corresponding task instance.</li>
     *     <li>Adds the newly created task to the task list.</li>
     *     <li>Displays a confirmation message to the user via the user interface.</li>
     *     <li>Updates the task count in the task list.</li>
     *     <li>Saves the updated task list to persistent storage.</li>
     * </ol>
     *
     * @param taskList the task list to which the new task will be added.
     * @param storage  the storage component used for saving the task list.
     * @throws IOException             if an error occurs during file operations.
     * @throws InvalidCommandException if an unrecognized task type is encountered.
     */

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
