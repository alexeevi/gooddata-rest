package ivan.homework.api;

/**
 * A sentence with Yoda order fo words derived from existing sentence
 */
public class YodaSentence extends Sentence {

    public YodaSentence(Sentence sentence) {
        this.id = sentence.id;
        this.dateCreated = sentence.dateCreated;
        this.displayCount = sentence.displayCount;
        this.reGenerateCount = sentence.reGenerateCount;
        this.noun = sentence.noun;
        this.verb = sentence.verb;
        this.adjective = sentence.adjective;
    }

    @Override
    public String getText() {
        return String.format("%s %s %s", capitalize(adjective.getWord()), noun.getWord(), verb.getWord());
    }
}
