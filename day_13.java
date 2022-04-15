
import java.util.ArrayList;
import java.util.LinkedList;

public class day_13 {
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
     * Kth Distance Set
     * 1. All Nodes at Kth Level 
     * 2. All Nodes at Kth Distance
     * 3. K Distance Space Optimized
     * 4. Burning Tres 
     * 5. Burning Tree with Water
     * 6. LCA in Binary and Binary Search Tree
     * 
     *                                                                                                                  
     */

    //https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
    public int maxLevelSum(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return root == null ? -1 : root.val;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        
        int levelAns = 1, level = 1;
        int maxSum = root.val;
        
        while(que.size() != 0){
            int size = que.size();
            int sum = 0;
            
            while(size-- > 0){
                TreeNode rNode = que.removeFirst();
                sum += rNode.val;
                
                if(rNode.left != null){
                    que.add(rNode.left);
                }
                if(rNode.right != null){
                    que.add(rNode.right);
                }
            }
            
            
            if(maxSum < sum){
                maxSum = sum;
                levelAns = level;
            }
            
            level++;
        }
        
        return levelAns;
    }

    // kth Level Down
    public static void KthLevelDown(TreeNode root, int k, ArrayList<Integer> ans){
        if(root == null || k < 0)
            return;

        if(k == 0){
            ans.add(root.val);
            return;
        }

        KthLevelDown(root.left, k - 1, ans);
        KthLevelDown(root.right, k - 1, ans);
    }


    public static void main(String[] args) {
        
    }
}
