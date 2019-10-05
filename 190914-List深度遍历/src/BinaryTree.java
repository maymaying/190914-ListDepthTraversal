import java.util.ArrayList;
import java.util.List;
public class BinaryTree {

    public class TreeNode {
        char val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(char val) {
            this.val = val;
        }
    }

    //给定一个二叉树，返回它的 前序 遍历
    public List<Character> preorderTraversal(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }

        List<Character> left=preorderTraversal(root.left);
        List<Character> right=preorderTraversal(root.right);

        List<Character> list=new ArrayList<>();
        list.add(root.val);
        list.addAll(left);
        list.addAll(right);

        return list;
    }

    //给定一个二叉树，返回它的 中序 遍历
    public List<Character> inorderTraversal(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }

        List<Character> left=inorderTraversal(root.left);
        List<Character> right=inorderTraversal(root.right);

        List<Character> list=new ArrayList<>();
        list.addAll(left);
        list.add(root.val);
        list.addAll(right);

        return list;
    }

    private static List<Character> inorderList = new ArrayList<>();
    private static void inorderReturnList(TreeNode root) {
        if (root != null) {
            inorderReturnList(root.left);
            inorderList.add(root.val);
            inorderReturnList(root.right);
        }
    }

    private static List<Character> inorderReturnList2(TreeNode root) {
        List<Character> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        list.addAll(inorderReturnList2(root.left));
        list.add(root.val);
        list.addAll(inorderReturnList2(root.right));
        return list;
    }

    //给定一个二叉树，返回它的 后序 遍历
    public List<Character> postorderTraversal(TreeNode root) {
        List<Character> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);
        return list;
    }

    //对称二叉树
    private boolean isMirror(TreeNode p,TreeNode q){
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        return p.val==q.val && isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isMirror(root.left,root.right);
    }

    //检查两棵树是否相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }

        return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    //另一棵树的子树
    public boolean search(TreeNode root,TreeNode t){
        if(root==null){
            return false;
        }
        if(isSameTree(root,t)){
            return true;
        }
        if(search(root.left,t)){
            return true;
        }
        return search(root.right,t);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return search(s,t);
    }

    //求二叉树的最大高度
    public int getHeight(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }
    //判断一棵树是否是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        if(!isBalanced(root.left)){
            return false;
        }
        if(!isBalanced(root.right)){
            return false;
        }

        int left=getHeight(root.left);
        int right=getHeight(root.right);
        int diff=left-right;
        if(diff>= -1 && diff<= 1){
            return true;
        }

        return false;
    }

    //交换一次的先前排序
    public int[] prevPermOpt1(int[] A) {
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                // 1. 比 A[i] 小
                // 2. 小的最大的
                // 3. 如果相等，取左边的
                int maxIndex = -1;
                int max = Integer.MIN_VALUE;
                for (int j = i + 1; j < A.length; j++) {
                    if (A[j] < A[i] && A[j] > max) {
                        max = A[j];
                        maxIndex = j;
                    }
                }
                // i 是要交换的位置
                // maxIndex 就是要交换的位置
                if (maxIndex != -1) {
                    int t = A[i];
                    A[i] = A[maxIndex];
                    A[maxIndex] = t;
                }
                return A;
            }
        }

        return A;
    }

    //二叉树的最近公共祖先
    public boolean search1(TreeNode root,TreeNode t){
        if(root==null){
            return false;
        }
        if(root==t){
            return true;
        }
        if(search(root.left,t)){
            return true;
        }
        return search(root.right,t);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p==root || q==root){
            return root;
        }

        boolean pInLeft=search1(root.left,p);
        boolean qInLeft=search1(root.left,q);
        if(pInLeft && qInLeft){
            return lowestCommonAncestor(root.left,p,q);
        }

        if(!pInLeft && !qInLeft){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

}
