package interview;

import java.util.Arrays;
import java.util.List;

public class dpInterviewPrep {

    // 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs/submissions/
    public int climStairs_(int n, int[] dp) {
        if (n <= 0) {
            return n == 0 ? 1 : 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int count = 0;
        count += climStairs_(n - 1, dp);
        count += climStairs_(n - 2, dp);

        return dp[n] = count;
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return climStairs_(n, dp);
    }

    // 746. Min Cost Climbing Stairs
    // https://leetcode.com/problems/min-cost-climbing-stairs/submissions/
    public int minCostClimbingStairs(int[] cost, int n, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];

        if (dp[n] != -1)
            return dp[n];

        return dp[n] = Math.min(minCostClimbingStairs(cost, n - 1, dp), minCostClimbingStairs(cost, n - 2, dp))
                + (n != cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return minCostClimbingStairs(cost, n, dp);
    }

    // 198. House Robber
    // https://leetcode.com/problems/house-robber/
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;

        // Size of dp re-think
        int[] inc = new int[n];
        int[] exc = new int[n];

        inc[0] = nums[0];
        exc[0] = 0;

        for (int i = 1; i < n; i++) {
            int including = exc[i - 1] + nums[i];
            int excluding = Math.max(exc[i - 1], inc[i - 1]);

            exc[i] = excluding;
            inc[i] = including;
        }

        return Math.max(exc[n - 1], inc[n - 1]);
    }

    // 213. House Robber II
    // https://leetcode.com/problems/house-robber-ii/
    public int robII(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        if (n == 1)
            return nums[0];

        int includingFromStarting = nums[0];
        int excludingFromStarting = 0;
        for (int i = 1; i < n - 1; i++) {
            int inc = excludingFromStarting + nums[i];
            int exc = Math.max(includingFromStarting, excludingFromStarting);

            includingFromStarting = inc;
            excludingFromStarting = exc;
        }

        int includingFromEnd = nums[1];
        int excludingFromEnd = 0;
        for (int i = 2; i < n; i++) {
            int inc = excludingFromEnd + nums[i];
            int exc = Math.max(includingFromEnd, excludingFromEnd);

            includingFromEnd = inc;
            excludingFromEnd = exc;
        }

        return Math.max(Math.max(includingFromEnd, excludingFromEnd),
                Math.max(includingFromStarting, excludingFromStarting));
    }

    // 5. Longest Palindromic Substring
    // https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];
        int maxLen = 0, si = -1, ei = -1;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n && j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }

                if (dp[i][j] && maxLen < (j - i + 1)) {
                    maxLen = j - i + 1;
                    si = i;
                    ei = j;
                }
            }
        }

        return maxLen == 0 ? s.charAt(0) + "" : s.substring(si, ei + 1);

    }

    // 647. Palindromic Substrings
    // https://leetcode.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int len = 0;
        int count = 0;

        for (int gap = 0; gap < n; gap++) {

            for (int i = 0, j = gap; i < n && j < n; i++, j++) {
                if (i == j) {
                    dp[i][j] = true;
                    len = j - i + 1 > len ? j - i + 1 : len;
                    count++;
                } else if (j - i == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        len = j - i + 1 > len ? j - i + 1 : len;
                        count++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        len = j - i + 1 > len ? j - i + 1 : len;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // 91. Decode Ways
    // https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s, int idx, int[] dp) {
        if (idx == s.length())
            return dp[idx] = 1;

        if (dp[idx] != -1)
            return dp[idx];

        int count = 0;
        char ch = s.charAt(idx);
        if (ch != '0') {
            count += numDecodings(s, idx + 1, dp);
        }

        if (idx + 1 < s.length()) {
            int num = (s.charAt(idx + 1) - '0') + ((s.charAt(idx) - '0') * 10);
            if (num >= 10 && num <= 26) {
                count += numDecodings(s, idx + 2, dp);
            }
        }

        return dp[idx] = count;
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);
        return numDecodings(s, 0, dp);
    }

    // 322. Coin Change
    // https://leetcode.com/problems/coin-change/
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, (int) 1e9);

        minCoins[0] = 0;
        for (int i = 0; i < coins.length; i++) { // IMPORT: firstly coins loop then dp traversal
            for (int j = coins[i]; j < minCoins.length; j++) {
                minCoins[j] = Math.min(minCoins[j], minCoins[j - coins[i]] + 1);
            }
        }

        return minCoins[amount] == (int) 1e9 ? -1 : minCoins[amount];
    }

    // 152. Maximum Product Subarray
    // https://leetcode.com/problems/maximum-product-subarray/
    public int maxProduct(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n][n];

        int max = -(int) 1e9;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n && i < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = nums[i];
                } else if (gap == 1) {
                    dp[i][j] = nums[i] * nums[j];
                } else {
                    dp[i][j] = nums[i] * nums[j] * dp[i + 1][j - 1];
                }

                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }

    // 139. Word Break
    // https://leetcode.com/problems/word-break/
    public int wordBreak(String s, int idx, int len, List<String> wordDict, int[] dp) {
        if (idx >= s.length()) {
            if (idx == s.length())
                return dp[idx] = 1;
            return 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        int count = 0;
        for (int i = idx; i <= s.length(); i++) {
            String str = s.substring(idx, i);
            if (str.length() > len)
                break;
            if (wordDict.contains(str))
                count += wordBreak(s, i, len, wordDict, dp);

        }
        return dp[idx] = count;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = 0;
        for (String str : wordDict) {
            len = Math.max(len, str.length());
        }

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        int ans = wordBreak(s, 0, len, wordDict, dp);
        return ans == 0 ? false : true;
    }

    // 300. Longest Increasing Subsequence
    // https://leetcode.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1)
            return n;

        int[] dp = new int[n];
        dp[0] = 1;
        int LIS = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (LIS < dp[i]) {
                LIS = dp[i];
            }
        }

        return LIS;
    }

    // 416. Partition Equal Subset Sum
    // https://leetcode.com/problems/partition-equal-subset-sum/
    public boolean targetSumSubset(int[] arr, int tar) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][tar + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= tar; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    if (dp[i - 1][j] || ((j - arr[(i - 1)] >= 0) && dp[i - 1][j - arr[i - 1]])) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[n][tar];
    }

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int ele : nums)
            totalSum += ele;

        if (totalSum % 2 == 1)
            return false;

        int tar = totalSum / 2;
        return targetSumSubset(nums, tar);
    }

    // 1143. Longest Common Subsequence
    // https://leetcode.com/problems/longest-common-subsequence/
    public int longestCommonSubsequence(String text1, String text2, int n, int m, int[][] dp) {
        if (n == 0 && m == 0) {
            return dp[n][m] = 0;
        }

        if (n == 0 || m == 0) {
            return 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (text1.charAt(n - 1) == text2.charAt(m - 1)) {
            return dp[n][m] = longestCommonSubsequence(text1, text2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(longestCommonSubsequence(text1, text2, n - 1, m, dp),
                    longestCommonSubsequence(text1, text2, n, m - 1, dp));
        }

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);

        }

        return longestCommonSubsequence(text1, text2, n, m, dp);
    }

    // 309. Best Time to Buy and Sell Stock with Cooldown
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][3];
        int maxProfit = 0;

        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);

            maxProfit = Math.max(Math.max(maxProfit, dp[i][2]), Math.max(dp[i][0], dp[i][1]));
        }

        return maxProfit;
    }

    // 494. Target Sum
    // https://leetcode.com/problems/target-sum/
    public int change(int amount, int[] coins) {
        int n = coins.length;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i < dp.length; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    // 494. Target Sum
    // https://leetcode.com/problems/target-sum/
    public int findTargetSumWays_(int[] arr, int n, int sum, int tar, int[][] dp) {
        if (n == 0) {
            if (tar == sum)
                return dp[n][sum] = 1;
            return dp[n][sum] = 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;

        count += findTargetSumWays_(arr, n - 1, sum + arr[n - 1], tar, dp);
        count += findTargetSumWays_(arr, n - 1, sum - arr[n - 1], tar, dp);

        return dp[n][sum] = count;
    }

    public int findTargetSumWays(int[] nums, int target) {
        if ((nums.length == 0 || nums.length == 1) && target == nums[0]) {
            return 1;
        }

        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        int[][] dp = new int[nums.length + 1][2 * sum + 1]; // Taking care of both positive and negative sides
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        int ans = findTargetSumWays_(nums, nums.length, sum, target + sum, dp);

        return ans;
    }

    // 329. Longest Increasing Path in a Matrix
    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    public int longestIncreasingPath(int[][] arr, int i, int j, int[][] dir, int[][] dp) {

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int max = 1;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < arr.length && c < arr[0].length && arr[r][c] > arr[i][j])
                max = Math.max(longestIncreasingPath(arr, r, c, dir, dp) + 1, max);
        }

        return dp[i][j] = max;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;

        int[][] dp = new int[n][m];
        int LIP = 1;
        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 },
        };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (dp[i][j] == 0) {
                    longestIncreasingPath(matrix, i, j, dir, dp);
                }
                LIP = Math.max(LIP, dp[i][j]);
            }
        }

        return LIP;
    }

    // 115. Distinct Subsequences
    // https://leetcode.com/problems/distinct-subsequences/
    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (m == 0 || n < m) {
            return dp[n][m] = m == 0 ? 1 : 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        int a = numDistinct(s, t, n - 1, m - 1, dp);
        int b = numDistinct(s, t, n - 1, m, dp);
        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            return dp[n][m] = a + b;
        } else {
            return dp[n][m] = b;
        }

    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);

        return numDistinct(s, t, n, m, dp);
    }

    // 72. Edit Distance
    // https://leetcode.com/problems/edit-distance/
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = n == 0 ? m : n;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        int delete = minDistance(word1, word2, n - 1, m, dp);
        int insert = minDistance(word1, word2, n, m - 1, dp);
        int replace = minDistance(word1, word2, n - 1, m - 1, dp);

        int count = 0;
        if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
            return dp[n][m] = replace;
        } else {
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
        }
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int d[] : dp) {
            Arrays.fill(d, -1);
        }

        return minDistance(word1, word2, n, m, dp);
    }

    public static void main(String[] args) {

    }
}
