package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.FindTask;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Class Duke.
 */
public class Duke {

    /**
     * The file path.
     */
    public String filePath;

    /**
     * The storage.
     */
    private Storage storage;

    /**
     * The task list.
     */
    private TaskList taskList;

    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     */
    public Duke() {
        ui = new Ui();
        this.filePath = "../duke/data/duke.txt";
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * process the command and output String
     * @param input the command by user
     * @return string which is the response to the command
     */
    public String processCommand(String input) {
        try {
            input = input.toLowerCase();
            if (input.equalsIgnoreCase("bye")) {
                return this.ui.bye();
            } else if (input.equalsIgnoreCase("list")) {
                return this.taskList.list();
            } else if (input.startsWith("done")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    return this.taskList.done(taskNumber, this.storage);
                } catch (StringIndexOutOfBoundsException e) {
                    return new DukeException("OOPS!!! Done format is wrong\n").toString();
                } catch (NumberFormatException e) {
                    return new DukeException("OOPS!!! Done format is wrong\n").toString();
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    return this.taskList.delete(taskNumber, this.storage);
                } catch (StringIndexOutOfBoundsException e) {
                    return new DukeException("OOPS!!! Delete format is wrong").toString();
                } catch (NumberFormatException e) {
                    return new DukeException("OOPS!!! Delete format is wrong").toString();
                }
            } else if (input.startsWith("find")) {
                String[] keyWords = input.substring(5).trim().split(" ");
                FindTask findTask = new FindTask(this.taskList.getTaskList());
                findTask.search(keyWords);
                return findTask.list();
            } else {
                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    if (input.startsWith("todo")) {
                        return this.taskList.addTodo(input, this.storage);
                    } else if (input.startsWith("deadline")) {
                        return this.taskList.addDeadline(input, this.storage);
                    } else if (input.startsWith("event")) {
                        return this.taskList.addEvent(input, this.storage);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        } catch (DukeException e) {
            return e.toString();
        }
        return "";
    }

    /**
     * This method will be running throughout the entire session
     * It will listen to the incoming users's command and process the comment accordingly.
     *
     * @return a greeting string
     */
    public String greet() {
        return this.ui.greetLogo() + this.ui.greet();
    }


}
