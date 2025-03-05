package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    boolean isDone;
    int taskNumber;
    public MarkCommand(boolean isDone, int taskNumber) {
        this.isDone = isDone;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws InvalidCommandException, IOException {
        if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList.get(taskNumber - 1).setDone(isDone);
        Ui.printTaskAsDone(taskList.get(taskNumber - 1));
        storage.saveFile(taskList, storage);
    }
}
