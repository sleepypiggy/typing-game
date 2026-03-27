package ui;

import java.awt.Component;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Event;
import model.EventLog;

/**
 * Represents a screen printer for printing event log to screen.
 */
public class ConsolePrinter extends JInternalFrame implements LogPrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
	
	/**
	 * Constructor sets up window in which log will be printed on screen
	 * @param parent  the parent component
	 */
    public ConsolePrinter() {
        
    }
	
    @Override
    public void printLog(EventLog el) {
        System.out.println();
        System.out.println("Event log: ");
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }
}
