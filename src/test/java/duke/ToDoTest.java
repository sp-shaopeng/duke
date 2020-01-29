package duke;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void TestToDoDescription(){
        ToDos task = new ToDos("buy food for mum");
        assertEquals("buy food for mum", task.getDescription());
    }
}
