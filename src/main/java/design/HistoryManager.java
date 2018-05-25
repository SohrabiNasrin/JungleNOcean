package design;

import java.io.*;
import java.util.LinkedList;

// Invoker in Command Design Pattern
public class HistoryManager implements Serializable{

    private LinkedList<Command> history = new LinkedList<>();

    public void addAndExecute(Command command){
        this.history.add(command);
        command.execute();
    }
    public void undoTheLast(){
        if(this.history.size()>1) {
            this.history.removeLast().unDo();
            this.history.removeLast().unDo();
        }
    }

    public void saveState(){

        }

    public void reload(){
        for (int i = 0 ;i < history.size() ; i ++) {
            System.out.println("the piece is " + history.get(i));
            this.undoTheLast();
        }
    }

    public int size(){
        return this.history.size();
    }


}
