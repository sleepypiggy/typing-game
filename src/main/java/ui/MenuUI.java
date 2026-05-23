package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.AudioPlayer;
import model.Round;
import model.UserInfo;

// The MenuUI class represents the menu screen of the typing game.
public class MenuUI extends UIElement {
    private JTextArea title;

    private boolean isGameUICreated;

    private GameWindow gameWindow;
    private Round currentRound;

    private ImageIcon backgroundImage;

    private JButton startButton;
    private JButton exitButton;

    private JPanel elementContainer;

    private AudioPlayer buttonClickSfx;
    private AudioPlayer menuMusic;

    // EFFECTS: initializes all the components needed for the menu to run properly.
    public MenuUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, GameWindow gamewindow) {
        super(typingGame, currentRound, userInfo);
        initAudio();
        isGameUICreated = false;
        this.gameWindow = gamewindow;
        this.currentRound = currentRound;
        setLayout(new BorderLayout());
        initButtons();
        setTitle();
        displayElements();
        initBackground();
    }

    public void initAudio() {
        this.buttonClickSfx = new AudioPlayer("./data/buttonClick.wav", false);
        this.menuMusic = new AudioPlayer("./data/backgroundMusic.wav", true);
        this.menuMusic.playAudio();
    }

    // MODIFIES: this
    // EFFECTS: sets the background image and makes sure nothing else is interfering with it
    public void initBackground() {
        backgroundImage = new ImageIcon("./data/background.gif");
        elementContainer.setOpaque(false);
    }

    // EFFECTS: draws the background image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    // MODIFIES: this
    // EFFECTS: sets the title text on the menu screen
    public void setTitle() {
        title = new JTextArea(2, 5);
        title.setText("Typing Game");
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setFocusable(false);
        title.setOpaque(false);
        Font currentFont = title.getFont();
        title.setFont(currentFont.deriveFont(150f));
        title.setForeground(Color.WHITE);
    }

    // MODIFIES: this
    // EFFECTS: initializes the button fields and decorates them accordingly
    public void initButtons() {
        this.startButton = new JButton("Start");
        this.exitButton = new JButton("Exit");
        hideOriginalButton(startButton);
        hideOriginalButton(exitButton);
        this.startButton.setForeground(Color.WHITE);
        this.exitButton.setForeground(Color.WHITE);
        this.startButton.setFont(this.startButton.getFont().deriveFont(50.0f));
        this.exitButton.setFont(this.startButton.getFont().deriveFont(30.0f));

        startButtonAction();
        exitButtonAction();
        handleButtonMouseEvents();
    }

    // EFFECTS: handles what happens when the mouse is hovered over the buttons
    public void handleButtonMouseEvents() {
        this.startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setForeground(new Color(215, 215, 215));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setForeground(Color.WHITE);
            }
        });

        this.exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(new Color(215, 215, 215));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
        });
    }

    
    // MODIFIES: this
    // EFFECTS: handles the layout of the elements and then displays them on the screen.
    public void displayElements() {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        GridBagConstraints exitButtonConstraints =  new GridBagConstraints();
        elementContainer = new JPanel(new GridBagLayout());

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;

        startButtonConstraints.gridx = 0;
        startButtonConstraints.gridy = 1;
        startButtonConstraints.anchor = GridBagConstraints.WEST;

        exitButtonConstraints.gridx = 0;
        exitButtonConstraints.gridy = 2;
        exitButtonConstraints.anchor = GridBagConstraints.WEST;

        titleConstraints.insets = new Insets(0, 50, 0, 0);
        startButtonConstraints.insets = new Insets(0, 50, 0, 0);
        exitButtonConstraints.insets = new Insets(0, 50, 0, 0);
        

        elementContainer.add(title, titleConstraints);
        elementContainer.add(startButton, startButtonConstraints);
        elementContainer.add(exitButton, exitButtonConstraints);

        add(elementContainer, BorderLayout.WEST);
    }

    // EFFECTS: what the start button on screen does when clicked
    public void startButtonAction() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                if (!isGameUICreated) {
                    gameWindow.createGameUI();
                    isGameUICreated = true;
                    gameWindow.switchToGameUI();
                } else {
                    gameWindow.switchToGameUI();
                }
            }
        });
    }

    // EFFECTS: what the exit button on screen does when clicked
    public void exitButtonAction() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickSfx.playAudio();
                System.exit(0);
            }
        });
    }

    // MODIFIES: button
    // EFFECTS: hides the original button so custom button shows without interference
    public void hideOriginalButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(false);
    }

    

}
