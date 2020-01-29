package duke.task;

public class ToDos extends Task {

    public ToDos(String DESCRIPTION) {
        super(DESCRIPTION);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
