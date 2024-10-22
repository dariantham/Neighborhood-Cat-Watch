package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class EventLogShutdownHook extends WindowAdapter {
    private JFrame frame;

    public EventLogShutdownHook(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Printing event log on window close:");
        for (Event event : eventLog) {
            System.out.println(event);
        }
    }
}