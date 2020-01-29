package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate DEADLINE_DATE;
    public Deadlines(String description, LocalDate DEADLINE_DATE) {
        super(description);
        this.DEADLINE_DATE = DEADLINE_DATE;
    }

    public LocalDate getDeadlineDate(){
        return this.DEADLINE_DATE;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.DEADLINE_DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
