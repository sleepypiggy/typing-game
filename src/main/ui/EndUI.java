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
    
    // EFFECTS: initializes the typingGame, the current round, and all the user information. Also sets the layout
    //          of this, and initializes the buttons as well as their layouts.
    public EndUI(TypingGame typingGame, Round currentRound, UserInfo userInfo) {
        
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons and calls the methods required to initialize their respective logic.
    public void initButtons() {
        
    }

    // MODIFIES: this
    // EFFECTS: creates the button layout managers and adds the buttons to them.
    public void initButtonLayout() {
        
    }

    // EFFECTS: adds phrase of current round to list on addPhraseButton click.
    public void addPhraseButtonLogic() {
        
    }

    // EFFECTS: removes phrase from list on removePhraseButton click.
    public void removePhraseButtonLogic() {
        
    }

    // EFFECTS: displays list of phrases on viewPhraseButton click.
    public void viewPhraseButtonLogic() {
        
    }

    // EFFECTS: downloads list of phrases on saveButton click.
    public void saveButtonLogic() {
        
    }

    // EFFECTS: loads list of saved phrases on loadButton click.
    public void loadButtonLogic() {
        
    }

    // EFFECTS: exits program on exitButton click.
    public void exitButtonLogic() {
        
    }

    // EFFECTS: displays the saved phrases in a pop-up window.
    public void showSavedPhrases() {

    }
}
