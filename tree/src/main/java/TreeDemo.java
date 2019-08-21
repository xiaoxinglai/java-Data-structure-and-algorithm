import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

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
        }
        return node;
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
     * 先序遍历 使用栈实现
     */
    public static void preStackOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        //根左右
        Stack<TreeNode> stack = new Stack<>();


        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.data);
                //记录下访问过的节点
                stack.push(node);
                node = node.leftChild;
            }
            //当左边都遍历完
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.rightChild;
            }
        }


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
     * 中序遍历使用栈实现
     *
     * @param node
     */
    public static void inStackOrder(TreeNode node) {
        //左 中 右
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.data);
                node = node.rightChild;
            }
        }

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


    /**
     * 栈后序遍历
     *
     * @param node
     */
    public static void postStackOrder(TreeNode node) {
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            //左右中
            while (node != null) {
                if (node != lastNode) {
                    System.out.println("将node放到push:"+node.data);
                    stack.push(node);
                }
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println("将node Pop:"+node.data);
                if (node.rightChild == null||lastNode==node.rightChild) {
                    System.out.print("node左节点为空:"+node.data);
                    lastNode = node;
                } else {
                    node = node.rightChild;
                }
            }
        }

    }


    public static void main(String[] args) {

        //        LinkedList<Integer> linkedList = new LinkedList<Integer>(
        //                Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
        LinkedList<Integer> linkedList = new LinkedList<Integer>(Arrays.asList(new Integer[]{1, 2, null, null, 3}));

        //1,2,3和1,2,null,3构建的二叉树 第一个是一个只有左节点的树
        //1, 2,null,null, 3 构建的才是 根节点是1，左节点是2 右节点是3
        //注意！链表构建的和数组不一样，数组的话  根节点是1，左节点是2 右节点是3   存法是123  链表是1, 2,null,null, 3
        TreeNode t = TreeDemo.createBinaryTree(linkedList);
        TreeDemo.preOrder(t);
        System.out.println("先序遍历");
        preStackOrder(t);
        System.out.println("栈先序遍历");
        TreeDemo.inOrder(t);
        System.out.println("中序遍历");
        inStackOrder(t);
        System.out.println("栈中序遍历");
        TreeDemo.postOrder(t);
        System.out.println("后序遍历");
        postStackOrder(t);
        System.out.println("栈后序遍历");
    }

}
