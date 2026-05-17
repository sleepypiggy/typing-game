package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJson {
    protected void checkPhrase(String actualPhrase, String testPhrase) {
        assertEquals(actualPhrase, testPhrase);
    }
}
