package Impl;

import IntefaceList.IList;

//todo 链表型线性表
public class LinkedList<T> implements IList {

    //头节点
    private LNode<T> tnode;
    private Integer length = 0;
    //当前节点
    private LNode<T> cnode;

    //尾巴节点

    public LNode<T> getTnode() {
        return tnode;
    }

    private LNode<T> taillNode;


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
        if (i > length) {
            return null;
        }
        cnode = tnode.getNext();

        LNode<T> newNode = new LNode<>();
        newNode.setData((T) e);


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

        if (i == 1) {
            newNode.setNext(cnode);
            tnode.setNext(newNode);
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

    @Override
    public IList ListDelete(Integer i) {

        cnode = tnode.getNext();
        /**
         * 如果删的是尾节点
         */
        if (i.equals(this.length)) {

            for (i = 1; i < this.length; i++) {
                cnode = cnode.getNext();
            }
            taillNode = cnode;
            cnode.setNext(null);
            this.length--;
            return this;
        }

        while (i - 1 > 0) {
            cnode = cnode.getNext();
            i--;
        }
        cnode.setNext(cnode.getNext().getNext());
        length--;
        return this;
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
