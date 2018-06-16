package test;

import Impl.DuLinkedList;

public class DuLinkedTest {
    public static void main(String[] args) {
        DuLinkedList duLinkedList=new DuLinkedList();
        duLinkedList.InitList();
        duLinkedList.add("a");
        duLinkedList.add("b");
        duLinkedList.add("c");
        duLinkedList.ListTraverse();
        duLinkedList.UnListTraverse();
        System.out.println(duLinkedList.GetElem(3));
        System.out.println(duLinkedList.LocateElem("b"));
        System.out.println(duLinkedList.PriorElem("b"));
        System.out.println(duLinkedList.NextElem("b"));

        duLinkedList.ListInsert(2,"p");
        duLinkedList.ListTraverse();
        duLinkedList.UnListTraverse();

        duLinkedList.ListDelete(3);
        duLinkedList.ListTraverse();
        duLinkedList.UnListTraverse();


    }
}
