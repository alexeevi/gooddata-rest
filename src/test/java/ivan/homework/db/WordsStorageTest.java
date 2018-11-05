package ivan.homework.db;

import ivan.homework.api.Category;
import ivan.homework.api.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordsStorageTest {

    private WordsStorage storage;

    @Before
    public void init() {
        storage = new WordsStorage();
        storage.add(new Word("noun", Category.NOUN));
        storage.add(new Word("verb", Category.VERB));
    }

    @Test
    public void testAddSameWord() {
        storage.add(new Word("noun", Category.NOUN));
        assertEquals(2, storage.getAll().size());
    }

    @Test
    public void testAddSameTextButDifferentCategory() {
        storage.add(new Word("noun", Category.VERB));
        assertEquals(3, storage.getAll().size());
    }

    @Test
    public void testAddDifferent() {
        storage.add(new Word("adjective", Category.ADJECTIVE));
        assertEquals(3, storage.getAll().size());
    }

    @Test
    public void testGetByKey() {
        Word word = storage.getByKey("noun");
        assertEquals("noun", word.getWord());
        assertEquals(Category.NOUN, word.getCategory());
    }

    @Test
    public void testGetRandomFromEmpty() {
        assertNull(storage.getRandomOf(Category.ADJECTIVE));
    }

    @Test
    public void testGetRandomFromSingle() {
        Word word = storage.getRandomOf(Category.NOUN);
        assertNotNull(word);
        assertEquals("noun", word.getWord());
        assertEquals(Category.NOUN, word.getCategory());
    }
}
