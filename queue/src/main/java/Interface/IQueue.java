package Interface;

/**
 * 队列接口
 * <p>
 * 队列是一种先进先出的线性表
 * 只能在表的一端进行插入，另一段进行删除
 * 允许插入的一端叫队尾，允许删除的一端叫队头（）
 *
 *
 * ps:还存在一种 双端队列 即队头和队尾都可以进行插入和删除的操作，队头和队尾在这里叫端点
 * 以及输入受限的双端队列（一端输入和删除，另一端只能删除）
 * 输出受限的双端队列（一端输入和删除，另一端只能输入）
 * 但是双端队列应用不广泛 不在此做讨论
 */
public interface IQueue<T> {

    /**
     * 初始化队列 构造一个空队列
     */
    IQueue InitQueue();

    /**
     * 销毁队列
     */
    IQueue DestroyQueue();

    /**
     * 清空队列
     */
    IQueue ClearQueue();

    /**
     * 队列判空
     */
    Boolean isEmpty();

    /**
     * 返回队列长度
     */
    Integer QueueLength();

    /**
     * 返回队列头元素
     */
    T GetHead();

    /**
     * 插入队尾元素
     */
    Boolean EnQueue(T e);

    /**
     * 删除队头元素  即出队
     */
    T DeQueue();

}
