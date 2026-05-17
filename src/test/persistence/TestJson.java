package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class TestJson {
    protected void checkPhrase(String actualPhrase, String testPhrase) {
        assertEquals(actualPhrase, testPhrase);
    }
}
