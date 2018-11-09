package Node;

//节点对象
public class LNode<T> {
    //数据
    private T data;
    //下一个节点
    private  LNode<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LNode<T> getNext() {
        return next;
    }

    public void setNext(LNode<T> next) {
        this.next = next;
    }
}
