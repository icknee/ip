import java.util.Scanner;


public class Bird {
    private static final int MAX_TASKS = 100;
    private static final String INDENT = "  ";
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;


    public static void printReply(String message) {
        System.out.println("\t" + message);
    }

    public static void printGreeting() {
        printLine();
        printReply("Hello! I'm bird");
        printReply("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void printGoodBye() {
        printLine();
        printReply("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void printTaskList(Task[] taskList, int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            printReply(i + 1 + ". " + taskList[i].toString());
        }
        printLine();
    }

    private static void printNewTaskAdded(int taskCount, Task newTask) {
        printLine();
        printReply("task added!");
        printReply(INDENT + newTask.toString());
        printReply("you now have " + (taskCount + 1) + " tasks in the list");
        printLine();
    }

    public static void addTodos(Task[] taskList, String line, int taskCount) {
        String description = line.substring(TODO_DESCRIPTION_INDEX);
        ToDos newToDos = new ToDos(description);
        taskList[taskCount] = newToDos;
        printNewTaskAdded(taskCount, newToDos);
    }

    public static void addDeadlines(Task[] taskList, String line, int taskCount) {
        int byIndex = line.indexOf("/");
        String description = line.substring(DEADLINE_DESCRIPTION_INDEX, byIndex - 1);
        String by = line.substring(byIndex + 4);
        Deadlines newDeadlines = new Deadlines(description, by);
        taskList[taskCount] = newDeadlines;
        printNewTaskAdded(taskCount, newDeadlines);
    }

    public static void addEvents(Task[] taskList, String line, int taskCount) {
        int fromIndex = line.indexOf("/");
        int toIndex = line.indexOf("/", fromIndex + 1);
        String description = line.substring(EVENT_DESCRIPTION_INDEX, fromIndex - 1);
        String from = line.substring(fromIndex + 6, toIndex - 1);
        String to = line.substring(toIndex + 4);
        Events newEvents = new Events(description, from, to);
        taskList[taskCount] = newEvents;
        printNewTaskAdded(taskCount, newEvents);
    }


    public static void toggleTaskStatus(Task[] taskList, int taskNumber, boolean done) {
        taskList[taskNumber - 1].setDone(done);
        printLine();
        if (done) {
            printReply("Nice! I've marked this task as done:");
        } else {
            printReply("OK, I've marked this task as not done yet:");
        }
        printReply(taskList[taskNumber - 1].toString());
        printLine();
    }


    public static void main(String[] args) {
        printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int taskCount = 0;

        line = in.nextLine();
        String command = line.split(" ")[0];

        while (!line.equals("bye")) {
            switch (command) {
            case "list":
                printTaskList(taskList, taskCount);
                break;
            case "mark":
            case "unmark":
                try {
                    toggleTaskStatus(taskList, Integer.parseInt(line.split(" ")[1]), command.equals("mark"));
                } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    printLine();
                    printReply("Invalid task number, please try again with numbers from 1 to " + taskCount);
                    printLine();
                }
                break;
            case "todo":
                addTodos(taskList, line, taskCount);
                taskCount++;
                break;
            case "deadline":
                try {
                    addDeadlines(taskList, line, taskCount);
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    printLine();
                    printReply("deadline <task> /by <time>");
                    printLine();
                }
                break;
            case "event":
                try {
                    addEvents(taskList, line, taskCount);
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    printLine();
                    printReply("event <task> /from <time> /to <time>");
                    printLine();
                }
                break;
            default:
                printLine();
                printReply("Invalid command");
                printLine();
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        printGoodBye();
    }
}

