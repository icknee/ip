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

/**
 * The Storage class is responsible for managing the persistent storage of tasks.
 *  This class handles the creation of the storage file and its parent directories,
 *  saving the current state of a task list to the file, loading tasks from the file,
 *  and determining the number of tasks stored in the file. It delegates the task-specific
 *  file writing operations to the FileSaver class methods based on the type of task.
 */

public class Storage {
    private String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     * It initializes the storage by setting the file path and ensuring that the file
     * (along with any required parent directories) exists.
     * </p>
     *
     * @param filepath the path to the file used for storing tasks.
     */

    public Storage(String filepath) {
        this.filepath = filepath;
        createFile();
    }

    /**
     * Creates the storage file and any necessary parent directories.
     * If the file or its parent directories do not exist, this method attempts to create them.
     * If an I/O error occurs during the creation process, an {@link InvalidFileException} is thrown.
     */

    private void createFile() {
        File f = new File(filepath);
        try {
            f.getParentFile().mkdirs(); // Create parent directories
            f.createNewFile();          // Create actual file
        } catch (IOException e) {
            throw new InvalidFileException("Could not create file");
        }
    }

    /**
     * Clears the contents of the storage file.
     * This private method opens the file for writing, effectively erasing the current content.
     *
     * @throws IOException if an I/O error occurs while opening the file.
     */

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.close();
    }

    /**
     * Saves the current state of the task list to the storage file.
     * The method first clears the existing file contents, then iterates over the tasks
     * in the provided {@code TaskList}. Depending on the task type (ToDos, Deadlines, or Events),
     * it delegates the task-specific file saving to the corresponding methods in the FileSaver class.
     * Finally, the updated task list is persisted to the file.
     *
     * @param taskList the TaskList containing the tasks to be saved.
     * @param storage  the Storage instance used to perform the clear operation on the file.
     * @throws IOException if an error occurs during file operations.
     */

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

    /**
     * Loads tasks from the storage file into an ArrayList.
     * This method reads the file line by line using a Scanner, and for each line,
     * it calls the {@code lineToArray} method from the FileLoader utility to parse
     * the line into a Task object which is then added to an ArrayList.
     *
     * @return an ArrayList of Task objects loaded from the storage file.
     * @throws FileNotFoundException     if the storage file is not found.
     * @throws InvalidCommandException   if a task in the file is improperly formatted.
     */

    public ArrayList<Task> loadFileToArray() throws FileNotFoundException, InvalidCommandException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            lineToArray(taskList, s.nextLine());
        }
        return taskList;
    }

    /**
     * Retrieves the count of tasks stored in the storage file.
     * This method scans the storage file line by line and increments a counter for each non-empty line.
     * The resulting count represents the number of tasks saved in the file.
     *
     * @return the number of tasks stored in the file.
     * @throws FileNotFoundException if the storage file is not found.
     */

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
