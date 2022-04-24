import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day_22 {

    /**
     * Back Tracking
     * 1. Subsets
     * 2. Combination Sum
     * 3. Permutations
     * 4. Subsets II
     * 5. Combination Sum II
     * 6. Word Search
     * 7. Word Search II
     * 8. Letter Combinations of a Phone Number
     * 
     */

    // 1.Subsets
    // https://leetcode.com/problems/subsets/
    public void subsets(int[] nums, int idx, List<Integer> ans, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }

        subsets(nums, idx + 1, ans, res);

        ans.add(nums[idx]);
        subsets(nums, idx + 1, ans, res);
        ans.remove(ans.size() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        subsets(nums, 0, ans, res);
        return res;
    }

    // 2. Combination Sum
    // https://leetcode.com/problems/combination-sum/
    public int combinationSum(int[] candidates, int target, int idx, List<Integer> ans, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return 1;
        }

        if (idx == candidates.length) {
            return 0;
        }

        int count = 0;
        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                ans.add(candidates[i]);
                count += combinationSum(candidates, target - candidates[i], i, ans, res);
                ans.remove(ans.size() - 1);
            }
        }

        return count;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0)
            return res;

        List<Integer> ans = new ArrayList<>();
        int ansCount = combinationSum(candidates, target, 0, ans, res);

        return res;
    }

    // 3. Permutations
    // https://leetcode.com/problems/permutations/
    public void permute(int[] nums, boolean vis[], List<Integer> ans, List<List<Integer>> res) {
        if (ans.size() == nums.length) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                ans.add(nums[i]);
                permute(nums, vis, ans, res);
                ans.remove(ans.size() - 1);
                vis[i] = false;
            }
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)
            return res;

        boolean[] vis = new boolean[nums.length];
        List<Integer> ans = new ArrayList<>();
        permute(nums, vis, ans, res);

        return res;
    }

    // 4. Subsets II
    // https://leetcode.com/problems/subsets-ii/
    /**
     * Constraints
     * -10 <= nums[i] <= 10
     * 1 <= nums.length <= 10
     * 
     * 10 + 10 + 1 = size of Array ;
     */
    public void subsetsWithDup(int[] nums, int idx, List<Integer> ans, List<List<Integer>> res) {
        if (idx == nums.length) {
            return;
        }

        int prev = -(int) 1e9;
        for (int i = idx; i < nums.length; i++) {
            if (prev != nums[i]) { // Sexy ass important
                ans.add(nums[i]);

                List<Integer> base = new ArrayList<>(ans);
                res.add(base);

                subsetsWithDup(nums, i + 1, ans, res);

                ans.remove(ans.size() - 1);
            }

            prev = nums[i];
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)
            return res;

        res.add(new ArrayList<>());

        Arrays.sort(nums); // Important
        List<Integer> ans = new ArrayList<>();
        subsetsWithDup(nums, 0, ans, res);

        return res;
    }

    // 5. Combination Sum II
    // https://leetcode.com/problems/combination-sum-ii/
    public int combinationSum2(int[] arr, int tar, int idx, List<Integer> ans, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return 1;
        }

        int count = 0;
        // boolean[] vis = new boolean[51]; // Imp point
        int prev = -1; // Contraints ke according vali deni hai prev ko
        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i] && tar - arr[i] >= 0) {
                ans.add(arr[i]);

                count += combinationSum2(arr, tar - arr[i], i + 1, ans, res);

                ans.remove(ans.size() - 1);
            }
            if (tar - arr[i] < 0) {
                break;
            }
            prev = arr[i];

        }
        return count;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        Arrays.sort(candidates);

        combinationSum2(candidates, target, 0, ans, res);

        return res;
    }

    // 6. Word Search
    // https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word, int idx, int sr, int sc, int[][] dir) {
        if (idx == word.length())
            return true;

        char ch = board[sr][sc];
        board[sr][sc] = '$';
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] != '$'
                    && board[r][c] == word.charAt(idx)) {
                if (exist(board, word, idx + 1, r, c, dir)) {
                    return true;
                }
            }
        }
        board[sr][sc] = ch;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exist(board, word, 1, i, j, dir)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 7. Word Search II (Trie )
    // https://leetcode.com/problems/word-search-ii/

    public class Node {
        String str;
        Node[] childs;

        Node() {
            childs = new Node[26];
        }
    }

    public void insert(Node root, String words) {
        Node curr = root;
        for (int i = 0; i < words.length(); i++) {
            char ch = words.charAt(i);

            if (curr.childs[ch - 'a'] == null) {
                curr.childs[ch - 'a'] = new Node();
            }
            curr = curr.childs[ch - 'a'];
        }
        curr.str = words;
    }

    public void dfs(char[][] board, int i, int j, Node root, boolean[][] vis, int[][] dir, List<String> ans) {

        char ch = board[i][j];
        Node[] childs = root.childs;

        if (childs[ch - 'a'] == null) {
            return;
        } else if (childs[ch - 'a'].str != null) {
            ans.add(childs[ch - 'a'].str);
            childs[ch - 'a'].str = null;
        }

        vis[i][j] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && !vis[r][c]) {
                dfs(board, r, c, childs[ch - 'a'], vis, dir, ans);
            }
        }

        vis[i][j] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        if (words.length == 0) {
            return ans;
        }

        Node root = new Node();
        for (String word : words) {
            insert(root, word);
        }

        boolean[][] vis = new boolean[board.length][board[0].length];
        int[][] dir = {
                { 0, 1 },
                { 1, 0 },
                { 0, -1 },
                { -1, 0 }
        };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, vis, dir, ans);
            }
        }

        return ans;
    }

    // 8. Palindrome Partitioning
    // https://leetcode.com/problems/palindrome-partitioning/
    public boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }

        return true;
    }

    public void partition(String s, int idx, List<String> ans, List<List<String>> res) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome(s, idx, i)) {
                ans.add(s.substring(idx, i + 1));

                partition(s, i, ans, res);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> ans = new ArrayList<>();

        partition(s, 0, ans, res);

        return res;
    }

    // 8. Letter Combinations of a Phone Number
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/

    String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String str, int idx) {
        if (idx == str.length()) {
            List<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        List<String> smallAns = letterCombinations(str, idx + 1); // Subse phle smallAns ko nikal lo
        List<String> myAns = new ArrayList<>();

        char ch = str.charAt(idx);
        String code = codes[ch - '0']; // Code that should be add in front of every possible answer

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            for (String s : smallAns) {
                myAns.add(c + s); // Add krne se saari possiblities cover hojayengi
            }
        }

        return myAns;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();
        return letterCombinations(digits, 0);
    }

    public static void main(String[] args) {

    }
}
