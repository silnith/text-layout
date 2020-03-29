package org.silnith.text.layout.ui.listener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 * Listens to a {@link JTabbedPane} and when all the tabs are closed, disposes the containing {@link JFrame}.
 */
public class EmptyScrollPaneListener extends ContainerAdapter {

    private final JTabbedPane tabbedPane;

    private final JFrame frame;

    public EmptyScrollPaneListener(final JTabbedPane tabbedPane, final JFrame frame) {
        this.tabbedPane = tabbedPane;
        this.frame = frame;
    }

    @Override
    public void componentRemoved(final ContainerEvent e) {
        assert SwingUtilities.isEventDispatchThread();

        if (tabbedPane.getTabCount() == 0) {
            frame.dispose();
        }
    }

}
