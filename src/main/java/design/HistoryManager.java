package design;

import java.util.LinkedList;

// Invoker in Command Design Pattern
public class HistoryManager {

    private LinkedList<Command> histort = new LinkedList<>();

    public void addAndExecute(Command command){
        this.histort.add(command);
        command.execute();
    }
    public void undoTheLast(){
        if(this.histort.size()>1) {
            this.histort.removeLast().unDo();
            this.histort.removeLast().unDo();
        }
    }

}
