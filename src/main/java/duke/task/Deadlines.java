package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate deadlineDate;

    public Deadlines(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * @return (LocalDate) deadlineDate
     */
    public LocalDate getDeadlineDate(){
        return this.deadlineDate;
    }
    @Override

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
