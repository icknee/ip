package bird.commands;

import bird.exceptions.InvalidCommandException;
import bird.storage.Storage;
import bird.task.TaskList;

import java.io.IOException;

/**
 * The abstract Command class serves as the blueprint for all command types in the Bird application.
 *  Each subclass of Command must provide an implementation for the {@code execute} method. This method
 *  defines the specific behavior of the command by manipulating the task list and interacting with the storage.
*/

public abstract class Command {
    Boolean isExit = false;

    /**
     * Executes the command.
     * This method must be overridden by all child classes. It defines how the command will affect the
     * {@code TaskList} and the {@code Storage}. Exceptions related to command validity or I/O issues may be thrown.
     *
     * @param taskList the task list that the command will manipulate
     * @param storage  the storage system used for persisting task data
     * @throws InvalidCommandException if the command is invalid or cannot be processed
     * @throws IOException             if an I/O error occurs during execution
     */

    public abstract void execute(TaskList taskList, Storage storage) throws InvalidCommandException, IOException;

    /**
     * Checks if the execution of this command signals the application to exit.
     *
     * @return {@code true} if the command indicates an exit request; {@code false} otherwise
     */

    public boolean isExit() {
        return isExit;
    }

}
