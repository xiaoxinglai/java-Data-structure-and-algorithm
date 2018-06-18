package stack;

public class LinkNode<T> {
    private T date;
    private  LinkNode next;

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
