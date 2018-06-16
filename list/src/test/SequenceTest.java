package test;
import Impl.SequenceList;
public class SequenceTest {
    public static void main(String[] args) {


        SequenceList<Integer> list=new SequenceList<>();

        list.InitList();
      //  System.out.println(list.ListLength());
//        list.ListInsert(1,9);
//        list.ListInsert(2,2);
//        list.ListInsert(3,6);
//        list.ListInsert(4,5);
//        list.ListInsert(5,9);
        list.add(3);
        list.add(5);
        list.add(88);
        list.add(111);
//        System.out.println(list.ListLength());
//
//
//        list.ListTraverse();
//        System.out.println(list.GetElem(6));
//        System.out.println(list.LocateElem(99));
//        System.out.println(list.PriorElem(99));
//        System.out.println(list.NextElem(9));
//        list.ListDelete(1);
//        list.ListTraverse();

        SequenceList<Integer> list2=new SequenceList<>();
        list2.InitList();
        list2.add(2);
        list2.add(6);
        list2.add(8);
        list2.add(9);
        list2.add(11);
        list2.add(15);
        list2.add(2000);
        //list.Union(list2);
        list.MergeList(list2);
        list.ListTraverse();

        list.ListInsert(2,99);
        list.ListTraverse();
        System.out.println(list.PriorElem(99));
        System.out.println(list.NextElem(99));

    }
}
