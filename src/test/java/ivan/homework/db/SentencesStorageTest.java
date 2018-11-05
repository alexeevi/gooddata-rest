package ivan.homework.db;

import ivan.homework.api.Category;
import ivan.homework.api.Sentence;
import ivan.homework.api.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentencesStorageTest {

    private SentencesStorage storage;

    @Before
    public void init() {
        WordsStorage wordsStorage = new WordsStorage();
        wordsStorage.add(new Word("noun", Category.NOUN));
        wordsStorage.add(new Word("verb", Category.VERB));
        wordsStorage.add(new Word("adjective", Category.ADJECTIVE));

        storage = new SentencesStorage(wordsStorage);
    }

    @Test
    public void testAdd() {
        assertEquals("Noun verb adjective", storage.add().getText());
        assertEquals(1, storage.getAll().size());
    }

    @Test
    public void testGetByKey() {
        Sentence sentence = storage.add();
        assertEquals(0, sentence.getDisplayCount().longValue());
        sentence = storage.getByKey(sentence.getId());
        assertEquals(1, sentence.getDisplayCount().longValue());
    }

    @Test
    public void testGetByUnknownKey() {
        assertNull(storage.getByKey(10));
    }

    @Test
    public void testReGenerate() {
        Sentence sentence = storage.add();
        assertEquals(0, sentence.getReGenerateCount().longValue());
        sentence = storage.add();
        assertEquals(1, sentence.getReGenerateCount().longValue());
    }

    @Test
    public void testNotEnoughWords() {
        WordsStorage wordsStorage = new WordsStorage();
        wordsStorage.add(new Word("noun", Category.NOUN));
        storage = new SentencesStorage(wordsStorage);
        assertNull(storage.add(null));
    }

}
