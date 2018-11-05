package ivan.homework.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static ivan.homework.api.Category.NOUN;

/**
 * A word with its text representation and category.
 * Non-noun words are stored in all lower case.
 */
public class Word {
    private String word;
    private Category category;

    public Word(String word, Category category) {
        this.category = category;
        this.word = NOUN.equals(category) ? word : word.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word) &&
                category == word1.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, category);
    }

    @JsonProperty
    public String getWord() {
        return word;
    }

    @JsonProperty
    public Category getCategory() {
        return category;
    }
}
