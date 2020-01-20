import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> Tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        Greet();

        Scanner sc = new Scanner(System.in);

        while(true){
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("Bye")) {
                Bye();
                break;
            }
            else if(input.equalsIgnoreCase("List")){
                List();
            }
            else if(input.length() >= 6 && input.substring(0,4).equalsIgnoreCase(("Done"))){
                // the minimum length of Done command is with length 6
                int taskNumber = Integer.parseInt(input.substring(5));
                Done(taskNumber);
            }
            else{
                String TaskNature = input.substring(0,input.indexOf(" "));
                String Remain = input.substring(input.indexOf(" ") + 1);
                if(TaskNature.equalsIgnoreCase("Todo")){
                    ToDos newToDo = new ToDos(Remain);
                    Add(newToDo);
                }else{
                    String Description = Remain.substring(0,Remain.indexOf("/"));
                    String Time = Remain.substring(Remain.indexOf("/") + 1);
                    // remove "by' or "at"
                    Time = Time.substring(Time.indexOf(" "));
                    if(TaskNature.equalsIgnoreCase("deadline")){
                        Deadlines newDeadLine = new Deadlines(Description,Time);
                        Add(newDeadLine);
                    }else if(TaskNature.equalsIgnoreCase("event")){
                        Events newEvent = new Events(Description,Time);
                        Add(newEvent);
                    }else{
                        System.out.println("Wrong Command, Please Re-enter");
                    }
                }
            }
        }
    }



    public static void Greet(){
        System.out.println("      Hello! I'm Duke\n" + "      What can I do for you?");
    }

    public static void List(){
        System.out.println("      Here are the tasks in your list:");
        int taskSize = Tasks.size();
        for(int i = 0; i < taskSize; i++){
            int count = i + 1;
            System.out.println("      " + count + ". " + Tasks.get(i).toString());
        }
    }

    public static void Add(Task newTask){
        System.out.println("      Got it. I've added this task: ");
        Tasks.add(newTask);
        System.out.print("        " + newTask +"\n");
        int amtOfTask = Tasks.size();
        if(amtOfTask <= 1){
            System.out.println("      Now you have " + amtOfTask+ " task in list\n");
        }else {
            System.out.println("      Now you have " + amtOfTask+ " tasks in list\n");
        }
    }

    public static void Done(int number){
        Task getTask = Tasks.get(number - 1);
        getTask.markAsDone();
        System.out.println("      " + getTask);
    }

    public static void Bye(){
        System.out.println("      Bye. Hope to see you again soon\n!");
    }
}
