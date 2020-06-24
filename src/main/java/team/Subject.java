package team;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for Observer pattern
 *
 * @param <T> Generic used to determine the type of the state for the concrete Subject
 */
public abstract class Subject<T> {

    /**
     * Stores the list of Observers attached to the Subject
     */
    List<Observer> observers = new ArrayList<>();

    /**
     * Stores the state of the Subject
     */
    private T state;

    /**
     * @return The current state of the Subject
     */
    public T getState() {
        return state;
    }

    /**
     * Updates the state and notifies all observers that the state has changed
     *
     * @param state The new state
     */
    public void setState(T state) {
        this.state = state;
        this.notifyAllObservers();
    }

    /**
     * Attaches an Observer to the Subject by adding the Observer to the list of observers
     *
     * @param observer The observer to attach
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * Calls the update function of all attaches observers
     */
    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * @return A copy of the observers attached to the Subject
     */
    public List<Observer> getObservers() {
        return new ArrayList<>(this.observers);
    }

}
