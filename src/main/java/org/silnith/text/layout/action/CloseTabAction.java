package org.silnith.text.layout.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 * An action to close the currently-selected tab.
 */
public class CloseTabAction extends AbstractAction {

    private final JTabbedPane tabbedPane;

    public CloseTabAction(final String name, final JTabbedPane tabbedPane) {
        super(name);
        if (tabbedPane == null) {
            throw new IllegalArgumentException("Tabbed pane is null.");
        }
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        assert SwingUtilities.isEventDispatchThread();

        tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
        // TODO: unhook any listeners?
    }

}
