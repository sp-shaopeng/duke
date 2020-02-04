package duke;

import duke.task.Deadlines;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The Class DeadLineTest.
 */
public class DeadLineTest {

    /**
     * Test deadline format.
     */
    @Test
    public void testDeadlineFormat() {
        LocalDate deadLineDate = LocalDate.parse("2019-02-11");
        Deadlines task = new Deadlines("Return Book", deadLineDate);
        assertEquals("Feb 11 2019", task.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
    /*
    @Test
    public void Trying() {
        assertEquals("a","b","lol\n");
    }
    */

}
