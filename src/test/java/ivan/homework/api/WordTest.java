package ivan.homework.api;

import org.junit.Test;

import static ivan.homework.api.Category.*;
import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void shouldIgnoreCaseForNonNoun() {
        Word word = new Word("WOrd", ADJECTIVE);
        assertEquals("word", word.getWord());
        assertEquals(ADJECTIVE, word.getCategory());

        word = new Word("WOrd", VERB);
        assertEquals("word", word.getWord());
        assertEquals(VERB, word.getCategory());
    }

    @Test
    public void shouldKeepCaseForNoun() {
        Word word = new Word("WOrd", NOUN);
        assertEquals("WOrd", word.getWord());
        assertEquals(NOUN, word.getCategory());
    }

    @Test
    public void testEquals() {
        assertNotEquals(new Word("word", NOUN), new Word("word", VERB));
        assertNotEquals(new Word("abcd", NOUN), new Word("bcd", NOUN));
        assertEquals(new Word("abcd", NOUN), new Word("abcd", NOUN));
    }
}
