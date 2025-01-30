import java.util.Scanner;


public class Bird {
    private static final int MAX_TASKS = 100;
    private static final String INDENT = "    ";

    public static void printReply(String message) {
        System.out.println(INDENT + message);
    }

    public static void printGreeting() {
        printLine();
        printReply("Hello! I'm bird");
        printReply("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    public static void printGoodBye() {
        printLine();
        printReply("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void printTaskList(Task[] taskList, int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            printReply(i + 1 + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        printLine();
    }

    public static void addTask(Task[] taskList, String task, int taskCount) {
        printLine();
        printReply("added: " + task);
        printLine();
        Task newTask = new Task(task);
        taskList[taskCount] = newTask;
    }


    public static void toggleTaskStatus(Task[] taskList, int taskNumber, boolean done) {
        taskList[taskNumber - 1].setDone(done);
        printLine();
        if (done) {
            printReply("Nice! I've marked this task as done:");
        } else {
            printReply("OK, I've marked this task as not done yet:");
        }
        printReply("  " + "[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].description);
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
                        printReply("Invalid task number");
                        printLine();
                    }
                    break;
                default:
                    addTask(taskList, line, taskCount);
                    taskCount++;
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        printGoodBye();
    }
}

