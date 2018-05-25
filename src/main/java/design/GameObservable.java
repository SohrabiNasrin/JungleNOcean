package design;

import java.util.List;

public class GameObservable implements GameSubject {

    private final List<GameObserver> observers;

    public GameObservable(List<GameObserver> observers){
        this.observers = observers;

    }

    @Override
    public void registerObserver(GameObserver observer) {
          this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(GameObserver observer) {
         this.observers.remove(observer);
    }

    @Override
    public void setChange() {

    }

    @Override
    public void notifyObservers(List<GameObserver> observers) {

        for(GameObserver obs : observers)
            obs.update(this);

    }
}
