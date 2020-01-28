
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import static java.lang.System.exit;
import java.time.LocalDate;


public class Duke {

    public static ArrayList<Task> Tasks = new ArrayList<Task>();
    public static String path = "data/duke.txt";


    public static void main(String[] args) {

        ImportData(path);


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


    public static void ImportData(String path){
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] content = line.split("-");
                String TaskNature = content[0];
                int isDone = Integer.parseInt(content[1]);
                if(TaskNature.equals("T")){
                    ToDos todo = new ToDos(content[2]);
                    if(isDone == 1){
                        todo.isDone = true;
                    }
                    Tasks.add(todo);
                }else if(TaskNature.equals("D")){
                    String date = content[3] + "-" + content[4] + "-" + content[5];
                    LocalDate deadlineDate = LocalDate.parse(date.trim());
                    Deadlines deadline = new Deadlines(content[2],deadlineDate);
                    if(isDone == 1) {
                        deadline.isDone = true;
                    }
                    Tasks.add(deadline);
                }else if(TaskNature.equals("E")){
                    String date = content[3] + "-" + content[4] + "-" + content[5];
                    LocalDate EventDate = LocalDate.parse(date.trim());
                    StringBuilder duration = new StringBuilder();
                    for(int i = 6; i < content.length; i++) {
                        if (i == content.length - 1) {
                            duration.append(content[i]);
                        } else {
                            duration.append(content[i] + "-");
                        }
                    }
                    Events event = new Events(content[2],EventDate,duration.toString());
                    if(isDone == 1) {
                        event.isDone = true;
                    }
                    Tasks.add(event);
                }else{
                    break;
                }
            }
        }catch(java.io.FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
            exit(0);
        }catch(Exception e){
            System.out.println("File Corrupted " + e.getMessage());
        }

    }


    //THIS METHOD IS FOR TODO
    public static void appendToFile(String TaskNature, int isDone, String TaskDescription){
        try {
            File data = new File(path);
            FileWriter fr = new FileWriter(data, true);
            String line = TaskNature + "-" + isDone + "-" + TaskDescription;
            fr.write(line + "\n");
            fr.close();
        }catch(java.io.IOException e){
            System.out.println("UNABLE TO SAVE DATA");
        }
    }

    //THIS METHOD IS FOR DEADLINE AND EVENTS
    public static void appendToFile(String TaskNature, int isDone, String TaskDescription, String Time){
       try {
           File data = new File(path);
           FileWriter fr = new FileWriter(data, true);
           String line = TaskNature + "-" + isDone + "-" + TaskDescription + "-" + Time;
           fr.write(line + "\n");
           fr.close();
       }catch(java.io.IOException e){
           System.out.println("UNABLE TO SAVE DATA");
       }
    }

    public static void updateFile(String path){
        try {
            StringBuilder NewData = new StringBuilder();
            Writer fileWriter = new FileWriter(path, false); //overwrites file
            for(int i = 0; i < Tasks.size(); i++){
                Task task = Tasks.get(i);
                if(task instanceof ToDos){
                    String line;
                    if(task.isDone) {
                        line = "T" + "-1-" + task.description;
                    }else{
                        line = "T" + "-0-" + task.description;
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }else if(task instanceof Deadlines){
                    String line;
                    if(task.isDone) {
                        line = "D" + "-1-" + task.description + "-" + ((Deadlines) task).deadlineDate.toString();
                    }else{
                        line = "D" + "-0-" + task.description + "-" + ((Deadlines) task).deadlineDate.toString();
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }else{
                    String line;
                    if(task.isDone) {
                        line = "E" + "-1-" + task.description + "-" + ((Events) task).EventDate + "-" + ((Events) task).Duration;
                    }else{
                        line = "E" + "-0-" + task.description + "-" + ((Events) task).EventDate + "-" + ((Events) task).Duration;
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }

            }
            fileWriter.write(NewData.toString());
            if(fileWriter != null){
                fileWriter.close();
            }
        }catch(java.io.IOException e){
            System.out.println(("ERROR " + e.getMessage()));
        }

    }

    public static void AddTodo(String input) throws DukeException{
        String Remain = input.replace("todo","").trim();
        if(Remain.length() >= 1){
            ToDos newToDo = new ToDos(Remain);
            Add(newToDo);
            appendToFile("T",0,Remain);
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void AddDeadline(String input) throws DukeException{
        String Remain = input.replace("deadline","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /by ");
                LocalDate deadlineDate = LocalDate.parse(detail[1].trim());
                Deadlines newDeadLine = new Deadlines(detail[0], deadlineDate);
                Add(newDeadLine);
                appendToFile("D",0,detail[0],detail[1]);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : description, YYYY-MM-DD");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a deadline is wrong");
        }

    }


    public static void AddEvent(String input) throws DukeException{
        String Remain = input.replace("event","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /at ");
                String[] splitDateTime = detail[1].trim().split(" ");
                LocalDate EventDate = LocalDate.parse(splitDateTime[0].trim());
                Events newEvent = new Events(detail[0], EventDate,splitDateTime[1].trim());
                Add(newEvent);
                appendToFile("E",0,detail[0],detail[1]);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : description, YYYY-MM-DD and hours");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of an event is wrong.");
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
        updateFile(path);
    }

    public static void Delete(int number) throws DukeException{
        if(number <= Tasks.size() && number >= 1) {
            Task getTask = Tasks.get(number - 1);
            Tasks.remove(number - 1);
            System.out.println("      Noted. I've removed this task:");
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
        updateFile(path);
    }

    public static void Bye(){
        System.out.println("      Bye. Hope to see you again soon!");

    }
}
