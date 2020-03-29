package org.silnith.text.layout.ui.factory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.silnith.text.layout.action.SetLookAndFeelAction;

/**
 * Vends a menu for setting the application Look &amp; Feel.
 */
public class LookAndFeelMenuFactory {

    private final List<Action> actions;

    public LookAndFeelMenuFactory() {
        final LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        actions = new ArrayList<Action>(installedLookAndFeels.length);
        for (final LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
            final String lookAndFeelName = lookAndFeelInfo.getName();
            final String className = lookAndFeelInfo.getClassName();

            final SetLookAndFeelAction setLookAndFeelAction = new SetLookAndFeelAction(lookAndFeelName, className);
            setLookAndFeelAction.putValue(Action.NAME, lookAndFeelName);
            // TODO: Set any properties on the action.
            actions.add(setLookAndFeelAction);
        }
    }

    public JMenu getLookAndFeelMenu() {
        final JMenu lookAndFeelMenu = new JMenu("Look and Feel");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_L);
        for (final Action action : actions) {
            lookAndFeelMenu.add(action);
        }
        return lookAndFeelMenu;
    }

}
