import java.util.Scanner;


public class Bird {
    private static final int MAX_TASKS = 100;
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;


    public static void addTodos(Task[] taskList, String line, int taskCount) throws InvalidCommandException {
        if (line.length() < 6) {
            throw new InvalidCommandException("todo <task>");
        }
        String description = line.substring(TODO_DESCRIPTION_INDEX);
        ToDos newToDos = new ToDos(description);
        taskList[taskCount] = newToDos;
        ConsoleFormatter.printNewTaskAdded(taskCount, newToDos);
    }

    public static void addDeadlines(Task[] taskList, String line, int taskCount) throws InvalidCommandException {
        try {
            int byIndex = line.indexOf("/");
            String description = line.substring(DEADLINE_DESCRIPTION_INDEX, byIndex - 1);
            String by = line.substring(byIndex + 4);
            Deadlines newDeadlines = new Deadlines(description, by);
            taskList[taskCount] = newDeadlines;
            ConsoleFormatter.printNewTaskAdded(taskCount, newDeadlines);
        } catch (Exception e) {
            throw new InvalidCommandException("deadline <task> /by <time>");
        }
    }

    public static void addEvents(Task[] taskList, String line, int taskCount) throws InvalidCommandException {
        try {
            int fromIndex = line.indexOf("/");
            int toIndex = line.indexOf("/", fromIndex + 1);
            String description = line.substring(EVENT_DESCRIPTION_INDEX, fromIndex - 1);
            String from = line.substring(fromIndex + 6, toIndex - 1);
            String to = line.substring(toIndex + 4);
            Events newEvents = new Events(description, from, to);
            taskList[taskCount] = newEvents;
            ConsoleFormatter.printNewTaskAdded(taskCount, newEvents);
        } catch (Exception e) {
            throw new InvalidCommandException("event <task> /from <time> /to <time>");
        }
    }

    public static void markTaskAsDone(Task[] taskList, String enteredTaskNumber, int taskCount) throws InvalidCommandException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(enteredTaskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList[taskNumber - 1].setDone(true);
        ConsoleFormatter.printTaskAsDone(taskList[taskNumber - 1]);
    }

    public static void markTaskAsNotDone(Task[] taskList, String enteredTaskNumber, int taskCount) throws InvalidCommandException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(enteredTaskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Please enter a valid task number");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidCommandException("Task does not exist");
        }
        taskList[taskNumber - 1].setDone(false);
        ConsoleFormatter.printTaskAsNotDone(taskList[taskNumber - 1]);
    }

    private static int executeCommand(String command, Task[] taskList, int taskCount, String line) throws InvalidCommandException {
        switch (command) {
            case "list":
                ConsoleFormatter.printTaskList(taskList, taskCount);
                break;
            case "mark":
                markTaskAsDone(taskList, line.split(" ")[1], taskCount);
                break;
            case "unmark":
                markTaskAsNotDone(taskList, line.split(" ")[1], taskCount);
                break;
            case "todo":
                addTodos(taskList, line, taskCount);
                taskCount++;
                break;
            case "deadline":
                addDeadlines(taskList, line, taskCount);
                taskCount++;
                break;
            case "event":
                addEvents(taskList, line, taskCount);
                taskCount++;
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + line + "\n" + "\t" + "try entering 'help' to see the list of commands!");
        }
        return taskCount;
    }


    public static void main(String[] args) {
        ConsoleFormatter.printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int taskCount = 0;

        line = in.nextLine();
        String command = line.split(" ")[0];

        while (!line.equals("bye")) {
            try {
                taskCount = executeCommand(command, taskList, taskCount, line);
            } catch (InvalidCommandException e) {
                ConsoleFormatter.printWithLines(e.getMessage());
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        ConsoleFormatter.printGoodBye();
    }
}


