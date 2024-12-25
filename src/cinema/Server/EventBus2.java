package cinema.Server;

import cinema.Ticket_attendant.Movies;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class EventBus2 {
        private static EventBus2 instance;
    private List<Observer> observers = new ArrayList<>();

    private EventBus2() {}

    public static EventBus2 getInstance() {
        if (instance == null) {
            instance = new EventBus2();
        }
        return instance;
    }

  public void registerObserver(Observer observer) {
    observers.add(observer);
    System.out.println("Observer registered: " + observer.getClass().getName());
}

    public void notifyObservers(ObservableList<Movies> updatedList) {
        for (Observer observer : observers) {
            observer.update(updatedList);
        }
    }

    public interface Observer {
        void update(ObservableList<Movies> updatedList);
    }
}
