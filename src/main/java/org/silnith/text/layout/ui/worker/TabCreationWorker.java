package org.silnith.text.layout.ui.worker;

import java.util.concurrent.ExecutionException;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.silnith.text.layout.ui.factory.TabFactory;

/**
 * A Swing worker that creates a new tab. The work of instantiating all the new objects happens in the background, and
 * after all memory is allocated and classes initialized the event dispatch thread adds the tab.
 */
public class TabCreationWorker extends SwingWorker<JComponent, Object> {

    private final TabFactory tabFactory;

    private final JTabbedPane tabbedPane;

    public TabCreationWorker(final TabFactory tabFactory, final JTabbedPane tabbedPane) {
        super();
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
    protected JComponent doInBackground() throws Exception {
        assert !SwingUtilities.isEventDispatchThread();

        return tabFactory.getTab();
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
            tabbedPane.addTab("Title", null, get(), "Tooltip");

            tabbedPane.revalidate();
        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();

            final System.Logger logger = System.getLogger(TabCreationWorker.class.getName());
            logger.log(System.Logger.Level.ERROR, "Failed.", e);

            return;
        }
    }

}
