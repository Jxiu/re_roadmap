import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * 详情参见 Pattern类的java doc
 * java 8 assert 启用需要vm option -ea 或 -enableassert
 * @author yangg
 * @date 20201205
 */
public class MetaCharacters {

    /** A garage is a good place to park a car.*/
    public static final String S_1 = "A garage is a good place to park a car.";
    /** The car parked in the garage.*/
    public static final String S_2 = "The car parked in the garage.";
    /** The fat cat sat on the mat.*/
    private static final String S_3 = "The fat cat sat on the mat.";
    /** The number was 9.9997 but we rounded it off to 10.0.*/
    public static final String N_1 = "The number was 9.9997 but we rounded it off to 10.0.";


    public static void main(String[] args) {
        // 统配
        fullSop();
        // 字符集
        charSet();
        // 匹配次数
        repetitions();
        // 特征标群--捕获组 由多个子表达式使用()组成 ()中的表达式可以被看做一个整体
        capturingGroups();
        // 转义字符 \反斜线 \ 在表达式中用于转码紧跟其后的字符。用于指定 { } [ ] / \ + * . $ ^ | ? 这些特殊字符。
        // 如果想要匹配这些特殊字符则要在其前面加上反斜线 \。
        assert Pattern.compile("(f|m|c)ar\\.").matcher(S_1).find();
        // 锚点
        anchors();
        // 标志 也叫模式修正符号
        flags();
        greedyVsLazyMatching();

    }

    /**
     * 贪婪与惰性匹配模式
     */
    private static void greedyVsLazyMatching() {
        //贪婪匹配与惰性匹配 正则表达式默认采用贪婪匹配模式，在该模式下意味着会匹配尽可能长的子串。
        Matcher m = Pattern.compile(".*at").matcher(S_3);
        assert m.find();
        System.out.println("贪婪匹配模式：" + m.group());
        // 使用?将贪婪匹配模式转换为惰性匹配
        m = Pattern.compile(".*?at").matcher(S_3);
        assert m.find();
        System.out.println("惰性匹配模式：" + m.group());
    }

    /**
     * 匹配模式
     */
    private static void flags() {
        // i 忽略大小写
        // g 全局搜索
        // m 多行修饰符：锚点元字符^$的工作范围在每行的起始
        assert Pattern.compile("the", Pattern.CASE_INSENSITIVE).matcher(N_1).find();
        String multiline = "The Fat \n" +
                "cat Sat \n" +
                "on the mat.";
        Matcher ma = Pattern.compile(".(at)", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE).matcher(multiline);
        System.out.println("多行匹配：");
        while (ma.find()){
            System.out.println(ma.group());
        }
    }

    /**
     * 锚点
     */
    private static void anchors() {
        // ^符号 检查匹配字符串是否在被匹配字符串的开头
        // $符号 检查匹配字符串是否在被匹配字符串的结尾
        Pattern.compile("^(T|t)he").matcher(S_2).find();
        Pattern.compile("car\\.$").matcher(S_1).find();
    }

    /**
     * 捕获组
     */
    private static void capturingGroups() {
        Matcher matcher;
        // c g s组成一个子表达式(一个整体)来匹配 car
        // | 或运算符 或运算符就表示或，用作判断条件。
        System.out.println("Pattern = (?:c|p|g)ar ");
        matcher = Pattern.compile("(c|p|g)*ar").matcher(S_2);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        // 非捕获组 ?: 和(...)类似，但不予分组或捕获。
        System.out.println("Pattern = (?:c|p|g)ar ");
        matcher = Pattern.compile("(?:c|p|g)ar").matcher(S_2);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        String npg = "car";
        assert Pattern.compile("(?:c|p|g)ar").matcher(npg).find();

        // 零宽度断言（前后预查）
        // 先行断言和后发断言都属于非捕获簇（不捕获文本 ，也不针对组合计进行计数）。
        // 先行断言用于判断所匹配的格式是否在另一个确定的格式之前，
        // 匹配结果不包含该确定格式（仅作为约束）。

        // ?=  正先行断言--存在 ?=... 正先行断言，表示第一部分表达式之后必须跟着 ?=...定义的表达式。
        // ?!  负先行断言--排除
        // ?<= 正后发断言--存在
        // ?<! 负后发断言--排除
        // /(?<name>Sally)/ 捕获组命名
        matcher = Pattern.compile("(T|t)he(?=\\sfat)").matcher(S_3);
        assert matcher.find();
        System.out.println(matcher.group()); // The 后面必须有但是不匹配

        matcher = Pattern.compile("(T|t)he(?!\\sfat)").matcher(S_3);
        assert matcher.find();
        System.out.println(matcher.group()); //the 后面的字母不匹配

        //正后发断言 记作(?<=...) 用于筛选所有匹配结果，筛选条件为 其前跟随着断言中定义的格式。
        // 例如，表达式 (?<=(T|t)he\s)(fat|mat) 匹配 fat 和 mat，且其前跟着 The 或 the。
        matcher = Pattern.compile("(?<=(T|t)he\\s)(fat|mat)").matcher(S_3);
        assert matcher.find();
        System.out.println("正后发断言: " + matcher.group());
        //负后发断言 记作 (?<!...) 用于筛选所有匹配结果，筛选条件为 其前不跟随着断言中定义的格式。
        // 例如，表达式 (?<!(T|t)he\s)(cat) 匹配 cat，且其前不跟着 The 或 the。
        matcher = Pattern.compile("(?<!(T|t)he\\s)cat").matcher("The cat sat on cat.");
        assert matcher.find();
        System.out.println("负后发断言: " + matcher.group());


    }

    /**
     * 匹配次数
     */
    private static void repetitions() {
        // * 配置0或多次
        assert Pattern.compile("[a-z]*").matcher(S_1).find();
        // + 匹配一次或多次
        assert Pattern.compile("[oo]+").matcher(S_1).find() : "there is oo in s1";
        // ? 匹配前面字符0次或一次
        assert Pattern.compile("s?park").matcher(S_1).find() : "not match s?park";
        // {n,m} 匹配最少n次 最多m次
        assert Pattern.compile("[o]{1,2}").matcher(S_1).find();
        // {n} 只匹配n次
        assert Pattern.compile("[o]{2}").matcher(S_1).find();
        // {n，} 匹配n次以上
        assert Pattern.compile("[o]{1,}").matcher(S_1).find();
    }


    /**
     * 字符集
     */
    private static void charSet() {
        // 字符集 []
        assert  Pattern.compile("[aA]").matcher(S_1).find() : "three is not have a A or a";
        // 否定字符集
        assert Pattern.matches("[^W]*", S_1) : "must not have a W";
        // 简写字符集
        // .匹配换行外所有字符
        // \w 所有字母数字 [0-9a-zA-Z]
        // \W 所有非字母数字字符 [^\w]
        // \d 所有数字字符 [0-9]
        // \D 所有非数字字符 [^\d]
        // \s 所有空白字符 匹配所有空格字符，等同于： [\t\n\f\r\p{Z}]
        // \S 所有非空白字符
        // \f 换页字符
        // \n 换行字符
        // \r 回车字符
        // \t 制表字符
        // \v 垂直制表字符
        // \p 匹配 CR/LF（等同于 \r\n），用来匹配 DOS 行终止符

    }


    /**
     * 统配字符 .
     */
    public static void fullSop() {
        String s = "The car parked in the garage.";
        assert Pattern.compile(".ar").matcher(s).find();
    }
}
