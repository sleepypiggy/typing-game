package persistance;

import model.UserInfo;
import persistence.JsonReader;
import persistence.JsonWriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUserInfo() {
        try {
            UserInfo userInfo = new UserInfo();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserInfo.json");
            writer.open();
            writer.write(userInfo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserInfo.json");
            userInfo = reader.read();
            assertEquals(0, userInfo.getNumberOfSavedPhrases());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterUserInfo() {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.addSavedPhrase(new StringBuilder("test phrase 1"));
            userInfo.addSavedPhrase(new StringBuilder("test phrase 2"));
            JsonWriter writer = new JsonWriter("./data/testWriterUserInfo.json");
            writer.open();
            writer.write(userInfo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUserInfo.json");
            userInfo = reader.read();
            List<String> phrases = userInfo.getSavedPhrases();
            assertEquals(2, phrases.size());
            checkPhrase("test phrase 1", phrases.get(0));
            checkPhrase("test phrase 2", phrases.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
