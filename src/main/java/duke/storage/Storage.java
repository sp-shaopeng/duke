package duke.storage;

import duke.Undo;
import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Class Storage.
 */
public class Storage {

    /**
     * The file path.
     */
    private String filePath;

    private Undo versionControl;

    /**
     * Instantiates a new storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public void setVersionControl(Undo versionControl) {
        this.versionControl = versionControl;
    }

    /**
     * This method loads the data to duke base on the version.
     *
     * @param version s string which contains all data of previous versions
     * @return an arrayList which stores all the tasks
     * @throws DukeException the duke exception
     */

    public ArrayList<Task> loadData(String version) throws DukeException {
        ArrayList<Task> tasksList = new ArrayList<>();
        String[] allList = version.split("\n");
        for (int k = 0; k < allList.length; k++) {
            String line = allList[k];
            String[] content = line.split("-");
            String taskNature = content[0];
            int isDone = Integer.parseInt(content[1]);
            if (taskNature.equals("T")) {
                ToDos todo = new ToDos(content[2]);
                if (isDone == 1) {
                    todo.markAsDone();
                }
                tasksList.add(todo);
            } else if (taskNature.equals("D")) {
                String date = content[3] + "-" + content[4] + "-" + content[5];
                LocalDate deadlineDate = LocalDate.parse(date.trim());
                Deadlines deadline = new Deadlines(content[2], deadlineDate);
                if (isDone == 1) {
                    deadline.markAsDone();
                }
                tasksList.add(deadline);
            } else if (taskNature.equals("E")) {
                String date = content[3] + "-" + content[4] + "-" + content[5];
                LocalDate eventDate = LocalDate.parse(date.trim());
                StringBuilder duration = new StringBuilder();
                for (int i = 6; i < content.length; i++) {
                    if (i == content.length - 1) {
                        duration.append(content[i]);
                    } else {
                        duration.append(content[i] + "-");
                    }
                }
                Events event = new Events(content[2], eventDate, duration.toString());
                if (isDone == 1) {
                    event.markAsDone();
                }
                tasksList.add(event);
            } else {
                break;
            }
        }
        updateFile(version);
        return tasksList;
    }

    /**
     * Load.
     *
     * @return the array list
     * @throws DukeException the duke exception
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasksList = new ArrayList<>();
        StringBuilder vc = new StringBuilder();
        try {
            File data = new File(this.filePath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                vc.append(line + "\n");
                String[] content = line.split("-");
                String taskNature = content[0];
                int isDone = Integer.parseInt(content[1]);
                if (taskNature.equals("T")) {
                    ToDos todo = new ToDos(content[2]);
                    if (isDone == 1) {
                        todo.markAsDone();
                    }
                    tasksList.add(todo);
                } else if (taskNature.equals("D")) {
                    String date = content[3] + "-" + content[4] + "-" + content[5];
                    LocalDate deadlineDate = LocalDate.parse(date.trim());
                    Deadlines deadline = new Deadlines(content[2], deadlineDate);
                    if (isDone == 1) {
                        deadline.markAsDone();
                    }
                    tasksList.add(deadline);
                } else if (taskNature.equals("E")) {
                    String date = content[3] + "-" + content[4] + "-" + content[5];
                    LocalDate eventDate = LocalDate.parse(date.trim());
                    StringBuilder duration = new StringBuilder();
                    for (int i = 6; i < content.length; i++) {
                        if (i == content.length - 1) {
                            duration.append(content[i]);
                        } else {
                            duration.append(content[i] + "-");
                        }
                    }
                    Events event = new Events(content[2], eventDate, duration.toString());
                    if (isDone == 1) {
                        event.markAsDone();
                    }
                    tasksList.add(event);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            throw new DukeException("Unable to load data\n");
        }
        this.versionControl.addVersion(vc.toString());
        return tasksList;
    }


    /**
     * Append to file.
     *
     * @param taskNature      the task nature
     * @param isDone          the is done
     * @param taskDescription the task description
     * @throws DukeException the duke exception
     */
    public void appendToFile(String taskNature, int isDone, String taskDescription) throws DukeException {
        try {
            FileWriter fr = new FileWriter(this.filePath, true);
            String line = taskNature + "-" + isDone + "-" + taskDescription + "\n";
            String prevVersion = this.versionControl.getPrevVersion();
            String newVersion = prevVersion + line;
            this.versionControl.addVersion(newVersion);
            fr.write(line);
            fr.close();
        } catch (java.io.IOException e) {
            throw new DukeException("Unable to save data\n");
        }
    }

    /**
     * Append to file.
     *
     * @param taskNature      the task nature
     * @param isDone          the is done
     * @param taskDescription the task description
     * @param time            the time
     * @throws DukeException the duke exception
     */
    //THIS METHOD IS FOR DEADLINE AND EVENTS
    public void appendToFile(String taskNature, int isDone, String taskDescription, String time) throws DukeException {
        try {
            //File data = new File(this.FILE_PATH);
            FileWriter fr = new FileWriter(this.filePath, true);
            String line = taskNature + "-" + isDone + "-" + taskDescription + "-" + time + "\n";
            String prevVersion = this.versionControl.getPrevVersion();
            String newVersion = prevVersion + line;
            this.versionControl.addVersion(newVersion);
            fr.write(line);
            fr.close();
        } catch (java.io.IOException e) {
            throw new DukeException("Unable to save data\n");
        }
    }

    /**
     * Update the file with a String data.
     *
     * @param data a string which contains all the data
     * @throws DukeException the duke exception
     */

    public void updateFile(String data) throws DukeException {
        try {
            Writer fileWriter = new FileWriter(this.filePath, false);
            fileWriter.write(data);
            fileWriter.close();
        } catch (java.io.IOException e) {
            throw new DukeException("Error" + e.getMessage() + "\n");
        }
    }

    /**
     * Update file.
     *
     * @param tasksList the tasksList
     * @throws DukeException the duke exception
     */
    public void updateFile(ArrayList<Task> tasksList) throws DukeException {

        try {
            StringBuilder newData = new StringBuilder();
            Writer fileWriter = new FileWriter(this.filePath, false); //overwrites file
            for (int i = 0; i < tasksList.size(); i++) {
                Task task = tasksList.get(i);
                if (task instanceof ToDos) {
                    String line;
                    if (task.getStatus()) {
                        line = "T" + "-1-" + task.getDescription();
                    } else {
                        line = "T" + "-0-" + task.getDescription();
                    }
                    newData.append(line);
                    newData.append("\n");
                } else if (task instanceof Deadlines) {
                    String line;
                    if (task.getStatus()) {
                        line = "D" + "-1-" + task.getDescription() + "-"
                                + ((Deadlines) task).getDeadlineDate().toString();
                    } else {
                        line = "D" + "-0-" + task.getDescription() + "-"
                                + ((Deadlines) task).getDeadlineDate().toString();
                    }
                    newData.append(line);
                    newData.append("\n");
                } else {
                    String line;
                    if (task.getStatus()) {
                        line = "E" + "-1-" + task.getDescription() + "-"
                                + ((Events) task).getEventDate() + "-" + ((Events) task).getDuration();
                    } else {
                        line = "E" + "-0-" + task.getDescription() + "-"
                                + ((Events) task).getEventDate() + "-" + ((Events) task).getDuration();
                    }
                    newData.append(line);
                    newData.append("\n");
                }

            }
            this.versionControl.addVersion(newData.toString());
            fileWriter.write(newData.toString());
            fileWriter.close();

        } catch (java.io.IOException e) {
            throw new DukeException("Error" + e.getMessage() + "\n");
        }
    }


}
