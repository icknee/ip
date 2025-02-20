package bird;

import bird.datafile.FileManager;
import bird.exceptions.InvalidCommandException;
import bird.exceptions.InvalidFileException;
import bird.task.Task;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class Bird {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        File f = new File("data/tasklist.txt");
        if (!f.exists()) {
            try {
                FileManager.createFile();        // Create actual file
            } catch (InvalidFileException e) {
                ConsoleFormatter.printWithLines(e.getMessage());
                return;
            }
        }


        ConsoleFormatter.printGreeting();
        String line;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<Task>();

        int taskCount = 0;
        try {
            taskCount = FileManager.getTaskCount();
            taskList = FileManager.loadFileToArray();
        } catch (FileNotFoundException | InvalidCommandException e) {
            ConsoleFormatter.printWithLines("Unable to load file");
        }


        line = in.nextLine();
        String command = line.split(" ")[0];

        try {
            taskList = FileManager.loadFileToArray();
        } catch (FileNotFoundException e) {
            ConsoleFormatter.printWithLines("Data file cannot be found");
            return;
        } catch (InvalidCommandException e) {
            ConsoleFormatter.printWithLines("Data file is likely to be corrupted");
        }

        while (!line.equals("bye")) {
            try {
                taskCount = CommandHandler.executeCommand(command, taskList, taskCount, line);
            } catch (InvalidCommandException e) {
                ConsoleFormatter.printWithLines(e.getMessage());
            } catch (IOException e) {
                ConsoleFormatter.printWithLines("Unable to save to file");
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        ConsoleFormatter.printGoodBye();
    }
}


