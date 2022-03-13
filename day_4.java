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

    // 1. Buy And Sell Stocks - One Transaction Allowed
    public static int oneTransactionAllowed(int[] arr, int n) {
        int leastPrice = (int)1e9;
        int maxProfit = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < leastPrice){
                leastPrice = arr[i];
            }

            int todaysProfit = arr[i] - leastPrice;
            if(todaysProfit > maxProfit){
                maxProfit = todaysProfit;
            }
        }
        return maxProfit;
    }

    // 2. Infinite Transaction Allowed
    public static int infiniteTransactionAllowed_myAns(int[] arr, int n) {
        int leastPrice = (int)1e9;
        int currentProfit = 0;
        int totalProfit = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < leastPrice){
                leastPrice = arr[i];
            }

            int todaysProfit = arr[i] - leastPrice;
            if(currentProfit > todaysProfit){
                totalProfit += currentProfit;
                currentProfit = 0;
                leastPrice = arr[i];
            } else {
                currentProfit = todaysProfit;
            }
            
        }


        return totalProfit;
    }

    public static int infiniteTransactionAllowed_sirAns(int[] arr, int n) {
        int bd = 0, sd = 0, profit = 0;

        for (int i = 1; i < arr.length; i++) {
           if(arr[i] >= arr[i - 1]){
            sd++;
           } else {
            profit += arr[sd] - arr[bd];
            bd = sd = i;
           }
        }
        profit += arr[sd] - arr[bd];
        return profit;
    }

    //   3. With Transaction Fee and Infinite Transaction Allowed
    public static int withTransactionFeeInfiniteTransaction(int[] arr, int n, int fee) {
        int[][] dp = new int[n][2];

        dp[0][0] = -arr[0];
        dp[0][1] = 0;

        for(int i = 1; i < n; i++){
            if(dp[i - 1][0] < dp[i - 1][1] - arr[i]){
                dp[i][0] = dp[i - 1][1] - arr[i];
            } else {
                dp[i][0] = dp[i - 1][0];
            }

            if(dp[i - 1][1] < dp[i - 1][0] + arr[i] - fee){
                dp[i][1] = dp[i - 1][0] + arr[i] - fee;
            } else {
                dp[i][1] = dp[i - 1][1] ;
            }
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    
    }

    //4. With Cool Down and Infinite Transaction Allowed
    public static int withCoolDownFeeInfiniteTransaction(int[] arr, int n) {
        int[][] dp = new int[n][3];

        dp[0][0] = -arr[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < dp.length; i++) {
            if(dp[i - 1][0] < dp[i - 1][2] - arr[i]){
                dp[i][0] = dp[i - 1][2] - arr[i];
            } else {
                dp[i][0] = dp[i - 1][0];
            }

            if(dp[i - 1][1] < dp[i - 1][0] + arr[i]){
                dp[i][1] = dp[i - 1][0] + arr[i];
            } else {
                dp[i][1] = dp[i - 1][1];
            }

            if(dp[i - 1][2] < dp[i - 1][1]){
                dp[i][2] = dp[i - 1][1];
            } else {
                dp[i][2] = dp[i - 1][2];
            }
        }

        return Math.max(dp[n - 1][0], Math.max(dp[n - 1][2], dp[n - 1][1]));
    }

    // 5. Two Transaction Allowed
    public static int twoTransactionAllowed(int[] arr, int n) {
        /**
         * Note: 
         * 1) Ek baari Left se traverse krte hue maximum profit till that particular index store krvao
         *      1.1) For a particular index selling is mandatory yeh socho
         * 
         * 2) Ek baari Right se traverse krte hue maximum profit before that particular index store krvao
         *      2.1) For a particular index buying is mandatory yeh socho
         */
        
        int least = arr[0];
        int[] dpL = new int[n];

        for(int i = 1; i < n; i++){
            least = arr[i] < least ? arr[i] : least;  
            dpL[i] = (dpL[i - 1] < (arr[i] - least)) ? arr[i] - least : dpL[i - 1]; 
        }

        int max = arr[n - 1];
        int[] dpR = new int[n];

        for(int i = n - 2; i >= 0; i--){
            max = arr[i] > max ? arr[i] : max;  
            dpR[i] = (dpR[i + 1] < (max - arr[i])) ? max - arr[i] : dpR[i + 1]; 
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dpR[i] + dpL[i]);
        }

        return ans;
    
        
    }

    // 6. K Transaction Allowed 
    public static int kTransactionAllowed(int[] arr, int n, int noOfTransactions) {
        if(arr.length == 0)
            return 0;
        int[][] dp = new int[noOfTransactions + 1][n];

        // Isme Optimization lg skti hai aur third loop hta skte hai 
        // for (int t = 1; t <= noOfTransactions; t++) {
        //     for (int d = 1; d < n; d++) {
        //         dp[t][d] = dp[t][d - 1];

        //         for (int k = 0; k < d; k++) {
        //             dp[t][d] = dp[t][d] < dp[t - 1][k] + arr[d] - arr[k] ? 
        //                                     dp[t - 1][k] + arr[d] - arr[k] 
        //                                     : dp[t][d];        
        //         }
        //     }
        // }

        for (int t = 1; t <= noOfTransactions; t++) {
            int max = -(int)1e9;

            for (int d = 1; d < n; d++) {
                if(dp[t - 1][d - 1] - arr[d - 1] > max){
                    max = dp[t - 1][d - 1] - arr[d - 1];
                }

                if(max + arr[d] > dp[t][d - 1]){
                    dp[t][d] = max + arr[d];
                } else {
                    dp[t][d] = dp[t][d - 1];
                }
            }
        }



        return dp[noOfTransactions][n - 1];
    }


    public static void buyAndSell() {
        int n = scn.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        // System.out.println(oneTransactionAllowed(arr, n));
        System.out.println(infiniteTransactionAllowed_myAns(arr, n));
        // System.out.println(infiniteTransactionAllowed_sirAns(arr, n));
    }

    public static void buyAndSellWithFee() {
        int n = scn.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int fee = scn.nextInt();

        System.out.println(withTransactionFeeInfiniteTransaction(arr, n, fee));
    }


    public static void main(String[] args) {
        // buyAndSell();
    }

}
