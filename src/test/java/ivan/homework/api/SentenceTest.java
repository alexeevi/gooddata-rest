package ivan.homework.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SentenceTest {

    Sentence sentence;

    @Before
    public void init() {
        Word noun = new Word("noun", Category.NOUN);
        Word verb = new Word("verb", Category.VERB);
        Word adjective = new Word("adjective", Category.ADJECTIVE);

        Date date = new Date();
        sentence = new Sentence(1l, date, noun, verb, adjective);
        assertEquals(date, sentence.getDateCreated());
    }

    @Test
    public void testText() {
        assertEquals("Noun verb adjective", sentence.getText());
    }
}
