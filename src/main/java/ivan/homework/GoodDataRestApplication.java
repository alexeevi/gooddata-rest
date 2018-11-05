package ivan.homework;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import ivan.homework.db.SentencesStorage;
import ivan.homework.db.WordsStorage;
import ivan.homework.resources.SentenceResource;
import ivan.homework.resources.WordsResource;

public class GoodDataRestApplication extends Application<GoodDataRestConfiguration> {

    private WordsStorage wordsStorage = new WordsStorage();
    private SentencesStorage sentencesStorage = new SentencesStorage(wordsStorage);

    public static void main(final String[] args) throws Exception {
        new GoodDataRestApplication().run(args);
    }

    @Override
    public String getName() {
        return "GoodData REST API";
    }

    @Override
    public void run(final GoodDataRestConfiguration configuration,
                    final Environment environment) {
        final WordsResource wordsResource = new WordsResource(wordsStorage);
        final SentenceResource sentenceResource = new SentenceResource(sentencesStorage);

        environment.jersey().register(wordsResource);
        environment.jersey().register(sentenceResource);
    }

}
