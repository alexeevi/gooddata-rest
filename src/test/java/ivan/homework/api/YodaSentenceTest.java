package ivan.homework.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YodaSentenceTest extends SentenceTest {

    @Test
    public void testText() {
        YodaSentence yodaSentence = new YodaSentence(sentence);
        assertEquals("Adjective noun verb", yodaSentence.getText());
    }
}
