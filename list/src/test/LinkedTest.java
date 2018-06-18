package test;

import Impl.LinkedList;

public class LinkedTest {
    public static void main(String[] args) {


//        LinkedList<Integer> list=new LinkedList<>();
//
//        list.InitList();
//      //  System.out.println(list.ListLength());
////        list.ListInsert(1,9);
////        list.ListInsert(2,2);
////        list.ListInsert(3,6);
////        list.ListInsert(4,5);
////        list.ListInsert(5,9);
//        list.add(3);
//        list.add(5);
//        list.add(88);
//        list.add(111);
//        System.out.println("合并前");
//        list.ListTraverse();
////        System.out.println(list.ListLength());
////
////
////        list.ListTraverse();
////        System.out.println(list.GetElem(3));
////        System.out.println(list.LocateElem(88));
////        System.out.println(list.PriorElem(99));
////        System.out.println(list.NextElem(9));
////        list.ListDelete(1);
////        list.ListTraverse();
//
//        LinkedList<Integer> list2=new LinkedList<>();
//        list2.InitList();
//        list2.add(2);
//        list2.add(3);
//        list2.add(5);
//        list2.add(9);
//        list2.add(11);
//        list2.add(15);
//        list2.add(2000);
//       //list.Union(list2);
//       list.MergeList(list2);
//        System.out.println("合并后");
//        list.ListTraverse();
//
//        list.ListInsert(3,222);
//        list.ListTraverse();
//
//        list.ListInsert(4,99);
//        list.ListTraverse();
//
//        list.ListInsert(6,12345);
//        list.ListTraverse();
//
//        System.out.println(list.PriorElem(88));
//        System.out.println(list.NextElem(88));
//
//      System.out.println("位置"+list.LocateElem(111));
//        list.ListTraverse();
//        list.Reversal();
//        System.out.println("逆向之后");
//        list.ListTraverse();


        LinkedList<Integer> list=new LinkedList<>();
         list.InitList();
         list.add(3);
        list.add(5);
        list.add(88);
        list.add(111);
        list.ListTraverse();
        list.ListDelete(2);
        list.ListTraverse();


         list.ListInsert(2,22);
        list.ListTraverse();
        list.Reversal();
        list.ListTraverse();

    }
}
