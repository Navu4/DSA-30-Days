import java.util.ArrayList;
import java.util.LinkedList;

public class day_14 {

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

    /**
     * View Set
     * 1. Left View
     * 2. Right View
     * 3. Verticla Order
     * i. Top View
     * ii. Down View
     * 5. Diagonal Order
     * 6. Vertical Sum of Binary Tree
     * 7. Vertical Order LeetCode IMPORTANT
     * 
     */

    // Vertical Order

    public void widthOfTree(TreeNode root, int width, int[] minMax) {
        if (root == null) {
            return;
        }

        minMax[0] = Math.max(width, minMax[0]);
        minMax[1] = Math.min(width, minMax[1]);

        widthOfTree(root.left, width - 1, minMax);
        widthOfTree(root.right, width + 1, minMax);
    }

    public class Pair {
        int vl;
        TreeNode node;

        Pair(int vl, TreeNode node) {
            this.vl = vl;
            this.node = node;
        }
    }

    public ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        int[] minMax = new int[2];
        widthOfTree(root, 0, minMax);

        int width = minMax[0] - minMax[1] + 1;
        for (int i = 0; i < width; i++) {
            res.add(new ArrayList<>());
        }

        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(Math.abs(minMax[1]), root));

        while (que.size() != 0) {
            Pair rp = que.removeFirst();
            int vl = rp.vl;
            TreeNode node = rp.node;

            res.get(vl).add(node.val);
            if (node.left != null) {
                que.add(new Pair(vl - 1, node.left));
            }

            if (node.right != null) {
                que.add(new Pair(vl + 1, node.right));
            }
        }

        return res;
    }

    // Diagonal
    public ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> que = new LinkedList<>();

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();

            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                while (node != null) {
                    if (node.left != null) {
                        que.add(node.left);
                    }

                    smallAns.add(node.val);
                    node = node.right;
                }
            }
            res.add(smallAns);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
