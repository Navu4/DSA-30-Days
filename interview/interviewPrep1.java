package interview;

import java.util.Scanner;
import java.util.Stack;

public class interviewPrep1 {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static Scanner scn = new Scanner(System.in);

    public static class PairSum {
        boolean isSum;
        int totalSum;

        PairSum(int totalSum, boolean isSum) {
            this.totalSum = totalSum;
            this.isSum = isSum;
        }
    }

    // https://practice.geeksforgeeks.org/problems/sum-tree/1/
    public static PairSum isSumTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return new PairSum(root == null ? 0 : root.val, true);

        PairSum left = isSumTree(root.left);
        if (left.isSum == false)
            return new PairSum(0, false);

        PairSum right = isSumTree(root.right);
        if (right.isSum == false)
            return new PairSum(0, false);

        PairSum ans = new PairSum(0, true);

        ans.isSum = left.totalSum + right.totalSum == root.val;
        ans.totalSum = left.totalSum + right.totalSum + root.val;

        return ans;
    }

    public static void numberOfPathInExactlyKSteps() {
        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 },
        };

        int n = scn.nextInt();
        int m = scn.nextInt();

        int k = scn.nextInt();

        int x = scn.nextInt();
        int y = scn.nextInt();

        boolean[][] vis = new boolean[n][m];

        int ans = numberOfPathInExactlyKSteps(x, y, n, m, k, dir, vis);
        System.out.println(ans);
    }

    public static int numberOfPathInExactlyKSteps(int sr, int sc, int n, int m, int k, int[][] dir, boolean[][] vis) {

        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (k - 1 > 0)
                    count += numberOfPathInExactlyKSteps(r, c, n, m, k - 1, dir, vis);
            } else {
                if (k - 1 == 0) {
                    System.out.println(r + " " + c + " " + k);
                    count += 1;
                }
            }
        }
        vis[sr][sc] = false;

        return count;
    }

    // https://www.geeksforgeeks.org/move-zeroes-end-array/
    public static void pushZerosToEnd(int[] arr, int n) {
        int countNonZeros = 0, itr = 0;
        while (itr < n) {
            if (arr[itr] != 0) {
                arr[countNonZeros++] = arr[itr];
            }

            itr++;
        }

        while (countNonZeros < n) {
            arr[countNonZeros++] = 0;
        }
    }

    // https://www.geeksforgeeks.org/reduce-the-string-by-removing-k-consecutive-identical-characters/
    public static class PairStack {
        int count;
        char ch;

        PairStack(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String removingKConsecutiveIdenticalCharacters(String str, int k) {
        Stack<PairStack> st = new Stack<>();
        st.push(new PairStack(str.charAt(0), 1));

        StringBuilder sb = new StringBuilder();
        int n = str.length(), idx = 1;

        while (idx < n) {
            PairStack top = st.peek();
            char ch = str.charAt(idx);
            if (ch == top.ch && top.count + 1 == k) {
                int count = top.count;
                while (count-- > 0) {
                    st.pop();
                }
            } else {
                st.push(new PairStack(ch, ch == top.ch ? top.count + 1 : 1));
            }
            idx++;
        }

        while (st.size() != 0) {
            char ch = st.pop().ch;
            sb.append(ch);
        }

        return sb.reverse().toString();
    }

    public static class Node {
        int data;
        Node right, left;

        Node() {
        }

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    // https://practice.geeksforgeeks.org/problems/height-of-spiral-tree/1/
    public static int findTreeHeight(Node root) {
        if (root == null)
            return 0;

        if (root.left != null && root.left.right == root && root.right != null && root.right.left == root)
            return 1;

        return Math.max(findTreeHeight(root.left), findTreeHeight(root.right)) + 1;
    }

    public static int numberOfWeeks(int[] milestones) {
        int max = -1, n = milestones.length;

        long sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, milestones[i]);
            sum += milestones[i];
        }

        long remainingSum = sum - max;
        if (remainingSum <= max - 1)
            return (int) (2 * remainingSum + 1);
        else
            return (int) sum;
    }

    public static void main(String[] args) {
        // numberOfPathInExactlyKSteps();
        String str = scn.next();
        int k = scn.nextInt();

        String ans = removingKConsecutiveIdenticalCharacters(str, k);
        System.out.println(ans);
    }
}
