package duke.ui;


/**
 * The Class Ui provides a basic user interface.
 */
public class Ui {
    
    /** The logo. */
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greet with the logo.
     */
    public void greetLogo() {
        System.out.println("Hello from\n" + this.logo + "\n");
    }

    /**
     * Normal greeting.
     */
    public void greet() {
        System.out.println("      Hello! I'm Duke\n" + "      What can I do for you?");
    }

    /**
     * Prints the input request.
     */
    public void printInputRequest() {
        System.out.println();
        System.out.println("Input Command: ");
    }

    /**
     * Sign off before exiting the program.
     */
    public void bye() {
        System.out.println("      Bye. Hope to see you again soon!");
    }

    /**
     * To notify that there is an error in the File.
     */
    public void showLoadingError() {
        System.out.println("File is corrupted, Start on a new file");
    }
}
