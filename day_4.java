import java.util.Scanner;

public class day_4 {

    public static Scanner scn = new Scanner(System.in);
    public static void printArr(int[] arr){
        for(int ele : arr){
            System.out.print(ele + " ");
        }
        System.out.println();
        
    }
    public static void print2Arr(int[][] arr){
        for (int[] a : arr) {
            printArr(a);
        }
        System.out.println();
    }

    public static void print2ArrBool(boolean[][] arr){
        for (boolean[] a : arr) {
            for (boolean b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 
     * Set 4 Buy and Sell
        1. One Transaction Allowed
        2. Infinite Transaction Allowed
        3. With Transaction Fee and Infinite Transaction Allowed
        4. With Cool Down and Infinite Transaction Allowed
        5. Two Transaction Allowed
        6. K Transaction Allowed 
    */



    public static void main(String[] args) {
        
    }

}
