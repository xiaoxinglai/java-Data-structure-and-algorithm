package Impl;

import IntefaceList.IList;

//循环链表
public class CircLinkedList  implements IList {
    /**
     * 循环链表是另一种形式的链式存储结构 它的特点是表中最后一个节点的指针 指向头节点
     * 整个链表形成一个环，从表中任意节点出发均可找到表中其他节点
     *
     * 循环链表为空表的判断：头节点的指针指向头节点
     * 循环链表的操作和线性链表基本一致，差别仅在于算法中的循环条件不是p或者p->next是否为空
     * 而且它们是否等于头节点
     */

      





    @Override
    public void InitList() {

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
        return null;
    }

    @Override
    public Integer LocateElem(Object e) {
        return null;
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
        return null;
    }

    @Override
    public void ListTraverse() {

    }

    @Override
    public IList Union(IList list) {
        return null;
    }

    @Override
    public IList MergeList(IList list) {
        return null;
    }

    @Override
    public void add(Object e) {

    }
}
