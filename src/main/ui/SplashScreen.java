package ui;

import javax.swing.*;
import java.awt.*;

// a class representing the splash screen for when CatWatchUI is launched
public class SplashScreen extends JWindow {
    private int duration;

    // MODIFIES: this
    // EFFECTS: constructs a splash screen with a given duration
    public SplashScreen(int duration) {
        this.duration = duration;
    }

    // EFFECTS: shows the splash screen to indicate that the application is loading
    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the size and position of the splash screen
        int width = 700;
        int height = 463;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen content
        JLabel label = new JLabel(new ImageIcon("./data/OreoSplash.jpg"));
        JLabel copyrt = new JLabel("Darian Tham 2024", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);

        // Display the splash screen
        setVisible(true);

        // Close the splash screen after the specified duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}
