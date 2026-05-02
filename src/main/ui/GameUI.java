package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Round;
import model.UserInfo;

public class GameUI extends UIElement {
    private Round currentRound;
    private UserInfo userInfo;
    private GameWindow gameWindow;

    private boolean isEndUICreated;

    private JLabel actualText;
    private JPanel actualTextContainer;

    private JTextField userInput;
    

    public GameUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        isEndUICreated = false;
        this.currentRound = currentRound;
        this.gameWindow = gameWindow;
        setLayout(new BorderLayout());
        this.actualText = new JLabel(currentRound.getActualText().toString());
        displayActualText();
        displayUserInput();
        userInputAction();
    }

    public void displayActualText() {
        actualTextContainer = new JPanel(new GridBagLayout());
        actualTextContainer.add(this.actualText);
        add(this.actualTextContainer);
    }

    public void displayUserInput() {
        userInput = new JTextField(10);
        add(userInput, BorderLayout.SOUTH);
        SwingUtilities.invokeLater(() -> {
            userInput.requestFocusInWindow();
            userInput.selectAll();
        });
    }

    // MODIFIES: this
    // EFFECTS: what happens when the user presses 'enter' (when they are finished typing their phrase)
    public void userInputAction() {
        userInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder text = new StringBuilder(userInput.getText());
                currentRound.setUserText(text);
                currentRound.calculateAccuracy(new StringBuilder(actualText.getText()), text);
                currentRound.setElapsedTime();
                currentRound.setNumberOfUserTypedCharacters(text);
                currentRound.setWordsPerMinute();
                if (!isEndUICreated) {
                    gameWindow.createEndUI();
                    isEndUICreated = true;
                    gameWindow.switchToGameOverUI();
                } else {
                    gameWindow.switchToGameOverUI();
                }
            }
        });
    }

}
