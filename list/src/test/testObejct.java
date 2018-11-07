package test;

public class testObejct {
    public static void main(String[] args) {
        Integer a=3;
        Integer b=4;
        a=b;
        a=6;
        System.out.println("a的值："+a+","+"b的值:"+b);  //a 6  b 4


        demo demoA=new demo();
        demoA.setDate(1);

        demo demoA1=demoA;

        demo demoB=new demo();

        demoB.setDate(2);
        System.out.println("A: "+demoA.getDate()+"  B: "+demoB.getDate()); // A 1  B 2


        demoA =demoB;
        demoB.setDate(999);

        System.out.println("A: "+demoA.getDate()+"B: "+demoB.getDate()); // A 999  B 999
        // 但是事实此时demoA和demoB都表示同一个对象, 原来的demoA被没有被改变
        System.out.println("demoA与demoB的地址是否相等："+String.valueOf(demoA==demoB));

        System.out.println("原来demoA的值仍没有变化"+demoA1.getDate());

 }

}
