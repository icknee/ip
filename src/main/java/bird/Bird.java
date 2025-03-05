package bird;

import bird.commands.Command;
import bird.storage.Storage;
import bird.exceptions.InvalidCommandException;
import bird.task.TaskList;


import java.io.FileNotFoundException;
import java.io.IOException;


public class Bird {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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
    public static void main(String[] args) {
        new Bird("data/tasklist.txt").run();
    }
}


