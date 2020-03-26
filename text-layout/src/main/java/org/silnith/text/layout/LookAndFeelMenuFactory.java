package org.silnith.text.layout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelMenuFactory {

	private final List<Action> actions;

	public LookAndFeelMenuFactory() {
		final LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
		actions = new ArrayList<Action>(installedLookAndFeels.length);
		for (final LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
			final String name = lookAndFeelInfo.getName();
			final String className = lookAndFeelInfo.getClassName();

			final SetLookAndFeelAction setLookAndFeelAction = new SetLookAndFeelAction(name, className);
			// TODO: Set any properties on the action.
			actions.add(setLookAndFeelAction);
		}
	}

	public JMenu getLookAndFeelMenu() {
		final JMenu lookAndFeelMenu = new JMenu("Look and Feel");
		for (final Action action : actions) {
			lookAndFeelMenu.add(action);
		}
		return lookAndFeelMenu;
	}

}