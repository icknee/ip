package bird.storage;

import bird.exceptions.InvalidCommandException;
import bird.exceptions.InvalidFileException;
import bird.task.Deadlines;
import bird.task.Events;
import bird.task.Task;
import bird.task.ToDos;

import java.util.ArrayList;

/**
 * The FileLoader class provides methods for parsing task data from persistent storage.
 *  It reads lines from a file and converts each line into a corresponding Task object
 *  (ToDo, Deadline, or Event) based on a predefined format.
 */

public class FileLoader {
    private static final int DESCRIPTION_INDEX = 8;
    private static final int ISDONE_INDEX = 4;

    /**
     * Parses a single line from the storage file and adds the corresponding Task object to the provided list.
     * The type of task is determined by the first character of the line:
     * <ul>
     * <li>'T' - ToDo task</li>
     * <li>'D' - Deadline task</li>
     * <li>'E' - Event task</li>
     * </ul>
     *
     * @param taskList the ArrayList to which the parsed Task will be added.
     * @param line a single line from the storage file representing a task.
     * @throws InvalidCommandException if the command format is invalid.
     * @throws InvalidFileException if an error occurs while parsing the line or if the file format is corrupted.
     */

    public static void lineToArray(ArrayList<Task> taskList, String line) throws InvalidCommandException {
        char taskType = line.charAt(0);
        try {
            switch (taskType) {
            case 'T':
                loadToDoToArray(taskList, line);
                break;
            case 'D':
                loadDeadlineToArray(taskList, line);
                break;
            case 'E':
                loadEventToArray(taskList, line);
                break;
            default:
                throw new InvalidFileException("File is likely to be corrupted.");
            }
        } catch (Exception e) {
            throw new InvalidFileException("Error while parsing file");
        }
    }

    /**
     * Parses a line representing an Event task and adds the resulting Event object to the task list.
     * The line is expected to have a specific format that includes the event description,
     * starting time (after "/from"), and ending time (after "/to"). The task's completion
     * status is determined by the character at the ISDONE index.
     *
     * @param taskList the ArrayList to which the Event task will be added.
     * @param line a line from the storage file representing an Event task.
     */

    private static void loadEventToArray(ArrayList<Task> taskList, String line) {
        int fromIndex = line.indexOf("/");
        int toIndex = line.indexOf("/", fromIndex + 1);
        String description = line.substring(DESCRIPTION_INDEX, fromIndex - 1);
        String from = line.substring(fromIndex + 6, toIndex - 1);
        String to = line.substring(toIndex + 4);
        Events newEvents = new Events(description, from, to);
        newEvents.setDone(line.charAt(ISDONE_INDEX) == 'X');
        taskList.add(newEvents);
    }

    /**
     * Parses a line representing a Deadline task and adds the resulting Deadline object to the task list.
     * The line is expected to have a specific format that includes the deadline description and
     * deaedline time (after "/by"). The task's completion status is determined by the character at the ISDONE index.
     *
     * @param taskList the ArrayList to which the Event task will be added.
     * @param line a line from the storage file representing an Event task.
     */

    private static void loadDeadlineToArray(ArrayList<Task> taskList, String line) {
        int byIndex = line.indexOf("/");
        String description = line.substring(DESCRIPTION_INDEX, byIndex - 1);
        String by = line.substring(byIndex + 4);
        Deadlines newDeadline = new Deadlines(description, by);
        newDeadline.setDone(line.charAt(ISDONE_INDEX) == 'X');
        taskList.add(newDeadline);
    }

    /**
     * Parses a line representing a ToDo task and adds the resulting ToDo object to the task list.
     * The line is expected to contain only the ToDo task description.
     * The task's completion status is determined by the character at the ISDONE index.
     *
     * @param taskList the ArrayList to which the Event task will be added.
     * @param line a line from the storage file representing an Event task.
     */

    private static void loadToDoToArray(ArrayList<Task> taskList, String line) {
        String description = line.substring(DESCRIPTION_INDEX);
        ToDos newToDos = new ToDos(description);
        newToDos.setDone(line.charAt(ISDONE_INDEX) == 'X');
        taskList.add(newToDos);
    }
}
