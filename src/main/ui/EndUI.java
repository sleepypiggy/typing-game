package ui;

import model.Round;
import model.UserInfo;
import model.exception.LogException;
import model.EventLog;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// This class displays all the UI elements for anything that is on the end screen, including things like
// the buttons, and the saved phrases (if the button to view saved phrases is clicked).

@ExcludeFromJacocoGeneratedReport
public class EndUI extends UIElement {
    private ButtonSpriteSheet buttonSpriteSheet;

    private GameWindow gameWindow;
    private Round currentRound;

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

    private JPanel buttonLayout;
    private JPanel buttonContainer;

    private JLabel wordsPerMinute;
    private JLabel accuracy;
    private JPanel statsContainer;
    private JPanel statsContainerLayout;

    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, initializes the buttons as well as their layouts, and adds an image to the window.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, ButtonSpriteSheet buttonSpriteSheet, GameWindow gameWindow) {
        super(typingGame, currentRound, userInfo);
        this.buttonSpriteSheet = buttonSpriteSheet;
        this.gameWindow = gameWindow;
        this.currentRound = currentRound;
        setLayout(new BorderLayout());
        loadButtonImages();
        loadButtonClickedImages();
        initButtons();
        hideOriginalButtonBackground();
        initButtonLayout();
        displayStats();
    }

    // MODIFIES: this
    // EFFECTS: displays the words per minute and accuracy on screen
    private void displayStats() {
        wordsPerMinute = new JLabel("Words per minute: " + String.valueOf(currentRound.getWordsPerMinute()));
        accuracy = new JLabel("Accuracy: " + String.valueOf(currentRound.getAccuracy()));

        statsContainer = new JPanel(new GridLayout(2, 1));
        statsContainerLayout = new JPanel(new GridBagLayout());
        
        statsContainer.add(wordsPerMinute);
        statsContainer.add(accuracy);
        statsContainerLayout.add(statsContainer);

        add(statsContainerLayout, BorderLayout.NORTH);
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
    // EFFECTS: creates the button layout managers and adds the buttons to them.
    public void initButtonLayout() {
        buttonLayout = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonContainer = new JPanel(new GridBagLayout());

        buttonLayout.add(addPhraseButton);
        buttonLayout.add(removePhraseButton);
        buttonLayout.add(viewPhraseButton);
        buttonLayout.add(saveButton);
        buttonLayout.add(loadButton);
        buttonLayout.add(exitButton);
        buttonLayout.add(newRoundButton);

        buttonContainer.add(buttonLayout);
        add(buttonContainer, BorderLayout.SOUTH);
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
                currentRound.setRandomLineIndex(currentRound.getNumberOfLines());
                currentRound.newRound(currentRound.getRandomLineIndex());
                gameWindow.getGameUI().updateActualTextDisplay(currentRound.getActualText().toString());
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
