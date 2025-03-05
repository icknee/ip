package bird.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bird.exceptions.InvalidCommandException;
import bird.exceptions.InvalidFileException;
import bird.task.*;

import static bird.storage.FileLoader.lineToArray;


public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        createFile();
    }

    public void createFile() {
        File f = new File(filepath);
        try {
            f.getParentFile().mkdirs(); // Create parent directories
            f.createNewFile();          // Create actual file
        } catch (IOException e) {
            throw new InvalidFileException("Could not create file");
        }
    }

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.close();
    }

    public void saveFile(TaskList taskList, Storage storage) throws IOException {
        storage.clearFile();
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            if (taskList.get(i) instanceof ToDos) {
                FileSaver.saveToDoToFile((ToDos) taskList.get(i));
            } else if (taskList.get(i) instanceof Deadlines) {
                FileSaver.saveDeadlineToFile((Deadlines) taskList.get(i));
            } else if (taskList.get(i) instanceof Events) {
                FileSaver.saveEventToFile((Events) taskList.get(i));
            }
        }
    }


    public ArrayList<Task> loadFileToArray() throws FileNotFoundException, InvalidCommandException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            lineToArray(taskList, s.nextLine());
        }
        return taskList;
    }

    public int getTaskCount() throws FileNotFoundException {
        int taskCount = 0;
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext() && s.nextLine() != "") {
            taskCount++;
        }
        return taskCount;
    }
}
