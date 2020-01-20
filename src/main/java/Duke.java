import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> Tasks = new ArrayList<String>();

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
            }else if(input.equalsIgnoreCase("List")){
                List();
            }else{
                Add(input);
            }
        }

    }


    public static void Greet(){
        System.out.println("      Hello! I'm Duke\n" + "      What can I do for you?");
    }

    public static void Echo(String content){
        System.out.println("      " + content + "\n");
    }


    public static void List(){
        int taskSize = Tasks.size();
        for(int i = 0; i < taskSize; i++){
            int count = i + 1;
            System.out.println("      " + count + ". " + Tasks.get(i).toString());
        }
    }

    public static void Add(String newTask){
        Tasks.add(newTask);
        System.out.print("        added: " + newTask +"\n");
    }


    public static void Bye(){
        System.out.println("      Bye. Hope to see you again soon!\n");
    }

}
