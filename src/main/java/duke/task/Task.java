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

    /**
     * Mark the task to be done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Mark the task to be undone
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * @return the description of the task (e.g: Buy food)
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * @return a boolean: true: the task is done
     *                    false: the task is not done
     */
    public boolean getStatus(){
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
