package impl;

import Interface.IQueue;

/**
 * 循环队列
 * <p>
 * 注意：判空和判满的两种情况：
 * 情况1.另设一个标识位区别队列是空还是满
 * 情况2.少用一个元素空间，约定以"队列头指针在队尾指针的下一位位置上" 作为队列满的标志
 *
 * @param <T>
 */

public class CycQueue<T> implements IQueue {

    private Integer MAXSIZE = 6; //循环队列最大长度为7  0~6
    private Object[] arr;
    private Integer front;//头指针，若队列不为空，指向队头元素
    private Integer rear; //尾指针，若队列不为空，指向队列尾元素的下一个位置

    public IQueue InitQueue() {
        arr = new Object[MAXSIZE];
        front = rear = 0;
        return this;
    }

    public IQueue DestroyQueue() {
        arr = null;
        rear = front = 0;
        return this;
    }

    public IQueue ClearQueue() {
        rear = front = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        return this;
    }

    public Boolean isEmpty() {
        if (front == rear) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Integer QueueLength() {
        return (rear - front + MAXSIZE) % MAXSIZE; //求环形队列的元素个数
    }

    public Object GetHead() {
        return arr[front];
    }

    //入队前判满
    public Boolean EnQueue(Object e) {
        //队列头指针在队尾指针的下一位位置上  说明满了
        if ((rear + 1) % MAXSIZE == front) {
            return Boolean.FALSE;
        }
        arr[rear] = e;
        rear = (rear + 1) % MAXSIZE;
        return Boolean.TRUE;
    }

    //出队前判空
    public Object DeQueue() {
        if (rear == front) {
            return null;
        }
        T e = (T) arr[front];
        front = (front + 1) % MAXSIZE;
        return e;
    }


    public static void main(String[] args) {
        CycQueue<Integer> cycQueue = new CycQueue<Integer>();
        cycQueue.InitQueue();
        cycQueue.EnQueue(1);
        cycQueue.EnQueue(2);
        cycQueue.EnQueue(3);
        cycQueue.EnQueue(4);
        cycQueue.EnQueue(5);
        cycQueue.EnQueue(6);

        Integer s = cycQueue.QueueLength();
        System.out.println(cycQueue.GetHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(cycQueue.DeQueue());
        }
        System.out.println(cycQueue.isEmpty());

        cycQueue.EnQueue(4);
        cycQueue.EnQueue(5);
        cycQueue.EnQueue(6);
        s = cycQueue.QueueLength();
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(cycQueue.DeQueue());
        }
        System.out.println(cycQueue.isEmpty());

    }
}
