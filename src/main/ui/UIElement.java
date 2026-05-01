package ui;

import javax.swing.JPanel;

import model.Round;
import model.UserInfo;

public abstract class UIElement extends JPanel {
    private TypingGame typingGame;
    private Round currentRound;
    private UserInfo userInfo;

    public UIElement(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        this.typingGame = typingGame;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
    }

    public TypingGame getTypingGame() {
        return this.typingGame;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

}
