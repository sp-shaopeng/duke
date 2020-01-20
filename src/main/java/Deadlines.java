public class Deadlines extends Task {
    protected String deadLine;

    public Deadlines(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadLine + ")";
    }
}
