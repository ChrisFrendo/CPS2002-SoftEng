package team;

/**
 * Observer class from Observer pattern
 *
 * @param <T> generic class which must be a child of Subject
 *            used to determine the Subject type for a concrete observer
 */
public abstract class Observer<T extends Subject> {

    /**
     * The observer's subject
     */
    protected T subject = null;

    /**
     * Updates
     */
    public abstract void update();
}
