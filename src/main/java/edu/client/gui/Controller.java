package gui;
/**
 * Created by Doston Hamrakulov
 */

import edu.client.EventListener;

/**
 * Interface for a controller. Provides the controller with access to the game.
 */
public interface Controller {

    /**
     * Initialise the controller with a game instance.
     */
    void initialise(EventListener listener);
}
