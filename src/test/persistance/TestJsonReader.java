package persistance;

import model.UserInfo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileNotFound.json");
        try {
            UserInfo userInfo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUserInfo() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUserInfo.json");
        try {
            UserInfo userInfo = reader.read();
            assertEquals(0, userInfo.getNumberOfSavedPhrases());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUserInfo() {
        JsonReader reader = new JsonReader("./data/testReaderUserInfo.json");
        try {
            UserInfo userInfo = reader.read();
            List<String> phrases = userInfo.getSavedPhrases();
            assertEquals(2, phrases.size());
            checkPhrase("test phrase 1", phrases.get(0));
            checkPhrase("test phrase 2", phrases.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
