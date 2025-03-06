package bird;

import bird.commands.Command;
import bird.storage.Storage;
import bird.exceptions.InvalidCommandException;
import bird.task.TaskList;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Bird class represents the main entry point of the application.
 * It initializes the storage, task list, and user interface. It also
 * continuously reads user commands, parses them, executes the corresponding actions,
 * and handles potential exceptions.
 */

public class Bird {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new Bird application with the specified file path for storing tasks.
     * This constructor initializes the user interface, loads the saved tasks from the file,
     * and sets up the task list. Any errors during initialization such as missing file or
     * invalid commands are caught and printed.
     *
     * @param filePath the path to the file that contains the saved tasks.
     */

    public Bird(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFileToArray());
            taskList.setTaskCount(storage.getTaskCount());
        } catch (FileNotFoundException | InvalidCommandException e) {
            Ui.printWithLines(e.getMessage());
        }
    }

    /**
     * Runs the main loop of the Bird application.
     * This method prints a greeting to the user, then enters a loop where it reads user commands,
     * parses them to determine the intended action, executes the command, and handles any exceptions.
     * The loop continues until a command signals the application to exit.
     */

    public void run() {
        Ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = null;
            try {
                c = Parser.parse(fullCommand);
                c.execute(taskList, storage);
            } catch (InvalidCommandException | IOException e) {
                Ui.printWithLines(e.getMessage());
            }
            try {
                isExit = c.isExit();
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    /**
     * The main entry point for the Bird application.
     *
     * Although this application primarily uses a Scanner for interactive user input,
     * the command-line arguments (provided via the {@code args} parameter) are still included
     * to meet the Java entry point signature requirements.
     *
     * @param args an array of command-line arguments. These are not directly involved in the
     *             application's interactive input process.
     */

    public static void main(String[] args) {
        new Bird("data/tasklist.txt").run();
    }
}


