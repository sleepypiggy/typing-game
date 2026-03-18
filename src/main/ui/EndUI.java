package ui;

import model.Round;
import model.UserInfo;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// QUESTIONS: - Does changing the button to a sprite count as the image aspect?
//            - Do we have to display the list of x in a "panel" specifically?
//            - How is was the difficulty of last weeks lab in comparison to midterm 4?

// This class displays all the UI elements for anything that is on the end screen, including things like
// the buttons, and the saved phrases (if the button to view saved phrases is clicked).

@ExcludeFromJacocoGeneratedReport
public class EndUI extends JPanel {
    private TypingGame typingGame;
    private Round currentRound;
    private UserInfo userInfo;

    private JButton addPhraseButton;
    private JButton removePhraseButton;
    private JButton viewPhraseButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;

    private JPanel buttonLayout;
    private JPanel buttonContainer;

    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, and initializes the buttons as well as their layouts.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        this.typingGame = typingGame;
        this.currentRound = currentRound;
        this.userInfo = userInfo;
        setLayout(new BorderLayout());
        initButtons();
        initButtonLayout();
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons and calls the methods required to initialize their respective logic.
    public void initButtons() {
        addPhraseButton = new JButton("Add phrase to favorites");
        removePhraseButton = new JButton("Remove a phrase");
        viewPhraseButton = new JButton("View phrases");
        saveButton = new JButton("Download phrases");
        loadButton = new JButton("Load phrases");
        exitButton = new JButton("Exit");

        addPhraseButtonLogic();
        removePhraseButtonLogic();
        viewPhraseButtonLogic();
        saveButtonLogic();
        loadButtonLogic();
        exitButtonLogic();
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
                typingGame.removePhrase();
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
        for (String s : userInfo.getSavedPhrases()) {
            savedPhrases.append("- " + s + "\n");
        }
        JOptionPane.showMessageDialog(this, savedPhrases.toString(), "Saved Phrases", JOptionPane.INFORMATION_MESSAGE);
    }


}
