package ui;

import model.Round;
import model.UserInfo;
import model.exception.LogException;
import model.Event;
import model.EventLog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// This class displays all the UI elements for anything that is on the end screen, including things like
// the buttons, and the saved phrases (if the button to view saved phrases is clicked).

@ExcludeFromJacocoGeneratedReport
public class EndUI extends UIElement {
    private ButtonSpriteSheet buttonSpriteSheet;

    private GameWindow gameWindow;
    private Round currentRound;
    private UserInfo userInfo;
    private TypingGame typingGame;

    private JButton addPhraseButton;
    private Image addPhraseButtonImage;
    private ImageIcon addPhraseButtonImageIcon;
    private Image addPhraseButtonImageClicked;
    private ImageIcon addPhraseButtonImageIconClicked;

    private JButton removePhraseButton;
    private Image removePhraseButtonImage;
    private ImageIcon removePhraseButtonImageIcon;
    private Image removePhraseButtonImageClicked;
    private ImageIcon removePhraseButtonImageIconClicked;

    private JButton viewPhraseButton;
    private Image viewPhraseButtonImage;
    private ImageIcon viewPhraseButtonImageIcon;
    private Image viewPhraseButtonImageClicked;
    private ImageIcon viewPhraseButtonImageIconClicked;

    private JButton saveButton;
    private Image saveButtonImage;
    private ImageIcon saveButtonImageIcon;
    private Image saveButtonImageClicked;
    private ImageIcon saveButtonImageIconClicked;

    private JButton loadButton;
    private Image loadButtonImage;
    private ImageIcon loadButtonImageIcon;
    private Image loadButtonImageClicked;
    private ImageIcon loadButtonImageIconClicked;

    private JButton exitButton;
    private Image exitButtonImage;
    private ImageIcon exitButtonImageIcon;
    private Image exitButtonImageClicked;
    private ImageIcon exitButtonImageIconClicked;

    private JButton newRoundButton;
    private Image newRoundButtonImage;
    private ImageIcon newRoundButtonImageIcon;
    private Image newRoundButtonImageClicked;
    private ImageIcon newRoundButtonImageIconClicked;

    private JLabel wordsPerMinute;
    private JLabel accuracy;

    private JLabel savedPhrasesLabel;
    private JTextArea savedPhrases;
    private JScrollPane scroll;
    private JPanel savedPhrasesContainer;
    private GridBagConstraints savedPhrasesLabelContraints;
    private GridBagConstraints savedPhrasesConstraints;

    private JPanel iconContainer;
    private GridBagConstraints wordsPerMinuteConstraints;
    private GridBagConstraints accuracyConstraints;
    private GridBagConstraints addPhraseButtonConstraints;
    private GridBagConstraints removePhraseButtonConstraints;
    private GridBagConstraints viewPhraseButtonConstraints;
    private GridBagConstraints saveButtonConstraints;
    private GridBagConstraints loadButtonConstraints;
    private GridBagConstraints exitButtonConstraints;
    private GridBagConstraints newRoundButtonConstraints;

    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, initializes the buttons as well as their layouts, and adds an image to the window.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, ButtonSpriteSheet buttonSpriteSheet, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        this.buttonSpriteSheet = buttonSpriteSheet;
        this.gameWindow = gameWindow;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
        this.typingGame = typingGame;
        setLayout(new BorderLayout());
        loadButtonImages();
        loadButtonClickedImages();
        initButtons();
        hideOriginalButtonBackground();
        displayStats();
        displayImageIcons();
        initJList();
    }

    public void initJList() {
        savedPhrasesLabel  = new JLabel("Saved Phrases");

        savedPhrasesLabelContraints = new GridBagConstraints();
        savedPhrasesConstraints = new GridBagConstraints();
        savedPhrasesContainer = new JPanel(new GridBagLayout());
        setGridBagConstraintsGridPosition(savedPhrasesLabelContraints, 0, 0);
        setGridBagConstraintsGridPosition(savedPhrasesConstraints, 0, 1);
        savedPhrasesLabelContraints.weightx = 0;
        savedPhrasesLabelContraints.weighty = 0;
        savedPhrasesLabelContraints.fill = GridBagConstraints.NONE;

        savedPhrases = new JTextArea(10, 30);
        scroll = new JScrollPane(savedPhrases);

        savedPhrasesConstraints.weightx = 1.0;
        savedPhrasesConstraints.weighty = 1.0;
        savedPhrasesConstraints.fill = GridBagConstraints.BOTH;

        savedPhrases.setEditable(false);
        savedPhrases.setWrapStyleWord(true);
        savedPhrases.setFocusable(false);
        savedPhrases.setLineWrap(true);
        savedPhrases.setOpaque(false);

        savedPhrasesContainer.add(savedPhrasesLabel, savedPhrasesLabelContraints);
        savedPhrasesContainer.add(scroll, savedPhrasesConstraints);
        add(savedPhrasesContainer, BorderLayout.EAST);
    }

    public void displayImageIcons() {
        iconContainer = new JPanel(new GridBagLayout());
        initGridBagConstraints();

        setGridBagConstraintsGridPosition(wordsPerMinuteConstraints, 0, 0);
        setGridBagConstraintsGridPosition(accuracyConstraints, 0, 1);
        setGridBagConstraintsGridPosition(addPhraseButtonConstraints, 0, 2);
        setGridBagConstraintsGridPosition(removePhraseButtonConstraints, 1, 2);
        setGridBagConstraintsGridPosition(viewPhraseButtonConstraints, 0, 3);
        setGridBagConstraintsGridPosition(saveButtonConstraints, 1, 3);
        setGridBagConstraintsGridPosition(loadButtonConstraints, 0, 4);
        setGridBagConstraintsGridPosition(exitButtonConstraints, 1, 4);
        setGridBagConstraintsGridPosition(newRoundButtonConstraints, 0, 5);

        wordsPerMinuteConstraints.gridwidth = 2;
        accuracyConstraints.gridwidth = 2;
        accuracyConstraints.anchor = GridBagConstraints.WEST;

        setGridBagConstraintInsets(wordsPerMinuteConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(accuracyConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(addPhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(removePhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(viewPhraseButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(saveButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(loadButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(exitButtonConstraints, 0, 20, 0, 0);
        setGridBagConstraintInsets(newRoundButtonConstraints, 0, 20, 0, 0);

        wordsPerMinuteConstraints.weightx = 1;
        wordsPerMinuteConstraints.fill = GridBagConstraints.HORIZONTAL;

        removePhraseButtonConstraints.anchor = GridBagConstraints.WEST;
        saveButtonConstraints.anchor = GridBagConstraints.WEST;
        exitButtonConstraints.anchor = GridBagConstraints.WEST;

        addElementsToIconContainer();

        add(iconContainer, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: initializes the GridBagConstraints for each element
    public void initGridBagConstraints() {
        wordsPerMinuteConstraints = new GridBagConstraints();
        accuracyConstraints = new GridBagConstraints();
        addPhraseButtonConstraints = new GridBagConstraints();
        removePhraseButtonConstraints = new GridBagConstraints();
        viewPhraseButtonConstraints = new GridBagConstraints();
        saveButtonConstraints = new GridBagConstraints();
        loadButtonConstraints = new GridBagConstraints();
        exitButtonConstraints = new GridBagConstraints();
        newRoundButtonConstraints = new GridBagConstraints();
    }

    public void addElementsToIconContainer() {
        iconContainer.add(wordsPerMinute, wordsPerMinuteConstraints);
        iconContainer.add(accuracy, accuracyConstraints);
        iconContainer.add(addPhraseButton, addPhraseButtonConstraints);
        iconContainer.add(removePhraseButton, removePhraseButtonConstraints);
        iconContainer.add(viewPhraseButton, viewPhraseButtonConstraints);
        iconContainer.add(saveButton, saveButtonConstraints);
        iconContainer.add(loadButton, loadButtonConstraints);
        iconContainer.add(exitButton, exitButtonConstraints);
        iconContainer.add(newRoundButton, newRoundButtonConstraints);
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
    private void displayStats() {
        if (currentRound.getAccuracy() < 60) {
            wordsPerMinute = new JLabel("Words per minute: " + String.valueOf(currentRound.getWordsPerMinute() + " (invalid)"));
        } else {
            wordsPerMinute = new JLabel("Words per minute: " + String.valueOf(currentRound.getWordsPerMinute()));
        }
        accuracy = new JLabel("Accuracy: " + String.valueOf(currentRound.getAccuracy()) + "%");
    }

    // MODIFIES: this
    // EFFECTS: updates the displayed stats
    public void updateDisplayedStats(double wpm, double accuracy) {
        if (accuracy < 60) {
            this.wordsPerMinute.setText("Words per minute: " + wpm + " (invalid)");
        } else {
            this.wordsPerMinute.setText("Words per minute: " + wpm);
        }
        this.accuracy.setText("Accuracy: " + accuracy);
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons and calls the methods required to initialize their respective logic.
    public void initButtons() {
        addPhraseButton = new JButton(addPhraseButtonImageIcon);
        removePhraseButton = new JButton(removePhraseButtonImageIcon);
        viewPhraseButton = new JButton(viewPhraseButtonImageIcon);
        saveButton = new JButton(saveButtonImageIcon);
        loadButton = new JButton(loadButtonImageIcon);
        exitButton = new JButton(exitButtonImageIcon);
        newRoundButton = new JButton(newRoundButtonImageIcon);

        addPhraseButton.setPreferredSize(new Dimension(93, 93));
        removePhraseButton.setPreferredSize(new Dimension(93, 93));
        viewPhraseButton.setPreferredSize(new Dimension(93, 93));
        saveButton.setPreferredSize(new Dimension(93, 93));
        loadButton.setPreferredSize(new Dimension(93, 93));
        exitButton.setPreferredSize(new Dimension(93, 93));
        newRoundButton.setPreferredSize(new Dimension(93, 93));

        addPhraseButtonLogic();
        removePhraseButtonLogic();
        viewPhraseButtonLogic();
        saveButtonLogic();
        loadButtonLogic();
        exitButtonLogic();
        newRoundButtonLogic();

        addPhraseButton.setPressedIcon(addPhraseButtonImageIconClicked);
        removePhraseButton.setPressedIcon(removePhraseButtonImageIconClicked);
        viewPhraseButton.setPressedIcon(viewPhraseButtonImageIconClicked);
        saveButton.setPressedIcon(saveButtonImageIconClicked);
        loadButton.setPressedIcon(loadButtonImageIconClicked);
        exitButton.setPressedIcon(exitButtonImageIconClicked);
        newRoundButton.setPressedIcon(newRoundButtonImageIconClicked);
    }

    // MODIFIES: this
    // EFFECTS: adds custom button sprites to each of the buttons
    public void loadButtonImages() {
        addPhraseButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 320, 288, 31, 31);
        addPhraseButtonImage = addPhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        addPhraseButtonImageIcon = new ImageIcon(addPhraseButtonImage);

        removePhraseButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 384, 288, 31, 31);
        removePhraseButtonImage = removePhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        removePhraseButtonImageIcon = new ImageIcon(removePhraseButtonImage);

        viewPhraseButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 448, 32, 31, 31);
        viewPhraseButtonImage = viewPhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        viewPhraseButtonImageIcon = new ImageIcon(viewPhraseButtonImage);

        saveButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 448, 0, 31, 31);
        saveButtonImage = saveButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        saveButtonImageIcon = new ImageIcon(saveButtonImage);

        loadButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 448, 224, 31, 31);
        loadButtonImage = loadButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        loadButtonImageIcon = new ImageIcon(loadButtonImage);

        exitButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 384, 192, 31, 31);
        exitButtonImage = exitButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        exitButtonImageIcon = new ImageIcon(exitButtonImage);

        newRoundButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 384, 256, 31, 31);
        newRoundButtonImage = newRoundButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        newRoundButtonImageIcon = new ImageIcon(newRoundButtonImage);
    }

    // MODIFIE: this
    // EFFECTS: adds custom button sprite to each button on click
    private void loadButtonClickedImages() {
        addPhraseButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 352, 288, 31, 31);
        addPhraseButtonImageClicked = addPhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        addPhraseButtonImageIconClicked = new ImageIcon(addPhraseButtonImageClicked);

        removePhraseButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 416, 288, 31, 31);
        removePhraseButtonImageClicked = removePhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        removePhraseButtonImageIconClicked = new ImageIcon(removePhraseButtonImageClicked);

        viewPhraseButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 480, 32, 31, 31);
        viewPhraseButtonImageClicked = viewPhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        viewPhraseButtonImageIconClicked = new ImageIcon(viewPhraseButtonImageClicked);

        saveButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 480, 0, 31, 31);
        saveButtonImageClicked = saveButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        saveButtonImageIconClicked = new ImageIcon(saveButtonImageClicked);

        loadButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 480, 224, 31, 31);
        loadButtonImageClicked = loadButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        loadButtonImageIconClicked = new ImageIcon(loadButtonImageClicked);

        exitButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 416, 192, 31, 31);
        exitButtonImageClicked = exitButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        exitButtonImageIconClicked = new ImageIcon(exitButtonImageClicked);

        newRoundButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 416, 256, 31, 31);
        newRoundButtonImageClicked = newRoundButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        newRoundButtonImageIconClicked = new ImageIcon(newRoundButtonImageClicked);
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

        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        newRoundButton.setContentAreaFilled(false);
        newRoundButton.setBorderPainted(false);
        newRoundButton.setFocusPainted(false);
    }

    // MODIFIES: this
    // EFFECTS: handles what the addPhraseButton does on click.
    public void addPhraseButtonLogic() {
        addPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                showSavedPhrases();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the saveButton does on click.
    public void saveButtonLogic() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                getTypingGame().loadUserInfo();
                savedPhrases.setText(typingGame.getUserInfo().getSavedPhrasesToView());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles what the exitButton does on click.
    public void exitButtonLogic() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                EventLog.getInstance().logEvent(new Event("New round started. "));
                currentRound.setRandomLineIndex(currentRound.getNumberOfLines());
                currentRound.newRound(currentRound.getRandomLineIndex());
                gameWindow.getGameUI().updateUserInputArea();
                gameWindow.getGameUI().updateActualTextDisplay(currentRound.getActualText().toString());
                gameWindow.getGameUI().clearUserInput();
                gameWindow.getGameUI().focusUserInput();
                currentRound.startRoundTime();
                gameWindow.switchToGameUI();
            }
        });
    }

    // EFFECTS: displays the saved phrases in a pop-up window.
    public void showSavedPhrases() {
        String displayedPhrases =  getTypingGame().getUserInfo().getSavedPhrasesToView();
        JOptionPane.showMessageDialog(this, displayedPhrases, "Saved Phrases", JOptionPane.INFORMATION_MESSAGE);
    }
}
