package datastruct;

import java.util.Arrays;

public class A {

    public static void main(String[] args) {
        int[]  arr = new int[]{2,9,4,5,5};
        int cont =arr.length;

        for(int i=0;i<cont ;i++)
        {
            for(int j=i;j<cont;j++)
            {
                if(arr[i]>arr[j])
                {
                    int x= arr[j];
                    arr[j]=arr[i];
                    arr[i]=x;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public  static class B{
//        public static void main(String[] args) {
//            int[] arr = new int[]{2,3,4,5,5};
//            int cont =arr.length;
//            for{int i=0;i<cont ;i++} {
//                for(int j=i;j<cont;j++) {
//                    if(arr[i]>arr[ j]) {
//                        int x= arr[ j]; arr[ j]=arr[i]; arr[i]=x;
//                    } }
//            }
    }
}
