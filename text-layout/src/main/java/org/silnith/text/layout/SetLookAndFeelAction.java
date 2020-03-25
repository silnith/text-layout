package org.silnith.text.layout;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SetLookAndFeelAction extends AbstractAction {

	private static final long serialVersionUID = -2878965383715117528L;

	private final String className;

	public SetLookAndFeelAction(final String name, final String className) {
		super(name);
		this.className = className;
	}

	@Override
	public void actionPerformed(final ActionEvent actionEvent) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (final ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();

			final System.Logger logger = System.getLogger(SetLookAndFeelAction.class.getName());
			logger.log(System.Logger.Level.ERROR, "Failed.", e);

			return;
		}

		for (final Frame frame : JFrame.getFrames()) {
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
}