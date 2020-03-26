package org.silnith.text.layout;

import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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
		} catch (final InterruptedException e) {
			return;
		} catch (final ExecutionException e) {
			return;
		}
	}
}