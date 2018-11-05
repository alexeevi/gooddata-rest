package ivan.homework.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A sentence consisting of one noun, one verb and one adjective.
 */
public class Sentence {
    protected long id;
    protected AtomicLong displayCount;
    protected AtomicLong reGenerateCount;
    protected Date dateCreated;
    protected Word noun;
    protected Word verb;
    protected Word adjective;

    public Sentence() {
    }

    public Sentence(long id, Date dateCreated, Word noun, Word verb, Word adjective) {
        this.id = id;
        this.displayCount = new AtomicLong();
        this.reGenerateCount = new AtomicLong();
        this.dateCreated = dateCreated;
        this.noun = noun;
        this.verb = verb;
        this.adjective = adjective;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getText() {
        return String.format("%s %s %s", capitalize(noun.getWord()), verb.getWord(), adjective.getWord());
    }

    @JsonProperty
    public Long getDisplayCount() {
        return displayCount.get();
    }

    @JsonProperty
    public Long getReGenerateCount() {
        return reGenerateCount.get();
    }

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void incrementDisplayCount() {
        displayCount.incrementAndGet();
    }

    public void incrementReGenerateCount() {
        reGenerateCount.incrementAndGet();
    }

    protected String capitalize(String input) {
        return input.length() > 1 ? getCapitalLetter(input) + input.substring(1) : getCapitalLetter(input);
    }

    private String getCapitalLetter(String input) {
        return input.substring(0, 1).toUpperCase();
    }
}
