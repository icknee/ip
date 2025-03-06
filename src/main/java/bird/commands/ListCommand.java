package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.TaskList;

/**
 * The ListCommand class is responsible for printing all tasks from the task list.
 *  When executed, this command retrieves the current number of tasks from the
 *  TaskList and utilizes the Ui class to print a formatted list of all tasks.
 */

public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method prints the entire task list using the {@link Ui#printTaskList(TaskList, Integer)}
     * method and displays the current tasks with their corresponding indices.
     *
     * @param taskList the TaskList from which tasks are retrieved.
     * @param storage  the Storage component (unused in this command).
     */

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printTaskList(taskList, taskList.getTaskCount());
    }
}
