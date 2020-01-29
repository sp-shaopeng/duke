package duke.task;

import java.util.ArrayList;

public class FindTask extends TaskList{
    protected ArrayList <Task> SpecificTask;

    public FindTask(String description,ArrayList<Task> allData) {
        SpecificTask = new ArrayList<Task>();
        for(int i = 0; i < allData.size(); i++){
            if(allData.get(i).getDescription().contains(description.trim())){
                SpecificTask.add(allData.get(i));
            }
        }
    }

    public ArrayList<Task> getSpecificTask(){
        return this.SpecificTask;
    }

    @Override
    public void List() {
        System.out.println("      Here are the tasks in your list:");
        int taskSize = this.SpecificTask.size();
        for(int i = 0; i < taskSize; i++){
            int count = i + 1;
            System.out.println("      " + count + ". " + this.SpecificTask.get(i).toString());
        }
    }
}
