package duke.storage;
import duke.task.*;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }


    /**
     *Return an Arraylist <Task>, this contains of all the tasks which are saved in the duke.txt
     * <p>
     *This method always returns an ArrayList even if the file is Not found or corrupted
     * @return an Arraylist<Task>
     */
    public ArrayList<Task> load(){
        ArrayList<Task> Tasks = new ArrayList<>();
        try {
            File data = new File(this.filePath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] content = line.split("-");
                String TaskNature = content[0];
                int isDone = Integer.parseInt(content[1]);
                if(TaskNature.equals("T")){
                    ToDos todo = new ToDos(content[2]);
                    if(isDone == 1){
                        todo.markAsDone();
                    }
                    Tasks.add(todo);
                }else if(TaskNature.equals("D")){
                    String date = content[3] + "-" + content[4] + "-" + content[5];
                    LocalDate deadlineDate = LocalDate.parse(date.trim());
                    Deadlines deadline = new Deadlines(content[2],deadlineDate);
                    if(isDone == 1) {
                        deadline.markAsDone();
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
                        event.markAsDone();
                    }
                    Tasks.add(event);
                }else{
                    break;
                }
            }
        }catch(java.io.FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }catch(Exception e){
            System.out.println("File Corrupted " + e.getMessage());
        }

        return Tasks;
    }

    /**
     * This method will throw an exception to denote that when IOException occurs
     * @param TaskNature An alphabet "T", "D" or "E" which respectively represent TODO DEADLINE and EVENT
     * @param isDone An integer 1: represents the task has been done
     *                          0: represents the task has not been done
     * @param TaskDescription A string which describe the task
     */


    //THIS METHOD IS FOR TODO
    public void appendToFile(String TaskNature, int isDone, String TaskDescription){
        try {
//            File data = new File(this.filePath);
            FileWriter fr = new FileWriter(this.filePath, true);
            String line = TaskNature + "-" + isDone + "-" + TaskDescription;
            fr.write(line + "\n");
            fr.close();
        }catch(java.io.IOException e){
            System.out.println("UNABLE TO SAVE DATA");
        }
    }


    /**
     * Making use of polymorphism, this method has one more argument: "TIME". It is used to append to database
     * when there is new Deadline or Event being created
     * This method will throw an exception to denote that when IOException occurs
     * @param TaskNature An alphabet "T", "D" or "E" which respectively represent TODO DEADLINE and EVENT
     * @param isDone An integer 1: represents the task has been done
     *                          0: represents the task has not been done
     * @param TaskDescription A string which describe the task
     */
    //THIS METHOD IS FOR DEADLINE AND EVENTS
    public void appendToFile(String TaskNature, int isDone, String TaskDescription, String Time){
        try {
//            File data = new File(this.filePath);
            FileWriter fr = new FileWriter(this.filePath, true);
            String line = TaskNature + "-" + isDone + "-" + TaskDescription + "-" + Time;
            fr.write(line + "\n");
            fr.close();
        }catch(java.io.IOException e){
            System.out.println("UNABLE TO SAVE DATA");
        }
    }


    /**
     *This method allows the txt file to have the latest data set after there is an operation
      * @param Tasks Overwrite the whole txt file with the updated version of Tasks
     */

    public void updateFile(ArrayList <Task> Tasks){

        try {
            StringBuilder NewData = new StringBuilder();
            Writer fileWriter = new FileWriter(this.filePath, false); //overwrites file
            for(int i = 0; i < Tasks.size(); i++){
                Task task = Tasks.get(i);
                if(task instanceof ToDos){
                    String line;
                    if(task.getStatus()) {
                        line = "T" + "-1-" + task.getDescription();
                    }else{
                        line = "T" + "-0-" + task.getDescription();
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }else if(task instanceof Deadlines){
                    String line;
                    if(task.getStatus()) {
                        line = "D" + "-1-" + task.getDescription() + "-" + ((Deadlines) task).getDeadlineDate().toString();
                    }else{
                        line = "D" + "-0-" + task.getDescription() + "-" + ((Deadlines) task).getDeadlineDate().toString();
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }else{
                    String line;
                    if(task.getStatus()) {
                        line = "E" + "-1-" + task.getDescription() + "-" + ((Events) task).getEventDate() + "-" + ((Events) task).getDuration();
                    }else{
                        line = "E" + "-0-" + task.getDescription() + "-" + ((Events) task).getEventDate() + "-" + ((Events) task).getDuration();
                    }
                    NewData.append(line);
                    NewData.append("\n");
                }

            }
            fileWriter.write(NewData.toString());
            fileWriter.close();

        }catch(java.io.IOException e){
            System.out.println(("ERROR " + e.getMessage()));
        }
    }


}
