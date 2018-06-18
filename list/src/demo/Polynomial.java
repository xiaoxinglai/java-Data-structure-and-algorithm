package demo;

import Impl.LinkedList;

public class Polynomial {
    //存储多项式用的链表
    private LinkedList list;
    private int Length=0;

    public int getLength() {
        return Length;
    }

    /**
     * 输入系数列表，建立表示一元多项式的有序链表
     */
    void CreatPolyn(term[] terms){
        list=new LinkedList();
        list.InitList();
        for (term term : terms) {
            //判断是否存在相同指数的项
           int j= locateElem(term);
           if (j!=-1){
               term it=(term)list.GetElem(j);
               term.setCoef(term.getCoef()+it.getCoef());
               list.ListDelete(j);
               list.ListInsert(j,term);
           }else {
               list.add(term);
               Length++;
           }
        }
    }



    void printPolyn(){
        for (int i = 1; i <=list.ListLength() ; i++) {
            term it=(term)list.GetElem(i);
            if (i!=list.ListLength()){
                System.out.print(it.getCoef()+"x"+it.expn+"+");
            }else {
                System.out.print(it.getCoef()+"x"+it.expn);
            }

        }
        System.out.println();
    }


    //判断链表里面有没有这个系数的函数，如果有，返回它的节点序号，如果没有，则返回-1
    int locateElem(term term){
        for (int i = 1; i <=list.ListLength() ; i++) {
            term it=(term)list.GetElem(i);
            if (it.getExpn()==term.getExpn()){
                return i;
            }
        }
        return -1;
    }

    term getTerm(int i){
        return (term)list.GetElem(i);
    }

    void  addPolyn(Polynomial PolynomialB){

        int k=1;
        term[] terms=new term[PolynomialB.getLength()];
        while (k<=PolynomialB.getLength()){
            terms[k-1]= PolynomialB.getTerm(k);
            k++;
        }


        for (term term : terms) {
            //判断是否存在相同指数的项
            int j= locateElem(term);
            if (j!=-1){
                term it=(term)list.GetElem(j);
                term.setCoef(term.getCoef()+it.getCoef());
                list.ListDelete(j);
                list.ListInsert(j,term);
            }else {
                list.add(term);
                Length++;
            }
        }






    }
}
