
import java.util.Scanner;

public class spiralOrder {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();

        char[][] arr = new char[n][m];
        for (char[] a : arr) {
            for (int i = 0; i < a.length; i++) {
                a[i] = scn.next().charAt(0);
            }
        }

        spiralOrderTraversal(arr);
    }

    public static void spiralOrderTraversal(char[][] arr) {
        int minr = -1, minc = -1, maxr = arr.length, maxc = arr[0].length;

        int i = 0, j = 0;

        // L, R, D, U
        char dir = 'R';
        int count = maxr * maxc;

        while (count != 0) { // condition

            if (j == maxc) {
                dir = 'D';
                minr += 1;

                i++;
                j--;
                continue;
            } else if (i == maxr) {
                dir = 'L';
                maxc -= 1;

                i--;
                j--;
                continue;
            } else if (j == minc) {
                dir = 'U';
                maxr -= 1;

                i--;
                j++;
                continue;
            } else if (i == minr) {
                dir = 'R';

                j++;
                i++;
                continue;
            }

            System.out.print(arr[i][j] + " "); // Console
            count--;
            switch (dir) {
                case 'R':
                    j++;
                    break;
                case 'D':
                    i++;
                    break;
                case 'L':
                    j--;
                    break;
                case 'U':
                    i--;
                    break;

                default:
                    break;
            }

        }

    }
}
