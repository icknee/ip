package bird;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bird.task.*;


public class FileHandler {
    private static final int MAX_TASKS = 100;

    public static void createFile() {
        File f = new File("data/tasklist.txt");
        try {
            f.getParentFile().mkdirs(); // Create parent directories
            f.createNewFile();          // Create actual file
        } catch (IOException e) {
            throw new InvalidFileException("Could not create file");
        }
    }

    public static void saveFile(Task[] taskList, int taskCount) throws IOException {
        for (int i = 0; i < taskCount; i++) {
            if (taskList[i] instanceof ToDos) {
                saveToDoToFile((ToDos) taskList[i]);
            } else if (taskList[i] instanceof Deadlines) {
                saveDeadlineToFile();
            } else if (taskList[i] instanceof Events) {
                saveEventToFile();
            }
        }
    }

    private static void saveEventToFile() {


    }

    private static void saveDeadlineToFile() {
        
    }

    private static void saveToDoToFile(ToDos task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("T | " + task.getStatusIcon() + " | " + task.getDescription());
        fw.close();
    }

    public static Task[] loadFileToArray() throws FileNotFoundException, InvalidCommandException {
        Task[] taskList = new Task[MAX_TASKS];
        File f = new File("data/tasklist.txt");
        Scanner s = new Scanner(f);
        int taskNum = 0;
        while (s.hasNext()) {
            lineToArray(taskList, s.nextLine(), taskNum);
        }
        return taskList;
    }

    public static void lineToArray(Task[] taskList, String line, int taskNum) throws InvalidCommandException {
        char taskType = line.charAt(0);
        switch (taskType) {
            case 'T':

                break;
            case 'D':

                break;
            case 'E':
                break;
            default:
                throw new InvalidFileException("File is likely to be corrupted.");
        }
    }
}
