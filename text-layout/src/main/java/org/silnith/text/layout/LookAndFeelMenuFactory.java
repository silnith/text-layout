package org.silnith.text.layout;

import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelMenuFactory {

	private final JMenu lookAndFeelMenu;

	public LookAndFeelMenuFactory() {
		lookAndFeelMenu = new JMenu("Look and Feel");
		for (final LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
			final String name = lookAndFeelInfo.getName();
			final String className = lookAndFeelInfo.getClassName();
		
			final SetLookAndFeelAction setLookAndFeelAction = new SetLookAndFeelAction(name, className);
			// TODO: Set any properties on the action.
		
			lookAndFeelMenu.add(setLookAndFeelAction);
		}
	}

	public JMenu getLookAndFeelMenu() {
		return lookAndFeelMenu;
	}
	
}