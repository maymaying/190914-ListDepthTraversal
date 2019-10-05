public class TreeSearch {

    //search的四种实现方式
    BinaryTree.TreeNode search1(BinaryTree.TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        BinaryTree.TreeNode left = search1(root.left, val);
        if (left != null) {
            return left;
        }
        return search1(root.right, val);
    }

    boolean search2(BinaryTree.TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        if (search2(root.left, val)) {
            return true;
        }
        return search2(root.right, val);
    }

    boolean search3(BinaryTree.TreeNode root, BinaryTree.TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        if (search3(root.left, subRoot)) {
            return true;
        }
        return search3(root.right, subRoot);
    }
    public boolean isSameTree(BinaryTree.TreeNode p, BinaryTree.TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }

        return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    boolean search4(BinaryTree.TreeNode root, BinaryTree.TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        if (search4(root.left, node)) {
            return true;
        }
        return search4(root.right, node);

    }


}
