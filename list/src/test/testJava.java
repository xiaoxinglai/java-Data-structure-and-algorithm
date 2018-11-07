package test;

/**
 * 🈯️java值传递还是引用传递
 * <p>
 * 验证其为值传递
 */
public class testJava {
    demo demoA = new demo();

    public static void main(String[] args) {
        demo A = new demo();
        A.setDate(111);
        System.out.println("修改之前A的值:" + A.getDate());
        modifyTest(A);
        System.out.println("返回方法之后A的值" + A.getDate());

    }

    private static void modifyTest(demo a) {
        a = new demo();
        a.setDate(123);
        System.out.println("在方法里面修改之后的a的值:" + a.getDate());
    }
}



