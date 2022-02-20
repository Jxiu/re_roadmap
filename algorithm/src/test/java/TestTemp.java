import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class TestTemp {

    @Test
    public void testSum(){
        int n = 2;
        System.out.println(IntStream.range(1, n).reduce(Integer::sum).getAsInt());
    }
}
