package design;

import java.util.List;

public interface GameSubject {


     void  registerObserver(GameObserver observer);
     void  unregisterObserver(GameObserver observer);
     void  setChange();
     void  notifyObservers(List<GameObserver> observers);


}
