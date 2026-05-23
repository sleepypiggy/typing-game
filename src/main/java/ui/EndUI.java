package ui;

import model.Round;
import model.UserInfo;
import model.exception.LogException;
import model.AudioPlayer;
import model.Event;
import model.EventLog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.*;

// TODO more functionality:
// - remove quotes from the quote pool
// - add music and sfx

// This class displays all the UI elements for anything that is on the end screen, including things like
// the buttons, and the saved phrases (if the button to view saved phrases is clicked).
public class EndUI extends UIElement {
    private ImageIcon backgroundImage;

    private GameWindow gameWindow;
    private Round currentRound;
    private UserInfo userInfo;
    private TypingGame typingGame;

    private JButton addPhraseButton;
    private JButton removePhraseButton;
    private JButton viewPhraseButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addCustomQuoteButton;
    private JButton newRoundButton;
    private JButton exitButton;

    // after-round stats
    private JLabel wordsPerMinute;
    private JLabel accuracy;
    private JLabel timeTaken;

    private JLabel savedPhrasesLabel;
    private JTextArea savedPhrases;
    private JScrollPane scroll;
    private JPanel savedPhrasesContainer;
    private GridBagConstraints savedPhrasesLabelContraints;
    private GridBagConstraints savedPhrasesConstraints;

    private JPanel iconContainer;

    private AudioPlayer buttonClickSfx;


    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, initializes the buttons as well as their layouts, and adds an image to the window.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        this.gameWindow = gameWindow;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
        this.typingGame = typingGame;
        initAudio();
        setLayout(new BorderLayout());
        initButtons();
        hideOriginalButtonBackground();
        initStats();
        displayImageIcons();
        initListOfSavedPhrases();
        initBackground();
    }

    public void initAudio() {
        this.buttonClickSfx = new AudioPlayer("./data/buttonClick.wav", false);
    }

    // TODO: probably best to move the setOpaque(false) to their respective init methods
    // MODIFIES: this
    // EFFECTS: initializes the image used for the background and makes everything see-through
    public void initBackground() {
        iconContainer.setOpaque(false);
        savedPhrasesContainer.setOpaque(false);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        savedPhrases.setOpaque(false);
        backgroundImage = new ImageIcon("./data/background2.gif");
    }

    // EFFECTS: regular paintComponent method but also paints the background image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void initListOfSavedPhrases() {
        savedPhrasesLabel = new JLabel("Saved Phrases");
        savedPhrasesLabelContraints = new GridBagConstraints();
        savedPhrasesConstraints = new GridBagConstraints();
        savedPhrasesContainer = new JPanel(new GridBagLayout());
        savedPhrases = new JTextArea(10, 30);
        scroll = new JScrollPane(savedPhrases);

        savedPhrasesLabel.setForeground(Color.WHITE);

        setGridBagConstraintsGridPosition(savedPhrasesLabelContraints, 0, 0);
        setGridBagConstraintsGridPosition(savedPhrasesConstraints, 0, 1);
        savedPhrasesLabelContraints.weightx = 0;
        savedPhrasesLabelContraints.weighty = 0;
        savedPhrasesLabelContraints.fill = GridBagConstraints.NONE;

        savedPhrasesLabelContraints.insets = new Insets(5, 0, 0, 20);
        savedPhrasesLabelContraints.anchor = GridBagConstraints.WEST;

        savedPhrasesConstraints.weightx = 1.0;
        savedPhrasesConstraints.weighty = 1.0;
        savedPhrasesConstraints.fill = GridBagConstraints.BOTH;
        savedPhrasesConstraints.insets = new Insets(0, 0, 20, 20);

        savedPhrases.setEditable(false);
        savedPhrases.setWrapStyleWord(true);
        savedPhrases.setFocusable(false);
        savedPhrases.setLineWrap(true);
        savedPhrases.setOpaque(false);
        savedPhrases.setForeground(Color.WHITE);

        savedPhrasesContainer.add(savedPhrasesLabel, savedPhrasesLabelContraints);
        savedPhrasesContainer.add(scroll, savedPhrasesConstraints);
        add(savedPhrasesContainer, BorderLayout.EAST);
    }

    public void initButtonHoverEffects(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(215, 215, 215));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });
    }

    public void displayImageIcons() {
        GridBagConstraints wordsPerMinuteConstraints = new GridBagConstraints();
        GridBagConstraints accuracyConstraints = new GridBagConstraints();
        GridBagConstraints timeTakenConstraints = new GridBagConstraints();
        GridBagConstraints addPhraseButtonConstraints = new GridBagConstraints();
        GridBagConstraints removePhraseButtonConstraints = new GridBagConstraints();
        GridBagConstraints viewPhraseButtonConstraints = new GridBagConstraints();
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        GridBagConstraints loadButtonConstraints = new GridBagConstraints();
        GridBagConstraints addCustomQuoteButtonContraints = new GridBagConstraints();
        GridBagConstraints newRoundButtonConstraints = new GridBagConstraints();
        GridBagConstraints exitButtonConstraints = new GridBagConstraints();

        iconContainer = new JPanel(new GridBagLayout());

        setGridBagConstraintsGridPosition(wordsPerMinuteConstraints, 0, 0);
        setGridBagConstraintsGridPosition(accuracyConstraints, 0, 1);
        setGridBagConstraintsGridPosition(timeTakenConstraints, 0, 2);
        setGridBagConstraintsGridPosition(addPhraseButtonConstraints, 0, 3);
        setGridBagConstraintsGridPosition(removePhraseButtonConstraints, 0, 4);
        setGridBagConstraintsGridPosition(viewPhraseButtonConstraints, 0, 5);
        setGridBagConstraintsGridPosition(saveButtonConstraints, 0, 6);
        setGridBagConstraintsGridPosition(loadButtonConstraints, 0, 7);
        setGridBagConstraintsGridPosition(addCustomQuoteButtonContraints, 0, 8);
        setGridBagConstraintsGridPosition(newRoundButtonConstraints, 0, 9);
        setGridBagConstraintsGridPosition(exitButtonConstraints, 0, 10);

        wordsPerMinuteConstraints.gridwidth = 2;
        accuracyConstraints.gridwidth = 2;
        timeTakenConstraints.gridwidth = 2;
        timeTakenConstraints.anchor = GridBagConstraints.WEST;
        accuracyConstraints.anchor = GridBagConstraints.WEST;

        setGridBagConstraintInsets(wordsPerMinuteConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(accuracyConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(timeTakenConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(addPhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(removePhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(viewPhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(saveButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(loadButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(addCustomQuoteButtonContraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(newRoundButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(exitButtonConstraints, 0, 20, 0, 0);

        wordsPerMinuteConstraints.weightx = 1;
        wordsPerMinuteConstraints.fill = GridBagConstraints.HORIZONTAL;

        addPhraseButtonConstraints.anchor = GridBagConstraints.WEST;
        removePhraseButtonConstraints.anchor = GridBagConstraints.WEST;
        viewPhraseButtonConstraints.anchor = GridBagConstraints.WEST;
        saveButtonConstraints.anchor = GridBagConstraints.WEST;
        loadButtonConstraints.anchor = GridBagConstraints.WEST;
        addCustomQuoteButtonContraints.anchor = GridBagConstraints.WEST;
        newRoundButtonConstraints.anchor = GridBagConstraints.WEST;
        exitButtonConstraints.anchor = GridBagConstraints.WEST;

        iconContainer.add(wordsPerMinute, wordsPerMinuteConstraints);
        iconContainer.add(accuracy, accuracyConstraints);
        iconContainer.add(timeTaken, timeTakenConstraints);
        iconContainer.add(addPhraseButton, addPhraseButtonConstraints);
        iconContainer.add(removePhraseButton, removePhraseButtonConstraints);
        iconContainer.add(viewPhraseButton, viewPhraseButtonConstraints);
        iconContainer.add(saveButton, saveButtonConstraints);
        iconContainer.add(loadButton, loadButtonConstraints);
        iconContainer.add(addCustomQuoteButton, addCustomQuoteButtonContraints);
        iconContainer.add(newRoundButton, newRoundButtonConstraints);
        iconContainer.add(exitButton, exitButtonConstraints);

        add(iconContainer, BorderLayout.WEST);
    }

    // EFFECTS: creates new Insets for gbc
    public void setGridBagConstraintInsets(GridBagConstraints gbc, int top, int left, int bottom, int right) {
        gbc.insets = new Insets(top, left, bottom, right);
    }


    // MODIFIES: this
    // EFFECTS: sets the gridx and gridy position of gbc to x and y
    public void setGridBagConstraintsGridPosition(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
    }

    // MODIFIES: this
    // EFFECTS: displays the words per minute and accuracy on screen
    private void initStats() {
        if (currentRound.getAccuracy() < 60) {
            wordsPerMinute = new JLabel("Words per minute: " + String.valueOf(currentRound.getWordsPerMinute() + " (invalid)"));
        } else {
            wordsPerMinute = new JLabel("Words per minute: " + String.valueOf(currentRound.getWordsPerMinute()));
        }
        accuracy = new JLabel("Accuracy: " + String.valueOf(currentRound.getAccuracy()) + "%");
        timeTaken = new JLabel("Time taken: " + String.valueOf(currentRound.getTimeTaken()) + "s");

        accuracy.setForeground(Color.WHITE);
        wordsPerMinute.setForeground(Color.WHITE);
        timeTaken.setForeground(Color.WHITE);

        accuracy.setFont(accuracy.getFont().deriveFont(60.0f));
        wordsPerMinute.setFont(wordsPerMinute.getFont().deriveFont(60.0f));
        timeTaken.setFont(timeTaken.getFont().deriveFont(60.0f));

    }

    // MODIFIES: this
    // EFFECTS: updates the displayed stats
    public void updateDisplayedStats(double wpm, double accuracy) {
        if (accuracy < 60) {
            this.wordsPerMinute.setText("Words per minute: " + wpm + " (invalid)");
        } else {
            this.wordsPerMinute.setText("Words per minute: " + wpm);
        }
        this.accuracy.setText("Accuracy: " + accuracy + "%");
        timeTaken.setText(String.valueOf("Time taken: " + currentRound.getTimeTaken()) + "s");
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons and calls the methods required to initialize their respective logic.
    public void initButtons() {
        addPhraseButton = new JButton("Add phrase");
        removePhraseButton = new JButton("Remove phrase");
        viewPhraseButton = new JButton("View");
        saveButton = new JButton("Download saved phrases");
        loadButton = new JButton("Load saved phrases");
        addCustomQuoteButton = new JButton("Add custom phrase");
        newRoundButton = new JButton("New round");
        exitButton = new JButton("Quit");

        addPhraseButton.setForeground(Color.WHITE);
        removePhraseButton.setForeground(Color.WHITE);
        viewPhraseButton.setForeground(Color.WHITE);
        saveButton.setForeground(Color.WHITE);
        loadButton.setForeground(Color.WHITE);
        addCustomQuoteButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);
        newRoundButton.setForeground(Color.WHITE);

        addPhraseButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        removePhraseButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        viewPhraseButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        saveButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        loadButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        addCustomQuoteButton.setFont(addCustomQuoteButton.getFont().deriveFont(30.0f));
        newRoundButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));
        exitButton.setFont(addPhraseButton.getFont().deriveFont(30.0f));

        addPhraseButtonLogic();
        removePhraseButtonLogic();
        viewPhraseButtonLogic();
        saveButtonLogic();
        loadButtonLogic();
        addCustomQuoteButtonLogic();
        newRoundButtonLogic();
        exitButtonLogic();

        initButtonHoverEffects(addPhraseButton);
        initButtonHoverEffects(removePhraseButton);
        initButtonHoverEffects(viewPhraseButton);
        initButtonHoverEffects(saveButton);
        initButtonHoverEffects(loadButton);
        initButtonHoverEffects(addCustomQuoteButton);
        initButtonHoverEffects(exitButton);
        initButtonHoverEffects(newRoundButton);
    }

    // MODIFIES: this
    // EFFECTS: hides the background of the default buttons
    public void hideOriginalButtonBackground() {
        addPhraseButton.setContentAreaFilled(false);
        addPhraseButton.setBorderPainted(false);
        addPhraseButton.setFocusPainted(false);

        removePhraseButton.setContentAreaFilled(false);
        removePhraseButton.setBorderPainted(false);
        removePhraseButton.setFocusPainted(false);

        viewPhraseButton.setContentAreaFilled(false);
        viewPhraseButton.setBorderPainted(false);
        viewPhraseButton.setFocusPainted(false);

        saveButton.setContentAreaFilled(false);
        saveButton.setBorderPainted(false);
        saveButton.setFocusPainted(false);

        loadButton.setContentAreaFilled(false);
        loadButton.setBorderPainted(false);
        loadButton.setFocusPainted(false);

        addCustomQuoteButton.setContentAreaFilled(false);
        addCustomQuoteButton.setBorderPainted(false);
        addCustomQuoteButton.setFocusPainted(false);

        newRoundButton.setContentAreaFilled(false);
        newRoundButton.setBorderPainted(false);
        newRoundButton.setFocusPainted(false);

        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
    }

    // MODIFIES: this
    // EFFECTS: handles what the addPhraseButton does on click.
    public void addPhraseButtonLogic() {
        addPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                getTypingGame().savePhrase();
                savedPhrases.setText(typingGame.getUserInfo().getSavedPhrasesToView());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the removePhraseButton does on click.
    public void removePhraseButtonLogic() {
        removePhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                getTypingGame().getUserInfo().removeSavedPhraseUIVersion();
                savedPhrases.setText(typingGame.getUserInfo().getSavedPhrasesToView());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the viewPhraseButton does on click.
    public void viewPhraseButtonLogic() {
        viewPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                showCurrentRoundActualAndUserPhrase();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the saveButton does on click.
    public void saveButtonLogic() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                getTypingGame().saveUserInfo();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the loadButton does on click.
    public void loadButtonLogic() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                getTypingGame().loadUserInfo();
                savedPhrases.setText(typingGame.getUserInfo().getSavedPhrasesToView());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to input their own quote to add to the pool quotes for the game
    public void addCustomQuoteButtonLogic() {
        addCustomQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();

                Path path = Paths.get("./data/phrases.txt");
                String customQuote = JOptionPane.showInputDialog("Input custom quote: ");
                try {
                    Files.writeString(path, customQuote + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException ioe) {
                    System.out.println("Something went wrong when trying to add custom quote. ");
                    ioe.printStackTrace();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the exitButton does on click.
    public void exitButtonLogic() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();

                LogPrinter lp;
                lp = new ConsolePrinter();
                try {
                    lp.printLog(EventLog.getInstance());
                } catch (LogException le) {
                    System.out.println("System Error. ");
                    le.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the newRoundButton does on click
    public void newRoundButtonLogic() {
        newRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();

                EventLog.getInstance().logEvent(new Event("New round started. "));
                currentRound.setRandomLineIndex(currentRound.getNumberOfLines());
                currentRound.newRound(currentRound.getRandomLineIndex());
                gameWindow.getGameUI().updateActualTextDisplay(currentRound.getActualText().toString());
                gameWindow.getGameUI().clearUserInput();
                gameWindow.getGameUI().focusUserInput();
                gameWindow.switchToGameUI();
                gameWindow.getGameUI().resetUserInputtedSomething();
            }
        });
    }

    // EFFECTS: displays the actual text, and the user-typed text in a pop-up window.
    public void showCurrentRoundActualAndUserPhrase() {
        String displayedPhrases = "Actual: " + currentRound.getActualText() + "\n User: " + currentRound.getUserText() + "\n"
        + "Correct keystrokes: " + currentRound.getNumberOfCorrectKeystrokes() + "\n" + "Incorrect keystrokes: " + currentRound.getNumberOfIncorrectKeystrokes();
        JOptionPane.showMessageDialog(this, displayedPhrases, "Saved Phrases", JOptionPane.INFORMATION_MESSAGE);
    }
}
