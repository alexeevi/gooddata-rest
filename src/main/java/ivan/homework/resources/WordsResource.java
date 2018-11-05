package ivan.homework.resources;

import ivan.homework.api.Result;
import ivan.homework.api.Word;
import ivan.homework.api.Category;
import ivan.homework.db.Storage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
public class WordsResource {

    final private Storage<Word> storage;

    public WordsResource(Storage<Word> storage) {
        this.storage = storage;
    }

    @GET
    public Result<Collection<Word>> getAll() {
        return new Result<>(storage.getAll());
    }

    @GET
    @Path("/{word}")
    public Result<Word> getOne(@PathParam("word") String wordString) {
        Word word = storage.getByKey(wordString);
        if (word == null) {
            throw new NotFoundException("Word not found");
        }
        return new Result<>(word);
    }

    @POST
    @Path("/{category}/{word}")
    public Result<Word> addOne(@PathParam("category") String category, @PathParam("word") String wordString) {
        Category wordCategory;
        try {
            wordCategory = Category.valueOf(category.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Unknown category " + category);
        }
        Word word = new Word(wordString.trim(), wordCategory);
        storage.add(word);
        return new Result<>(word);
    }
}
