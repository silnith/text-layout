package org.silnith.text.layout;

import java.awt.event.ActionEvent;

import javax.swing.Action;

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
		this.action = action;
	}

	@Override
	public void run() {
		final ActionEvent event = new ActionEvent(this, 0, null);
		action.actionPerformed(event);
	}
}