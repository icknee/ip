package bird.storage;

import bird.task.Deadlines;
import bird.task.Events;
import bird.task.ToDos;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileSaver class provides utility methods for appending task information to the storage file.
 *  It contains static methods for saving different types of tasks (Event, Deadline, and ToDo) in a
 *  predefined format to the file "data/tasklist.txt". Each method opens the file in append mode,
 *  writes the task data in a specific format, and then closes the file writer to ensure that the data
 *  is properly saved.
 */

public class FileSaver {
    /**
     * Saves an event task to the storage file.
     * The method appends the event task information to "data/tasklist.txt" in the following format:
     * "E | [status icon] | [description] /from [starting time] /to [ending time]".
     *
     * @param task the Events object representing the event task to be saved.
     * @throws IOException if an error occurs while writing to the file.
     */

    static void saveEventToFile(Events task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " /from " + task.getFrom() + " /to " + task.getTo() + "\n");
        fw.close();
    }

    /**
     * Saves a deadline task to the storage file.
     * The method appends the deadline task information to "data/tasklist.txt" in the following format:
     * "D | [status icon] | [description] /by [deadline]".
     *
     * @param task the Deadlines object representing the deadline task to be saved.
     * @throws IOException if an error occurs while writing to the file.
     */

    static void saveDeadlineToFile(Deadlines task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " /by " + task.getBy() + "\n");
        fw.close();
    }

    /**
     * Saves a ToDo task to the storage file.
     * The method appends the ToDo task information to "data/tasklist.txt" in the following format:
     * "T | [status icon] | [description]".
     *
     * @param task the ToDos object representing the ToDo task to be saved.
     * @throws IOException if an error occurs while writing to the file.
     */

    static void saveToDoToFile(ToDos task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + "\n");
        fw.close();
    }
}
