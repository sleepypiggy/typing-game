package ui;

import java.awt.Button;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class ButtonSpriteSheet {
    private BufferedImage buttonSpriteSheetBufferedImage;

    public ButtonSpriteSheet() {
        initSpriteSheetImage();
    }

    // MODIFIES: this
    // EFFECTS: instantiates the sprite sheet image used for all the button sprites
    public void initSpriteSheetImage() {
        try {
            this.buttonSpriteSheetBufferedImage = ImageIO.read(new File("./data/buttons.png"));
        } catch (IOException e) {
            System.out.println("Something went wrong. ");
        }
    }

    public BufferedImage getButtonSpriteSheetBufferedImage() {
        return this.buttonSpriteSheetBufferedImage;
    }

}
