package bird;

import bird.commands.*;
import bird.exceptions.InvalidCommandException;
import bird.task.Deadlines;

public class Parser {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;

    public static Command parse(String fullCommand) throws InvalidCommandException {
        String action = fullCommand.split(" ")[0];
        Command c;
        switch (action) {
            case "list":
                c = new ListCommand();
                break;
            case "mark", "unmark":
                int markTaskNumber;
                try {
                    markTaskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
                } catch (Exception e) {
                    throw new InvalidCommandException("Please enter a valid task number");
                }
                c = new MarkCommand((action.equals("mark")), markTaskNumber);
                break;
            case "todo":
                if (fullCommand.length() < 6) {
                    throw new InvalidCommandException("todo <task>");
                }
                String toDoDescription = fullCommand.substring(TODO_DESCRIPTION_INDEX);
                c = new AddCommand(toDoDescription);
                break;
            case "deadline":
                try {
                    int byIndex = fullCommand.indexOf("/");
                    String deadlineDescription = fullCommand.substring(DEADLINE_DESCRIPTION_INDEX, byIndex - 1);
                    String by = fullCommand.substring(byIndex + 4);
                    Deadlines newDeadlines = new Deadlines(deadlineDescription, by);
                    c = new AddCommand(deadlineDescription, by);
                } catch (Exception e) {
                    throw new InvalidCommandException("deadline <task> /by <time>");
                }
                break;
            case "event":
                try {
                    int fromIndex = fullCommand.indexOf("/");
                    int toIndex = fullCommand.indexOf("/", fromIndex + 1);
                    String eventDescription = fullCommand.substring(EVENT_DESCRIPTION_INDEX, fromIndex - 1);
                    String eventFrom = fullCommand.substring(fromIndex + 6, toIndex - 1);
                    String eventTo = fullCommand.substring(toIndex + 4);
                    c = new AddCommand(eventDescription, eventFrom, eventTo);
                } catch (Exception e) {
                    throw new InvalidCommandException("event <task> /from <time> /to <time>");
                }
                break;
            case "delete":
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
                } catch (Exception e) {
                    throw new InvalidCommandException("Please enter a valid task number");
                }
                c = new DeleteCommand(taskNumber);
                break;
            case "bye":
                c = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + fullCommand + "\n" + "\t" + "try entering 'list' to see the list of tasks!");
        }
        return c;
    }
}
