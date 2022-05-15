import java.util.List;
import java.util.ArrayList;

public class day_18 {
    /**
     * Trie Data Structure
     * 1. Implement Trie (Prefix Tree)
     * 2. Complete String
     * 3. Design Add and Search Words Data Structure
     * 4. Word Search II
     * 
     * 
     */

    // 208. Implement Trie (Prefix Tree)
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    class Trie {

        public class Node {
            boolean isEnd;
            Node[] childs = new Node[26];
        }

        final private Node root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {

            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr.childs[ch - 'a'] == null) {
                    curr.childs[ch - 'a'] = new Node();
                }

                curr = curr.childs[ch - 'a'];
            }
            curr.isEnd = true;

        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {

            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr.childs[ch - 'a'] != null) {
                    curr = curr.childs[ch - 'a'];
                } else {
                    return false;
                }
            }

            return curr.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String word) {

            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr.childs[ch - 'a'] != null) {
                    curr = curr.childs[ch - 'a'];
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    // Complete String
    // https://www.codingninjas.com/codestudio/problems/complete-string_2687860?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0

    class CompleteString {
        public class Node {
            boolean isEnd;
            char ch;
            Node[] childs;

            Node() {
                this.childs = new Node[26];
            }
        }

        public void insertIntoTrie(String str, Node root) {
            Node curr = root;
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);

                if (curr.childs[ch - 'a'] == null) {
                    curr.childs[ch - 'a'] = new Node();
                }

                curr = curr.childs[ch - 'a'];
            }
            curr.isEnd = true;
        }

        public boolean checkIfExists(String word, Node root) {
            Node curr = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr == null || curr.childs[ch - 'a'] == null || curr.childs[ch - 'a'].isEnd == false) {
                    return false;
                }
                curr = curr.childs[ch - 'a'];
            }
            return curr.isEnd == true;
        }

        public String completeString(int n, String[] a) {

            Node root = new Node();
            for (String str : a) {
                insertIntoTrie(str, root);
            }

            String ans = "";
            for (String str : a) {

                if (checkIfExists(str, root)) {
                    if (str.length() > ans.length()) {
                        ans = str;
                    } else if (str.length() == ans.length() && str.compareTo(ans) < 0) {
                        ans = str;
                    }
                }
            }

            return ans.length() == 0 ? "None" : ans;
        }
    }

    // Count Distinct SubString
    // https://www.codingninjas.com/codestudio/problems/count-distinct-substrings_985292?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0
    public class CountDistinctSubstring {
        public class Node {
            Node[] childs;

            Node() {
                this.childs = new Node[26];
            }
        }

        public int countDistinctSubstrings(String s) {
            if (s.length() == 0)
                return 1;

            Node root = new Node();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                Node curr = root;

                for (int j = i; j < s.length(); j++) {
                    char ch = s.charAt(j);
                    if (curr != null && curr.childs[ch - 'a'] == null) {
                        count++;
                        curr.childs[ch - 'a'] = new Node();
                    }

                    curr = curr.childs[ch - 'a'];
                }
            }

            return count + 1;
        }
    }

    // 211. Design Add and Search Words Data Structure
    // https://leetcode.com/problems/design-add-and-search-words-data-structure/
    class WordDictionary {
        private class Node {
            Node[] childs;
            boolean isEnd;

            Node() {
                childs = new Node[26];
            }
        }

        final private Node root;

        public WordDictionary() {
            root = new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr.childs[ch - 'a'] == null) {
                    curr.childs[ch - 'a'] = new Node();
                }

                curr = curr.childs[ch - 'a'];
            }

            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character ''.'' to represent any one letter.
         */
        public boolean search(String word) {
            return search(root, word, 0);
        }

        private boolean search(Node root, String word, int idx) {
            if (idx == word.length()) {
                return root.isEnd;
            }

            char ch = word.charAt(idx);

            if (ch == '.') {
                for (Node child : root.childs) {
                    if (child != null && search(child, word, idx + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (root.childs[ch - 'a'] == null)
                    return false;
                else
                    return search(root.childs[ch - 'a'], word, idx + 1);
            }
        }
    }

    // Word Search 2
    // https://leetcode.com/problems/word-search-ii/
    public static class Node {
        Node[] childs;
        String str;

        Node() {
            childs = new Node[26];
        }
    }

    public static void insert(Node root, String s) {
        Node curr = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (curr.childs[ch - 'a'] == null) {
                curr.childs[ch - 'a'] = new Node();
            }

            curr = curr.childs[ch - 'a'];
        }
        curr.str = s;
    }

    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;

        Node root = new Node();
        for (String s : words) {
            insert(root, s);
        }

        List<String> ans = new ArrayList<String>();
        boolean[][] vis = new boolean[n][m];
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

    public static void dfs(char[][] board, int i, int j, Node root, boolean[][] vis, int[][] dir, List<String> ans) {

        char ch = board[i][j];
        Node[] childs = root.childs;

        if (childs[ch - 'a'] == null)
            return;
        else if (childs[ch - 'a'].str != null) {
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

}
