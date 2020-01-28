public class Events extends Task {

    protected String Duration;

    public Events(String description, String Duration) {
        super(description);
        this.Duration = Duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.Duration + ")";
    }
}
