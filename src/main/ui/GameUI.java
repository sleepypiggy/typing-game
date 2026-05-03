package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Round;
import model.UserInfo;

public class GameUI extends UIElement {
    private Round currentRound;
    private UserInfo userInfo;
    private GameWindow gameWindow;

    private boolean isEndUICreated;

    private JTextArea actualText;
    private JPanel actualTextContainer;

    private JTextField userInput;
    

    public GameUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        isEndUICreated = false;
        this.currentRound = currentRound;
        this.gameWindow = gameWindow;
        setLayout(new BorderLayout());
        this.actualText = new JTextArea(currentRound.getActualText().toString());
        displayActualText();
        displayUserInput();
        userInputAction();
    }

    // MODIFIES: this
    // EFFECTS: handles everything related to displaying the actual text properly
    public void displayActualText() {
        actualText.setEditable(false);
        actualText.setWrapStyleWord(true);
        actualText.setSize( (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.2), HEIGHT);
        actualText.setLineWrap(true);
        actualText.setFocusable(false);
        actualText.setOpaque(false);

        actualTextContainer = new JPanel(new GridBagLayout());
        actualTextContainer.add(this.actualText);
        add(this.actualTextContainer);
    }

    public void displayUserInput() {
        userInput = new JTextField();
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
