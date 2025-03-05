package bird.commands;

import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

public abstract class Command {
    Boolean isExit = false;

    public void execute(TaskList taskList, Storage storage) throws InvalidCommandException, IOException {

    }

    public boolean isExit() {
        return isExit;
    }

}
