package org.silnith.text.layout.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.silnith.text.layout.ui.factory.TabFactory;
import org.silnith.text.layout.ui.worker.TabCreationWorker;

/**
 * An action to add a tab to the pane.
 */
public class NewTabAction extends AbstractAction {

    private final TabFactory tabFactory;

    private final JTabbedPane tabbedPane;

    public NewTabAction(final String name, final TabFactory tabFactory, final JTabbedPane tabbedPane) {
        super(name);
        if (tabFactory == null) {
            throw new IllegalArgumentException("Tab factory is null.");
        }
        if (tabbedPane == null) {
            throw new IllegalArgumentException("Tabbed pane is null.");
        }
        this.tabFactory = tabFactory;
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        assert SwingUtilities.isEventDispatchThread();

        final SwingWorker<?, ?> worker = new TabCreationWorker(tabFactory, tabbedPane);
        worker.execute();
    }

}
