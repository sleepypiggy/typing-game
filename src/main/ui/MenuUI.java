package ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Round;
import model.UserInfo;

public class MenuUI extends UIElement {
    private ButtonSpriteSheet buttonSpriteSheet;

    private GameWindow gameWindow;

    private JButton startButton;
    private JButton exitButton;
    private JPanel buttonLayout;
    private JPanel buttonContainer;

    public MenuUI(TypingGame typingGame, Round currentRound, UserInfo userInfo, ButtonSpriteSheet buttonSpriteSheet, GameWindow gamewindow) {
        super(typingGame, currentRound, userInfo);
        this.buttonSpriteSheet = buttonSpriteSheet;
        this.gameWindow = gamewindow;
        setLayout(new BorderLayout());
        initButtons();
        layoutButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes the button fields
    public void initButtons() {
        this.startButton = new JButton("Start");
        this.exitButton = new JButton("Exit");

        startButtonAction();
        exitButtonAction();
    }

    public void layoutButtons() {
        buttonLayout = new JPanel(new GridLayout(2, 1));
        buttonContainer = new JPanel(new GridBagLayout());

        buttonLayout.add(startButton);
        buttonLayout.add(exitButton);

        buttonContainer.add(buttonLayout);
        
        this.add(buttonContainer);
    }

    public void startButtonAction() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.switchToGameUI();
            }
        });
    }

    public void exitButtonAction() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    

}
