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
