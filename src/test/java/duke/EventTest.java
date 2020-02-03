package duke;

import duke.task.Events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * The Class EventTest.
 */
public class EventTest {
    
    /**
     * Test event date format.
     */
    @Test
    public void testEventDateFormat() {
        LocalDate eventDate = LocalDate.parse("2019-02-11");
        Events task = new Events("Return Book", eventDate, "3-6pm");
        assertEquals("Feb 11 2019", task.getEventDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Test event duration format.
     */
    @Test
    public void testEventDurationFormat() {
        LocalDate eventDate = LocalDate.parse("2019-02-11");
        Events task = new Events("Return Book", eventDate, "3-6pm");
        assertEquals("3-6pm", task.getDuration());
    }

}
