package org.silnith.text.layout;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public final class NewWindowAction extends AbstractAction {
	
	private final FrameFactory frameFactory;
	
	public NewWindowAction(final String name, final FrameFactory frameFactory) {
		super(name);
		this.frameFactory = frameFactory;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		assert SwingUtilities.isEventDispatchThread();
		
		final SwingWorker<?, ?> worker = new FrameCreationWorker(frameFactory);
		worker.execute();
	}
}