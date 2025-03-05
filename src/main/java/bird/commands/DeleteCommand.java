package bird.commands;

import bird.Ui;
import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
