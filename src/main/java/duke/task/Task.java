package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
//        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "tick" : "X"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }
    public void markAsNotDone(){
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getStatus(){
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
