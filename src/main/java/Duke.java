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
            try {
                String input = sc.nextLine();
                input = input.toLowerCase();
                if (input.equalsIgnoreCase("bye")) {
                    Bye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    List();
                } else if (input.startsWith("done")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(5));
                        Done(taskNumber);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7));
                        Delete(taskNumber);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }
                } else {
                    if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){

                        if(input.startsWith("todo")){
                            AddTodo(input);
                        }else if(input.startsWith("deadline")){
                            AddDeadline(input);
                        }else if(input.startsWith("event")){
                            AddEvent(input);
                        }
                    }else{
                        throw new DukeException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }catch(DukeException e){
                System.out.println(e);
            }
        }
    }

    public static void AddTodo(String input) throws DukeException{
        String Remain = input.replace("todo","").trim();
        if(Remain.length() >= 1){
            ToDos newToDo = new ToDos(Remain);
            Add(newToDo);
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void AddDeadline(String input) throws DukeException{
        String Remain = input.replace("deadline","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /by ");
                Deadlines newDeadLine = new Deadlines(detail[0], detail[1]);
                Add(newDeadLine);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! The description of a deadline is wrong.");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a deadline cannot be empty.");
        }

    }


    public static void AddEvent(String input) throws DukeException{
        String Remain = input.replace("event","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /at ");
                Events newEvent = new Events(detail[0], detail[1]);
                Add(newEvent);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! The description of an event is wrong.");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of an event cannot be empty.");
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
            System.out.println("      Now you have " + amtOfTask+ " task in list");
        }else {
            System.out.println("      Now you have " + amtOfTask+ " tasks in list");
        }
    }

    public static void Done(int number) throws DukeException{
        if(number <= Tasks.size() && number >= 1) {
            Task getTask = Tasks.get(number - 1);
            getTask.markAsDone();
            System.out.println("      " + getTask);
        }else{
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
    }

    public static void Delete(int number) throws DukeException{
        if(number <= Tasks.size() && number >= 1) {
            Task getTask = Tasks.get(number - 1);
            Tasks.remove(number - 1);
            System.out.println("        Noted. I've removed this task:");
            System.out.println("        " + getTask);
            int amtOfTask = Tasks.size();
            if(amtOfTask <= 1){
                System.out.println("      Now you have " + amtOfTask+ " task in list");
            }else {
                System.out.println("      Now you have " + amtOfTask+ " tasks in list");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
    }

    public static void Bye(){
        System.out.println("      Bye. Hope to see you again soon!");
    }
}
