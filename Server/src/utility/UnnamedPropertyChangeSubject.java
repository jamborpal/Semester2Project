package utility;

import java.beans.PropertyChangeListener;

/**
 * The interface Unnamed property change subject.
 */
public interface UnnamedPropertyChangeSubject
{
    /**
     * Add listener.
     *
     * @param listener the listener
     */
    void addListener(PropertyChangeListener listener);

    /**
     * Remove listener.
     *
     * @param listener the listener
     */
    void removeListener(PropertyChangeListener listener);
}
