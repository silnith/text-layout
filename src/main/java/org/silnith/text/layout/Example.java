package org.silnith.text.layout;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.silnith.text.layout.action.factory.ActionFactory;
import org.silnith.text.layout.ui.factory.ContentPanelFactory;
import org.silnith.text.layout.ui.factory.FrameFactory;
import org.silnith.text.layout.ui.factory.LookAndFeelMenuFactory;
import org.silnith.text.layout.ui.factory.TabFactory;

/**
 * The main application entry point.
 */
public class Example {

    /**
     * The main application entry point.
     * 
     * @param args the command-line arguments
     * @throws IOException do not care
     * @throws InvocationTargetException do not care
     * @throws InterruptedException do not care
     * @throws ClassNotFoundException do not care
     * @throws InstantiationException do not care
     * @throws IllegalAccessException do not care
     * @throws UnsupportedLookAndFeelException do not care
     */
    public static void main(final String[] args) throws IOException, InvocationTargetException, InterruptedException,
            ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        final ActionFactory actionFactory = new ActionFactory();
        final ContentPanelFactory contentPanelFactory = new ContentPanelFactory();
        final TabFactory tabFactory = new TabFactory(contentPanelFactory);
        final LookAndFeelMenuFactory lookAndFeelMenuFactory = new LookAndFeelMenuFactory();
        final FrameFactory frameFactory = new FrameFactory(actionFactory, tabFactory, lookAndFeelMenuFactory);

        final Action newWindowAction = actionFactory.getNewWindowAction(frameFactory);
        SwingUtilities.invokeAndWait(new ActionInvoker(newWindowAction));

        System.out.println("The application is running.");
    }

}
