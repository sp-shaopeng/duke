package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class Events wis for tasks which are Event format.
 */
public class Events extends Task {

    /**
     * The duration: when the event held in hours e.g 3-6pm.
     */
    protected String duration;

    /**
     * The Event date: the date the event held.
     */
    protected LocalDate eventDate;

    /**
     * Instantiates a new events.
     *
     * @param description the description
     * @param eventDate   the event date
     * @param duration    the duration
     */
    public Events(String description, LocalDate eventDate, String duration) {
        super(description);
        this.duration = duration;
        this.eventDate = eventDate;
    }

    /**
     * Gets the event date.
     *
     * @return (LocalDate) eventDate
     */
    public LocalDate getEventDate() {
        return this.eventDate;
    }

    /**
     * Gets the duration.
     *
     * @return the duration e.g(2-9pm)
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.duration + ")";

    }
}
