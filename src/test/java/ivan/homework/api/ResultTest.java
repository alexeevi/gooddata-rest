package ivan.homework.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {

    @Test
    public void testResult() {
        String data = new String("data");
        Result<String> result = new Result<>(data);
        assertEquals(data, result.getResult());
    }
}
