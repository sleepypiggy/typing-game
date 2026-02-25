package persistance;

import model.Round;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    protected void checkPhrase(String actualPhrase, String testPhrase) {
        assertEquals(actualPhrase, testPhrase);
    }
}
