public class StringTest {

    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "Hel" + "lo";
        String s4 = "Hel" + new String("lo");
        String s5 = new String("Hello");
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;
        final String s10 = "H";
        String s11 = s10 + "ello";
        String s12 = getH() + "ello";
        final String s13 = getH();
        String s14 = s13 + "ello";

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s1 == s9);
        System.out.println(s1 == s11);
        System.out.println(s1 == s12);
        System.out.println(s4 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s14);
        String s = "1";
        System.out.println(toString(s));;
        System.out.println(test());;
    }

    public static String getH(){
        return "H";
    }


    public static String toString(String str) {
        int x = 0;
        try {
            x++;
            if (str == ""){
                throw new IllegalArgumentException();
            }
        }catch (Exception e){
            x = 5;
            return  String.valueOf(x);
        }finally {
            x++;
        }
        return  String.valueOf(x);
    }

    public static int test(){
        int i = 0;
        try{
            i=1;
            return i;
        }catch (Exception e){
            i=3;
            return i;
        }finally {
            i++;
        }
    }
}
