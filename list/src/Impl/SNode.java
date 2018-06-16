package Impl;

//静态链表的节点对象
public class SNode<T> {
    //数据
    private  T date;
    //游标
    private  int cur;

    public SNode() {
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }
}
