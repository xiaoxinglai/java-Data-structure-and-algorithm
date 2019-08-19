import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName treeDemo
 * @Author laixiaoxing
 * @Date 2019/8/19 下午11:43
 * @Description 二叉树的构建和遍历
 * @Version 1.0
 */
public class TreeDemo {

    static class TreeNode {
        private Integer data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Integer data) {
            this.data = data;
        }
    }

    /**
     * 构建一个二叉树
     *
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        //用递归的形式构建树，其实和先序遍历的顺序是一样的
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        } return node;
    }


    /**
     * 先序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }


    /**
     * 中序遍历
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.leftChild);
        System.out.print(node.data);
        inOrder(node.rightChild);
    }


    /**
     * 后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.print(node.data);
    }


    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<Integer>(
                Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));

        TreeNode t = TreeDemo.createBinaryTree(linkedList);
        TreeDemo.preOrder(t);
        System.out.println("先序遍历");
        TreeDemo.inOrder(t);
        System.out.println("中序遍历");
        TreeDemo.postOrder(t);
        System.out.println("后序遍历");
    }

}
