package org.silnith.text.layout.ui.factory;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.silnith.text.layout.ActionInvoker;
import org.silnith.text.layout.action.factory.ActionFactory;
import org.silnith.text.layout.ui.listener.EmptyScrollPaneListener;

/**
 * A factory for creating new frames.
 */
public class FrameFactory {

    private final ActionFactory actionFactory;

    private final TabFactory tabFactory;

    private final LookAndFeelMenuFactory lookAndFeelMenuFactory;

    private Dimension preferredSize;

    public FrameFactory(final ActionFactory actionFactory, final TabFactory tabFactory,
            final LookAndFeelMenuFactory lookAndFeelMenuFactory) {
        super();
        if (actionFactory == null) {
            throw new IllegalArgumentException("Action factory is null.");
        }
        if (tabFactory == null) {
            throw new IllegalArgumentException("Tab factory is null.");
        }
        if (lookAndFeelMenuFactory == null) {
            throw new IllegalArgumentException("Look & Feel factory is null.");
        }
        this.actionFactory = actionFactory;
        this.tabFactory = tabFactory;
        this.lookAndFeelMenuFactory = lookAndFeelMenuFactory;
        preferredSize = new Dimension(800, 600);
    }

    public JFrame getFrame() {
        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setPreferredSize(preferredSize);

        final Action newTabAction = actionFactory.getNewTabAction(tabFactory, tabbedPane);

        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(actionFactory.getNewWindowAction(this));
        fileMenu.add(newTabAction);
        fileMenu.add(actionFactory.getCloseTabAction(tabbedPane));
        fileMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        fileMenu.add(actionFactory.getExitAction());

        final JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);
        menubar.add(lookAndFeelMenuFactory.getLookAndFeelMenu());

        final JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setJMenuBar(menubar);
        frame.setContentPane(tabbedPane);

        // TODO: Configuration setting to enable or disable this behavior.
        tabbedPane.addContainerListener(new EmptyScrollPaneListener(tabbedPane, frame));

        SwingUtilities.invokeLater(new ActionInvoker(newTabAction));

        return frame;
    }

}
