public class day_15 {
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right){
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

     // Post & inOrder
    
     public static TreeNode preOrIn(int[] pre, int[] in, int psi, int pei, int isi, int iei) {
        if(psi > pei || isi > iei)
            return null;


        int idx = isi;
        while(in[idx] != pre[psi])
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
    public static TreeNode PostOrderAndInOrder(int[] post, int[] in, int psi, int pei, int isi, int iei){
        if(psi > pei || isi > iei){
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
    
    public static TreeNode PostOrderAndInOrder(int[] post, int[] in){
        int n = post.length;
        return PostOrderAndInOrder(post, in, 0, n - 1, 0 , n - 1);
    }

    // PreOrder and PostOrder
    public static TreeNode PreOrderAndPostOrder(int[] pre, int[] post, int preSI, int preEI, int postSI, int postEI){
        if(preSI > preSI || postSI > postEI){
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
    
    public static TreeNode PreOrderAndPostOrder(int[] pre, int[] post){
        int n = post.length;
        return PostOrderAndInOrder(pre, post, 0, n - 1, 0 , n - 1);
    }



      public static void main(String[] args) {
          
      }

}

