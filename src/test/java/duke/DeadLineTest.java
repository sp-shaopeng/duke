package duke;
import duke.task.Deadlines;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTest {
    @Test
    public void TestDeadlineFormat(){
        LocalDate deadLineDate = LocalDate.parse("2019-02-11");
        Deadlines task = new Deadlines("Return Book",deadLineDate);
        assertEquals("Feb 11 2019",task.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }


}
