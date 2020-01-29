package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected String DURATION;
    protected LocalDate EventDate;
    public Events(String description,LocalDate EventDate, String DURATION) {
        super(description);
        this.DURATION = DURATION;
        this.EventDate = EventDate;
    }

    public LocalDate getEventDate(){
        return this.EventDate;
    }

    public String getDuration(){
        return this.DURATION;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + EventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +" " + this.DURATION + ")";

    }
}
