package ui;

import model.Round;
import model.UserInfo;

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
public class EndUI extends JPanel {
    private BufferedImage buttonSpriteSheetBufferedImage;

    private TypingGame typingGame;
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

    private JLabel keyboardImageLabel;
    private Image keyboardImage;
    private ImageIcon keyboardImageIcon;

    private JPanel buttonLayout;
    private JPanel buttonContainer;

    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, initializes the buttons as well as their layouts, and adds an image to the window.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        this.typingGame = typingGame;
        this.currentRound = currentRound;
        initSpriteSheetImage();
        setLayout(new BorderLayout());
        loadButtonImages();
        loadButtonClickedImages();
        initButtons();
        hideOriginalButtonBackground();
        initButtonLayout();
        loadKeyboardImage();
    }

    // MODIFIES: this
    // EFFECTS: display an image of a keyboard in the window.
    public void loadKeyboardImage() {
        try {
            keyboardImage = ImageIO.read(new File("./data/keyboard.png"));
        } catch (IOException e) {
            System.out.println("Image not found. ");
            e.printStackTrace();
        }
        keyboardImage.getScaledInstance(926, 322, Image.SCALE_SMOOTH);
        keyboardImageIcon = new ImageIcon(keyboardImage);
        keyboardImageLabel = new JLabel(keyboardImageIcon);
        add(keyboardImageLabel);
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

        addPhraseButtonLogic();
        removePhraseButtonLogic();
        viewPhraseButtonLogic();
        saveButtonLogic();
        loadButtonLogic();
        exitButtonLogic();

        addPhraseButton.setPressedIcon(addPhraseButtonImageIconClicked);
        removePhraseButton.setPressedIcon(removePhraseButtonImageIconClicked);
        viewPhraseButton.setPressedIcon(viewPhraseButtonImageIconClicked);
        saveButton.setPressedIcon(saveButtonImageIconClicked);
        loadButton.setPressedIcon(loadButtonImageIconClicked);
        exitButton.setPressedIcon(exitButtonImageIconClicked);
    }

    // MODIFIES: this
    // EFFECTS: creates the button layout managers and adds the buttons to them.
    public void initButtonLayout() {
        buttonLayout = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonContainer = new JPanel(new GridBagLayout());

        buttonLayout.add(addPhraseButton);
        buttonLayout.add(removePhraseButton);
        buttonLayout.add(viewPhraseButton);
        buttonLayout.add(saveButton);
        buttonLayout.add(loadButton);
        buttonLayout.add(exitButton);

        buttonContainer.add(buttonLayout);
        add(buttonContainer, BorderLayout.SOUTH);
    }

    // EFFECTS: adds custom button sprites to each of the buttons
    public void loadButtonImages() {
        addPhraseButtonImage = getImage(buttonSpriteSheetBufferedImage, 320, 288, 31, 31);
        addPhraseButtonImage = addPhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        addPhraseButtonImageIcon = new ImageIcon(addPhraseButtonImage);

        removePhraseButtonImage = getImage(buttonSpriteSheetBufferedImage, 384, 288, 31, 31);
        removePhraseButtonImage = removePhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        removePhraseButtonImageIcon = new ImageIcon(removePhraseButtonImage);

        viewPhraseButtonImage = getImage(buttonSpriteSheetBufferedImage, 448, 32, 31, 31);
        viewPhraseButtonImage = viewPhraseButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        viewPhraseButtonImageIcon = new ImageIcon(viewPhraseButtonImage);

        saveButtonImage = getImage(buttonSpriteSheetBufferedImage, 448, 0, 31, 31);
        saveButtonImage = saveButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        saveButtonImageIcon = new ImageIcon(saveButtonImage);

        loadButtonImage = getImage(buttonSpriteSheetBufferedImage, 448, 224, 31, 31);
        loadButtonImage = loadButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        loadButtonImageIcon = new ImageIcon(loadButtonImage);

        exitButtonImage = getImage(buttonSpriteSheetBufferedImage, 384, 192, 31, 31);
        exitButtonImage = exitButtonImage.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        exitButtonImageIcon = new ImageIcon(exitButtonImage);
    }

    // EFFECTS: adds custom button sprite to each button on click
    private void loadButtonClickedImages() {
        addPhraseButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 352, 288, 31, 31);
        addPhraseButtonImageClicked = addPhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        addPhraseButtonImageIconClicked = new ImageIcon(addPhraseButtonImageClicked);

        removePhraseButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 416, 288, 31, 31);
        removePhraseButtonImageClicked = removePhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        removePhraseButtonImageIconClicked = new ImageIcon(removePhraseButtonImageClicked);

        viewPhraseButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 480, 32, 31, 31);
        viewPhraseButtonImageClicked = viewPhraseButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        viewPhraseButtonImageIconClicked = new ImageIcon(viewPhraseButtonImageClicked);

        saveButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 480, 0, 31, 31);
        saveButtonImageClicked = saveButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        saveButtonImageIconClicked = new ImageIcon(saveButtonImageClicked);

        loadButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 480, 224, 31, 31);
        loadButtonImageClicked = loadButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        loadButtonImageIconClicked = new ImageIcon(loadButtonImageClicked);

        exitButtonImageClicked = getImage(buttonSpriteSheetBufferedImage, 416, 192, 31, 31);
        exitButtonImageClicked = exitButtonImageClicked.getScaledInstance(93, 93, Image.SCALE_SMOOTH);
        exitButtonImageIconClicked = new ImageIcon(exitButtonImageClicked);
    }

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
    }

    // EFFECTS: returns a select portion of image.
    public BufferedImage getImage(BufferedImage image, int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }

    // EFFECTS: instantiates the sprite sheet image used for all the button sprites
    public void initSpriteSheetImage() {
        try {
            buttonSpriteSheetBufferedImage = ImageIO.read(new File("./data/buttons.png"));
        } catch (IOException e) {
            System.out.println("Something went wrong. ");
        }
    }

    // EFFECTS: handles what the addPhraseButton does on click.
    public void addPhraseButtonLogic() {
        addPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typingGame.savePhrase();
            }
        });
    }

    // EFFECTS: handles what the removePhraseButton does on click.
    public void removePhraseButtonLogic() {
        removePhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typingGame.getUserInfo().removeSavedPhraseUIVersion();
            }
        });
    }

    // EFFECTS: handles what the viewPhraseButton does on click.
    public void viewPhraseButtonLogic() {
        viewPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSavedPhrases();
            }
        });
    }

    // EFFECTS: handles what the saveButton does on click.
    public void saveButtonLogic() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typingGame.saveUserInfo();
            }
        });
    }

    // EFFECTS: handles what the loadButton does on click.
    public void loadButtonLogic() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typingGame.loadUserInfo();
            }
        });
    }

    // EFFECTS: handles what the exitButton does on click.
    public void exitButtonLogic() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // EFFECTS: displays the saved phrases in a pop-up window.
    public void showSavedPhrases() {
        StringBuilder savedPhrases = new StringBuilder();
        for (String s : typingGame.getUserInfo().getSavedPhrases()) {
            savedPhrases.append("- " + s + "\n");
        }
        JOptionPane.showMessageDialog(this, savedPhrases.toString(), "Saved Phrases", JOptionPane.INFORMATION_MESSAGE);
    }
}
