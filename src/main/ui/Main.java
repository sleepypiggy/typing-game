package ui;

import java.awt.Font;
import java.io.File;

import javax.swing.UIManager;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// The main class where the program instantiates the required objects to run, which
// in this case, is the TypingGame() object.

@ExcludeFromJacocoGeneratedReport
public class Main {
 
    // EFFECTS: instantiates a TypingGame() object to start the program.
    public static void main(String[] args) throws Exception {
        loadGlobalFont();
        new TypingGame();
    }

    // EFFECTS: changes all Font instances to custom font
    private static void loadGlobalFont() {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("./data/Stardew_Valley.ttf"));
            Font uiFont = baseFont.deriveFont(Font.PLAIN, 30f);

            Object[] keys = UIManager.getLookAndFeelDefaults().keySet().toArray();
            for (Object key : keys) {
                Object value = UIManager.get(key);
                if (value instanceof Font) {
                    UIManager.put(key, uiFont);
                }
            }
            Font buttonFont = uiFont.deriveFont(Font.PLAIN, 20f);
            UIManager.put("Button.font", buttonFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}