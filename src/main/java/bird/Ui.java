package bird;

import bird.task.Task;
import bird.task.TaskList;

import java.util.Scanner;

/**
 * The Ui class handles all user interface operations in the Bird application.
 * It provides methods to read user input from the console and print various types of messages.
 */

public class Ui {
    public static final String INDENT = "  ";
    private Scanner in = new Scanner(System.in);

    /**
     * Reads a line of user input from the console.
     *
     * @return the command inputted by the user.
     */

    public String readCommand() {
        String line = in.nextLine();
        return line;
    }

    /**
     * Prints a message with a tab indentation.
     *
     * @param message the message to be printed.
     */

    public static void printWithIndent(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Prints a message enclosed by horizontal separator lines.
     *
     * @param message the message to be printed.
     */

    public static void printWithLines(String message) {
        printLine();
        printWithIndent(message);
        printLine();
    }

    /**
     * Prints an ASCII art representation of a bird with indent.
     */

    public static void printBird() {
        System.out.println("      .-.\n" +
                "     /'v'\\\n" +
                "    (/   \\)\n" +
                "   ='=\"=\"===<\n" +
                "      |_|");
    }

    /**
     * Prints a greeting message along with the ASCII bird art.
     *
     * This method initiates the user interface by welcoming the user
     * and asking what service they require.
     */

    public static void printGreeting() {
        printLine();
        printWithIndent("Hello! I'm bird");
        printBird();
        printWithIndent("What can I do for you?");
        printLine();
    }

    /**
     * Prints a horizontal separator line.
     */

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    /**
     * Prints a farewell message enclosed by horizontal separator lines.
     */

    public static void printGoodBye() {
        printWithLines("Bye bye!");
    }

    /**
     * Prints the complete list of tasks.
     * Each task is printed on a separate line, numbered consecutively.
     * The output is enclosed by horizontal separator lines.
     *
     * @param taskList  the TaskList containing the tasks.
     * @param taskCount the number of tasks to print.
     */

    public static void printTaskList(TaskList taskList, Integer taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            printWithIndent(i + 1 + ". " + taskList.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints the details of the new task and the updated task count.
     *
     * @param taskCount the current count of tasks (before the new task is added).
     * @param newTask   the task that has been added.
     */
    public static void printNewTaskAdded(int taskCount, Task newTask) {
        printLine();
        printWithIndent("task added!");
        printWithIndent(INDENT + newTask.toString());
        if (taskCount == 0) {
            printWithIndent("you now have 1 task in the list");
        } else {
            printWithIndent("you now have " + (taskCount + 1) + " tasks in the list");
        }
        printLine();
    }

    /**
     * Prints a confirmation that a task has been marked as done.
     * It displays the task details after marking it as completed.
     *
     * @param newTask the task that has been marked as done.
     */

    public static void printTaskAsDone(Task newTask) {
        printLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent(INDENT + newTask.toString());
        printLine();
    }

    /**
     * Prints a confirmation that a task has been marked as not done.
     * It displays the task details after marking it as incomplete.
     *
     * @param newTask the task that has been marked as not done.
     */

    public static void printTaskAsNotDone(Task newTask) {
        printLine();
        printWithIndent("OK, I've marked this task as not done yet:");
        printWithIndent(INDENT + newTask.toString());
        printLine();
    }

    /**
     * Prints a notification that a task has been deleted.
     * It displays the details of the deleted task and the updated task count.
     *
     * @param deletedTask the task that has been deleted.
     * @param taskCount   the count of tasks before deletion.
     */

    public static void printTaskAsDeleted(Task deletedTask, int taskCount) {
        printLine();
        printWithIndent("task deleted!");
        printWithIndent(INDENT + deletedTask.toString());
        if (taskCount == 2) {
            printWithIndent("you now have 1 task in the list");
        } else {
            printWithIndent("you now have " + (taskCount - 1) + " tasks in the list");
        }
        printLine();
    }
}
