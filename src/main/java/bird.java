import java.util.Scanner;


public class bird {
    public static void reply(String message){
        System.out.println("    " + message);
    }

    public static void greet(){
        printLine();
        reply("Hello! I'm bird");
        reply("What can I do for you?");
        printLine();
    }

    public static void printLine(){
        System.out.println("    " + "____________________________________________________________");
    }

    public static void bye(){
        printLine();
        reply("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void printTasks(String[] taskList, int taskCount){
        printLine();
        for (int i = 0; i < taskCount; i++){
            reply(Integer.toString(i + 1) + ". " + taskList[i]);
        }
        printLine();
    }

    public static void addTask(String[] taskList, String task, int taskCount){
        printLine();
        reply("added: " + task);
        printLine();
        taskList[taskCount] = task;
    }

    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        int taskCount = 0;

        line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")) {
                printTasks(taskList, taskCount);
            } else {
                addTask(taskList, line, taskCount);
                taskCount++;
            }
            line = in.nextLine();
        }
        bye();
    }
}

