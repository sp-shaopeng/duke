package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    public String Duration;
    public LocalDate EventDate;
    public Events(String description,LocalDate EventDate, String Duration) {
        super(description);
        this.Duration = Duration;
        this.EventDate = EventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + EventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +" " + this.Duration + ")";

    }
}
