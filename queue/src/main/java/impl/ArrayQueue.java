package impl;

import Interface.IQueue;

/**
 * 数组型队列
 * <p>
 * 同样需要一个头指针，一个尾指针  当头指针=尾指针=0时候为空
 * 需要实现分配一个固定大小的数组
 * 正常情况下下，尾指针永远指向队尾元素的下一个位置，比如说队尾元素在0 尾指针则在1
 * <p>
 * 注意！：数组型队列有很大的劣势，容易造成存储空间浪费，而且不易扩容。
 * 比如说，最大空间为6的数组队列， 进去了6个了元素，然后从队头出去了5个元素，此时，仍然不能插入新的元素
 * 因为队尾指针仍然指向第6个元素，其仍然占据了最后一个位置，而队头是不允许插入的。这样造成前面5个位置浪费。
 * <p>
 * 解决方法：1.元素移动位置，出队一个 后面的元素往前挪。   缺点：每次出队都需要移动位置 很麻烦 效率也低
 * 2.动态扩容，  缺点：浪费了前面的空间
 * 3.最佳解决方案：构造环形队列
 */
public class ArrayQueue<T> implements IQueue {
    private Integer size;
    private Integer header;
    private Integer tail;
    private final Integer length = 6;
    private Object[] arr;

    public IQueue InitQueue() {
        arr = new Object[length];
        tail = header = size = 0;
        return this;
    }

    public IQueue DestroyQueue() {
        arr = null;
        tail = header = size = 0;
        return this;
    }

    public IQueue ClearQueue() {
        tail = header = size = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        return this;
    }

    public Boolean isEmpty() {
        if (tail == header) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Integer QueueLength() {
        return size;
    }

    public Object GetHead() {
        return arr[header];
    }

    public Boolean EnQueue(Object e) {
        if (size >= length) {
            return Boolean.FALSE;
        }

        if (header == tail) {//先判断是不是空的 如果是 重置头尾指针  ,不然这个队列就只能用一次了
            header = 0;
            arr[header] = e;
            tail = 1;
            size++;
            return Boolean.TRUE;
        } else {
            arr[tail] = e;
            tail = tail + 1;
            size++;
            return Boolean.TRUE;
        }


    }

    public Object DeQueue() {
        if (header == tail) {
            return null;
        }
        T e = (T) arr[header];
        header = header + 1;
        size--;
        return e;
    }


    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        arrayQueue.InitQueue();
        arrayQueue.EnQueue(1);
        arrayQueue.EnQueue(2);
        arrayQueue.EnQueue(3);
        arrayQueue.EnQueue(4);
        arrayQueue.EnQueue(5);
        arrayQueue.EnQueue(6);
        Integer s = arrayQueue.size;
        System.out.println(arrayQueue.GetHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(arrayQueue.DeQueue());
        }
        System.out.println(arrayQueue.isEmpty());
        arrayQueue.EnQueue(1);
        arrayQueue.EnQueue(2);
        arrayQueue.EnQueue(3);
        arrayQueue.EnQueue(4);
         s = arrayQueue.size;
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(arrayQueue.DeQueue());
        }
        System.out.println(arrayQueue.isEmpty());
    }
}
