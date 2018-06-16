package Node;

//双向链表的头节点
public class DuLNode<T> {
    private T date;
    //前指针
    private DuLNode  prior;
    //后指针
    private DuLNode  next;

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public DuLNode getPrior() {
        return prior;
    }

    public void setPrior(DuLNode prior) {
        this.prior = prior;
    }

    public DuLNode getNext() {
        return next;
    }

    public void setNext(DuLNode next) {
        this.next = next;
    }
}
