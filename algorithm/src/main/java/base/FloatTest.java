package base;

public class FloatTest {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.75F)));// 0.11 1.1 X 2(-1)
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(8.25F)));// 1000.01 1.00001 X 2(3)
        //0 01111110 10000000000000000000000 1.1 X 2()
        //0 10000010 00001000000000000000000
        //0    1   1    1  1  1 1 0
        //1    0   0    0  0  0 1 0
        //128  64  32   16 8  4 2 1

        //0 0111111010 000000000000000000000
        //0 1000001000 001000000000000000000
    }
}
