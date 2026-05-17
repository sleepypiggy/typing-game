package ui;

import java.awt.Font;
import java.io.File;
import java.io.InputStream;

import javax.swing.UIManager;


// The main class where the program instantiates the required objects to run, which
// in this case, is the TypingGame() object.

public class App {
 
    // EFFECTS: instantiates a TypingGame() object to start the program.
    public static void main(String[] args) throws Exception {
        loadGlobalFont();
        new TypingGame();
    }

    // EFFECTS: changes all Font instances to custom font
    private static void loadGlobalFont() {
    try {
        InputStream is = App.class.getResourceAsStream("/Stardew_Valley.ttf");

        if (is == null) {
            throw new RuntimeException("Font not found in resources!");
        }

        Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
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