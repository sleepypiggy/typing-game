package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
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
    private JPanel userInputContainer;
    private GridBagConstraints userInputContainerConstraints;

    public GameUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        isEndUICreated = false;
        this.currentRound = currentRound;
        this.gameWindow = gameWindow;
        setLayout(new BorderLayout());
        this.actualText = new JTextArea(5, 80);
        this.actualText.setText(currentRound.getActualText().toString());
        displayActualText();
        displayUserInput();
        userInputAction();
    }

    // MODIFIES: this
    // EFFECTS: handles everything related to displaying the actual text properly
    public void displayActualText() {
        actualText.setEditable(false);
        actualText.setWrapStyleWord(true);
        //actualText.setSize( (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.2), HEIGHT);
        actualText.setLineWrap(true);
        actualText.setFocusable(false);
        actualText.setOpaque(false);

        actualTextContainer = new JPanel(new GridBagLayout());
        actualTextContainer.add(this.actualText);
        add(this.actualTextContainer);
    }

    public void displayUserInput() {
        userInputContainerConstraints = new GridBagConstraints();
        userInput = new JTextField(60);
        userInput.setOpaque(false);
        userInputContainer = new JPanel(new GridBagLayout());

        userInputContainer.add(userInput, userInputContainerConstraints);

        add(userInputContainer, BorderLayout.SOUTH);
        focusUserInput();
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
                    gameWindow.getEndUI().updateDisplayedStats(currentRound.getWordsPerMinute(), currentRound.getAccuracy());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes the actual text displayed in the game
    public void updateActualTextDisplay(String text) {
        this.actualText.setText(text);
    }

    // TODO: might not get used
    // MODIFIES: this
    // EFFECTS: changes the size of the user input area
    public void updateUserInputArea() {
        //userInput.setColumns(currentRound.getActualText().toString().length());
        userInput.setColumns(60);

    }

    // MODIFIES: this
    // EFFECTS: clears the user input (used when user wants to play a new round)
    public void clearUserInput() {
        this.userInput.setText("");
    }

    // EFFECTS: focuses the user's input in the typing area
    public void focusUserInput() {
        SwingUtilities.invokeLater(() -> {
            userInput.requestFocusInWindow();
            userInput.selectAll();
        });
    }

}
