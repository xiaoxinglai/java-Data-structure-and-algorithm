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

        demo demoB=new demo();

        demoB.setDate(2);
        System.out.println("A:"+demoA.getDate()+"B: "+demoB.getDate()); // A 1  B 2


        demoA =demoB;
        demoB.setDate(999);

        System.out.println("A:"+demoA.getDate()+"B: "+demoB.getDate()); // A 999  B 999




 }

}
