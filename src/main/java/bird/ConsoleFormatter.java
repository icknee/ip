package bird;

import bird.task.Task;

import java.util.ArrayList;

public class ConsoleFormatter {
    private static final String INDENT = "  ";
    public static void printWithIndent(String message) {
        System.out.println("\t" + message);
    }

    public static void printWithLines(String message) {
        printLine();
        printWithIndent(message);
        printLine();
    }

    public static void printGreeting() {
        printLine();
        printWithIndent("Hello! I'm bird");
        printWithIndent("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void printGoodBye() {
        printWithLines("Bye bye!");
    }

    public static void printTaskList(ArrayList<Task> taskList, int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            printWithIndent(i + 1 + ". " + taskList.get(i).toString());
        }
        printLine();
    }

    public static void printNewTaskAdded(int taskCount, Task newTask) {
        printLine();
        printWithIndent("task added!");
        printWithIndent(INDENT + newTask.toString());
        printWithIndent("you now have " + (taskCount + 1) + " tasks in the list");
        printLine();
    }

    public static void printTaskAsDone(Task newTask) {
        printLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent(INDENT + newTask.toString());
        printLine();
    }

    public static void printTaskAsNotDone(Task newTask) {
        printLine();
        printWithIndent("OK, I've marked this task as not done yet:");
        printWithIndent(INDENT + newTask.toString());
        printLine();
    }

    public static void printTaskAsDeleted(Task deletedTask, int taskCount) {
        printLine();
        printWithIndent("task deleted!");
        printWithIndent(deletedTask.toString());
        printWithIndent("now you have " + (taskCount - 1) + " tasks in the list");
        printLine();
    }
}
