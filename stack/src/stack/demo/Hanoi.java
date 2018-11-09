package stack.demo;

/**
 * 汉诺塔问题：
 * 假设有三根柱子，x y z
 * x上有3个圆盘，从底部开始从大到小编号为n 到 1
 * 若每次只能移动一个圆盘，且大圆盘不能在小圆盘上面
 * 现在需要将3个圆盘 都从x柱子上都移动到z柱子上，且保持原来的顺序，
 * 需要移动多少次？ 若是n个圆盘呢？
 */
public class Hanoi {



    private static int num=0;//计数器

    /**
     * @param x 起始柱子
     * @param y 辅助柱子
     * @param z 目的柱子
     * @param n 盘子个数
     */
    public static void HanoiExcute(char x, char y, char z, int n) {
        //如果要把n个盘子都放到z上，那么需要把n-1到1个盘子都放到y上，然后把第n个盘子放到z上，再把n-1到1个盘子都放到z上
        if (n==1){
            move(x,z,1);//将第1个盘子 从x移动到z
        }else {
            //将n-1到1之间的所有盘子从x移动到y
            HanoiExcute(x,z,y,n-1);
            move(x,z,n);//将第n个盘子从x移动到z
            //将n-1到1之间到所有盘子从y移动到z
            HanoiExcute(y,x,z,n-1);
        }
    }


    /**
     * @param a 起始柱子
     * @param b 目的柱子
     * @param n 盘子编号
     */
    public  static void move(char a,  char b, int n){
        num++;
        System.out.println("第"+num+"次移动，将盘子"+n+"从"+a+"移动到"+b);
    }



    public static void main(String[] args) {
        char x='x';
        char y='y';
        char z='z';
        int n=3;
        HanoiExcute(x,y,z,n);
    }

}
