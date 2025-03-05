package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printGoodBye();
    }

}
