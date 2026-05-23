package ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AudioPlayer;
import model.Round;
import model.UserInfo;

public class GameUI extends UIElement {
    private Round currentRound;
    private UserInfo userInfo;
    private GameWindow gameWindow;

    private ImageIcon backgroundImage;

    private boolean isEndUICreated;

    private JTextArea actualText;
    private JPanel actualTextContainer;

    private boolean userInputtedSomething;

    private JTextField userInput;
    private JPanel userInputContainer;

    private AudioPlayer userInputEnterSfx;

    public GameUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        this.currentRound = currentRound;
        this.gameWindow = gameWindow;
        isEndUICreated = false;
        userInputtedSomething = false;
        setLayout(new BorderLayout());
        initAudio();
        displayActualText();
        displayUserInput();
        userInputAction();
        focusUserInput();
        detectUserInputChange();
        initBackgroundImage();
    }

    public void initAudio() {
        this.userInputEnterSfx = new AudioPlayer("./data/userInputEnter.wav", false);
    }

    // EFFECTS: calls regular paintComponent method and also draws the background image.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the background image of this panel.
    public void initBackgroundImage() {
        backgroundImage = new ImageIcon("./data/background3.gif");
    }

    // MODIFIES: this
    // EFFECTS: detects changes in the user input so program knows when to start timer.
    public void detectUserInputChange() {
        userInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                startRoundTimer();
            }
            public void removeUpdate(DocumentEvent e) {
                startRoundTimer();
            }
            public void insertUpdate(DocumentEvent e) {
                startRoundTimer();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: helper method for detectUserInputChange(), used to start the timer and keep track of userInputtedSomething state.
    public void startRoundTimer() {
        if (!userInputtedSomething) {
            userInputtedSomething = true;
            currentRound.startRoundTime();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets userInputtedSomething to false (used to make sure we're only detecing user input when needed)
    public void resetUserInputtedSomething() {
        userInputtedSomething = false;
    }

    // MODIFIES: this
    // EFFECTS: handles everything related to displaying the actual text properly
    public void displayActualText() {
        actualText = new JTextArea(5, 80);
        actualText.setText(currentRound.getActualText().toString());

        actualText.setEditable(false);
        actualText.setWrapStyleWord(true);
        actualText.setLineWrap(true);
        actualText.setFocusable(false);
        actualText.setOpaque(true);
        actualText.setBackground(new Color(25, 25, 25, 125));
        actualText.setForeground(Color.WHITE);

        actualTextContainer = new JPanel(new GridBagLayout());
        actualTextContainer.add(this.actualText);
        actualTextContainer.setOpaque(false);

        add(this.actualTextContainer);
    }

    // MODIFIES: this
    // EFFECTS: handles everythign related to displaying the user input area properly
    public void displayUserInput() {
        GridBagConstraints userInputContainerConstraints = new GridBagConstraints();
        userInput = new JTextField(80);

        userInput.setOpaque(false);
        userInput.setForeground(Color.WHITE);

        userInputContainer = new JPanel(new GridBagLayout());
        userInputContainer.setOpaque(false);

        userInputContainerConstraints.insets = new Insets(0, 0, 50, 0);

        userInputContainer.add(userInput, userInputContainerConstraints);

        add(userInputContainer, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: what happens when the user presses 'enter' (when they are finished typing their phrase)
    public void userInputAction() {
        userInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInputEnterSfx.playAudio();

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
