package ui;

import javax.swing.*;
import java.awt.*;

// This class is responsible for creating the window that displays the user interface. It also handles
// the logic that switches between different "screens" like the menu screen, the main game screen, and
// the end screen.

public class GameWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private MenuUI menuUI;
    private GameUI gameUI;
    private EndUI endUI;

    public GameWindow() {
        menuUI = new MenuUI(this);
        gameUI = new GameUI();
        endUI = new EndUI();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(menuUI, "menu");
        mainPanel.add(gameUI, "game");
        mainPanel.add(endUI, "end");

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
        //setResizable(false);

        add(mainPanel);
    }

    // EFFECTS: displays the GameUI class.
    public void switchToGameUI() {
        cardLayout.show(mainPanel, "game");
    }

    // EFFECTS: displays the EndUI class.
    public void switchToGameOverUI() {
        cardLayout.show(mainPanel, "end");
    }

}
