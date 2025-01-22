public class bird {
    public static void greet(){
        System.out.println("Hello! I'm bird");
        System.out.println("What can I do for you?");
    }

    public static void line(){
        System.out.println("____________________________________________________________");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        line();
        greet();
        line();
        bye();
        line();
    }
}

