import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> Tasks;

    public TaskList(ArrayList<Task> Tasks){
        this.Tasks = Tasks;
    }
    public TaskList(){
        Tasks = new ArrayList<>();
    }

    public void AddTodo(String input, Storage data) throws DukeException{
        String Remain = input.replace("todo","").trim();
        if(Remain.length() >= 1){
            ToDos newToDo = new ToDos(Remain);
            Add(newToDo);
            data.appendToFile("T",0,Remain);
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void AddDeadline(String input,Storage data) throws DukeException{
        String Remain = input.replace("deadline","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /by ");
                LocalDate deadlineDate = LocalDate.parse(detail[1].trim());
                Deadlines newDeadLine = new Deadlines(detail[0], deadlineDate);
                Add(newDeadLine);
                data.appendToFile("D",0,detail[0],detail[1]);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : description, YYYY-MM-DD");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of a deadline is wrong");
        }

    }


    public void AddEvent(String input, Storage data) throws DukeException{
        String Remain = input.replace("event","").trim();
        if(Remain.length() >= 1){
            try{
                String[] detail = Remain.split(" /at ");
                String[] splitDateTime = detail[1].trim().split(" ");
                LocalDate EventDate = LocalDate.parse(splitDateTime[0].trim());
                Events newEvent = new Events(detail[0], EventDate,splitDateTime[1].trim());
                Add(newEvent);
                data.appendToFile("E",0,detail[0],detail[1]);
            }catch(Exception e){
                throw new DukeException("      ☹ OOPS!!! Please enter in the format of : description, YYYY-MM-DD and hours");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! The description of an event is wrong.");
        }

    }




    public void List(){
        System.out.println("      Here are the tasks in your list:");
        int taskSize = this.Tasks.size();
        for(int i = 0; i < taskSize; i++){
            int count = i + 1;
            System.out.println("      " + count + ". " + this.Tasks.get(i).toString());
        }
    }

    public void Add(Task newTask){
        System.out.println("      Got it. I've added this task: ");
        this.Tasks.add(newTask);
        System.out.print("        " + newTask +"\n");
        int amtOfTask = this.Tasks.size();
        if(amtOfTask <= 1){
            System.out.println("      Now you have " + amtOfTask+ " task in list");
        }else {
            System.out.println("      Now you have " + amtOfTask+ " tasks in list");
        }
    }

    public void Done(int number,Storage data) throws DukeException{
        if(number <= this.Tasks.size() && number >= 1) {
            Task getTask = this.Tasks.get(number - 1);
            getTask.markAsDone();
            System.out.println("      " + getTask);
        }else{
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
        data.updateFile(this.Tasks);
    }

    public void Delete(int number, Storage data) throws DukeException{
        if(number <= this.Tasks.size() && number >= 1) {
            Task getTask = this.Tasks.get(number - 1);
            this.Tasks.remove(number - 1);
            System.out.println("      Noted. I've removed this task:");
            System.out.println("        " + getTask);
            int amtOfTask = this.Tasks.size();
            if(amtOfTask <= 1){
                System.out.println("      Now you have " + amtOfTask+ " task in list");
            }else {
                System.out.println("      Now you have " + amtOfTask+ " tasks in list");
            }
        }else{
            throw new DukeException("      ☹ OOPS!!! There is no such tasks");
        }
        data.updateFile(this.Tasks);
    }
}
