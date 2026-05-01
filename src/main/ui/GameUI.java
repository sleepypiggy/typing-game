package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import model.Round;
import model.UserInfo;

public class GameUI extends UIElement {
    private Round currentRound;
    private UserInfo userInfo;

    private JLabel actualText;
    private JPanel actualTextContainer;

    private JTextArea userInput;
    

    public GameUI(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        super(typingGame, currentRound, userInfo);
        setLayout(new BorderLayout());
        this.actualText = new JLabel(currentRound.getActualText().toString());
        displayActualText();
        displayUserInput();
    }

    public void displayActualText() {
        actualTextContainer = new JPanel(new GridBagLayout());
        actualTextContainer.add(this.actualText);
        add(this.actualTextContainer);
    }

    public void displayUserInput() {
        userInput = new JTextArea(3, 10);
        userInput.setFocusable(true);
        userInput.requestFocusInWindow();
        add(userInput, BorderLayout.SOUTH);
    }

}
