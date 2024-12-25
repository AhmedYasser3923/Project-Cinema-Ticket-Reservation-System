package cinema.Server;

import cinema.Ticket_attendant.PendingTickets;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class EventBus {
    private static EventBus instance;
    private List<Observer> observers = new ArrayList<>();

    private EventBus() {}

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

  public void registerObserver(Observer observer) {
    observers.add(observer);
    System.out.println("Observer registered: " + observer.getClass().getName());
}

    public void notifyObservers(ObservableList<PendingTickets> updatedList) {
        for (Observer observer : observers) {
            observer.update(updatedList);
        }
    }

    public interface Observer {
        void update(ObservableList<PendingTickets> updatedList);
    }
}

