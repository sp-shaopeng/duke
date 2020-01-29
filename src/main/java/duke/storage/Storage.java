package duke.storage;
import duke.task.*;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


import static java.lang.System.exit;

public class Storage {
    private String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

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
            exit(0);
        }catch(Exception e){
            System.out.println("File Corrupted " + e.getMessage());
        }

        return Tasks;
    }




    //THIS METHOD IS FOR TODO
    public void appendToFile(String TaskNature, int isDone, String TaskDescription){
        try {
            File data = new File(this.filePath);
            FileWriter fr = new FileWriter(data, true);
            String line = TaskNature + "-" + isDone + "-" + TaskDescription;
            fr.write(line + "\n");
            fr.close();
        }catch(java.io.IOException e){
            System.out.println("UNABLE TO SAVE DATA");
        }
    }

    //THIS METHOD IS FOR DEADLINE AND EVENTS
    public void appendToFile(String TaskNature, int isDone, String TaskDescription, String Time){
        try {
            File data = new File(this.filePath);
            FileWriter fr = new FileWriter(data, true);
            String line = TaskNature + "-" + isDone + "-" + TaskDescription + "-" + Time;
            fr.write(line + "\n");
            fr.close();
        }catch(java.io.IOException e){
            System.out.println("UNABLE TO SAVE DATA");
        }
    }

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
