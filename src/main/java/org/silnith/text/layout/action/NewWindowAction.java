package org.silnith.text.layout.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.silnith.text.layout.ui.factory.FrameFactory;
import org.silnith.text.layout.ui.worker.FrameCreationWorker;

/**
 * An action to create a new window.
 */
public class NewWindowAction extends AbstractAction {

    private final FrameFactory frameFactory;

    public NewWindowAction(final String name, final FrameFactory frameFactory) {
        super(name);
        if (frameFactory == null) {
            throw new IllegalArgumentException("Frame factory is null.");
        }
        this.frameFactory = frameFactory;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        assert SwingUtilities.isEventDispatchThread();

        final SwingWorker<?, ?> worker = new FrameCreationWorker(frameFactory);
        worker.execute();
    }

}
