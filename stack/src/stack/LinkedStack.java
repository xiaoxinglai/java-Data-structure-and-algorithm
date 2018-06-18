package stack;

public class LinkedStack<T> {
    //栈底指针
    private LinkNode base;
    //栈顶指针
    private LinkNode top;
    //栈的当前大小
    private Integer size;


    /**
     * 栈的初始化
     */
   public void InitStack() {

        top = base = new LinkNode();
        size = 0;
    }


    /**
     * 获取栈的大小
     */
    public int StackLenth() {
        return this.size;
    }

    /**
     * 获取栈顶元素
     */
    public T GetTop() {
        return (T) top.getDate();
    }

    /**
     * 入栈
     */
    public void Push(T e) {
        LinkNode<T> newNode = new LinkNode<T>();
        newNode.setDate(e);
        top.setNext(newNode);
        top = newNode;
        size++;
    }

    /**
     * 出栈
     */
    public   T Pop() {

        if (base==top){
            return  null;
        }

        T e=(T) top.getDate();
        size--;
        LinkNode nowNode=base;

        while (nowNode.getNext().getNext()!=null){
            nowNode=nowNode.getNext();
        }
        nowNode.setNext(null);
        top=nowNode;
        return  e;

    }


    /**
     * 获取指定位置的链表中的元素
     */
    public  T getLocateElem(int i){
        LinkNode nowNode=base;
        int j=1;
        while (nowNode!=null&&nowNode.getNext()!=null){
            nowNode=nowNode.getNext();
            if (i==j){
                return  (T) nowNode.getDate();
            }
            j++;
        }

        return  null;
    }

    /**
     * 遍历栈
     */
    public  void print(){

        for (int i = size; i >0 ; i--) {
            System.out.print(this.getLocateElem(i));
        }
        System.out.println();
    }


}
