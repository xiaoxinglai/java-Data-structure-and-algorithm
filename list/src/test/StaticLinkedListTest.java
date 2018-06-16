package test;

import Impl.StaticLinkedLIst;

public class StaticLinkedListTest {
    public static void main(String[] args) {
        StaticLinkedLIst<String> staticLinkedLIstA=new StaticLinkedLIst();
        staticLinkedLIstA.InitList();
        staticLinkedLIstA.add("c");
        staticLinkedLIstA.add("b");
        staticLinkedLIstA.add("e");
        staticLinkedLIstA.add("g");
        staticLinkedLIstA.add("f");
        staticLinkedLIstA.add("d");
        staticLinkedLIstA.ListTraverse();


        StaticLinkedLIst<String> staticLinkedLIstB=new StaticLinkedLIst();
        staticLinkedLIstB.InitList();
        staticLinkedLIstB.add("a");
        staticLinkedLIstB.add("b");
        staticLinkedLIstB.add("n");
        staticLinkedLIstB.add("f");
        staticLinkedLIstB.ListTraverse();

        staticLinkedLIstB.Differnce(staticLinkedLIstA);

        staticLinkedLIstA.ListTraverse();


    }

}
