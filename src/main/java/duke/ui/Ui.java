package duke.ui;

public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greet with the logo
     */
    public void GreetLogo(){
        System.out.println("Hello from\n" + this.logo + "\n");
    }

    /**
     * Normal greeting
     */
    public void Greet(){
        System.out.println("      Hello! I'm Duke\n" + "      What can I do for you?");
    }

    /**
     * Sign off before exiting the program
     */
    public void Bye(){
        System.out.println("      Bye. Hope to see you again soon!");
    }

    /**
     * To notify that there is an error in the File
     */
    public void showLoadingError(){
        System.out.println("File is corrupted, Start on a new file");
    }
}
