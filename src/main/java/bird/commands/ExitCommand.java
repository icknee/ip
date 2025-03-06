package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.TaskList;

/**
 *  The ExitCommand class represents a command that terminates the application.
 */

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the exit command.
     * This method prints a goodbye message to the user and signals
     * the application to exit by setting the exit flag.
     *
     * @param taskList the task list (unused in this command).
     * @param storage  the storage component (unused in this command).
     */

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printGoodBye();
    }

}
