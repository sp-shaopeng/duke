package duke;
import duke.storage.*;
import duke.ui.*;
import duke.task.*;
import duke.exception.*;
import java.util.Scanner;



public class Duke {

    public String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.GreetLogo();
        this.ui.Greet();
        Scanner sc = new Scanner(System.in);
        while(true){
            try {
                String input = sc.nextLine();
                input = input.toLowerCase();
                if (input.equalsIgnoreCase("bye")) {
                    this.ui.Bye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    this.tasks.List();
                } else if (input.startsWith("done")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(5));
                        this.tasks.Done(taskNumber, this.storage);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Done format is wrong"));
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7));
                        this.tasks.Delete(taskNumber, this.storage);
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }catch(NumberFormatException e){
                        System.out.println(new DukeException(      "OOPS!!! Delete format is wrong"));
                    }
                } else {
                    if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){

                        if(input.startsWith("todo")){
                            this.tasks.AddTodo(input,this.storage);
                        }else if(input.startsWith("deadline")){
                            this.tasks.AddDeadline(input,this.storage);
                        }else if(input.startsWith("event")){
                            this.tasks.AddEvent(input,this.storage);
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
        new Duke("data/duke.txt").run();
    }

}
