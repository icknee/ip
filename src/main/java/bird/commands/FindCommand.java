package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.Task;
import bird.task.TaskList;

import static bird.Ui.INDENT;

/**
 * The FindCommand class filters tasks in a TaskList based on a provided keyword.
 *  When executed, this command searches through all tasks and prints only those tasks
 *  whose description contains the specified keyword. Note that the keyword is case-sensitive.
 */

public class FindCommand extends Command {
    String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search within task descriptions.
     */

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command which searches the task list for tasks containing the keyword.
     * The method displays a header for the results, iterates through the task list, and for each task
     * whose description contains the keyword, prints the task with proper indentation. The results are
     * enclosed between horizontal lines for clarity.
     *
     * @param taskList the list of tasks to be searched.
     * @param storage  the storage system (unused in this command).
     */

    public void execute(TaskList taskList, Storage storage) {
        Ui.printLine();
        Ui.printWithIndent("Here are the tasks containing " + keyword + ":");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                Ui.printWithIndent(INDENT + task.toString());
            }
        }
        Ui.printLine();
    }
}
