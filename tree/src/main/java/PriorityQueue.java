import java.util.Arrays;

/**
 * 优先级队列
 * <p>
 * 优先级队列不再遵循先入先出的原则，而是分为两种情况：
 * 最大优先级队列，无论入队顺序如何，都是当前最大的元素优先出队
 * 最小优先级队列，无论入队顺序如何，都是当前最小的元素优先出队
 * <p>
 * <p>
 * 最大堆的堆顶是整个堆中的最大元素
 * 最小堆的堆顶是整个堆中的最小元素
 * <p>
 * 因此可以用最大堆来实现最大优先级队列，最小堆来实现最小优先级队列
 * 每一次入队就是一个插入操作，时间复杂度为logn
 * 每一次出队就是一个删除操作，时间复杂度为logn
 */
public class PriorityQueue {


    /**
     * 定一个数组 用于存储数据
     */
    private int[] array;
    /**
     * 当前大小
     */
    private int size;

    public PriorityQueue() {
        //设定数组的初始值为32
        this.array = new int[32];
    }

    /**
     * 入队
     *
     * @param key
     */
    public void enQueue(int key) {

        //判断需不需要扩容
        if (size >= array.length) {
            //扩容
            resize();

        }

        //入队是一个插入操作，然后进行上浮调整
        array[size++] = key;
        upAdjust();
    }


    /**
     * 出队
     */
    public int dequeue() throws Exception {
        if (size <= 0) {
            throw new Exception("队列已空");
        }
        //获取堆顶元素
        int res = array[0];

        //删除堆顶元素，将最后一个元素移动到堆顶 同时大小减1
        array[0] = array[--size];
        downAdjust();
        return res;
    }


    /**
     * 下沉调整
     */
    public void downAdjust() {

        //对堆顶元素进行下沉操作
        //根节点值
        int temp = array[0];
        //当前父节点位置
        int parent = 0;
        int child = 2 * parent + 1;
        while (parent <= size) {

            //找到两个子树中大的那一个
            if (child+1<size&&array[child]<array[child+1]){
                child++;
            }

            //如果没有比父节点更大的 就跳出循环    停止下沉
            if (temp>=array[child]){
                break;
            }
            array[parent]=array[child];
            parent=child;
            child=2*parent+1;
        }

        array[child]=temp;

    }

    /**
     * 数组扩容 2倍
     */
    private void resize() {
        array = Arrays.copyOf(array, 2 * size);
    }


    /**
     * 上浮
     */
    public void upAdjust() {
        //入队是一个插入操作，然后进行上浮调整

        //找到最后一个子树的位置
        int child = size - 1;

        //暂存其值
        int temp = array[child];

        //找到其父节点的位置
        int parent = (child - 1) / 2;


        //当子节点大于0，且父节点小于子节点的时候，持续上浮   大根堆
        while (child > 0 && temp > array[parent]) {
            array[child] = array[parent];
            //新的子节点
            child = parent;
            //新的父节点
            parent = (child - 1) / 2;
        }

        //直到找到一个父节点大于等于子节点的位置
        array[parent] = temp;
    }


    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue=new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);

        System.out.println("出队："+priorityQueue.dequeue());
        System.out.println("出队："+priorityQueue.dequeue());
    }
}


