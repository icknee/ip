package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

/**
 *  The DeleteCommand class is responsible for deleting a task from the task list.
 *  This command takes a task number as input and deletes the corresponding task from
 *  the TaskList.
 */

public class DeleteCommand extends Command {
    int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber the 1-indexed position of the task to be deleted.
     */

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     * The method performs the following operations:
     * <ol>
     *     <li>Validates that the provided task number is within the valid range of existing tasks.</li>
     *     <li>If the task number is invalid, an {@link bird.exceptions.InvalidCommandException} is thrown.</li>
     *     <li>If valid, prints a confirmation message that the task is deleted.</li>
     *     <li>Removes the task from the TaskList.</li>
     *     <li>Updates the task count.</li>
     *     <li>Saves the updated TaskList to persistent storage.</li>
     * </ol>
     *
     * @param taskList the TaskList from which the task should be removed.
     * @param storage  the Storage used to persist the updated TaskList.
     * @throws InvalidCommandException if the specified task number is not valid.
     * @throws IOException             if an error occurs during the saving of the TaskList.
     */

    @Override
    public void execute(TaskList taskList, Storage storage) throws InvalidCommandException, IOException {
        if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
            throw new InvalidCommandException("Task does not exist");
        }
        Ui.printTaskAsDeleted(taskList.get(taskNumber - 1), taskList.getTaskCount());
        taskList.remove(taskNumber - 1);
        taskList.SubtractTaskCount();
        storage.saveFile(taskList, storage);
    }
}
