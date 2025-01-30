import java.util.Scanner;


public class bird {
    public static void reply(String message) {
        System.out.println("    " + message);
    }

    public static void greet() {
        printLine();
        reply("Hello! I'm bird");
        reply("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    public static void bye() {
        printLine();
        reply("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void printTasks(Task[] taskList, int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            reply(i + 1 + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        printLine();
    }

    public static void addTask(Task[] taskList, String task, int taskCount) {
        printLine();
        reply("added: " + task);
        printLine();
        Task newTask = new Task(task);
        taskList[taskCount] = newTask;
    }

    public static void markTask(Task[] taskList, int taskNumber, int taskCount) {
        if (taskNumber > taskCount) {
            printLine();
            reply("Invalid task number");
            printLine();
            return;
        }
        taskList[taskNumber - 1].setDone(true);
        printLine();
        reply("Nice! I've marked this task as done:");
        reply("  " + "[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].description);
        printLine();
    }

    public static void unmarkTask(Task[] taskList, int taskNumber, int taskCount) {
        if (taskNumber > taskCount) {
            printLine();
            reply("Invalid task number");
            printLine();
            return;
        }
        taskList[taskNumber - 1].setDone(false);
        printLine();
        reply("OK, I've marked this task as not done yet:");
        reply("  " + "[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].description);
        printLine();
    }

    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskCount = 0;

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTasks(taskList, taskCount);
            } else if (line.startsWith("mark")) {
                markTask(taskList, Integer.parseInt(line.split(" ")[1]), taskCount);
            } else if (line.startsWith("unmark")) {
                unmarkTask(taskList, Integer.parseInt(line.split(" ")[1]), taskCount);
            } else {
                addTask(taskList, line, taskCount);
                taskCount++;
            }
            line = in.nextLine();
        }
        bye();
    }
}

