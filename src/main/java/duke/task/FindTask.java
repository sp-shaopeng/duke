
package duke.task;

import java.util.ArrayList;

/**
 * The Class FindTask: to get the specific task out.
 * by checking the task description with the keywords.
 */
public class FindTask extends TaskList {
    
    /** The specific task. */
    protected ArrayList<Task> specificTask;

    /**
     * Instantiates a new find task.
     *
     * @param description the description
     * @param allData the all data
     */
//    public FindTask(String description, ArrayList<Task> allData) {
//        specificTask = new ArrayList<Task>();
//        for (int i = 0; i < allData.size(); i++) {
//            if (allData.get(i).getDescription().contains(description.trim())) {
//                specificTask.add(allData.get(i));
//            }
//        }
//    }

    /**
     * Instantiates a new find task.
     *
     * @param allData the all data
     */
    public FindTask(ArrayList<Task> allData) {
        this.specificTask = allData;
    }

    public void search(String ... keywords) {
        ArrayList<Task> targetList = new ArrayList<>();
        for(int i = 0; i < specificTask.size(); i++) {
            for(String word: keywords) {
                if(specificTask.get(i).getDescription().contains(word)) {
                    targetList.add(specificTask.get(i));
                    break;
                }
            }
        }
        this.specificTask = targetList;
    }

    /**
     * Gets the specific task.
     *
     * @return the specific task
     */
    public ArrayList<Task> getSpecificTask() {
        return this.specificTask;
    }

    /**
     * List.
     */
    @Override
    public void list() {
        System.out.println("      Here are the tasks in your list:");
        int taskSize = this.specificTask.size();
        for (int i = 0; i < taskSize; i++) {
            int count = i + 1;
            System.out.println("      " + count + ". " + this.specificTask.get(i).toString());
        }
    }
}
