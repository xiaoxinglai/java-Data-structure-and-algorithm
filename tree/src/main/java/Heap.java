/**
 * 二叉堆
 * <p>
 * 本质上是一种完全二叉树，分为最大堆和最小堆
 * 最大堆： 任何一个父节点的值都大于等于它左右孩子节点的值
 * 最小堆：任何一个父节点的值，都小于或者等于它左右孩子节点的值
 * 二叉堆的根节点叫堆顶，最大堆的堆顶是整个堆中的最大元素，最小堆的堆顶是整个堆中的最小元素
 * <p>
 * <p>
 * 常规操作：
 * 1.插入操作
 * 2.删除节点
 * 3.构建二叉堆
 * <p>
 * 以上操作都依赖于堆的调整：
 * 上浮和下沉
 * <p>
 * 插入节点的时候，插在完全二叉树的最后一个节点上，然后进行上浮调整
 * 如果是最大堆，那么如果这个新插入的节点比其父节点大，那么就和父节点交换位置，依次类推，最终最多可以上浮到根节点位置
 * 如果是最小堆，那么如果这个新插入的节点比其父节点小，那么就和父节点交换位置，依次类推，最终最多可以上浮到根节点位置
 * <p>
 * <p>
 * 删除节点的时候，删除根节点，然后将最后一个节点作为根节点，然后进行下沉调整
 * 如果是最小堆，那么将新的根节点和其左右子树进行比较，如果根节点 大于两个子孩子节点中的最小值节点，和其交换位置 ，依次类推，最终最多可以下沉到叶子节点位置
 * 如果是最大堆，需要保证堆顶值最大，那么 新的根节点小于两个孩子中的最大值节点，那么和其交换位置，依次类推，最多可以下沉到叶子节点位置
 * <p>
 * <p>
 * 构建二叉堆，依次对所有非叶子节点进行下沉  最终就可以把一个无序的完全二叉树调整为二叉堆
 * <p>
 * <p>
 * 注意！二叉堆是一个完全二叉树，存储方式是顺序存储，存储在数组中，完全二叉树存储在数组中，不会有空位置
 * <p>
 * 可以根据数组的下标来计算 假设父节点的下标是parent ，那么它的左孩子的下标就是2xparent+1  右孩子就是2xparent+2
 * 注意！链表存储的则不能这样算
 */
public class Heap {


    /**
     * 先序遍历
     *
     * @param array
     */
    public static void pre(int[] array, int root) {
        if (root >= array.length) {
            return;
        }
        System.out.print(array[root]);
        pre(array, 2 * root + 1);
        pre(array, 2 * root + 2);
    }



    /**
     * 上浮调整
     */
    public static void upAdjust(int[] array) {
        //从最后一个节点开始
        int childIndex = array.length - 1;
        //这个节点的父节点  不管这个节点是左节点还是右节点 其（坐标-1）/2都是它父节点的下标
        int parentIndex = (childIndex - 1) / 2;

        //temp 保存插入的叶子节点的值 用于最后的赋值
        int temp = array[childIndex];
        //该值小于父节点的值 且子节点不是根节点
        while (childIndex > 0 && temp < array[parentIndex]) {
            //无须真正交换 单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = temp;

    }

    /**
     * 下沉节点
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        //temp保存父节点的值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子 否则就是左孩子  childIndex++
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            //如果父节点小于任何一个孩子的值 则直接跳出
            if (temp<=array[childIndex])
                break;

            array[parentIndex]=array[childIndex];
            parentIndex=childIndex;
            childIndex=2*childIndex+1;
        }
        array[parentIndex]=temp;

    }


    /**
     * 构建堆
     *
     * @param array 等待调整的堆
     */
    public static void buildHeadp(int[] array) {

        //从最后一个非叶子节点开始 依次左下沉操作
        for (int i = (array.length-2)/2; i >=0 ; i--) {
            downAdjust(array,i,array.length);
        }

    }




    /**
     * 注意！ 数组里面 存的时候 顺序和先序遍历什么的不一样 ，要按照左孩子的下标就是2xparent+1  右孩子就是2xparent+2 这样存
     * 如果没有的，空都要空开，以符合这个条件方便查找
     *链表的话则是用先序遍历的形式去存，且没有的节点用null表示
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{7, 1, 3, 10, 5,2,8,9,6};
        pre(array, 0);
        System.out.println("前序遍历");
        buildHeadp(array);
        pre(array, 0);
        System.out.println("构建成二叉堆，最小堆");

        int[] array2 = new int[]{1, 3, 2, 6, 5,7,8,9,10};
        pre(array2, 0);
        System.out.println("前序遍历");
        upAdjust(array2);
        pre(array2, 0);
        System.out.println("调整成二叉堆，最小堆");

        HeapSort(array2);
        for (int i : array2) {
            System.out.print(i+" ");
        }

    }




    /**
     * 堆排序
     * 原理：大根堆 ，将堆顶元素和最后一个元素交换位置之后，进行下沉调整，会将第二大的元素设置为堆顶，
     * 小根堆则是会将第二小的元素设置为堆顶
     *
     *
     * 依据上述特性， 重复以上，直到数组元素都遍历过 ，最后得到的数组 就是一个排序的数组
     *
     * @param array 等待排序的数组（已经构建成堆）
     */
    public static void HeapSort(int[] array) {
        int i=array.length-1;
        while(i>=0){
            int temp=array[0];
            array[0]=array[i];
            array[i]=temp;
            downAdjust(array,0,i);
            i--;
        }
    }








}
