package bird.commands;

import bird.Ui;
import bird.storage.Storage;
import bird.task.Task;
import bird.task.TaskList;

import static bird.Ui.INDENT;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
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
