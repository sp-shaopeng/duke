package duke.task;

import java.util.ArrayList;

public class FindTask extends TaskList{
    protected ArrayList <Task> SPECIFIC_TASK;

    public FindTask(String description,ArrayList<Task> allData) {
        SPECIFIC_TASK = new ArrayList<Task>();
        for(int i = 0; i < allData.size(); i++){
            if(allData.get(i).getDescription().contains(description.trim())){
                SPECIFIC_TASK.add(allData.get(i));
            }
        }
    }

    public ArrayList<Task> getSpecificTask(){
        return this.SPECIFIC_TASK;
    }

    @Override
    public void List() {
        System.out.println("      Here are the tasks in your list:");
        int taskSize = this.SPECIFIC_TASK.size();
        for(int i = 0; i < taskSize; i++){
            int count = i + 1;
            System.out.println("      " + count + ". " + this.SPECIFIC_TASK.get(i).toString());
        }
    }
}
