import java.util.HashMap;

public class day_10 {
    /**
     * String Set Question: Implementation of Subsequence and SubStrings
     * 
     * 1. Count Distinct Subsequence
     * 2. Count Distinct Palindromic Subsequence
     * 3. WildCard Matching
     * 4. Regular Expression Matching
     * 5. Longest Common Substring
     * 6. Edit Distance
     * 7. Scrable Strings -I and -II
     * 8. Minimum ASCII Delete Sum for two Strings
     * 9. Minimum Cost to Make String Identical
     * 
     * 
     */

    // 1. Count Distinct Subsequence
    // 940. Distinct Subsequences II
    // https://leetcode.com/problems/distinct-subsequences-ii/
    public int distinctSubseqII(String s) {
        int n = s.length();
        int mod = (int)1e9 + 7;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        HashMap<Character, Integer> lastOccurrence = new HashMap<>();
        // Instead of HashMap we can use array of size 26

        for(int i = 1; i < dp.length; i++){         
            
            char ch = s.charAt(i - 1);
            if(lastOccurrence.containsKey(ch)){
                int j = lastOccurrence.get(ch) - 1;                
                dp[i]=((( 2 * dp[i-1] ) % mod ) - dp[j] + mod ) % mod;  // IMPORTANT METHOD OF MODULUS
            } else {
                  dp[i]=((( 2 * dp[i-1] ) % mod ) % mod);
            }
            lastOccurrence.put(ch, i);
        }
        
        return (int)(dp[n] % mod - 1);
    }


    public static void main(String[] args) {
        
    }
}
