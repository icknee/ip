package bird;

import bird.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Bird {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        ConsoleFormatter.printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        //Task[] taskList = new Task[MAX_TASKS];
        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;

        line = in.nextLine();
        String command = line.split(" ")[0];

        while (!line.equals("bye")) {
            try {
                taskCount = CommandHandler.executeCommand(command, taskList, taskCount, line);
            } catch (InvalidCommandException e) {
                ConsoleFormatter.printWithLines(e.getMessage());
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        ConsoleFormatter.printGoodBye();
    }
}


