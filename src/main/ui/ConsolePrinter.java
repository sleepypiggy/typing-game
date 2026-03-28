package ui;

import javax.swing.JInternalFrame;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Event;
import model.EventLog;

/**
 * Represents a console printer for printing event log to console.
 */
@ExcludeFromJacocoGeneratedReport
public class ConsolePrinter extends JInternalFrame implements LogPrinter {	
	/**
	 * Constructor does not do anything in this case since the event log
	 * is printed directly to the console.
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
