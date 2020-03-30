package org.silnith.text.layout.ui.factory;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * A factory for creating new tabs.
 */
public class TabFactory {

    private final ContentPanelFactory contentPanelFactory;
    private final FontFactory fontFactory;

    public TabFactory(final ContentPanelFactory contentPanelFactory, final FontFactory fontFactory) {
        super();
        if (contentPanelFactory == null) {
            throw new IllegalArgumentException("Content panel factory is null.");
        }
        if (fontFactory == null) {
            throw new IllegalArgumentException("Font factory is null.");
        }
        this.contentPanelFactory = contentPanelFactory;
        this.fontFactory = fontFactory;
    }

    public JComponent getTab() {
        final JTextField addressField = new JTextField();
        addressField.setFont(fontFactory.getFont(Font.SANS_SERIF, 12.0f));
        // TODO: Create model, add document listeners.
        
        final JLabel addressLabel = new JLabel("URL", null, JLabel.TRAILING);
        addressLabel.setToolTipText("Uniform Resource Locator");
        addressLabel.setLabelFor(addressField);
        addressLabel.setFont(fontFactory.getDialogFont());
        
        final JPanel addressPanel = new JPanel(new BorderLayout());
        addressPanel.add(addressLabel, BorderLayout.LINE_START);
        addressPanel.add(addressField, BorderLayout.CENTER);
        
        final JScrollPane contentScrollPane = new JScrollPane(contentPanelFactory.getContentPanel(),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        final JComponent progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.PAGE_AXIS));
        // TODO: Hook the progress panel to the content panel via listeners.
        
        final JComponent mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        mainPanel.add(progressPanel, BorderLayout.PAGE_START);

        final JComponent informationPanel = new JPanel(new BorderLayout());
        // TODO: Add network listeners to populate the information panel.
        
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, mainPanel, informationPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(1.0);
        splitPane.setDividerSize(15);
        
        final JPanel entirePanel = new JPanel(new BorderLayout());
        entirePanel.add(addressPanel, BorderLayout.PAGE_START);
        entirePanel.add(splitPane, BorderLayout.CENTER);

        return entirePanel;
    }

}
