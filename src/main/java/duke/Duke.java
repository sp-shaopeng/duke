package duke;

import duke.storage.*;
import duke.ui.*;
import duke.task.*;
import duke.exception.*;
import java.util.Scanner;



public class Duke {

    public String FILE_PATH;
    private Storage STORAGE;
    private TaskList TASK_LIST;
    private Ui UI;

    public Duke(String FILE_PATH) {
        UI = new Ui();
        this.FILE_PATH = FILE_PATH;
        this.STORAGE = new Storage(FILE_PATH);
        try {
            TASK_LIST = new TaskList(STORAGE.load());
        } catch (Exception e) {
            UI.showLoadingError();
            TASK_LIST = new TaskList();
        }
    }

    /**
     * This method will be running throughout the entire session
     * It will listen to the incoming users's command and process the comment accordingly
     */

    public void run() {
        this.UI.GreetLogo();
        this.UI.Greet();
        Scanner sc = new Scanner(System.in);
        while(true){
            try {
                String input = sc.nextLine();
                input = input.toLowerCase();
                if (input.equalsIgnoreCase("bye")) {
                    this.UI.Bye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    this.TASK_LIST.List();
                } else if (input.startsWith("done")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(5));
                        this.TASK_LIST.Done(taskNumber, this.STORAGE);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7));
                        this.TASK_LIST.Delete(taskNumber, this.STORAGE);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }
                } else if (input.startsWith("find")){
                    String KeyWord = input.substring(5).trim();
                    FindTask findTask = new FindTask(KeyWord, this.TASK_LIST.getTasks());
                    findTask.List();
                } else {
                    if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){

                        if(input.startsWith("todo")){
                            this.TASK_LIST.AddTodo(input,this.STORAGE);
                        }else if(input.startsWith("deadline")){
                            this.TASK_LIST.AddDeadline(input,this.STORAGE);
                        }else if(input.startsWith("event")){
                            this.TASK_LIST.AddEvent(input,this.STORAGE);
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

    public static void main(String[] args) {
        new Duke("C:\\Users\\Shaopeng\\Desktop\\duke\\data\\duke.txt").run();
    }
}
