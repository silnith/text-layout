package org.silnith.text.layout.ui.worker;

import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.silnith.text.layout.ui.factory.FrameFactory;

/**
 * A Swing worker that creates a new frame.  The work of instantiating all the new objects
 * happens in the background, and after all memory is allocated and classes initialized
 * the event dispatch thread displays the frame.
 */
public final class FrameCreationWorker extends SwingWorker<JFrame, Object> {
	
	private final FrameFactory frameFactory;
	
	public FrameCreationWorker(final FrameFactory frameFactory) {
		super();
		this.frameFactory = frameFactory;
	}

	@Override
	protected JFrame doInBackground() throws Exception {
		assert !SwingUtilities.isEventDispatchThread();
		
		return frameFactory.getFrame();
	}

	@Override
	protected void done() {
		assert SwingUtilities.isEventDispatchThread();
		
		if (isCancelled()) {
			return;
		}
		
		if (!isDone()) {
			return;
		}
		
		try {
			final JFrame frame = this.get();
			
			frame.pack();
			
			frame.setVisible(true);
		} catch (final InterruptedException | ExecutionException e) {
			return;
		}
	}
}