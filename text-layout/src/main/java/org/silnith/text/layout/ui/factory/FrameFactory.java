package org.silnith.text.layout.ui.factory;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.silnith.text.layout.action.factory.ActionFactory;

public class FrameFactory {

	private final ActionFactory actionFactory;
	
	private final ContentPanelFactory contentFactory;

	private final LookAndFeelMenuFactory lookAndFeelMenuFactory;

	public FrameFactory(final ActionFactory actionFactory, final ContentPanelFactory contentFactory, final LookAndFeelMenuFactory lookAndFeelMenuFactory) {
		super();
		this.actionFactory = actionFactory;
		this.contentFactory = contentFactory;
		this.lookAndFeelMenuFactory = lookAndFeelMenuFactory;
	}

	public JFrame getFrame() {
		final Action newWindowAction = actionFactory.getNewWindowAction(this);
		final Action exitAction = actionFactory.getExitAction();

		final JMenu lookAndFeelMenu = lookAndFeelMenuFactory.getLookAndFeelMenu();
		
		final JComponent contentPanel = contentFactory.getContentPanel();
		
		final Dimension preferredScrollPaneSize = new Dimension(640, 480);
		
		return createFrame(newWindowAction, exitAction, lookAndFeelMenu, contentPanel, preferredScrollPaneSize);
	}

	private JFrame createFrame(final Action newWindowAction, final Action exitAction, final JMenu lookAndFeelMenu,
			final JComponent contentPanel, final Dimension preferredScrollPaneSize) {
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(newWindowAction);
		fileMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
		fileMenu.add(exitAction);

		final JMenuBar menubar = new JMenuBar();
		menubar.add(fileMenu);
		menubar.add(lookAndFeelMenu);

		final JScrollPane scrollPane = new JScrollPane(contentPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(preferredScrollPaneSize);
		
		final JFrame frame = new JFrame("Example");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setJMenuBar(menubar);
		frame.setContentPane(scrollPane);
		
		return frame;
	}

}