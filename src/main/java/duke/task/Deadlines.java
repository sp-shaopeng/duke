package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * The Class Deadlines is for tasks whose is a Deadline format.
 */
public class Deadlines extends Task {

    /**
     * The deadline date: the due date.
     */
    protected LocalDate deadLineDate;

    /**
     * Instantiates a new deadlines.
     *
     * @param description  the description
     * @param deadLineDate the deadline date
     */
    public Deadlines(String description, LocalDate deadLineDate) {
        super(description);
        this.deadLineDate = deadLineDate;
    }

    /**
     * Gets the deadline date.
     *
     * @return the deadline date
     */
    public LocalDate getDeadlineDate() {
        return this.deadLineDate;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadLineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
