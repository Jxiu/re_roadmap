import com.sun.istack.internal.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用的正则表达式工具
 * @author jxiu
 * @date 2020/12/28
 */
public class CommonRegularExpressUtil {
    /**
     * email正则
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[0-9a-zA-Z_-\\u4e00-\\u9fa5]+@[0-9a-zA-Z]]+(\\.[0-9a-zA-Z_-]+)+$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^1(3|4|5|6|7|8|9)\\d{9}$");
    /**
     * 固定电话正则
     * XXXX-XXXXXXXXX
     * (XXXX)-XXXXXXXXX
     */
    public static final Pattern LAND_LINE_PATTERN = Pattern.compile("(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}");
    /**
     * 域名正则
     */
    public static final Pattern DOMAIN_NAME_PATTERN = Pattern.compile("^((http|https):\\/\\/)?(\\w([\\w|\\\\|-]){0,61}[\\w]?\\\\.)+[a-zA-Z]{2,6}(\\/)");

    public static final Pattern IP_PATTERN = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))");

    public static boolean isIP(String ip){
        return IP_PATTERN.matcher(ip).matches();
    }



    /**
     * 检验是否为域名
     * @param domain
     * @return
     */
    public static boolean isDomainName(String domain){
        return DOMAIN_NAME_PATTERN.matcher(domain).matches();
    }


    /**
     * 是否为固定电话
     * @param landLine
     * @return
     */
    public static  boolean isLandLine(@NotNull String landLine){
        return LAND_LINE_PATTERN.matcher(landLine).matches();
    }

    /**
     * 是否是email
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * 是否为手机号码
     * @param phone
     * @return
     */
    public static boolean isPhoneNumber(String phone){
        return PHONE_PATTERN.matcher(phone).matches();
    }



    private CommonRegularExpressUtil() {
    }
}
