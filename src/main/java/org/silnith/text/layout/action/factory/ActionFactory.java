package org.silnith.text.layout.action.factory;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.silnith.text.layout.action.CloseTabAction;
import org.silnith.text.layout.action.NewTabAction;
import org.silnith.text.layout.action.NewWindowAction;
import org.silnith.text.layout.ui.factory.FrameFactory;
import org.silnith.text.layout.ui.factory.TabFactory;

/**
 * Vends the various UI actions.
 */
public class ActionFactory {

    private final Action exitAction;
    private int acceleratorMask;

    public ActionFactory() {
        super();
        
        if (System.getProperty("os.name").contains("Mac")) {
            acceleratorMask = InputEvent.META_DOWN_MASK;
        } else {
            acceleratorMask = InputEvent.CTRL_DOWN_MASK;
        }

        exitAction = new AbstractAction("Exit") {

            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }

        };
//        exitAction.putValue(Action.ACTION_COMMAND_KEY, "foo");
        exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
//        exitAction.putValue(Action.LARGE_ICON_KEY, null);
        exitAction.putValue(Action.LONG_DESCRIPTION, "Cause the JVM to terminate immediately.");
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit the application.");
//        exitAction.putValue(Action.SMALL_ICON, null);
    }

    public Action getNewWindowAction(final FrameFactory frameFactory) {
        final Action action = new NewWindowAction("New Window", frameFactory);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, acceleratorMask));
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
        return action;
    }

    public Action getNewTabAction(final TabFactory tabFactory, final JTabbedPane tabbedPane) {
        final Action action = new NewTabAction("New Tab", tabFactory, tabbedPane);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, acceleratorMask));
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
        return action;
    }

    public Action getCloseTabAction(final JTabbedPane tabbedPane) {
        final Action action = new CloseTabAction("Close Tab", tabbedPane);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, acceleratorMask));
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
        return action;
    }

    public Action getExitAction() {
        return exitAction;
    }

}
