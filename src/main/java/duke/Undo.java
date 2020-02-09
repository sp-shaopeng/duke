package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Undo {

    private ArrayList<String> allVersionTask;


    public Undo() {
        allVersionTask = new ArrayList<>();
    }


    public void undo(TaskList taskList, Storage data) throws DukeException {
        int total = this.allVersionTask.size();
        System.out.println(total);
        String prevVersion = allVersionTask.get(total - 2);
        allVersionTask.remove(total - 1);
        ArrayList<Task> prevTaskList = data.loadData(prevVersion);
        taskList.setTaskList(prevTaskList);
    }


    public void addVersion(String latestVersion) {
        allVersionTask.add(latestVersion);
    }

    public String getPrevVersion() {
        int size = allVersionTask.size();
        return allVersionTask.get(size - 1);
    }

    public int getSize() {
        return allVersionTask.size();
    }

}
