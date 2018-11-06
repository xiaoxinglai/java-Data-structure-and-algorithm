package stack;

/**
 * 栈是限定在表尾进行插入或者删除操作的线性表
 * 因为表尾叫栈顶
 * 表头叫栈底
 * 不含元素的叫空栈
 * 特点是后进先出LIFO
 */
public class stack<T> {
    //栈底指针
    private int base;
    //栈顶指针
    private int top;
    //栈当前大小
    int size;
    //存储栈的数组
    private T[] list;
    //初始大小
    private  Integer STACKINCREMENT = 10;

    /**
     * 初始化 构造一个空栈
     */
    public void InitStack() {
         list = (T[]) new Object[STACKINCREMENT];
        base = top = 0;
        size = 0;
        return;
    }

    /**
     * 销毁栈
     */
    public  void DestroyStack() {
        list = null;
        base = top = 0;
        size = 0;
    }

    /**
     * 清空栈
     */
    public void ClearStack() {
        list = (T[]) new Object[STACKINCREMENT];

        base = top = 0;
        size = 0;
    }

    /**
     * 判空
     */
    public Boolean StackEmpty() {
        if (size == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 返回栈的长度
     */
    public int StackLength() {
        return size;
    }

    /**
     * 查看栈顶元素
     */
    public T getTop() {
        if (!StackEmpty()){
            return list[top-1];
        }else {
            return null;
        }
    }

    /**
     * 插入栈顶元素
     */
    public Boolean push(T e) {
        //先判断栈有没有满 如果满了要扩容
        if (top-base>=STACKINCREMENT){
            //扩容
            addSize();
        }

        this.list[top]=e;
        top++;
        size++;
        return Boolean.TRUE;
    }

    /**
     * 出栈
     */
    public  T Pop() {
        //判断是否是空
        if (top==base){
            return  null;
        }
       T e= this.list[top-1];
       top--;
       size--;

        return e;
    }

    /**
     * 扩容
     */
    public void addSize(){
        STACKINCREMENT= (int) (STACKINCREMENT*1.5);
        T[] list = (T[]) new Object[STACKINCREMENT];

        for (int i = 0; i <this.size ; i++) {
            list[i]=this.list[i];
        }
        this.list=list;
        return;
    }

    /**
     * 遍历栈
     */
    public void print(){
        while(getTop()!=null){
            System.out.print(Pop());
        }
        System.out.println();
    }
}
