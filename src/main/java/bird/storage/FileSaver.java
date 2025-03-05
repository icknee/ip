package bird.storage;

import bird.task.Deadlines;
import bird.task.Events;
import bird.task.ToDos;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    static void saveEventToFile(Events task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " /from " + task.getFrom() + " /to " + task.getTo() + "\n");
        fw.close();
    }

    static void saveDeadlineToFile(Deadlines task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " /by " + task.getBy() + "\n");
        fw.close();
    }

    static void saveToDoToFile(ToDos task) throws IOException {
        FileWriter fw = new FileWriter("data/tasklist.txt", true);
        fw.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + "\n");
        fw.close();
    }
}
