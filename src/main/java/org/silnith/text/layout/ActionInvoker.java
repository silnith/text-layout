package org.silnith.text.layout;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.SwingUtilities;

/**
 * Triggers the provided action.
 */
public class ActionInvoker implements Runnable {

    private final Action action;

    /**
     * Creates a runnable that triggers the provided action.
     * 
     * @param action the action to trigger
     */
    public ActionInvoker(final Action action) {
        super();
        if (action == null) {
            throw new IllegalArgumentException("Action is null.");
        }
        this.action = action;
    }

    @Override
    public void run() {
        assert SwingUtilities.isEventDispatchThread();

        final ActionEvent event = new ActionEvent(this, 0, null);
        action.actionPerformed(event);
    }

}
