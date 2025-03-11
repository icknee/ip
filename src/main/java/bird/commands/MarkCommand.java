package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

/**
 * The MarkCommand class is responsible for marking a specific task as done or not done.
 *  This command toggles the status of a task in the TaskList based on the provided flag.
 *  It prints the corresponding message via the Ui and saves the updated state using Storage.
 */

public class MarkCommand extends Command {
    boolean isDone;
    int taskNumber;

    /**
     * Constructs a MarkCommand with the specified status and task number.
     *
     * @param isDone     the status to set for the task; {@code true} marks it as done, {@code false} marks it as not done.
     * @param taskNumberString the 1-indexed position of the task in the TaskList.
     */

    public MarkCommand(boolean isDone, String taskNumberString) throws InvalidCommandException {
        this.isDone = isDone;
        int TaskNumber;
        try {
            TaskNumber = Integer.parseInt(taskNumberString);
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        this.taskNumber = TaskNumber;
    }

    /**
     * Executes the mark command by setting the specified task's status to done or not done.
     * The method performs the following steps:
     * <ol>
     *     <li>Validates if the task number is within the range of existing tasks in the TaskList.</li>
     *     <li>Marks the task as done or not done by updating the task's status.</li>
     *     <li>Displays a corresponding message to the user using the Ui.</li>
     *     <li>Saves the updated TaskList to persistent storage.</li>
     * </ol>
     *
     * @param taskList the TaskList containing the task to be marked.
     * @param storage  the Storage component used to persist the updated TaskList.
     * @throws InvalidCommandException if the task number is out of bounds.
     * @throws IOException             if an error occurs during the saving process.
     */

    @Override
    public void execute(TaskList taskList, Storage storage) throws InvalidCommandException, IOException {
        if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList.get(taskNumber - 1).setDone(isDone);
        if (isDone) {
            Ui.printTaskAsDone(taskList.get(taskNumber - 1));
        } else {
            Ui.printTaskAsNotDone(taskList.get(taskNumber - 1));
        }
        storage.saveFile(taskList, storage);
    }
}
