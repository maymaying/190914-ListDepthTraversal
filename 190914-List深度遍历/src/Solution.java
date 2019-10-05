import java.util.Arrays;
import java.util.List;

public class Solution {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        int rootValue = preorder[0];
        int leftCount;
        for (leftCount = 0; leftCount < inorder.length; leftCount++) {
            if (inorder[leftCount] == rootValue) {
                break;
            }
        }
        //左子树的结点个数 leftCount
        //左子树的前序 + 中序
        TreeNode root = new TreeNode(rootValue);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftCount);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, leftCount);
        root.left = buildTree(leftPreorder, leftInorder);
        int[] rightPreorder = Arrays.copyOfRange(preorder, 1 + leftCount, preorder.length);
        int[] rightInorder = Arrays.copyOfRange(inorder,leftCount + 1, inorder.length);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }

    //从中序与后序遍历序列构造二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }

        int rootValue = postorder[postorder.length - 1];
        int leftCount = indexOf(inorder, rootValue);
        TreeNode root = new TreeNode(rootValue);
        int[] li = Arrays.copyOfRange(inorder, 0, leftCount);
        int[] lp = Arrays.copyOfRange(postorder, 0, leftCount);
        root.left = buildTree2(li, lp);
        int[] ri = Arrays.copyOfRange(inorder, leftCount + 1, inorder.length);
        int[] rp = Arrays.copyOfRange(postorder, leftCount, postorder.length - 1);
        root.right = buildTree2(ri, rp);

        return root;
    }
    private int indexOf(int[] a, int r) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == r) {
                return i;
            }
        }
        return -1;
    }

    //从前序与中序遍历序列构造二叉树
    TreeNode buildTree3(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }
        int rootValue = preorder.get(0);
        int leftCount = inorder.indexOf(rootValue);
        List<Integer> leftPre = preorder.subList(1, 1 + leftCount);
        List<Integer> leftIn = inorder.subList(0, leftCount);
        List<Integer> rightPre = preorder.subList(1 + leftCount, preorder.size());
        List<Integer> rightIn = inorder.subList(1 + leftCount, preorder.size());
        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree3(leftPre, leftIn);
        root.right = buildTree3(rightPre, rightIn);
        return root;
    }

    //从中序与后序遍历序列构造二叉树
    TreeNode buildTree4(List<Integer> inorder, List<Integer> postorder){
        if (inorder.isEmpty()) {
            return null;
        }
        int rootValue = postorder.get(postorder.size()- 1);
        int leftCount = inorder.indexOf(rootValue);
        TreeNode root = new TreeNode(rootValue);
        List<Integer> leftInorder = inorder.subList(0, leftCount);
        List<Integer> leftPostorder = postorder.subList(0, leftCount);
        root.left = buildTree4(leftInorder, leftPostorder);
        List<Integer> rightInorder = inorder.subList(leftCount + 1, inorder.size());
        List<Integer> rightPostorder = postorder.subList(leftCount, postorder.size() - 1);
        root.right = buildTree4(rightInorder, rightPostorder);

        return root;
    }
}