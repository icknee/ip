package bird;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bird.task.*;



public class FileHandler {
    private static final int DESCRIPTION_INDEX = 8;
    private static final int ISDONE_INDEX = 4;

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
                saveToDoToFile((ToDos) taskList.get(i));
            } else if (taskList.get(i) instanceof Deadlines) {
                saveDeadlineToFile((Deadlines) taskList.get(i));
            } else if (taskList.get(i) instanceof Events) {
                saveEventToFile((Events) taskList.get(i));
            }
        }
    }

    private static void saveEventToFile(Events task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " /from " + task.getFrom() + " /to " + task.getTo() + "\n");
        fw.close();
    }

    private static void saveDeadlineToFile(Deadlines task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " /by " + task.getBy() + "\n");
        fw.close();
    }

    private static void saveToDoToFile(ToDos task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + "\n");
        fw.close();
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
