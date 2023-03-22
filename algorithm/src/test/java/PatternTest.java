import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://www.baeldung.com/java-regex-lookahead-lookbehind
 */
public class PatternTest {

    /**
     * 使用【正向后行断言】获取【命名捕获组】
     * <br/>注意matcher.matches()方法，是试图匹配整个字符串
     */
    @Test
    public void namedCapturingGroupsWithLookBehind(){
        String s = "AccessKeyId: xxxxxx,asdfjkhl";
        Pattern pattern = Pattern.compile("(?<=AccessKeyId: )(?<nameGroup>\\w+)");
        Matcher matcher = pattern.matcher(s);
        assertFalse(matcher.matches());
        assertTrue(matcher.find());
        assertEquals("xxxxxx",matcher.group("nameGroup"));
    }


    /**
     * 捕获组
     * <br/>就是从内到外的括号内包含字符串
     */
    @Test
    public void capturingGroups(){
        String P_COMM = "(\\d{4})-((\\d{2})-(\\d{2}))";
        String DATE_STRING = "2017-04-25";

        Pattern pattern = Pattern.compile(P_COMM);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();//必须要有这句
        assertEquals("2017-04-25", matcher.group(0));
        assertEquals("2017", matcher.group(1));
        assertEquals("04-25", matcher.group(2));
        assertEquals("04", matcher.group(3));
        assertEquals("25", matcher.group(4));
    }

    /**
     * 非捕获组
     * <br/>捕获组中排查(?:)开头的组
     */
    @Test
    public void nonCapturingGroups(){
        String P_UNCAP = "(?:\\d{4})-((\\d{2})-(\\d{2}))";
        String DATE_STRING = "2017-04-25";

        Pattern pattern = Pattern.compile(P_UNCAP);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();
        assertEquals("2017-04-25", matcher.group(0));
        assertEquals("04-25", matcher.group(1));
        assertEquals("04", matcher.group(2));
        assertEquals("25", matcher.group(3));
    }

    /**
     *(?=pattern) 正向先行断言
     * <br/> sth(?=pattern),匹配后面包含pattern的sth
     */
    @Test
    public void lookAhead(){
        Pattern pattern = Pattern.compile("import (?=static).+");

        Matcher matcher = pattern
                .matcher("import static org.junit.jupiter.api.Assertions.assertEquals;");
        assertTrue(matcher.find());
        Assertions.assertEquals("import static org.junit.jupiter.api.Assertions.assertEquals;", matcher.group());

        Assertions.assertFalse(pattern.matcher("import java.util.regex.Matcher;").find());
    }

    /**
     * (?!pattern) 负向先行断言
     * <br/>sth(?=pattern),匹配后面不包含pattern的sth
     */
    @Test
    public void NegativeLookAhead(){
        Pattern pattern = Pattern.compile("import (?!static).+");

        Matcher matcher = pattern
                .matcher("import org.junit.jupiter.api.Assertions.assertEquals;");
        assertTrue(matcher.find());
        Assertions.assertEquals("import org.junit.jupiter.api.Assertions.assertEquals;", matcher.group());

        Assertions.assertFalse(pattern.matcher("import static java.util.regex.Matcher;").find());
    }

    /**
     * (?<=pattern) 正向后行断言
     * (?<=pattern)sth,匹配前面包含pattern的sth
     */
    @Test
    public void lookBehind(){
        Pattern pattern = Pattern.compile(".*(?<=jupiter).*assertEquals;");

        Matcher matcher = pattern
                .matcher("import static org.junit.jupiter.api.Assertions.assertEquals;");
        assertTrue(matcher.find());
        assertEquals("import static org.junit.jupiter.api.Assertions.assertEquals;", matcher.group());

        assertFalse(pattern.matcher("import static org.junit.Assert.assertEquals;").find());
    }

    /**
     * (?<=pattern) 负向后行断言
     * (?<!pattern)sth,匹配前面不包含pattern的sth
     */
    @Test
    public void NegativeLookBehind(){
        Pattern pattern = Pattern.compile(".*(?<!jupiter.{0,30})assertEquals;");

        Matcher matcher = pattern.matcher("import static org.junit.Assert.assertEquals;");
        assertTrue(matcher.find());
        assertEquals("import static org.junit.Assert.assertEquals;", matcher.group());

        assertFalse(pattern
                .matcher("import static org.junit.jupiter.api.Assertions.assertEquals;").find());
    }
}
