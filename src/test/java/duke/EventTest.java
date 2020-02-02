/**
 * 
 */
package duke;

import duke.task.Events;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: Auto-generated Javadoc
/**
 * The Class EventTest.
 */
public class EventTest {
    
    /**
     * Test event date format.
     */
    @Test
    public void TestEventDateFormat() {
        LocalDate EventDate = LocalDate.parse("2019-02-11");
        Events task = new Events("Return Book", EventDate, "3-6pm");
        assertEquals("Feb 11 2019", task.getEventDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Test event duration format.
     */
    @Test
    public void TestEventDurationFormat() {
        LocalDate EventDate = LocalDate.parse("2019-02-11");
        Events task = new Events("Return Book", EventDate, "3-6pm");
        assertEquals("3-6pm", task.getDuration());
    }

}
