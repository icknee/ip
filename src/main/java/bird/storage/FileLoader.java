package bird.storage;

import bird.exceptions.InvalidCommandException;
import bird.exceptions.InvalidFileException;
import bird.task.Deadlines;
import bird.task.Events;
import bird.task.Task;
import bird.task.ToDos;

import java.util.ArrayList;

public class FileLoader {
    private static final int DESCRIPTION_INDEX = 8;
    private static final int ISDONE_INDEX = 4;

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

    private static void loadDeadlineToArray(ArrayList<Task> taskList, String line) {
        int byIndex = line.indexOf("/");
        String description = line.substring(DESCRIPTION_INDEX, byIndex - 1);
        String by = line.substring(byIndex + 4);
        Deadlines newDeadline = new Deadlines(description, by);
        newDeadline.setDone(line.charAt(ISDONE_INDEX) == 'X');
        taskList.add(newDeadline);
    }

    private static void loadToDoToArray(ArrayList<Task> taskList, String line) {
        String description = line.substring(DESCRIPTION_INDEX);
        ToDos newToDos = new ToDos(description);
        newToDos.setDone(line.charAt(ISDONE_INDEX) == 'X');
        taskList.add(newToDos);
    }
}
