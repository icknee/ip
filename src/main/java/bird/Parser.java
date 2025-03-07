package bird;

import bird.commands.*;
import bird.exceptions.InvalidCommandException;
import bird.task.Deadlines;

/**
 * The Parser class is responsible for converting raw user input into executable Command objects.
 *  If the input does not follow the expected format, an {@code InvalidCommandException} is thrown.
 */

public class Parser {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;

    /**
     * Parses the full command string input by the user and returns the corresponding Command object.
     * The method extracts the action keyword from the input and, depending on the command type,
     * parses additional arguments as necessary. It supports commands such as:
     * <ul>
     *   <li>"list" - returns a ListCommand.</li>
     *   <li>"mark" / "unmark" - returns a MarkCommand with the specified task number.</li>
     *   <li>"todo" - returns an AddCommand for a todo task using the task description.</li>
     *   <li>"deadline" - returns an AddCommand for a deadline task using the task description and deadline time.</li>
     *   <li>"event" - returns an AddCommand for an event task using the task description, start time, and end time.</li>
     *   <li>"delete" - returns a DeleteCommand with the task number to be removed.</li>
     *   <li>"bye" - returns an ExitCommand to terminate the application.</li>
     *   <li>"find" - returns a FindCommand with the specified keyword.</li>
     * </ul>

     * @param fullCommand the complete command string entered by the user.
     * @return the corresponding Command object based on the parsed input.
     * @throws InvalidCommandException if the command is unknown, incomplete, or improperly formatted.
     */

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
        case "find":
            String keyword;
            try {
                keyword = fullCommand.split(" ")[1];
            } catch (Exception e) {
                throw new InvalidCommandException("find <keyword>");
            }
            c = new FindCommand(keyword);
            break;
        default:
            throw new InvalidCommandException("Unknown command: " + fullCommand + "\n" + "\t" + "refer to the user guide for the list of commands!");
        }
        return c;
    }
}
