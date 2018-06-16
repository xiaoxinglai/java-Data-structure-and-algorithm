package Impl;

import IntefaceList.IList;

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
