package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printTaskList(taskList, taskList.getTaskCount());
    }
}
