package duke.exception;

public class DukeException extends Exception {
    String errorMsg;

    public DukeException(String s) {
        super(s);
        this.errorMsg = s;
    }

    @Override
    public String toString() {
        return this.errorMsg;
    }
}
