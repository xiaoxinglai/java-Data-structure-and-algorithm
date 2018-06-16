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
