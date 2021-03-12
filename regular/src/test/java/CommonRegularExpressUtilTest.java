import java.util.regex.Pattern;

/**
 * @author jxiu
 * @date 2020/12/28
 */
class CommonRegularExpressUtilTest {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("^[2-9]|([1-9]\\d)$");
        p.matcher("2").matches();
        System.out.println(p.matcher("20").find());

    }
}