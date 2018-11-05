package ivan.homework.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper for creating JSON response with root field
 * @param <T>
 */
public class Result<T> {
    private T data;

    public Result(T data) {
        this.data = data;
    }

    @JsonProperty
    public T getResult() {
        return data;
    }
}
