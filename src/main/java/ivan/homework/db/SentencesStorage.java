package ivan.homework.db;

import ivan.homework.api.Category;
import ivan.homework.api.Sentence;
import ivan.homework.api.Word;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Stores dictionary of sentences.
 * Generates new sentences on demand.
 */
public class SentencesStorage implements Storage<Sentence> {

    private AtomicLong sequence = new AtomicLong();
    private Map<Long, Sentence> mapOfSentences = Collections.synchronizedMap(new HashMap<>());
    private Map<String, Sentence> mapOfTexts = Collections.synchronizedMap(new HashMap<>());

    private WordsStorage wordsStorage;

    public SentencesStorage(WordsStorage wordsStorage) {
        this.wordsStorage = wordsStorage;
    }

    @Override
    public Sentence add(Sentence object) {
        return add();
    }

    public Sentence add() {
        Word noun = wordsStorage.getRandomOf(Category.NOUN);
        Word verb = wordsStorage.getRandomOf(Category.VERB);
        Word adjective = wordsStorage.getRandomOf(Category.ADJECTIVE);
        if (noun == null || verb == null || adjective == null) {
            return null;
        }

        Long id = sequence.incrementAndGet();
        Sentence sentence = new Sentence(id, new Date(), noun, verb, adjective);
        String text = sentence.getText();
        synchronized (this) {
            Sentence existentSentence = mapOfTexts.get(text);
            if (existentSentence != null) {
                existentSentence.incrementReGenerateCount();
                sentence = existentSentence;
            } else {
                mapOfSentences.put(id, sentence);
                mapOfTexts.put(text, sentence);
            }
        }
        return sentence;
    }

    @Override
    public Collection<Sentence> getAll() {
        return Collections.unmodifiableCollection(mapOfSentences.values());
    }

    @Override
    public Sentence getByKey(Object key) {
        Sentence sentence = mapOfSentences.get(key);
        if (sentence != null) {
            sentence.incrementDisplayCount();
        }
        return sentence;
    }
}
