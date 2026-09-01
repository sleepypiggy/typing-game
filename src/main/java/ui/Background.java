package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Separate JPanel for the background image.

public class Background extends JPanel {

    private ImageIcon backgroundImage;

    public Background(String path) {
        initBackgroundImage(path);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void initBackgroundImage(String path) {
        backgroundImage = new ImageIcon(path);
    }

}
