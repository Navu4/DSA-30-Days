import java.util.LinkedList;

public class day_15 {
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
     * Construction Set
     * -------------------------------------
     * Construct BST using
     * 1. Inorder
     * 2. PreOrder
     * 3. PostOrder
     * 4. LevelOrder
     * 
     * 
     * Binary Tree Contruction
     * 1. Pre & In order
     * 2. Post & In Order
     * 3. Pre & Post Order
     * 4. InOrder and Level order
     * 
     */

    // Inorder
    public static TreeNode inorderConstruction(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = inorderConstruction(inOrder, si, mid - 1);
        root.right = inorderConstruction(inOrder, mid + 1, ei);

        return root;
    }

    // preOrder
    public static TreeNode preOrderConstruction(int[] preOrder, int[] idx, int lr, int rr) {
        // base case
        if (idx[0] == preOrder.length || lr > preOrder[idx[0]] || rr < preOrder[idx[0]]) {
            return null;
        }

        // pre order
        TreeNode node = new TreeNode(preOrder[idx[0]++]);

        node.left = preOrderConstruction(preOrder, idx, lr, node.val);
        node.right = preOrderConstruction(preOrder, idx, node.val, rr);

        return node;
    }

    // Level Order
    public static class Pair {
        int lr, rr;
        TreeNode root;

        Pair(TreeNode root, int lr, int rr) {
            this.root = root;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode levelOrderConstruction(int[] nums) {
        LinkedList<Pair> que = new LinkedList<>();
        int idx = 0;
        TreeNode root = new TreeNode(nums[idx++]);
        que.add(new Pair(root, -(int) 1e9, (int) 1e9));

        while (que.size() != 0) {
            Pair rp = que.removeFirst();
            int lr = rp.lr, rr = rp.rr;
            TreeNode rNode = rp.root;

            int val = nums[idx];
            if (val < lr && val > rr) {
                continue;
            }

            TreeNode node = new TreeNode(nums[idx++]);
            if (node.val < rNode.val) {
                rNode.left = node;
            } else {
                rNode.right = node;
            }

            que.add(new Pair(node, lr, node.val));
            que.add(new Pair(root, node.val, rr));
        }

        return root;
    }

    // Post & inOrder
    public static TreeNode preOrIn(int[] pre, int[] in, int psi, int pei, int isi, int iei) {
        if (psi > pei || isi > iei)
            return null;

        int idx = isi;
        while (in[idx] != pre[psi])
            idx++;

        int tel = idx - isi;

        TreeNode root = new TreeNode(pre[psi]);

        root.left = preOrIn(pre, in, psi + 1, psi + tel, isi, idx - 1);
        root.right = preOrIn(pre, in, psi + tel + 1, pei, idx + 1, iei);

        return root;
    }

    public static TreeNode PreOrderAndInorder(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return preOrIn(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    // Post and InOrder
    public static TreeNode PostOrderAndInOrder(int[] post, int[] in, int psi, int pei, int isi, int iei) {
        if (psi > pei || isi > iei) {
            return null;
        }

        int idx = isi;
        while (post[pei] != in[idx]) {
            idx++;
        }
        int tel = idx - isi;
        TreeNode root = new TreeNode(post[pei]);

        root.left = PostOrderAndInOrder(post, in, psi, psi + tel - 1, isi, idx - 1);
        root.right = PostOrderAndInOrder(post, in, psi + tel, pei - 1, idx + 1, iei);

        return root;
    }

    public static TreeNode PostOrderAndInOrder(int[] post, int[] in) {
        int n = post.length;
        return PostOrderAndInOrder(post, in, 0, n - 1, 0, n - 1);
    }

    // PreOrder and PostOrder
    public static TreeNode PreOrderAndPostOrder(int[] pre, int[] post, int preSI, int preEI, int postSI, int postEI) {
        if (preSI > preSI || postSI > postEI) {
            return null;
        }

        int idx = postSI;
        while (pre[preSI] != post[idx]) {
            idx++;
        }
        int tel = idx - postSI;
        TreeNode root = new TreeNode(post[preSI]);

        root.left = PreOrderAndPostOrder(pre, post, preSI + 1, preSI + tel + 1, postSI, idx);
        root.right = PreOrderAndPostOrder(pre, post, preSI + tel + 2, preEI, idx + 1, postEI - 1);

        return root;
    }

    public static TreeNode PreOrderAndPostOrder(int[] pre, int[] post) {
        int n = post.length;
        return PostOrderAndInOrder(pre, post, 0, n - 1, 0, n - 1);
    }

    public static void main(String[] args) {

    }

    static int[] calc(int ar[], int f, int m) {
        int n = ar.length;
        int den = n + f, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += ar[i];
        }
        int rhs = m * den;
        int c = 0, maxSum = 6 * f;
        rhs -= sum;
        int ans[] = { 0 };
        if (rhs > maxSum || rhs < 0) {
            return ans;
        }
        int chi[] = new int[f];
        while (rhs != 0) {
            if (rhs >= 5 + f) {
                chi[c] = 6;
                rhs -= 6;
                f--;
                c++;
            } else {
                rhs -= (f - 1);
                chi[c++] = rhs;
                f--;
                while (f != 0) {
                    chi[c++] = 1;
                    f--;
                }
                break;
            }
        }
        return chi;
    }

}
