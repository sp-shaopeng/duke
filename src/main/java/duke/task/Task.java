package duke.task;

public class Task {
    protected String DESCRIPTION;
    protected boolean isDone;

    public Task(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
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
        return this.DESCRIPTION;
    }

    public boolean getStatus(){
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.DESCRIPTION;
    }

}
