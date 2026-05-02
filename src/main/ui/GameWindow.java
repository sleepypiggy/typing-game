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
    private ButtonSpriteSheet buttonSpriteSheet;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private EndUI endUI;
    private MenuUI menuUI;
    private GameUI gameUI;

    // EFFECTS: instantiates the associated information so that the GUI can interact with and display information,
    //          and creates the window for the GUI to appear in, as well as the GUI itself.
    public GameWindow(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        this.typingGame = typingGame;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
        this.buttonSpriteSheet = new ButtonSpriteSheet();
        menuUI = new MenuUI(typingGame, currentRound, userInfo, this.buttonSpriteSheet, this);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(menuUI, "menu");

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(false);

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

    // MODIFIES: this
    // EFFECTS: creates the UI that displays the game area
    public void createGameUI() {
        this.gameUI = new GameUI(typingGame, currentRound, userInfo, this);
        mainPanel.add(gameUI, "game");
    }

    // MODIFIES: this
    // EFFECTS: creates the UI that displays the end screen area
    public void createEndUI() {
        this.endUI = new EndUI(typingGame, currentRound, userInfo, buttonSpriteSheet, this);
        mainPanel.add(endUI, "end");
    }

    // MODIFIES: this
    // EFFECTS: updates the current round of this to currentRound.
    public void updateCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }
}
