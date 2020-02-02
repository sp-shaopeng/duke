package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.FindTask;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;


/**
 * The Class Duke: driver class.
 */
public class Duke {

    /** The file path. */
    public String filePath;
    
    /** The storage. */
    private Storage storage;
    
    /** The task list. */
    private TaskList taskList;
    
    /** The ui. */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.filePath = filePath;
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\Shaopeng\\Desktop\\duke\\data\\duke.txt").run();
    }

    /**
     * This method will be running throughout the entire session
     * It will listen to the incoming users's command and process the comment accordingly.
     */

    public void run() {
        this.ui.greetLogo();
        this.ui.greet();
        ui.printInputRequest();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine();
                input = input.toLowerCase();
                if (input.equalsIgnoreCase("bye")) {
                    this.ui.bye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    this.taskList.list();
                } else if (input.startsWith("done")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(5));
                        this.taskList.done(taskNumber, this.storage);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("OOPS!!! Done format is wrong"));
                    } catch (NumberFormatException e) {
                        System.out.println(new DukeException("OOPS!!! Done format is wrong"));
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7));
                        this.taskList.delete(taskNumber, this.storage);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("OOPS!!! Delete format is wrong"));
                    } catch (NumberFormatException e) {
                        System.out.println(new DukeException("OOPS!!! Delete format is wrong"));
                    }
                } else if (input.startsWith("find")) {
                    String keyWord = input.substring(5).trim();
                    FindTask findTask = new FindTask(keyWord, this.taskList.getTaskList());
                    findTask.list();
                } else {
                    if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {

                        if (input.startsWith("todo")) {
                            this.taskList.addTodo(input, this.storage);
                        } else if (input.startsWith("deadline")) {
                            this.taskList.addDeadline(input, this.storage);
                        } else if (input.startsWith("event")) {
                            this.taskList.addEvent(input, this.storage);
                        }
                    } else {
                        throw new DukeException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            ui.printInputRequest();
        }
    }
}
