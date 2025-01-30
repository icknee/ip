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

    public static void echo(String message){
        printLine();
        reply(message);
        printLine();
    }

    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")){
            echo(line);
            line = in.nextLine();
        }
        bye();
    }
}

