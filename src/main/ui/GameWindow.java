package ui;

import model.Round;
import model.UserInfo;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

// This class is responsible for creating the window that displays the user interface. It also handles
// the logic that switches between different "screens" like the menu screen, the main game screen, and
// the end screen.

@ExcludeFromJacocoGeneratedReport
public class GameWindow extends JFrame {
    private TypingGame typingGame;
    private Round currentRound;
    private UserInfo userInfo;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private MenuUI menuUI;
    private GameUI gameUI;
    private EndUI endUI;

    public GameWindow(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        //menuUI = new MenuUI(this);
        //gameUI = new GameUI(currentRound);
        this.typingGame = typingGame;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
        endUI = new EndUI(typingGame, currentRound, userInfo);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //mainPanel.add(menuUI, "menu");
        //mainPanel.add(gameUI, "game");
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

    public void updateCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }
}
