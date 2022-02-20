package lc101.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LN455Test {


    @ParameterizedTest
    @MethodSource("findContentChildrenParameter")
    public void findContentChildrenTest(int[] child, int[] cookie, int expect){
        int num = LN455.findContentChildren(child,cookie);
        Assertions.assertEquals(expect, num);
    }

    public static Stream<Arguments> findContentChildrenParameter(){
        return Stream.of(Arguments.of(new int[]{1,2}, new int[]{1,2,3},2),
                Arguments.of(new int[]{1,2,3}, new int[]{1,2},2)
        );
    }

}