import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        while(true){
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("Bye")){
                Bye();
                break;
            }else{
                Echo(input);
            }
        }

    }


    public static void Greet(){
        System.out.println("      Hello! I'm Duke\n" + "      What can I do for you?");
    }

    public static void Echo(String content){
        System.out.println("      " + content + "\n");
    }

    public static void Bye(){
        System.out.println("      Bye. Hope to see you again soon!\n");
    }

}
