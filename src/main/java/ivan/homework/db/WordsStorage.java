package ivan.homework.db;

import ivan.homework.api.Category;
import ivan.homework.api.Word;

import java.util.*;

/**
 * Stores dictionary of words.
 * Allows random picking a word of given category.
 */
public class WordsStorage implements Storage<Word> {

    private Set<Word> setOfWordWithCategory = Collections.synchronizedSet(new HashSet<>());
    private Map<String, Word> mapStringToWord = Collections.synchronizedMap(new HashMap<>());
    private List<Word> nouns = Collections.synchronizedList(new ArrayList<>());
    private List<Word> verbs = Collections.synchronizedList(new ArrayList<>());
    private List<Word> adjectives = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Word add(Word word) {
        if (setOfWordWithCategory.add(word)) {
            List<Word> list = getCategoryArray(word.getCategory());
            if (list != null) {
                list.add(word);
                mapStringToWord.putIfAbsent(word.getWord(), word);
            }
        }
        return word;
    }

    @Override
    public Collection<Word> getAll() {
        return Collections.unmodifiableSet(setOfWordWithCategory);
    }

    @Override
    public Word getByKey(Object key) {
        return mapStringToWord.get(key);
    }

    public Word getRandomOf(Category category) {
        List<Word> list = getCategoryArray(category);
        if (list != null && !list.isEmpty()) {
            return list.get(Double.valueOf(Math.floor(Math.random() * list.size())).intValue());
        }
        return null;
    }

    private List<Word> getCategoryArray(Category category) {
        switch (category) {
            case NOUN: return nouns;
            case VERB: return verbs;
            case ADJECTIVE: return adjectives;
        }
        return null;
    }
}
