package lc101.string;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KmpTest {

    @ParameterizedTest
    @MethodSource("kmpTestProvider")
    public void test(String str, String match, int pidx){
//        String str = "CBC DCABCABABCABD BBCCA";
//        String match = "ABCABD";
        int index = Kmp.stringSearch(str, match);
        System.out.printf("%s 在 %s 中的位置为 %d", match, str, index);
        assertEquals(index, pidx);
    }

    public static Stream<Arguments> kmpTestProvider(){
        return Stream.of(
                Arguments.of("CBC DCABCABABCABD BBCCA", "ABCABD", 11),
                Arguments.of("CBC DCABCABABCAvBD BBCCA", "ABCABD", -1));
    }

}