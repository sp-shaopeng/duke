package duke.task;

import duke.exception.DukeException;
import duke.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Class TaskList is class to keep track of all the task that is created.
 */
public class TaskList {

    /**
     * The taskList.
     */
    protected ArrayList<Task> taskList;

    /**
     * Instantiates a new task list.
     *
     * @param taskList the taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Instantiates a new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Gets the taskList.
     *
     * @return the taskList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds the todo.
     *
     * @param input the input
     * @param data  the data
     * @throws DukeException the duke exception
     */
    public void addTodo(String input, Storage data) throws DukeException {
        String remainString = input.replace("todo", "").trim();
        if (remainString.length() >= 1) {
            ToDos newToDo = new ToDos(remainString);
            add(newToDo);
            data.appendToFile("T", 0, remainString);
        } else {
            throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds the deadline.
     *
     * @param input the input
     * @param data  the data
     * @throws DukeException the duke exception
     */
    public void addDeadline(String input, Storage data) throws DukeException {
        String remainString = input.replace("deadline", "").trim();
        if (remainString.length() >= 1) {
            try {
                String[] detail = remainString.split(" /by ");
                LocalDate deadLineDate = LocalDate.parse(detail[1].trim());
                Deadlines newDeadLine = new Deadlines(detail[0], deadLineDate);
                add(newDeadLine);
                data.appendToFile("D", 0, detail[0], detail[1]);
            } catch (Exception e) {
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : description, YYYY-MM-DD");
            }
        } else {
            throw new DukeException("      ☹ OOPS!!! The description of a deadline is wrong");
        }

    }


    /**
     * Adds the event.
     *
     * @param input the input
     * @param data  the data
     * @throws DukeException the duke exception
     */
    public void addEvent(String input, Storage data) throws DukeException {
        String remainString = input.replace("event", "").trim();
        if (remainString.length() >= 1) {
            try {
                String[] detail = remainString.split(" /at ");
                String[] splitDateTime = detail[1].trim().split(" ");
                LocalDate eventDate = LocalDate.parse(splitDateTime[0].trim());
                Events newEvent = new Events(detail[0], eventDate, splitDateTime[1].trim());
                add(newEvent);

                data.appendToFile("E", 0, detail[0], splitDateTime[0] + "-" + splitDateTime[1]);
            } catch (Exception e) {
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : "
                        + "description, YYYY-MM-DD and hours");
            }
        } else {
            throw new DukeException("      ☹ OOPS!!! The description of an event is wrong.");
        }

    }


    /**
     * List.
     */
    public void list() {
        System.out.println("      Here are the tasks in your list:");
        int taskSize = this.taskList.size();
        for (int i = 0; i < taskSize; i++) {
            int count = i + 1;
            System.out.println("      " + count + ". " + this.taskList.get(i).toString());
        }
    }

    /**
     * Adds the.
     *
     * @param newTask the new task
     */
    public void add(Task newTask) {
        System.out.println("      Got it. I've added this task: ");
        this.taskList.add(newTask);
        System.out.print("        " + newTask + "\n");
        int amtOfTask = this.taskList.size();
        if (amtOfTask <= 1) {
            System.out.println("      Now you have " + amtOfTask + " task in list");
        } else {
            System.out.println("      Now you have " + amtOfTask + " tasks in list");
        }
    }

    /**
     * Done.
     *
     * @param number the number
     * @param data   the data
     * @throws DukeException the duke exception
     */
    public void done(int number, Storage data) throws DukeException {
        if (number <= this.taskList.size() && number >= 1) {
            Task getTask = this.taskList.get(number - 1);
            getTask.markAsDone();
            System.out.println("      " + getTask);
        } else {
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
        data.updateFile(this.taskList);
    }


    /**
     * Delete.
     *
     * @param number the number
     * @param data   the data
     * @throws DukeException the duke exception
     */
    public void delete(int number, Storage data) throws DukeException {
        if (number <= this.taskList.size() && number >= 1) {
            Task getTask = this.taskList.get(number - 1);
            this.taskList.remove(number - 1);
            System.out.println("      Noted. I've removed this task:");
            System.out.println("        " + getTask);
            int amtOfTask = this.taskList.size();
            if (amtOfTask <= 1) {
                System.out.println("      Now you have " + amtOfTask + " task in list");
            } else {
                System.out.println("      Now you have " + amtOfTask + " tasks in list");
            }
        } else {
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
        data.updateFile(this.taskList);
    }
}
