package org.silnith.text.layout;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Action;
import javax.swing.SwingUtilities;

public class Example {
	
	public static void main(final String[] args) throws IOException, InvocationTargetException, InterruptedException {
		final ActionFactory actionFactory = new ActionFactory();
		final ContentPanelFactory contentPanelFactory = new ContentPanelFactory();
		final LookAndFeelMenuFactory lookAndFeelMenuFactory = new LookAndFeelMenuFactory();
		final FrameFactory frameFactory = new FrameFactory(actionFactory, contentPanelFactory, lookAndFeelMenuFactory);
		final Action newWindowAction = actionFactory.getNewWindowAction(frameFactory);
		
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				final ActionEvent event = new ActionEvent(this, 0, null);
				newWindowAction.actionPerformed(event);
			}
			
		});
		
		System.out.println("Boring.");
	}

}
