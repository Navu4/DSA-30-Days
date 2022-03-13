import java.util.Scanner;

public class day_5 {


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
     * Print Path using Reverse Engineering
     * 1. Print all path in min cost
     * 2. Print all path with max gold
     * 3. Print all path with minimum jumps
     * 4. Print Subsets with Target Sum 
     * 5. Paths in 0-1 knapsack
     *  
     * Additional Questions
     * 6. Largest Square Submatrix of all 1's
     * 7. 2 Keys Keyboard
     */

    public static void main(String[] args) {
        
    }
}
