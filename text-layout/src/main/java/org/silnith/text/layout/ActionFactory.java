package org.silnith.text.layout;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class ActionFactory {

	private final Action exitAction;

	public ActionFactory() {
		super();
		
		exitAction = new AbstractAction("Exit") {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		
		};
//		exitAction.putValue(Action.ACTION_COMMAND_KEY, "foo");
		exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
//		exitAction.putValue(Action.LARGE_ICON_KEY, null);
		exitAction.putValue(Action.LONG_DESCRIPTION, "Cause the JVM to terminate immediately.");
		exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit the application.");
//		exitAction.putValue(Action.SMALL_ICON, null);
	}
	
	public Action getNewWindowAction(final FrameFactory frameFactory) {
		var newWindowAction = new NewWindowAction("New Window", frameFactory);
		newWindowAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		newWindowAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		return newWindowAction;
	}
	
	public Action getExitAction() {
		return exitAction;
	}

}