import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ShoppingTest {
    static Map<String,Integer> map = new HashMap<>();
    ThreadLocal<Integer> i;

    static String data = "1. 4—1401 一份\n" +
            "2. 5-2004 一份\n" +
            "3. 4-303 一份\n" +
            "4. 4-302 一份\n" +
            "5. 4号2001 一份\n" +
            "6. 4-502一份\n" +
            "7. 4-1102老戴 一份\n" +
            "8. 6-1602 一份\n" +
            "9. 1-204 一份\n" +
            "10. 2-1304一份\n" +
            "11. 1-801 一份\n" +
            "12. 5-704 一份\n" +
            "13. 4一1802一份\n" +
            "14. 小微4-2003 一份\n" +
            "15. 4-1604 一份\n" +
            "16. 4-1603一份\n" +
            "17. 5-1201一份\n" +
            "18. 1-1204一份\n" +
            "19. 4-503 一份\n" +
            "20. 1-804  二份\n" +
            "21. 墨然 4—2002一份\n" +
            "22. 4-702 一份\n" +
            "23. 4-604一份\n" +
            "24. 4-1702*1份\n" +
            "25. 4-2101一份\n" +
            "26. 4-2004 一份\n" +
            "27. 4-2204 一份\n" +
            "28. 孙慧英 4一1903一份\n" +
            "29. 1-301 一份\n" +
            "30. 4-1402 一份\n" +
            "31. 4-1001 1份\n" +
            "32. 4-1503 1份\n" +
            "33. 1-101 1份\n" +
            "34. 4-1003  1份\n" +
            "35. 鱼跃龙门 1-1503 1份\n" +
            "36. 1-504 陆 1份\n" +
            "37. 强 1—1501一份\n" +
            "38. 火狼 2-402 一份\n" +
            "39. 2-801 一份\n" +
            "40. 6-2203 一份\n" +
            "41.4号楼老人一份";

    static String NUMBER = "[\\d|一|二|三|四|五|六|七|八|九]";
    @Test
    public void group(){
        Arrays.stream(data.split("\n")).map(s ->{
            Pattern pattern = Pattern.compile("\\d+\\D\\d");
            Matcher matcher = pattern.matcher(s);
            Order order = new Order();
            order.setOrderLine(s);
            order.setNum(0);


            if (matcher.find()){
                String[] locates = matcher.group().split("\\D");
                order.setFloor(locates[0]);
                order.setRom(locates[1]);
            }else {
                order.setFloor("-");
                order.setRom("-");
            }
            Pattern pattern1 = Pattern.compile(NUMBER+"份");
            Matcher matcher1 = pattern1.matcher(s);
            if (matcher1.find()){
                String nc =  matcher1.group();
                String num = nc.substring(0,nc.length()-1);
                order.setNum(convert(num));
            }else {
                order.setNum(0);

            }
            return order;
        }).collect(groupingBy(Order::getFloor, mapping(Function.identity(),toList())));
    }

    private Integer convert(String num) {
        if (map.isEmpty()){
            map.put("一",1);
            map.put("二",2);
            map.put("三",3);
            map.put("四",4);
            map.put("五",5);
            map.put("六",6);
            map.put("七",7);
            map.put("八",8);
            map.put("九",9);
            map.put("十",10);
            map.put("1",1);
            map.put("2",2);
            map.put("3",3);
            map.put("4",4);
            map.put("5",5);
            map.put("6",6);
            map.put("7",7);
            map.put("8",8);
            map.put("9",9);
        }
        return map.getOrDefault(num,0);
    }


    public static class Order{
        String orderLine;
        String floor;
        String rom;
        Integer num;

        public String getOrderLine() {
            return orderLine;
        }

        public void setOrderLine(String orderLine) {
            this.orderLine = orderLine;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getRom() {
            return rom;
        }

        public void setRom(String rom) {
            this.rom = rom;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderLine='" + orderLine + '\'' +
                    ", floor='" + floor + '\'' +
                    ", rom='" + rom + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    @Test
    public void pa(){
        String s = "1号1503";
        String s2 = "38. 火狼 2-402 一份";
        Pattern pattern = Pattern.compile("\\d+\\D\\d+");
        Matcher matcher = pattern.matcher(s2);
        if (matcher.find()){
            System.out.println(Arrays.toString(matcher.group().split("\\D")));
        }else {
            System.out.println(111);

        }

        Pattern pattern1 = Pattern.compile(NUMBER+"份");
        Matcher matcher1 = pattern1.matcher(s2);
        if (matcher1.find()){
            System.out.println(matcher1.group());
        }else {
            System.out.println(22);

        }

    }


}
