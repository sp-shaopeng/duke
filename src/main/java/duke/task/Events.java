package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected String Duration;
    protected LocalDate EventDate;
    public Events(String description,LocalDate EventDate, String Duration) {
        super(description);
        this.Duration = Duration;
        this.EventDate = EventDate;
    }
    /**
     * @return (LocalDate) EventDate
     */
    public LocalDate getEventDate(){
        return this.EventDate;
    }

    /**
     * @return the duration e.g(2-9pm)
     */
    public String getDuration(){
        return this.Duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + EventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +" " + this.Duration + ")";

    }
}
