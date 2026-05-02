package ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Round;
import model.UserInfo;

// The MenuUI class represents the menu screen of the typing game.
public class MenuUI extends UIElement {
    private ButtonSpriteSheet buttonSpriteSheet;

    private JLabel title;

    private Image startButtonImage;
    private ImageIcon startButtonImageIcon;
    private Image startButtonImageClicked;
    private ImageIcon startButtonImageIconClicked;

    private Image quitButtonImage;
    private ImageIcon quitButtonImageIcon;
    private Image quitButtonImageClicked;
    private ImageIcon quitButtonImageIconClicked;

    private boolean isGameUICreated;

    private GameWindow gameWindow;
    private Round currentRound;

    private JButton startButton;
    private JButton exitButton;
    private JPanel buttonLayout;
    private JPanel buttonContainer;

    // EFFECTS: initializes all the components needed for the menu to run properly.
    public MenuUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, ButtonSpriteSheet buttonSpriteSheet, GameWindow gamewindow) {
        super(typingGame, currentRound, userInfo);
        isGameUICreated = false;
        this.buttonSpriteSheet = buttonSpriteSheet;
        this.gameWindow = gamewindow;
        this.currentRound = currentRound;
        setLayout(new BorderLayout());
        loadButtonImages();
        loadButtonClickedImages();
        initButtons();
        layoutButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes the button fields
    public void initButtons() {
        this.startButton = new JButton(startButtonImageIcon);
        this.exitButton = new JButton(quitButtonImageIcon);
        hideOriginalButton(startButton);
        hideOriginalButton(exitButton);

        startButtonAction();
        exitButtonAction();

        this.startButton.setPressedIcon(startButtonImageIconClicked);
        this.exitButton.setPressedIcon(quitButtonImageIconClicked);
    }

    // MODIFIES: this
    // EFFECTS: handles the layout of the buttons on screen
    public void layoutButtons() {
        buttonLayout = new JPanel(new GridLayout(2, 1));
        buttonContainer = new JPanel(new GridBagLayout());

        buttonLayout.add(startButton);
        buttonLayout.add(exitButton);

        buttonContainer.add(buttonLayout);
        
        this.add(buttonContainer);
    }

    // EFFECTS: what the start button on screen does when clicked
    public void startButtonAction() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // gameWindow.switchToGameUI();
                if (!isGameUICreated) {
                    gameWindow.createGameUI();
                    isGameUICreated = true;
                    gameWindow.switchToGameUI();
                    currentRound.startRoundTime();
                } else {
                    gameWindow.switchToGameUI();
                    currentRound.startRoundTime();
                }
            }
        });
    }

    // EFFECTS: what the exit button on screen does when clicked
    public void exitButtonAction() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds custom button images
    public void loadButtonImages() {
        startButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 385, 320, 62, 31);
        startButtonImage = startButtonImage.getScaledInstance(186, 93, Image.SCALE_SMOOTH);
        startButtonImageIcon = new ImageIcon(startButtonImage);

        quitButtonImage = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 385, 448, 62, 31);
        quitButtonImage = quitButtonImage.getScaledInstance(186, 93, Image.SCALE_SMOOTH);
        quitButtonImageIcon = new ImageIcon(quitButtonImage);
    }

    // MODIFIES: this
    // EFFECTS: loads the clicked icons of the buttons
    public void loadButtonClickedImages() {
        startButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 513, 320, 62, 31);
        startButtonImageClicked = startButtonImageClicked.getScaledInstance(186, 93, Image.SCALE_SMOOTH);
        startButtonImageIconClicked = new ImageIcon(startButtonImageClicked);

        quitButtonImageClicked = buttonSpriteSheet.getImage(buttonSpriteSheet.getButtonSpriteSheetBufferedImage(), 513, 448, 62, 31);
        quitButtonImageClicked = quitButtonImageClicked.getScaledInstance(186, 93, Image.SCALE_SMOOTH);
        quitButtonImageIconClicked = new ImageIcon(quitButtonImageClicked);
    }

    // MODIFIES: button
    // EFFECTS: hides the original button so custom button shows without interference
    public void hideOriginalButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    

}
