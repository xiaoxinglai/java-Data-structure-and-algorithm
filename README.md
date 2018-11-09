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

## `tree`  树
## `diagram` 图
## `sorting` 排序查找
