package ivan.homework.resources;

import ivan.homework.api.Result;
import ivan.homework.api.Sentence;
import ivan.homework.api.YodaSentence;
import ivan.homework.db.Storage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/sentences")
@Produces(MediaType.APPLICATION_JSON)
public class SentenceResource {

    final private Storage<Sentence> storage;

    public SentenceResource(Storage<Sentence> storage) {
        this.storage = storage;
    }

    @GET
    public Result<Collection<Sentence>> getAll() {
        return new Result<>(storage.getAll());
    }

    @POST
    @Path("/generate")
    public Result<Sentence> generate() {
        Sentence sentence = storage.add(null);
        if (sentence == null) {
            throw new BadRequestException("Not enough words for generating complete sentence");
        }
        return new Result<>(sentence);
    }

    @GET
    @Path("/{id}")
    public Result<Sentence> getOne(@PathParam("id") Long id) {
        return new Result<>(findSentence(id));
    }

    @GET
    @Path("/{id}/yodaTalk")
    public Result<Sentence> getYoda(@PathParam("id") Long id) {
        return new Result<>(new YodaSentence(findSentence(id)));
    }

    private Sentence findSentence(Long id) {
        Sentence sentence = storage.getByKey(id);
        if (sentence == null) {
            throw new NotFoundException("Sentence not found");
        }
        return sentence;
    }
}
