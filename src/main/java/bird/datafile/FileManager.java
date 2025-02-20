package bird.datafile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bird.exceptions.InvalidCommandException;
import bird.exceptions.InvalidFileException;
import bird.task.*;
import bird.datafile.FileSaver;

import static bird.datafile.FileLoader.lineToArray;


public class FileManager {

    public static void createFile() {
        File f = new File("./data/tasklist.txt");
        try {
            f.getParentFile().mkdirs(); // Create parent directories
            f.createNewFile();          // Create actual file
        } catch (IOException e) {
            throw new InvalidFileException("Could not create file");
        }
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("./data/tasklist.txt");
        fw.close();
    }

    public static void saveFile(ArrayList<Task> taskList) throws IOException {
        clearFile();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof ToDos) {
                FileSaver.saveToDoToFile((ToDos) taskList.get(i));
            } else if (taskList.get(i) instanceof Deadlines) {
                FileSaver.saveDeadlineToFile((Deadlines) taskList.get(i));
            } else if (taskList.get(i) instanceof Events) {
                FileSaver.saveEventToFile((Events) taskList.get(i));
            }
        }
    }


    public static ArrayList<Task> loadFileToArray() throws FileNotFoundException, InvalidCommandException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File f = new File("data/tasklist.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            lineToArray(taskList, s.nextLine());
        }
        return taskList;
    }

    public static int getTaskCount() throws FileNotFoundException {
        int taskCount = 0;
        File f = new File("data/tasklist.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext() && s.nextLine() != "") {
            taskCount++;
        }
        return taskCount;
    }
}
