package org.silnith.text.layout.ui.factory;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * A factory for creating new tabs.
 */
public class TabFactory {

    private final ContentPanelFactory contentPanelFactory;

    public TabFactory(final ContentPanelFactory contentPanelFactory) {
        super();
        if (contentPanelFactory == null) {
            throw new IllegalArgumentException("Content panel factory is null.");
        }
        this.contentPanelFactory = contentPanelFactory;
    }

    public JComponent getTab() {
        final JComponent addressPanel = null;
        final JComponent configurationPanel = null;
        final JComponent downloadsPanel = null;
        final JComponent contentPanel = contentPanelFactory.getContentPanel();

        final JScrollPane scrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

}
