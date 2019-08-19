   * [java-Data-structure-and-algorithm](#java-data-structure-and-algorithm)
   * [java数据结构和算法实现练习](#java数据结构和算法实现练习)
      * [list线性表](#list线性表)
         * [线性表接口](#线性表接口)
            * [数组型线性表实现](#数组型线性表实现)
            * [链表型线性表的实现](#链表型线性表的实现)
               * [头节点](#头节点)
               * [链表](#链表)
            * [静态链表的实现](#静态链表的实现)
               * [节点](#节点)
               * [静态链表](#静态链表)
            * [双向链表的实现](#双向链表的实现)
               * [头节点](#头节点-1)
               * [双向链表](#双向链表)
            * [循环链表的实现](#循环链表的实现)
         * [链表的应用之一元多项式相加](#链表的应用之一元多项式相加)
      * [stack栈](#stack栈)
         * [栈应用之行编辑器](#栈应用之行编辑器)
         * [迷宫求解](#迷宫求解)
         * [表达式求值](#表达式求值)
         * [汉诺塔问题](#汉诺塔问题)
         * [队列的接口和元素节点](#队列的接口和元素节点)
         * [java队列的链表实现](#java队列的链表实现)
         * [java数组队列的实现](#java数组队列的实现)
         * [java循环队列的实现](#java循环队列的实现)
         * [队列应用之离散事件模拟](#队列应用之离散事件模拟)
      * [String 串](#string-串)
         * [串的接口定义](#串的接口定义)
         * [串的数组实现](#串的数组实现)
         * [串的三种存储结构分析](#串的三种存储结构分析)
            * [定长顺序存储](#定长顺序存储)
            * [堆分配存储表示](#堆分配存储表示)
            * [串的块链存储表示](#串的块链存储表示)
         * [串的匹配算法](#串的匹配算法)
            * [遍历算法](#遍历算法)
      * [tree  树](#tree--树)
      * [diagram 图](#diagram-图)
      * [sorting 排序查找](#sorting-排序查找)

# java-Data-structure-and-algorithm
# java数据结构和算法实现练习

## `list`线性表
### 线性表接口
```
package IntefaceList;

public interface IList<T> {

    /**
     * 构造一个空的线性表 进行初始化
     */
    void InitList();

    /**
     * 销毁线性表
     */
    IList DestroyList();

    /**
     * 将线性表重置为空表
     */
    IList ClearList();

    /**
     * 判断线性表是否为空
     */
    Boolean ListEmpty();

    /**
     * 返回线性表中元素的个数
     */
    Integer ListLength();

    /**
     * 获取线性表中第i个元素
     */
    T GetElem(Integer i);

    /**
     * 根据元素获取它的位序，如果有多个符合，则返回第一个，如果没有则返回0
     */
    Integer LocateElem(T e);

    /**
     * 获取该元素的上一个元素
     */
    T PriorElem(T e);
    /**
     *获取该元素的下一个元素
     */
    T NextElem(T e);
    /**
     * 在指定位置插入元素
     */
    IList ListInsert(Integer i, T e);

    /**
     *删除指定位置的元素
     */
    IList ListDelete(Integer i);

    /**
     * 遍历所有的元素
     */
    void  ListTraverse();

    /**
     * 合并两个线性表 合并不重复的元素
     */
    IList Union(IList list);

    /**
     * 按照递增顺序合并两个线性表
     */
    IList MergeList(IList list);

    /**
     * 在尾部新增元素
     */
    void add(T e);

}

```
#### 数组型线性表实现
```$xslt
package Impl;
import IntefaceList.IList;
public class SequenceList<T> implements IList {


    private T[] arr;

    //数组当前容量(里面实际包含的元素个素)
    private Integer size = 0;
    //数组当前大小
    private Integer Length = 4;


    @Override
    public void InitList() {

        this.arr = (T[]) new Object[Length];
    }

    @Override
    public IList DestroyList() {
        arr = null;
        size=0;
        return this;  //这里应该直接让list对象为null
    }

    @Override
    public IList ClearList() {
        this.arr = (T[]) new Object[Length];
        size=0;
        return this;
    }

    @Override
    public Boolean ListEmpty() {
        if (size > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Integer ListLength() {
        return size;
    }

    @Override
    public Object GetElem(Integer i) {
        if (i > size) {
            return null;
        }
        return arr[i - 1];
    }

    @Override
    public Integer LocateElem(Object e) {

        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return i + 1;
            }
        }

        return -1;
    }

    @Override
    public Object PriorElem(Object e) {
        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return arr[i - 1];
            }
        }

        return -1;
    }

    @Override
    public Object NextElem(Object e) {
        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return arr[i + 1];
            }
        }

        return -1;
    }

    @Override
    public IList ListInsert(Integer i, Object e) {
        if (i>size){
            return null;
        }

        size++;
        addsize();
        for (int j = i-1; j <this.size; j++) {
            arr[size-j]=arr[size-j-1];
            
        }
        arr[i - 1] = (T) e;
        return this;
    }

    @Override
    public IList ListDelete(Integer i) {
        size--;
        for (int i1 = i - 1; i1 + 1 < arr.length; i1++) {
            arr[i1] = arr[i1 + 1];
        }
        return this;
    }

    @Override
    public void ListTraverse() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.print(arr[i]);
                System.out.print(",");
            }
        }
        System.out.println();
    }

    /**
     * 合并不重复的元素
     *
     * @param list
     * @return
     */
    @Override
    public IList Union(IList list) {

        for (int i = 1; i <= list.ListLength(); i++) {
            if (this.LocateElem(list.GetElem(i)) == -1) {
                this.add(list.GetElem(i));
            }
        }

        return this;
    }

    /**
     * 合并两个列表 从小到大
     *
     * @param list
     * @return
     */
    @Override
    public IList MergeList(IList list) {

        //已知两个表都是递增的顺序
        //借助新的第三张表
        T[] NewArr = (T[]) new Object[Length + list.ListLength()];
        int pa = 1;
        int pb = 1;
        int pc = 0;
        while (pa <= list.ListLength() && pb <= this.ListLength()) {
            if ((int) list.GetElem(pa) <= (int) this.GetElem(pb)) {
                NewArr[pc] = (T) list.GetElem(pa);
                pc++;
                pa++;
            } else {
                NewArr[pc] = (T) this.GetElem(pb);
                pc++;
                pb++;
            }

        }


        while (pa<=list.ListLength()){

            NewArr[pc] = (T) list.GetElem(pa);
            pc++;
            pa++;
        }


        while (pb<=this.ListLength()){
            NewArr[pc] = (T) this.GetElem(pb);
            pc++;
            pb++;

        }

        this.arr=NewArr;

        size=size+ list.ListLength();

        return this;
    }

    @Override
    public void add(Object e) {
        arr[size] = (T) e;
        size++;
        addsize();
    }

    /**
     * 数组扩容
     */
    private void addsize() {
        if (size / (float) arr.length > 0.75) {
            Length = (int) (size * 1.5);
            T[] Newarr = (T[]) new Object[(Length)];

            for (int a = 0; a < arr.length; a++) {
                Newarr[a] = arr[a];
            }

            this.arr = Newarr;
        }
    }
}
/*
* 可以看出来，数组型的列表，时间都花在插入和删除上了，平均每次插入或者删除一个元素，需要移动一半的元素（根据期望值算出）
* 优点是取元素快，只要知道坐标 就可以
* */
```

***

#### 链表型线性表的实现

##### 头节点

```
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

```
##### 链表

```$xslt
package Impl;

import IntefaceList.IList;
import Node.LNode;

public class LinkedList<T> implements IList {

    //头节点
    private LNode<T> tnode;
    private Integer length = 0;
    //当前节点
    private LNode<T> cnode;

    //尾巴节点
    private LNode<T> taillNode;

    public LNode<T> getTnode() {
        return tnode;
    }

    @Override
    public void InitList() {
        tnode = new LNode<>(); //初始化头节点
        taillNode = tnode;
    }

    @Override
    public IList DestroyList() {

        tnode.setNext(null);
        length = 0;  //这里应该整个list对象为null 但java没有析构函数
        taillNode = tnode;
        return this;
    }

    @Override
    public IList ClearList() {
        cnode = tnode.getNext();
        while (cnode.getNext() != null) {
            cnode = cnode.getNext();
            cnode.setData(null);
            length--;
        }
        taillNode = tnode;
        return this;
    }

    @Override
    public Boolean ListEmpty() {
        if (length != 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    @Override
    public Integer ListLength() {
        return this.length;
    }

    @Override
    public Object GetElem(Integer i) {
        cnode = tnode.getNext();
        while (i - 1 > 0) {
            cnode = cnode.getNext();
            i--;
        }
        return cnode.getData();
    }

    @Override
    public Integer LocateElem(Object e) {
        cnode = tnode.getNext();
        int i = 1;
        while (cnode != null && !e.equals(cnode.getData())) {
            i++;
            cnode = cnode.getNext();
        }
        if (cnode == null) {
            return -1;
        }
        return i;
    }

    @Override
    public Object PriorElem(Object e) {
        cnode = tnode.getNext();

        LNode<T> pnode = null;

        while (!e.equals(cnode.getData())) {
            pnode = cnode; //上一个节点
            cnode = cnode.getNext();
        }
        return pnode.getData();

    }

    @Override
    public Object NextElem(Object e) {
        cnode = tnode.getNext();
        while (!e.equals(cnode.getData())) {
            cnode = cnode.getNext();
        }
        return cnode.getNext().getData();

    }

    @Override
    public IList ListInsert(Integer i, Object e) {

        LNode<T> newNode = new LNode<>();
        newNode.setData((T) e);
        cnode = tnode.getNext();


        if (i==1){
            tnode.setNext(newNode);
            newNode.setNext(cnode);
            length++;
            return this;
        }



        /**
         * 在最后一位插入
         */
        if (i.equals(this.length)) {

            newNode.setNext(null);
            newNode.setData(taillNode.getData());
            taillNode.setData((T) e);
            taillNode.setNext(newNode);
            taillNode = newNode;
            return this;
        }




        while (i > 2) {
            cnode = cnode.getNext();
            i--;
        }

        newNode.setNext(cnode.getNext());
        cnode.setNext(newNode);

        length++;


        return this;
    }

    //todo 修改后的完美删除
    @Override
    public IList ListDelete(Integer i) {
        cnode=tnode.getNext();
        if (cnode==null){
            return null;
        }

        int j=2;
        if (i==1){
            tnode.setNext(cnode.getNext());
            length--;
            return this;
        }

        while (cnode.getNext()!=null){
            if (i==j){
                cnode.setNext(cnode.getNext().getNext());
                length--;
                return this;
            }
            j++;
            cnode=cnode.getNext();
        }

        if (i==j){
            cnode.setNext(null);
            length--;
            taillNode=cnode;
        }

        return null;
    }

    @Override
    public void ListTraverse() {
        cnode = tnode.getNext();
        while (cnode.getNext() != null) {
            if (cnode.getData() != null) {
                System.out.print(cnode.getData() + ",");
            }
            cnode = cnode.getNext();
        }
        System.out.print(cnode.getData() + ",");
        System.out.println();

    }

    /**
     * 合并两个线性表中不重复的元素
     *
     * @param list
     * @return
     */
    @Override
    public IList Union(IList list) {
        for (int i = 1; i <= list.ListLength(); i++) {
            if ((this.LocateElem(list.GetElem(i))) == -1) {

                LNode<T> newNode = new LNode<>();
                taillNode.setNext(newNode);
                newNode.setData((T) list.GetElem(i));
                newNode.setNext(null);
                taillNode = newNode;
                this.length++;
            }
        }

        return this;
    }

    @Override
    public IList MergeList(IList list) {
        LinkedList<T> LB = (LinkedList<T>) list;
        //在已知道两个列表都是非递减序列的情况下合并
        //要求合并之后还是非递减序列
        //两个链表归并 要想不和数组型线性表一样借助第三个表实现 就得用到指针 知道节点的位置
        LNode<T> pb = LB.getTnode().getNext();
        LNode<T> pa = this.getTnode().getNext();
        cnode = tnode;  //当前节点
        this.length = this.length + list.ListLength();

        while (pa != null && pb != null) {
            if ((Integer) pa.getData() <= (Integer) pb.getData()) {
                cnode.setNext(pa);
                cnode = pa;
                pa = pa.getNext();
            } else {
                cnode.setNext(pb);
                cnode = pb;
                pb = pb.getNext();
            }

        }

        if (pa == null) {
            cnode.setNext(pb);
        } else {
            cnode.setNext(pa);
        }

        return list;
    }

    @Override
    public void add(Object e) {
        cnode = tnode.getNext();

        LNode<T> newNode = new LNode<>();
        newNode.setNext(null);
        newNode.setData((T) e);

        if (cnode != null) {
            while (cnode.getNext() != null) {
                cnode = cnode.getNext();
            }

            cnode.setNext(newNode);

        } else {
            tnode.setNext(newNode);
        }
        taillNode = newNode;

        this.length++;


    }


    /**
     * 链表的逆置   就是每插入一个 都是在尾部往前插入
     */
    public void Reversal() {
        cnode = tnode.getNext();
        if (cnode.getNext() == null) {
            return;
        }
        //第一个节点
        LNode<T> firstNode = new LNode<>();

        firstNode.setNext(null);
        firstNode.setData(cnode.getData());
        tnode.setNext(firstNode);

        cnode = cnode.getNext();

        while (cnode != null) {   //因为最后一个节点的next是null

            LNode<T> newNode = new LNode<>();
            newNode.setData(cnode.getData());
            newNode.setNext(tnode.getNext());
            tnode.setNext(newNode);
            cnode = cnode.getNext();
        }


    }
    /**
     * 尾节点的好处  可以更快的找到尾部节点  缺点就是增加了代码的复杂度  需要在插入和删除时候对尾节点进行维护
     */
}

```

***

#### 静态链表的实现

##### 节点
```$xslt

package Node;

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

```
##### 静态链表
```$xslt
package Impl;

import IntefaceList.IList;
import Node.SNode;

/**
 * 静态链表
 * 借用一维数组来描述线性链表
 * 这种描述方法便于在不设指针类型的高级程序设计语言中使用链表结构
 * 数组的一个分量表示一个节点，第0个分量看作是头节点  游标代替指针指示节点在数组中的位置
 * <p>
 * 与数组型线性表的区别
 * 1.有节点 每个节点包含 date 和 next游标
 * 2.需要自己实现malloc和free函数
 * 思路：将所有未被使用的数组元分量以及被删除的分量用游标链成一个备用的链表，每当进行插入时 从备用链表中取第一个节点
 * 作为待插入的新节点，在删除的时候将从链表中删除下来的节点链接到备用链表上
 */
public class StaticLinkedLIst<T> implements IList {
    //链表的最大长度
    private final Integer MAXSIZE = 100;

    //数组空间   备用链表和正在使用的链表都在同一个数组里面
    private SNode<T>[] SList = new SNode[MAXSIZE];

    //指向当前正在使用的链表的头节点的游标
    private int curr = 0;


    @Override
    public void InitList() {
        //将数组各分量链接成一个备用链表 SList[0].cur为头指针
        //0表示空指针
        for (int i = 0; i < MAXSIZE - 1; i++) {
            SList[i] = new SNode();
            SList[i].setCur(i + 1);
        }
        SList[MAXSIZE - 1] = new SNode();
        SList[MAXSIZE - 1].setCur(0);
    }


    //malloc函数 分配空间
    public int Malloc() {
        //从备用链表中取出一个元素 并返回该元素的游标

        int i = SList[0].getCur();
        if (i != 0) {
            SList[0].setCur(SList[i].getCur());
        }

        return i;
    }

    //free函数  释放空间
    public void Free(int k) {
        // 将下标为k的节点回收到备用链表中
        //备用链表的头节点 SList[0]
        SList[k].setCur(SList[0].getCur());
        SList[0].setCur(k);
    }

    @Override
    public IList DestroyList() {
        return null;
    }

    @Override
    public IList ClearList() {
        return null;
    }

    @Override
    public Boolean ListEmpty() {
        return null;
    }

    @Override
    public Integer ListLength() {
        return null;
    }

    @Override
    public Object GetElem(Integer i) {
        int k = SList[curr].getCur();

        for (int j = 1; j < i; j++) {
            k = SList[k].getCur();
        }
        return SList[k].getDate();
    }

    @Override
    public Integer LocateElem(Object e) {
        //获取第一个节点的下标  0表示最后一个节点的next游标的值
        int i = SList[curr].getCur();
        while (i != 0 && !e.equals(SList[i].getDate())) {
            i = SList[i].getCur();
        }
//返回的是该节点所在的下标
        return i;
    }


    @Override
    public Object PriorElem(Object e) {
        return null;
    }

    @Override
    public Object NextElem(Object e) {
        return null;
    }

    @Override
    public IList ListInsert(Integer i, Object e) {
        return null;
    }

    @Override
    public IList ListDelete(Integer i) {
        if (i == 0 || i == 1) {
            return null;

        }
        int k=curr;
        while (!i.equals(SList[k].getCur())){
            k=SList[k].getCur();
        }
        SList[k].setCur(SList[i].getCur());
        Free(i);

        return this;
    }

    @Override
    public void ListTraverse() {
        if (curr == 0) {
            return;
        }
        int k = SList[curr].getCur();
        while (SList[k].getCur() != 0) {
            System.out.print(SList[k].getDate() + ",");
            k = SList[k].getCur();
        }
        System.out.print(SList[k].getDate() + ",");
        System.out.println();

    }

    @Override
    public IList Union(IList list) {
        return null;
    }


    /**
     * 计算集合(A-B)U(B-A)之后的值
     * 其实就是合并 并把两个集合共有的元素都删掉
     *
     * @param list
     * @return
     */
    public void Differnce(IList list) {

        int a=curr;

        while (SList[a].getCur()!=0) {

            a=SList[a].getCur();
            T e = SList[a].getDate();

            if (list.LocateElem(e) != 0){
                list.ListDelete(list.LocateElem(e));
            }else {
                list.add(e);
            }


        }


    }


    @Override
    public IList MergeList(IList list) {

        return null;
    }

    @Override
    public void add(Object e) {

        //从备用链表里取出一个可以用的位置
        int i = Malloc();

        //如果没有头节点则 初始化头节点
        if (curr == 0) {
            int j = Malloc();
            SList[j].setDate((T) e);
            SList[j].setCur(0);
            curr = i;
            SList[curr].setCur(j);
            return;
        }

        int k = SList[curr].getCur();
        while (SList[k].getCur() != 0) {
            k = SList[k].getCur();
        }

        SList[i].setDate((T) e);
        SList[i].setCur(0);
        SList[k].setCur(i);

        return;
    }
}


```

*** 

#### 双向链表的实现

##### 头节点
```$xslt
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

```
##### 双向链表
```$xslt

package Impl;

import IntefaceList.IList;
import Node.DuLNode;

public class DuLinkedList implements IList {

    private DuLNode HeaderNode;
    //当前节点
    private DuLNode nowNode;


    @Override
    public void InitList() {
        HeaderNode = new DuLNode();
        HeaderNode.setNext(null);
        HeaderNode.setPrior(null);
    }

    @Override
    public void add(Object e) {
        DuLNode newNode = new DuLNode();
        newNode.setDate(e);
        newNode.setNext(null);
        nowNode = HeaderNode.getNext();
        if (nowNode == null) {
            HeaderNode.setNext(newNode);
            newNode.setPrior(HeaderNode);
            return;
        }
        while (nowNode.getNext() != null) {
            nowNode = nowNode.getNext();
        }
        nowNode.setNext(newNode);
        newNode.setPrior(nowNode);
    }

    @Override
    public void ListTraverse() {
        nowNode = HeaderNode.getNext();
        if (nowNode == null) {
            return;
        }
        while (nowNode != null) {
            System.out.print(nowNode.getDate() + ",");
            nowNode = nowNode.getNext();
        }
        System.out.println();
    }

    public void UnListTraverse() {
        nowNode = HeaderNode.getNext();
        if (nowNode == null) {
            return;
        }
        while (nowNode.getNext() != null) {
            nowNode = nowNode.getNext();
        }

        while (nowNode.getPrior() != HeaderNode) {
            System.out.print(nowNode.getDate() + ",");
            nowNode = nowNode.getPrior();
        }
        System.out.print(nowNode.getDate() + ",");
        System.out.println();


    }


    @Override
    public Object GetElem(Integer i) {
        nowNode = HeaderNode.getNext();
        if (i == 1) {
            return nowNode.getDate();
        }
        for (int j = 2; j <= i; j++) {
            nowNode = nowNode.getNext();
        }

        return nowNode.getDate();
    }

    @Override
    public Integer LocateElem(Object e) {
        nowNode = HeaderNode.getNext();
        int i = 1;
        while (!e.equals(nowNode.getDate())) {
            nowNode = nowNode.getNext();
            i++;
        }

        return i;
    }

    @Override
    public Object PriorElem(Object e) {
        nowNode = HeaderNode.getNext();
        while (!e.equals(nowNode.getDate())) {
            nowNode=nowNode.getNext();
        }

        return nowNode.getPrior().getDate();
    }

    @Override
    public Object NextElem(Object e) {
        nowNode = HeaderNode.getNext();
        while (!e.equals(nowNode.getDate())) {
            nowNode=nowNode.getNext();
        }
        return nowNode.getNext().getDate();
    }

    @Override
    public IList ListInsert(Integer i, Object e) {
        DuLNode newNode=new DuLNode();
        newNode.setDate(e);
        nowNode=HeaderNode.getNext();
        if (i==1){
            newNode.setPrior(HeaderNode);
            newNode.setNext(nowNode);
            nowNode.setPrior(newNode);

            HeaderNode.setNext(newNode);
            return this;
        }

        for (int j=2;j<i;j++){
            nowNode=nowNode.getNext();
        }
        newNode.setNext(nowNode.getNext());
        nowNode.getNext().setPrior(newNode);
        newNode.setPrior(nowNode);
        nowNode.setNext(newNode);



        return this;
    }

    @Override
    public IList ListDelete(Integer i) {
        nowNode=HeaderNode.getNext();
        if (i==1){
            HeaderNode.getNext().getNext().setPrior(HeaderNode);
            HeaderNode.setNext(HeaderNode.getNext().getNext());
            return null;
        }

        for (int j = 2; j <i ; j++) {
            nowNode=nowNode.getNext();
        }
        nowNode.getNext().getNext().setPrior(nowNode);

        nowNode.setNext(nowNode.getNext().getNext());


        return this;
    }


    @Override
    public IList DestroyList() {
        return null;
    }

    @Override
    public IList ClearList() {
        return null;
    }

    @Override
    public Boolean ListEmpty() {
        return null;
    }

    @Override
    public Integer ListLength() {
        return null;
    }


    @Override
    public IList Union(IList list) {
        return null;
    }

    @Override
    public IList MergeList(IList list) {
        return null;
    }


}

```

#### 循环链表的实现

```$xslt
package Impl;

import IntefaceList.IList;
import Node.LNode;

//循环链表
public class CircLinkedList<T> implements IList {
    /**
     * 循环链表是另一种形式的链式存储结构 它的特点是表中最后一个节点的指针 指向头节点
     * 整个链表形成一个环，从表中任意节点出发均可找到表中其他节点
     * <p>
     * 循环链表为空表的判断：头节点的指针指向头节点
     * 循环链表的操作和线性链表基本一致，差别仅在于算法中的循环条件不是p或者p->next是否为空
     * 而且它们是否等于头节点
     */

    //头节点
    private LNode<T> HeaderNode;
    //长度
    private Integer Length;

    //当前节点
    private LNode<T> nowNode;


    @Override
    public void InitList() {
        HeaderNode = new LNode<>();
        //循环链表
        HeaderNode.setNext(HeaderNode);
        Length = 0;
        //千万不要TailNode=HeaderNode  这样，因为java对象传递的是引用（地址）  会导致tailNode和HeaderNode都是同一个对象

    }

    @Override
    public IList DestroyList() {
        return null;
    }

    @Override
    public IList ClearList() {
        return null;
    }

    @Override
    public Boolean ListEmpty() {
        return null;
    }

    @Override
    public Integer ListLength() {
        return this.Length;
    }

    @Override
    public Object GetElem(Integer i) {

        nowNode = HeaderNode.getNext();

        int j = 1;
        while (nowNode != HeaderNode) {
            if (j == i) {
                return nowNode.getData();
            } else {
                nowNode = nowNode.getNext();
            }
        }
        return null;
    }

    @Override
    public Integer LocateElem(Object e) {
        nowNode = HeaderNode.getNext();
        int i = 1;
        while (nowNode != HeaderNode) {
            if (nowNode.getData().equals(e)) {
                return i;
            }
            i++;
            nowNode = nowNode.getNext();
        }
        return -1;
    }


    @Override
    public void add(Object e) {

        LNode NewNode = new LNode();
        NewNode.setData(e);
        NewNode.setNext(HeaderNode);

        //判断是不是第一个节点
        nowNode = HeaderNode.getNext();
        if (nowNode == HeaderNode) {
            //说明是第一个节点
            HeaderNode.setNext(NewNode);
            return;
        }

        while (nowNode.getNext() != HeaderNode){
            nowNode = nowNode.getNext();
        }

        nowNode.setNext(NewNode);


        Length++;
    }

    @Override
    public IList ListInsert(Integer i, Object e) {
        LNode newNode = new LNode();
        newNode.setData(e);
        nowNode = HeaderNode.getNext();
        if (i==1){
            HeaderNode.setNext(newNode);
            newNode.setNext(nowNode);
            return this;
        }

        for (int j = 2; j < i; j++) {
            nowNode = nowNode.getNext();
        }

        newNode.setNext(nowNode.getNext());
        nowNode.setNext(newNode);

        Length++;
        return this;
    }

    @Override
    public IList ListDelete(Integer i) {

        nowNode = HeaderNode.getNext();
        for (int j = 2; j < i; j++) {
            nowNode = nowNode.getNext();
        }
        nowNode.setNext(nowNode.getNext().getNext());

        return this;
    }

    @Override
    public void ListTraverse() {
        nowNode = HeaderNode.getNext();
        while (nowNode != HeaderNode) {
            System.out.print(nowNode.getData() + ",");
            nowNode=nowNode.getNext();
        }

        System.out.println();

    }


    @Override
    public Object PriorElem(Object e) {
        return null;
    }

    @Override
    public Object NextElem(Object e) {
        return null;
    }


    @Override
    public IList Union(IList list) {
        return null;
    }

    @Override
    public IList MergeList(IList list) {
        return null;
    }


}
```
### 链表的应用之一元多项式相加



## `stack`栈
  ### 栈应用之行编辑器  
  '#'表示退格 '@'表示退行  
  比如  whli##ilr#e（s#*s）  
  outcha@putchar(*s=#++)    
  实际有效的是如下  
  while(*s)  
  putchar(*s++)  
  思路：用栈存储输入，直到输入结束为止'\n'表示，则输出 ，期间如果遇到#则出栈 ，遇到@则清空栈
 
  代码如下：

```
public class LineEdit {
    public static void main(String[] args) throws IOException {
        stack stack = new stack();
        stack.InitStack();

        try {
            char ch = (char) System.in.read();
            while (ch != '\n') {
                if (ch == '#') {
                    stack.Pop();
                } else if (ch == '@') {
                    stack.ClearStack();
                } else {
                    stack.push(ch);
                }
                ch = (char) System.in.read();
            }
            List<Character> list=new ArrayList<>();
            while(stack.getTop()!=null){
                list.add((Character)stack.Pop());
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(list.size()-1-i));
            }

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            throw e;
        }


    }
}
```
 
输出如下：
>whli##ilr#e(s#*s)  
while(*s)

***

### 迷宫求解
```
package stack.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用java提供的stack实现
 */
public class MazePathStack {

    static class Spath { //行走的单位格子
        private int x;
        private int y;
        private int di; //方向 0 1 2 3 表示右 下 左 上

        public Spath(int x, int y, int di) {
            this.x = x;
            this.y = y;
            this.di = di;
        }

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return true;
            }

            if (obj instanceof Spath) {

                if ((((Spath) obj).x == this.x) && (((Spath) obj).y == this.y)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }
    }

    private static int[][] mg = MazePathStack.creatMG();//初始化时候生成迷宫
    private static Stack<Spath> spathStack = new Stack();//路径栈


    public static void main(String[] args) {
        Spath spath = new Spath(0, 1, 0); //初始坐标为入口 （0，1）方向为向右
        do {
            if (isPass(spath)) { //判断该坐标能否通行

                //判断该坐标是否是终点
                if (isEnd(spath)) {
                    spathStack.push(spath);
                    System.out.println("到达终点，坐标为:" + "(" + spath.x + "," + spath.y + ")");
                    System.out.println("路径为");
                    printAll();
                    return;
                }

                //判断该坐标是否已经来过
                if (isRecord(spath)) {
                    //来过 则放弃栈中最新的坐标 后退一格，换个方向重试
                    spathStack.pop();
                    spath = spathStack.peek();
                    spath = next(spath, false);//继续下一步 换个方向
                } else {
                    //没有来过 压入栈中 记录下来
                    spathStack.push(spath);
                    spath = next(spath, true); //继续下一步 沿原来方向
                }

            } else {
                spath = spathStack.peek(); //后退一步
                spath = next(spath, false);//换个方向 继续下一步
            }

        } while (!spathStack.empty());


    }

    private static void printAll() {
        //打印路径
        List<Spath> spaths=new ArrayList<>();
        while(!spathStack.empty()){
            spaths.add(spathStack.pop());
        }
        for (int i = spaths.size()-1; i >=0 ; i--) {
            System.out.println("("+spaths.get(i).x+","+spaths.get(i).y+")");
        }
    }


    private static Spath next(Spath spath, boolean status) {
        if (status) {
            //继续沿着原来方向
            return SpathNext(spath);

        } else {
            //换方向
            spath.di++;
            return SpathNext(spath);
        }
    }


    private static Spath SpathNext(Spath spath) {
        Spath spathNew = new Spath(spath.x, spath.y, 0);//生成新的路径节点 新的节点方向需要重置
        //方向 0 1 2 3 表示右 下 左 上
        if (spath.di == 0) { //向右走
            spathNew.y++;
        }

        if (spath.di == 1) { //向下走
            spathNew.x++;
        }

        if (spath.di == 2) {//向左走
            spathNew.y--;
        }

        if (spath.di == 3) {//向上走
            spathNew.x--;
        }

        if (spathNew.x>=0&&spathNew.x<5&&spathNew.y>=0&&spathNew.y<10){
            return spathNew;
        }
        return null;
    }


    private static Boolean isEnd(Spath spath) {
        if (spath.x == 4 && spath.y == 4) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private static Boolean isRecord(Spath spath) {
        return spathStack.contains(spath);
    }

    private static Boolean isPass(Spath spath) {
        if (spath==null){
            return Boolean.FALSE;
        }
        if (mg[spath.x][spath.y] == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    public static int[][] creatMG() {
        //创建一个迷宫
        int mg[][] = new int[5][10];
        //创建正确的路径，值为1表示可以通过
        mg[0][1] = 1;
        mg[1][1] = 1;
        mg[2][1] = 1;
        mg[2][2] = 1;
        mg[3][2] = 1;
        mg[3][3] = 1;
        mg[3][4] = 1;
        mg[4][4] = 1;

        //创建干扰路径
        mg[0][2] = 1;
        mg[0][3] = 1;
        mg[0][4] = 1;
        mg[1][4] = 1;
        mg[1][5] = 1;
        mg[1][6] = 1;
        mg[2][6] = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mg[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        return mg;
    }
}
```

>输出如下:  
0111100000  
0100111000  
0110001000  
0011100000  
0000100000  
到达终点，坐标为:(4,4)  
路径为   
(0,1)  
(1,1)  
(2,1)  
(2,2)  
(3,2)  
(3,3)  
(3,4)  
(4,4)


***
### 表达式求值
```
package stack.demo;



import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 表达式求值 算符优先法
 * 3*(5-2)#  #在这里表示结尾
 *
 * 思路：
 * 使用两个栈，分别是操作数栈 存储数字 和操作符栈 存储运算符
 * 读入表达式时
 * 如果是操作数 则入操作数栈
 * 如果是运算符 则入操作符栈
 * 当运算符入栈时，和操作符栈栈顶元素比较优先级
 * 如果优先级比栈顶元素高，则入栈,并接收下一个字符
 * 如果和栈顶元素相同，则脱括号 并接收下一个字符 （ 因为相同只有( )括号）
 * 如果小于栈顶元素优先级，则操作数出栈，操作符出栈 并计算运算结果再入栈
 *
 * 关键点：循环的退出条件 直到运算全部结束，即当前栈顶元素和读入的操作符均为#
 *
 * 例子：
 * 3*(5-2)#
 *
 * 算符优先级：
 * + -按顺序先后，先来优先级大于后来的，即从左到右依次计算
 * * / 优先级大于+ - ， 与* /比较则 先来的优先级大于后来的
 * + - * / 优先级均大于(  小于)
 */
public class EvaluateExpression {

    private static Stack<Character> stackOPR = new Stack<>(); //操作符栈
    private static Stack<Integer> stackOPN = new Stack<>();//操作数栈

    public static void main(String[] args) throws IOException {

        char c = (char) System.in.read();
        stackOPR.push('#');
        while (c != '#' ||  stackOPR.peek() != '#') {
            if (isOPN(c)) { //判断是否是操作数
                //是 则入操作数栈 ，读入下一个
                stackOPN.push(c-48); //asc码 '0'转为int是48  A是65 a是97
                c = (char) System.in.read();
            } else {
                switch (isPrior(c)) {
                    case '>':  //优先级比栈顶元素大 ，则入栈,并接收下一个字符
                        stackOPR.push(c);
                        c = (char) System.in.read();
                        break;
                    case '='://优先级和栈顶元素相同
                        stackOPR.pop(); //脱去括号
                        c = (char) System.in.read();
                        break;
                    case '<': //优先级比栈顶元素小，则操作数出栈，操作符出栈，运算之后入栈。即意思是优先级高的先运算
                        int b = stackOPN.pop();//顺序靠后的操作数
                        int a = stackOPN.pop();//顺序靠前的操作数
                        stackOPN.push(Option(a, b, stackOPR.pop()));
                        break;
                }
            }
        }
        System.out.println("计算结果为:"+stackOPN.pop());
    }

    //返回和栈顶元素优先级的比较结果，> 表示优先级大于栈顶元素 <  表示优先级小于栈顶元素 =表示优先级相等
    static char isPrior(Character c) {
        char c2 = stackOPR.peek();
        if (c == '+' || c == '-') { //c为后进入的操作符，c2为之前进入的操作符
            //如果同为+ - 则c2的优先级大于c  同理 * /就更不用说了
            if (c2 == '+' || c2 == '-' || c2 == '*' || c2 == '/') {  //所以和栈顶元素相比 优先级要小
                return '<';
            }
            if (c2 == '(') {
                return '>';
            }
            if (c2 == ')') {
                return '<';
            }
            if (c2 == '#') {
                return '>'; //#是定义为预算结束的标志符 比所有的操作符优先级都小
            }
        }
        if (c == '*' || c == '/') {
            if (c2 == '+' || c2 == '-') {
                return '>';
            }

            if (c2 == '*' || c2 == '/') {
                return '<';
            }
            if (c2 == '(') {
                return '>';
            }
            if (c2 == ')') {
                return '<';
            }
            if (c2 == '#') {
                return '>';
            }
        }


        if (c == '(') {
            return '>';
        }
        if (c == ')') {
            if (c2 != '(') {
                return '<';
            } else {
                return '=';
            }
        }

        if (c == '#') {
            if (c2 != '#') {
                return '<';
            } else {
                return '=';
            }
        }
        return 0;
    }


    static int Option(int a, int b, Character c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    static boolean isOPN(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/'||c=='('||c==')'||c=='#') {
            return false;
        } else {
            return true;
        }
    }
}

```

>输出结果:  
3*(7-2)#   
计算结果为:15

*难点在于  
1.表达式的优先级区分,要点为更高优先级的要先计算，比如说3x(1+2),这里(的优先级就要比x高，因为要先算括号里面的，而后面的+的优先级又要比(高，但是比)小。  
2.循环的退出条件 直到运算全部结束，即当前栈顶元素和读入的操作符均为#*

***
### 汉诺塔问题
```
package stack.demo;

/**
 * 汉诺塔问题：
 * 假设有三根柱子，x y z
 * x上有3个圆盘，从底部开始从大到小编号为n 到 1
 * 若每次只能移动一个圆盘，且大圆盘不能在小圆盘上面
 * 现在需要将3个圆盘 都从x柱子上都移动到z柱子上，且保持原来的顺序，
 * 需要移动多少次？ 若是n个圆盘呢？
 */
public class Hanoi {



    private static int num=0;//计数器

    /**
     * @param x 起始柱子
     * @param y 辅助柱子
     * @param z 目的柱子
     * @param n 盘子个数
     */
    public static void HanoiExcute(char x, char y, char z, int n) {
        //如果要把n个盘子都放到z上，那么需要把n-1到1个盘子都放到y上，然后把第n个盘子放到z上，再把n-1到1个盘子都放到z上
        if (n==1){
            move(x,z,1);//将第1个盘子 从x移动到z
        }else {
            //将n-1到1之间的所有盘子从x移动到y
            HanoiExcute(x,z,y,n-1);
            move(x,z,n);//将第n个盘子从x移动到z
            //将n-1到1之间到所有盘子从y移动到z
            HanoiExcute(y,x,z,n-1);
        }
    }


    /**
     * @param a 起始柱子
     * @param b 目的柱子
     * @param n 盘子编号
     */
    public  static void move(char a,  char b, int n){
        num++;
        System.out.println("第"+num+"次移动，将盘子"+n+"从"+a+"移动到"+b);
    }



    public static void main(String[] args) {
        char x='x';
        char y='y';
        char z='z';
        int n=30;
        HanoiExcute(x,y,z,n);
    }

}

```
>输出结果  
第1次移动，将盘子1从x移动到z  
第2次移动，将盘子2从x移动到y    
第3次移动，将盘子1从z移动到y  
第4次移动，将盘子3从x移动到z  
第5次移动，将盘子1从y移动到x  
第6次移动，将盘子2从y移动到z  
第7次移动，将盘子1从x移动到z



ps:汉诺塔问题的数学递归式子得到的时间复杂度为  
2的n次方-1

*经典的递归题目，要点在于两点。  
1.`HanoiExcute(char x, char y, char z, int n)`这个方法的目的，就是将x上的n个圆盘按原本 的顺序从x移动到z上，y作为辅助柱子。参数含义为x是起始柱子，y是辅助柱子，z是目标柱子，n为圆盘个数。   
递归的核心就是要定义好要递归的函数定义。  
2.`move(char a,  char b, int n)`这个方法的目的是，从a柱子上将编号为n的圆盘移动到b柱子上。参数含义为，a为起始柱子，b为目标柱子，n为圆盘编号*

***
### 队列的接口和元素节点
```
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
```

####节点
```
package Interface;

public class LNode<T> {
    private T data;
    private LNode next;

    public LNode() {

    }

    public LNode(T data, LNode next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LNode getNext() {
        return next;
    }

    public void setNext(LNode next) {
        this.next = next;
    }
}

```
### java队列的链表实现
```
package impl;

import Interface.IQueue;
import Interface.LNode;

import java.util.LinkedList;

/**
 * 链队列
 * <p>
 * 链队列中 需要有队头和队尾指针，且为了表示方便 这里添加一个头节点
 * 因此链队列判空的条件为 队头和队尾指针均指向头节点
 * 链队列的操作即是单链表的插入和删除操作的特殊情况
 *
 * @param <T>
 */
public class LinkedQueue<T> implements IQueue {


    private LNode Header;//头指针 指向头节点  即队首元素的前一个位置 （作用 方便删除队首元素，方便判断队列是否满）
    private LNode TaillPoint;//尾指针
    private Integer size;

    public IQueue InitQueue() {
        if (Header == null) {
            Header = new LNode<T>(); //实例化头节点
            //头尾指针均指向头节点
            TaillPoint = Header;
            size = 0;
        }
        return this;
    }

    public IQueue DestroyQueue() {
        //销毁
        Header = null;
        TaillPoint = Header;
        size = 0;
        return this;
    }

    public IQueue ClearQueue() {
        //头尾指针均指向头节点
        TaillPoint = Header;
        size = 0;
        return this;
    }

    public Boolean isEmpty() {
        if (TaillPoint == Header) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Integer QueueLength() {
        return size;
    }

    public T GetHead() {
        return (T) Header.getNext().getData();
    }

    public Boolean EnQueue(Object e) {
        //入队 从队尾入
        LNode newNode = new LNode<T>((T) e, null);
        TaillPoint.setNext(newNode);
        TaillPoint = newNode;
        size++;
        return Boolean.TRUE;
    }

    public T DeQueue() {
        //删除的时候 如果是最后一个元素, 这个时候尾指针需要调整为指向头节点。 ps:至始至终 头节点本身不需要变，但是头节点指向的next指针要变
        if (Header.getNext().getNext()==null ) {
            T e = (T) Header.getNext().getData();
            Header.setNext(null);
            TaillPoint = Header;
            return e;
        }
        T e = (T) Header.getNext().getData();
        Header.setNext(Header.getNext().getNext());
        size--;
        return e;
    }


    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<Integer>();
        linkedQueue.InitQueue();
        linkedQueue.EnQueue(1);
        linkedQueue.EnQueue(2);
        linkedQueue.EnQueue(3);
        Integer s = linkedQueue.size;
        System.out.println(linkedQueue.GetHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(linkedQueue.DeQueue());
        }
        System.out.println(linkedQueue.isEmpty());
    }
}
```
>输出结果：  
1  
1  
2  
3  
true

***
### java数组队列的实现

```
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

```

>输出结果  
1  
1  
2  
3  
4  
5  
6  
true  
1  
2  
3  
4  
true

***

### java循环队列的实现
```
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

```

>输出结果
1  
1  
2  
3  
4  
5  
true  
4  
5  
6  
true

***
### 队列应用之离散事件模拟
```
package demo;

import impl.LinkedQueue;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 队列的应用之离散事件模拟
 * 假设某银行有4个窗口，每个窗口某一时刻只能接待一个客户，因此客户需要在每个窗口前顺次排队
 * 对于刚进入银行的客户 如果某个窗口的业务员正空闲，则可上前办理业务
 * 若4个窗口均有客户，他便会排在人最少的窗口队尾
 * 要求：计算一天中 客户在银行逗留的平均时间
 * 平均时间等于=（每个客户离开银行的时间-每个客户进入银行的时间)之和 / 客户的人数
 * 这里，将客户进入银行 和离开银行 这两个时刻发生的事情称为事件
 * 这一程序也称为 事件驱动
 * <p>
 * 算法：
 * 事件类型：这里只有五种事件类型， 0-客户到达 1-A窗口客户离开 2-B窗口客户离开 3-B窗口客户离开 4-B窗口客户离开
 * 到达的客户事件，包括事件类型,到达时间
 * 离开的客户事件，包括事件类型,离开时间
 * <p>
 * 处理客户到达事件,新来的客户，只会插入到四个窗口队列中，最短的那个。同时 若银行未关门 则生成一个新的客户到达事件，该事件发生在前一个客户到达事件之后
 * 如果插入的队列为空 则再产生一个客户离开事件
 * 队列元素中包含，到达时间，办事所需时间
 * <p>
 * <p>
 * 处理客户离开事件，根据事件类型 从相应的窗口队列头，将事件出队,统计逗留时间,如果队列不为空，则再产生一个客户离开事件
 * <p>
 * 所以 这里使用两个数据结构，事件队列，窗口队列
 */
public class Simulation {

    private static Long TotalTime; //总逗留时间
    private static int CustomerNum;//客户总数
    private static Long CloseTime = 18 * 3600L;//银行关门时间 下午6点时刻的秒数

    static class Event { //事件类 元素
        private int eventType;//事件类型
        private Long time;//事件发生的时间

    }

    static class QEvent { //排队的队列元素
        private Long ArrivalTime; //到达时间
        private int Duration;//办事所需的时间
    }

    static class QueueWindow {
        private int windowNum;//窗口号
        private LinkedQueue<QEvent> WindowQueue;//该窗口的排队队列
    }


    private static LinkedQueue<Event> eventQueues = new LinkedQueue<Event>();//事件队列
    private static LinkedQueue<QEvent>[] queues = new LinkedQueue[3]; //4个窗口的客户队列

    public static void OpenForDay() {
        //初始化客户人数和逗留时间为0
        TotalTime = 0L;
        CustomerNum = 1;
        //生成第一个客户到达事件
        Event event = new Event();
        long rad= (long)( Math.random() * 30 * 60L);
        event.time = 8 * 3600L +rad; //8点开门  30分钟内随机来一名客户
        System.out.println("生成第一个客户到达事件,该客户为8点"+rad/60+"分到来");
        event.eventType = 0;
        eventQueues.InitQueue(); //初始化事件队列
        eventQueues.EnQueue(event); //发生的事件进入事件队列内
        //初始化窗口队列
        for (int i = 0; i < queues.length; i++) {
            LinkedQueue<QEvent> qEventLinkedQueue = new LinkedQueue<QEvent>();
            qEventLinkedQueue.InitQueue();
            queues[i] = qEventLinkedQueue;
        }

    }


    public static char getEventType() {
        if (eventQueues.GetHead().eventType == 0) {
            return 'A';
        } else {
            return 'D';
        }

    }


    /**
     * 找出四个正在排队的队列中 最短的队列
     *
     * @return
     */
    private static QueueWindow getShortestQueue() {
        int max = 0;
        int maxTag = 0;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].QueueLength() >= max) {
                max = queues[i].QueueLength();
                maxTag = i;
            }
        }
        QueueWindow queueWindow = new QueueWindow();
        queueWindow.windowNum = maxTag;
        queueWindow.WindowQueue = queues[maxTag];
        return queueWindow;
    }

    /**
     * 处理客户到达事件
     * <p>
     * 处理客户到达事件,新来的客户，只会插入到四个窗口队列中，最短的那个。
     * 同时 若银行未关门 则生成一个新的客户到达事件，该事件发生在前一个客户到达事件之后
     * 如果插入的队列为空 则再产生一个客户离开事件
     * 队列元素中包含，到达时间，办事所需时间
     */
    public static void CustomerArrived() {
        //原先事件队列里面的事件出队
        Event event = eventQueues.DeQueue();

        //生成排队事件
        QEvent qEvent = new QEvent();
        qEvent.ArrivalTime = event.time;
        qEvent.Duration = 5 * 60 + (int) (Math.random() * 20 * 60); //办事所需时间 是5~20分钟以内

        //找出最短的窗口队列
        QueueWindow queueWindow = getShortestQueue();
        //判断这个队列是否为空，是的话再产生一个客户离开事件
        if (queueWindow.WindowQueue.isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.time = qEvent.ArrivalTime + qEvent.Duration; //客户的离开时间，等于其到达的时间+办事的时间
            leaveEvent.eventType = queueWindow.windowNum;
            eventQueues.EnQueue(leaveEvent);
        }
        //窗口事件入队
        queueWindow.WindowQueue.EnQueue(qEvent);

        //产生新的客户到达事件
        Event newEvent = new Event();
        newEvent.eventType = 0;
        newEvent.time = event.time + 10 * 60L + (long) (Math.random() * 20 * 60L); //前一个客户来到之后  10～30分钟内随机来一名客户
        if (newEvent.time >= CloseTime) {
            return; //终止产生新的客户到达事件
        }
        eventQueues.EnQueue(newEvent); //新的到达事件入队
        CustomerNum++;//客户数+1
    }

    /**
     * *处理客户离开事件，根据事件类型 从相应的窗口队列头，将事件出队,统计逗留时间
     * 如果队列不为空，则再产生一个客户离开事件
     *
     * @param
     */
    private static void CustomerDeparture() {
        Event event = eventQueues.DeQueue();
        QEvent qEvent = queues[event.eventType].DeQueue();//对应窗口队列的排队事件出队
        TotalTime = TotalTime + (event.time - qEvent.ArrivalTime);//逗留时间  等于离开事件发生时间减去到达时间

        //如果该窗口不为空 则继续产生下一个客户离开事件
        if (!queues[event.eventType].isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.eventType = event.eventType;
            leaveEvent.time = event.time + queues[event.eventType].GetHead().Duration;// 新的客户离开时间 等于上一个客户离开的时间+该客户自己的办事时间
            eventQueues.EnQueue(leaveEvent);
        }
    }


    public static void main(String[] args) {
        OpenForDay();//初始化 开始一天
        while (!eventQueues.isEmpty()) {   //事件列表不为空时候
            switch (getEventType()) { //事件类型
                case 'A':
                    CustomerArrived();
                    break; //处理客户到达事件
                case 'D':
                    CustomerDeparture();
                    break;//处理客户离开事件
                default:
                    System.out.println("事件类型错误");
                    ;
            }


        }


        System.out.println("今天总共:" + CustomerNum + "个客户，总逗留时间为" + TotalTime + "秒" + "，等于" + TotalTime / 60 + "分");
        System.out.println("每个人平均逗留时间为:" + TotalTime / CustomerNum + "秒， 也就是" + (TotalTime / CustomerNum) / 60 + "分");
    }
}

```

>输出结果  
生成第一个客户到达事件,该客户为8点20分到来  
今天总共:29个客户，总逗留时间为25744秒，等于429分  
每个人平均逗留时间为:887秒， 也就是14分  

***

## `String` 串
### 串的接口定义
```aidl
package Interface;


import impl.ArrayString;

/**
 * 串 是由零个或者多个字符组成的有限序列
 * 串中字符的数目n称为串的长度   零个字符的串称为空串 它的长度为零
 * 串中任意个连续字符字符组成的子序列称为该串的子串  包含子串的串相应的称为主串
 * 通常称字符在序列中的序号为该字符在串中的位置  子串的位置则是以该子串的第一个字符在主串中的位置来表示 （ps:第一个字符的位置是1 不是零）
 * 两个串相等的前提是 两个串的长度相等 且对应位置的各个字符也都相等
 * 由一个或多个空格组成的串 也称为空格串，不等于空串，长度为空格的数目
 *
 * 串的存储结构与线性表相似，但是基本操作不同，串的基本操作是以串的整体作为操作对象，线性表是以单个元素作为操作对象
 * 例如 在串中查询某个子串，求取某个子串，在串的某个位置插入一个子串 以及删除一个子串等
 *
 * 以下操作中：
 * 串赋值，串比较,求串长,串连接，求子串 这五种操作是最小操作，其他的串操作可以用这五种组合成
 *
 *
 */
public interface IString {

    /**
     * 生成一个其值等于chars的串T
     * @param chars
     * @return
     */
    IString StrAssign(char[]  chars);

    /**
     * 将串的内容复制给target
     * @param
     * @return
     */
    IString StrCopy(IString target);

    /**
     * 若是串为空 则返回true 否则返回false
     * @return
     */
    Boolean StrEmpty();

    /**
     * 如果本字符串比target大，则返回大于0 若是相等 返回0  若是不等 则返回-1
     * @param target
     * @return
     */
    int StrCompare(IString target);

    /**
     * 返回串的长度 即元素个数
     * @return
     */
    int StrLength();

    /**
     * 将串清空为空串
     */
    Boolean ClearString();

    /**
     * 连接为新串，并返回新串
     */
    IString Concat(IString s2);

    /**
     * 返回串的第post个字符起，长度为len的子串
     */
    IString SubString(int pos,int len);

    /**
     * 若主串中存在和串T值相同的子串，则返回它在主串中第pos个位置之后的第一次出现的位置，否则返回0
     *
     * @param T 子串
     * @param pos 指定位置
     * @return
     */
    int index(IString T,int pos);

    /**
     * 用V替换所有在主串中出现的所有与T相等的不重叠的子串
     * @param T
     * @return
     */
    IString Replace(IString T,IString V);

    /**
     * 在主串的第pos个字符之前插入串T
     * @param T
     * @param pos
     * @return
     */
    IString StrInsert(IString T,int pos);

    /**
     * 从主串中删除第pos个字符起长度为len的子串
     * @param pos
     * @param len
     * @return
     */
    IString StrDelete(int pos,int len);

    /**
     * 销毁
     */
    IString DestroyString();

    /**
     * 打印自己
     */
    void print();

    /**
     * 转为数组
     */
    char[] toArray();


}

```
***
### 串的数组实现
```aidl

package impl;

import Interface.IString;

/**
 * 定长顺序存储表示串
 * <p>
 * 用一组地址连续的存储单元存储串值的字符序列
 * 在串的定长顺序存储结构中，按照预定义的大小，为每个定义的串变量分配一个固定长度的存储区
 * 串的实际长度 可以在定长范围内随意
 * <p>
 * 定长数组，超出部分自动截断
 * <p>
 * <p>
 * 通俗的说，就是用字符数组来实现字符串
 * <p>
 * ps:
 * 串赋值，串比较,求串长,串连接，求子串(SubString) 这五种操作是最小操作，其他的串操作可以用这五种组合成
 *
 * 存储密度=串值所占的存储单位/实际分配的存储单位
 * 因此 数组型实现的串存储密度大于链表型存储实现的串
 *
 *
 *
 */

public class ArrayString implements IString {

    private final int MAXSTRLEN = 10;
    private char[] values;

    /**
     * 生成一个其值等于chars的串T
     *
     * @param chars
     * @return
     */
    public IString StrAssign(char[] chars) {
        int len = chars.length > MAXSTRLEN ? MAXSTRLEN : chars.length;
        values = new char[len];
        copyArray(values, chars);
        return this;
    }

    /**
     * 将串的内容复制给target
     *
     * @param
     * @return
     */
    public IString StrCopy(IString target) {
        target.StrAssign(this.values);
        return target;
    }

    /**
     * 若是串为空 则返回true 否则返回false
     *
     * @return
     */
    public Boolean StrEmpty() {
        if (StrLength() == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 如果本字符串比target大，则返回大于0 若是相等 返回0  若是不等 则返回-1
     *
     * @param target
     * @return
     */
    public int StrCompare(IString target) {
        char[] c2 = target.toArray();
        for (int i = 0; i < this.values.length && i < c2.length; i++) {
            if (this.values[i] != c2[i]) {
                return -1;
            }
        }
        return (this.values.length - c2.length);
    }

    /**
     * 返回串的长度 即元素个数
     *
     * @return
     */
    public int StrLength() {
        if (values != null) {
            return values.length;
        } else {
            return 0;
        }
    }


    /**
     * 将串清空为空串
     */
    public Boolean ClearString() {
        values = null;
        return true;
    }

    /**
     * 连接为新串，并返回新串
     */
    public IString Concat(IString s2) {
        int len = StrLength() + s2.StrLength() > MAXSTRLEN ? MAXSTRLEN : StrLength() + s2.StrLength();
        char[] charNews = new char[len];
        copyArray(charNews, this.values);
        char[] cs2 = s2.toArray();
        int limit = StrLength() + s2.StrLength() > MAXSTRLEN ? MAXSTRLEN - StrLength() : s2.StrLength();
        for (int i = 0; i < limit; i++) {
            charNews[StrLength() + i] = cs2[i];
        }
        IString newS = new ArrayString();
        newS.StrAssign(charNews);
        return newS;
    }

    /**
     * 返回串的第post个字符起，长度为len的子串
     */
    public IString SubString(int pos, int len) {
        if (pos < 0 || len + pos - 1 > StrLength()) {
            return null; //超出界限
        }
        char[] c = new char[len];
        for (int i = pos; i < len + pos; i++) {
            c[i - pos] = values[i - 1];
        }

        IString s = new ArrayString();
        s.StrAssign(c);
        return s;
    }

    /**
     * 若主串中存在和串T值相同的子串，则返回它在主串中第pos个位置之后的第一次出现的位置，否则返回0
     * 利用 compare subString 组合使用
     *
     * @param T   子串
     * @param pos 指定位置
     * @return
     */
    public int index(IString T, int pos) {

        if (pos + T.StrLength() > StrLength()) {
            return 0;
        }

        for (int i = pos; i < StrLength(); i++) {
            if (SubString(i, T.StrLength()).StrCompare(T) == 0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 用V替换所有在主串中出现的所有与T相等的不重叠的子串
     * <p>
     * 思路为 先找到相等的串，然后删除掉，再在原来的位置插入新的。
     * 因为如果直接替换元素，T V 若是长度不同，则会破坏原来数据的完整
     *
     * @param T
     * @return
     */
    public IString Replace(IString T, IString V) {

        IString news = this;
        int tag = news.index(T, 1);
        while (tag != 0) {
            news = news.StrDelete(tag, T.StrLength()).StrInsert(V, tag);
            tag = news.index(T, tag);
        }
        return news;
    }


    /**
     * 在主串的第pos个字符之前插入串T
     *
     * 要考虑到末尾插入的情况！！ 特殊情况！！
     * @param T
     * @param pos
     * @return
     */
    public IString StrInsert(IString T, int pos) {
        if (pos >= 1) {
            int len = StrLength() + T.StrLength() <= MAXSTRLEN ? StrLength() + T.StrLength() : MAXSTRLEN;
            char[] newc = new char[len];
            char[] inc = T.toArray();
            copyArray(newc, values);

            //在末尾插入
            if (StrLength() < pos && pos < MAXSTRLEN) {
                for (int i = 0; i < T.StrLength(); i++) {
                    newc[pos-1+i] = inc[i];

                }
            } else {
                //挪开位置
                for (int i = 0; i <= T.StrLength(); i++) {
                    newc[len - 1 - i] = newc[StrLength() - 1 - i];
                }
                //插入新值
                for (int i = 0; i < T.StrLength(); i++) {
                    newc[pos - 1 + i] = inc[i];
                }
            }

            IString news = new ArrayString();
            news.StrAssign(newc);
            return news;

        }

        return null;
    }

    /**
     * 从主串中删除第pos个字符起长度为len的子串
     *
     * @param pos
     * @param len
     * @return
     */
    public IString StrDelete(int pos, int len) {
        int leng = pos - 1 + len > StrLength() ? pos : StrLength() - len;
        char[] c = new char[leng];
        copyArray(c, SubString(1, pos - 1).toArray());
        IString s = new ArrayString();
        if (pos + len > StrLength()) {
            s.StrAssign(c);
            return s;
        } else {
            for (int i = 0; i <= leng - pos; i++) {
                c[pos - 1 + i] = values[pos + len + i - 1];
            }

            s.StrAssign(c);
            return s;
        }
    }

    public IString DestroyString() {
        return null;
    }

    public void print() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
        }
        System.out.println();
    }

    public char[] toArray() {
        char[] c = new char[values.length];
        copyArray(c, values);
        return c;
    }

    private void copyArray(char[] target, char[] source) {
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
    }

    public static void main(String[] args) {
        //赋值
        System.out.println("赋值");
        ArrayString as = new ArrayString();
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        as.StrAssign(chars);
        as.print();
        //拷贝
        System.out.println("拷贝");
        ArrayString as2 = new ArrayString();
        as.StrCopy(as2);
        as2.print();

        System.out.println("转为数组");
        System.out.println(as.toArray());
        char[] c2 = as.toArray();
        c2[2] = 'x';
        System.out.println(as.toArray());
        System.out.println(c2);

        System.out.println("求串长");
        System.out.println(as.StrLength());

        System.out.println("判空");
        System.out.println(as.StrEmpty());

        System.out.println("比较两个字符串");
        IString as3 = new ArrayString();
        as3.StrAssign(new char[]{'A', 'B'});
        System.out.println(as.StrCompare(as2));
        System.out.println(as.StrCompare(as3));


        System.out.println("连接 未截断");
        as.Concat(as2).print();
        System.out.println("连接 截断");

        IString as4 = new ArrayString();
        as4.StrAssign(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'});
        as.Concat(as4).print();

        System.out.println("截取子串 从第2个位置开始 截取4位");
        as4.SubString(2, 3).print();


        System.out.println("子串匹配");
        IString as5 = new ArrayString();
        as5.StrAssign(new char[]{'6', '7'});
        System.out.println(as4.index(as5, 1));


        System.out.println("插入新值");
        IString as6 = new ArrayString();
        as6.StrAssign(new char[]{'a', 'b', 'c'});
        as6.StrInsert(as5, 2).print();


        System.out.println("删除指定位置2开始 长度为3的子串");
        as4.print();
        as4.StrDelete(2, 3).print();
        as4.StrDelete(5, 2).print();


        System.out.println("子串替换");
        IString as7 = new ArrayString();
        as7.StrAssign(new char[]{'a', 'b', 'c', 'd', 'e', 'c', 'd'});

        IString T = new ArrayString();
        T.StrAssign(new char[]{'c', 'd'});

        IString V = new ArrayString();
        V.StrAssign(new char[]{'1', '2'});
        as7.Replace(T, V).print();
    }


}


```
>输出  
赋值  
abcde  
拷贝  
abcde  
转为数组  
abcde  
abcde  
abxde  
求串长  
5  
判空  
false  
比较两个字符串  
0  
-1  
连接 未截断  
abcdeabcde  
连接 截断  
abcde12345  
截取子串 从第2个位置开始 截取4位  
234  
子串匹配  
6  
插入新值  
a67bc  
删除指定位置2开始 长度为3的子串  
123456789  
156789  
1234789  
子串替换      
ab12e12

### 串的三种存储结构分析
#### 定长顺序存储 
类似线性表的顺序存储结构，用一组地址连续的存储单元存储串值的
字符序列。按照预定义的大小，为每个定义的串变量分配一个固定长度的存储区。
串的实际长度可在预定义长度范围内随意，超过的部分则被舍弃，称为截断。

#### 堆分配存储表示 
特点是存储空间是在程序执行过程中动态分配而得
#### 串的块链存储表示
一个链表节点存放多个字符，这样可以增加存储密度

存储密度=串值所占的存储单位/实际分配的存储单位

***

### 串的匹配算法
#### 遍历算法
```aidl
package demo;

/**
 * 串的模式匹配算法
 */
public class StringIndex {

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int index(String S, String T, int pos) {

        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕
        //双循环 时间复杂度是n*n  最坏情况下 每次匹配到最后一个字符时候不等，这样遍历了两层循环  ，最优情况下是n+m
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        for (int j = pos; j < S.length(); j++) {
            int tag = 0;
            for (int i = j; i - j < t.length; i++) {
                if (s[i] != t[i - j]) {
                    tag = 1;
                    break;
                }
            }
            if (tag == 0) {
                return j;
            }
        }

        return 0;
    }

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int indextwo(String S, String T, int pos) {
        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕

        int i = pos;
        int j = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                //重置指针，从新匹配
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == t.length) {
            return (i - j);
        } else {
            return 0;
        }
        //这个时间复杂度 最差仍然为n*n 等于每次快循环结束时候都要重置指针
    }


    public static void main(String[] args) {
        String s = "ab12cde";
        String t = "12";
        System.out.println(index(s, t, 2));
        System.out.println(indextwo(s,t,2));
    }
}

```
>输出为   
2  
2
****

#### 串的KMP算法

```aidl

package demo;

/*
字符串匹配算法
 */
public class StringKMP {

    //找出从第一个字符开始 子串T在主串S的第一个位置 如果没有则返回-1
    public static int index(String S, String T) {
        int tag = 0;
        int i = 0;
        int j = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            tag++;
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0; //一旦不等，j就从0开始
            }
        }


        System.out.println("循环了" + tag + "次");
        if (j == t.length) {
            return i - j;
        } else {
            return -1;
        }
    }


    /**
     * 使用kmp算法的情况下
     * kmp原理，就是 每次匹配的时候，如果t[j]和s[i]不相等，j不是从零开始，而是从k开始，k的特点是 t的0到k-1个位置字符和j-1 到 j-k-1个字符相等
     * 比如说
     * ababd在和ababfababde 匹配的时候
     * ababfababde
     * ababd
     * 在j=4的时候不匹配时，也就是t[4]=d  不匹配的时候，j的下一个值不是0 ，而是2， 即t[2]=a ，而且此时i指针也不需要动
     * 此时比对场景为
     * ababfababde
     *   ababd     此时 在t[2]仍然不匹配  j的下一个值是0
     * <p>
     * ababfababde  此时t[0]时候 仍然不匹配 ，则i要向前移动1位
     *     ababd
     * <p>
     * <p>
     * ababfababde   此时匹配成功返回i
     *      ababd
     * <p>
     * <p>
     * <p>
     * 因此 这里会发现，求j的下一个值 next[j] 等同于是在求 第j个字符前面的，最大相同前缀和和后缀的长度
     * 比如说 ababf f前面的最大相同前缀和后缀为ab 和ab  长度为2 因此next[4]=2
     * <p>
     * t=2的时候  也就是aba  第三个a前面的ab ，最大相同前缀和后缀长度为0  因此next[2]=0
     * <p>
     * 最大前缀： 包含第一个字符，不包含最后一个字符
     * 最大后缀：包含最后一个字符，不包含第一个字符
     * <p>
     * 最大相同前后缀，即前缀和后缀相同的部分 最大长度的
     *
     * @param
     */
    public static int KMP(String S, String T) {
        int tag = 0;

        int[] next = next(T);
        int i = 0;
        int j = 0;
        next[0] = -1;

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            tag++;
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        System.out.println("循环了" + tag + "次");
        if (j == t.length) {
            return i - j;
        } else {
            return -1;
        }
    }


    public static int[] next(String T) {
        char[] t = T.toCharArray();
        int[] next = new int[t.length];
        next[0] = -1;//-1表示不存在
        int i = 0;
        int j = 1;
        while (j < t.length) {
            if (i == -1 || t[i] == t[j]) {
                i++;
                next[j] = i;
                j++;
            } else {
                i = next[i];
            }
        }

        for (int i1 : next) {
            System.out.print(i1);
        }
        System.out.println();

        return next;
    }


    public static void main(String[] args) {
        System.out.println(index("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh")); //循环了68次
        System.out.println(index("abc", "bc")); //循环了3次
        next("abcabc");//-100123
        System.out.println(KMP("abc", "bc")); //循环了4次
        System.out.println(KMP("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh"));//循环了50次
        System.out.println(KMP("abcdeiopsfsfasdaffabcdefabcdefabcdefabcabcabcabcdehabcdes", "abcdeh"));//循环了74次

    }


}

```

## `tree`  树

树

## `diagram` 图
## `sorting` 排序查找
