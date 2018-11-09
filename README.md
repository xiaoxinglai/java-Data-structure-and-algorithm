#### java-Data-structure-and-algorithm
#### java数据结构和算法实现练习

### `list`  线性表
  * 栈应用之行编辑器  
  '#'表示退格 '@'表示退行  
  比如  whli##ilr#e（s#*s）  
  outcha@putchar(*s=#++)    
  实际有效的是如下  
  while(*s)  
  putchar(*s++)  
  思路：用栈存储输入，直到输入结束为止'\n'表示，则输出 ，期间如果遇到#则出栈 ，遇到@则清空栈
 
  代码如下：

```
public class LineEdit {
    public static void main(String[] args) throws IOException {
        stack stack = new stack();
        stack.InitStack();

        try {
            char ch = (char) System.in.read();
            while (ch != '\n') {
                if (ch == '#') {
                    stack.Pop();
                } else if (ch == '@') {
                    stack.ClearStack();
                } else {
                    stack.push(ch);
                }
                ch = (char) System.in.read();
            }
            List<Character> list=new ArrayList<>();
            while(stack.getTop()!=null){
                list.add((Character)stack.Pop());
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(list.size()-1-i));
            }

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            throw e;
        }


    }
}
```
 
输出如下：
>whli##ilr#e(s#*s)  
while(*s)

***

* 迷宫求解
```
package stack.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用java提供的stack实现
 */
public class MazePathStack {

    static class Spath { //行走的单位格子
        private int x;
        private int y;
        private int di; //方向 0 1 2 3 表示右 下 左 上

        public Spath(int x, int y, int di) {
            this.x = x;
            this.y = y;
            this.di = di;
        }

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return true;
            }

            if (obj instanceof Spath) {

                if ((((Spath) obj).x == this.x) && (((Spath) obj).y == this.y)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }
    }

    private static int[][] mg = MazePathStack.creatMG();//初始化时候生成迷宫
    private static Stack<Spath> spathStack = new Stack();//路径栈


    public static void main(String[] args) {
        Spath spath = new Spath(0, 1, 0); //初始坐标为入口 （0，1）方向为向右
        do {
            if (isPass(spath)) { //判断该坐标能否通行

                //判断该坐标是否是终点
                if (isEnd(spath)) {
                    spathStack.push(spath);
                    System.out.println("到达终点，坐标为:" + "(" + spath.x + "," + spath.y + ")");
                    System.out.println("路径为");
                    printAll();
                    return;
                }

                //判断该坐标是否已经来过
                if (isRecord(spath)) {
                    //来过 则放弃栈中最新的坐标 后退一格，换个方向重试
                    spathStack.pop();
                    spath = spathStack.peek();
                    spath = next(spath, false);//继续下一步 换个方向
                } else {
                    //没有来过 压入栈中 记录下来
                    spathStack.push(spath);
                    spath = next(spath, true); //继续下一步 沿原来方向
                }

            } else {
                spath = spathStack.peek(); //后退一步
                spath = next(spath, false);//换个方向 继续下一步
            }

        } while (!spathStack.empty());


    }

    private static void printAll() {
        //打印路径
        List<Spath> spaths=new ArrayList<>();
        while(!spathStack.empty()){
            spaths.add(spathStack.pop());
        }
        for (int i = spaths.size()-1; i >=0 ; i--) {
            System.out.println("("+spaths.get(i).x+","+spaths.get(i).y+")");
        }
    }


    private static Spath next(Spath spath, boolean status) {
        if (status) {
            //继续沿着原来方向
            return SpathNext(spath);

        } else {
            //换方向
            spath.di++;
            return SpathNext(spath);
        }
    }


    private static Spath SpathNext(Spath spath) {
        Spath spathNew = new Spath(spath.x, spath.y, 0);//生成新的路径节点 新的节点方向需要重置
        //方向 0 1 2 3 表示右 下 左 上
        if (spath.di == 0) { //向右走
            spathNew.y++;
        }

        if (spath.di == 1) { //向下走
            spathNew.x++;
        }

        if (spath.di == 2) {//向左走
            spathNew.y--;
        }

        if (spath.di == 3) {//向上走
            spathNew.x--;
        }

        if (spathNew.x>=0&&spathNew.x<5&&spathNew.y>=0&&spathNew.y<10){
            return spathNew;
        }
        return null;
    }


    private static Boolean isEnd(Spath spath) {
        if (spath.x == 4 && spath.y == 4) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private static Boolean isRecord(Spath spath) {
        return spathStack.contains(spath);
    }

    private static Boolean isPass(Spath spath) {
        if (spath==null){
            return Boolean.FALSE;
        }
        if (mg[spath.x][spath.y] == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    public static int[][] creatMG() {
        //创建一个迷宫
        int mg[][] = new int[5][10];
        //创建正确的路径，值为1表示可以通过
        mg[0][1] = 1;
        mg[1][1] = 1;
        mg[2][1] = 1;
        mg[2][2] = 1;
        mg[3][2] = 1;
        mg[3][3] = 1;
        mg[3][4] = 1;
        mg[4][4] = 1;

        //创建干扰路径
        mg[0][2] = 1;
        mg[0][3] = 1;
        mg[0][4] = 1;
        mg[1][4] = 1;
        mg[1][5] = 1;
        mg[1][6] = 1;
        mg[2][6] = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mg[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        return mg;
    }
}
```

>输出如下:  
0111100000  
0100111000  
0110001000  
0011100000  
0000100000  
到达终点，坐标为:(4,4)  
路径为   
(0,1)  
(1,1)  
(2,1)  
(2,2)  
(3,2)  
(3,3)  
(3,4)  
(4,4)


***
* 表达式求值
```
package stack.demo;



import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 表达式求值 算符优先法
 * 3*(5-2)#  #在这里表示结尾
 *
 * 思路：
 * 使用两个栈，分别是操作数栈 存储数字 和操作符栈 存储运算符
 * 读入表达式时
 * 如果是操作数 则入操作数栈
 * 如果是运算符 则入操作符栈
 * 当运算符入栈时，和操作符栈栈顶元素比较优先级
 * 如果优先级比栈顶元素高，则入栈,并接收下一个字符
 * 如果和栈顶元素相同，则脱括号 并接收下一个字符 （ 因为相同只有( )括号）
 * 如果小于栈顶元素优先级，则操作数出栈，操作符出栈 并计算运算结果再入栈
 *
 * 关键点：循环的退出条件 直到运算全部结束，即当前栈顶元素和读入的操作符均为#
 *
 * 例子：
 * 3*(5-2)#
 *
 * 算符优先级：
 * + -按顺序先后，先来优先级大于后来的，即从左到右依次计算
 * * / 优先级大于+ - ， 与* /比较则 先来的优先级大于后来的
 * + - * / 优先级均大于(  小于)
 */
public class EvaluateExpression {

    private static Stack<Character> stackOPR = new Stack<>(); //操作符栈
    private static Stack<Integer> stackOPN = new Stack<>();//操作数栈

    public static void main(String[] args) throws IOException {

        char c = (char) System.in.read();
        stackOPR.push('#');
        while (c != '#' ||  stackOPR.peek() != '#') {
            if (isOPN(c)) { //判断是否是操作数
                //是 则入操作数栈 ，读入下一个
                stackOPN.push(c-48); //asc码 '0'转为int是48  A是65 a是97
                c = (char) System.in.read();
            } else {
                switch (isPrior(c)) {
                    case '>':  //优先级比栈顶元素大 ，则入栈,并接收下一个字符
                        stackOPR.push(c);
                        c = (char) System.in.read();
                        break;
                    case '='://优先级和栈顶元素相同
                        stackOPR.pop(); //脱去括号
                        c = (char) System.in.read();
                        break;
                    case '<': //优先级比栈顶元素小，则操作数出栈，操作符出栈，运算之后入栈。即意思是优先级高的先运算
                        int b = stackOPN.pop();//顺序靠后的操作数
                        int a = stackOPN.pop();//顺序靠前的操作数
                        stackOPN.push(Option(a, b, stackOPR.pop()));
                        break;
                }
            }
        }
        System.out.println("计算结果为:"+stackOPN.pop());
    }

    //返回和栈顶元素优先级的比较结果，> 表示优先级大于栈顶元素 <  表示优先级小于栈顶元素 =表示优先级相等
    static char isPrior(Character c) {
        char c2 = stackOPR.peek();
        if (c == '+' || c == '-') { //c为后进入的操作符，c2为之前进入的操作符
            //如果同为+ - 则c2的优先级大于c  同理 * /就更不用说了
            if (c2 == '+' || c2 == '-' || c2 == '*' || c2 == '/') {  //所以和栈顶元素相比 优先级要小
                return '<';
            }
            if (c2 == '(') {
                return '>';
            }
            if (c2 == ')') {
                return '<';
            }
            if (c2 == '#') {
                return '>'; //#是定义为预算结束的标志符 比所有的操作符优先级都小
            }
        }
        if (c == '*' || c == '/') {
            if (c2 == '+' || c2 == '-') {
                return '>';
            }

            if (c2 == '*' || c2 == '/') {
                return '<';
            }
            if (c2 == '(') {
                return '>';
            }
            if (c2 == ')') {
                return '<';
            }
            if (c2 == '#') {
                return '>';
            }
        }


        if (c == '(') {
            return '>';
        }
        if (c == ')') {
            if (c2 != '(') {
                return '<';
            } else {
                return '=';
            }
        }

        if (c == '#') {
            if (c2 != '#') {
                return '<';
            } else {
                return '=';
            }
        }
        return 0;
    }


    static int Option(int a, int b, Character c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    static boolean isOPN(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/'||c=='('||c==')'||c=='#') {
            return false;
        } else {
            return true;
        }
    }
}

```

>输出结果:  
3*(7-2)#   
计算结果为:15

*难点在于  
1.表达式的优先级区分,要点为更高优先级的要先计算，比如说3x(1+2),这里(的优先级就要比x高，因为要先算括号里面的，而后面的+的优先级又要比(高，但是比)小。  
2.循环的退出条件 直到运算全部结束，即当前栈顶元素和读入的操作符均为#*

***
* 汉诺塔问题
```
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
        int n=30;
        HanoiExcute(x,y,z,n);
    }

}

```
>输出结果  
第1次移动，将盘子1从x移动到z  
第2次移动，将盘子2从x移动到y    
第3次移动，将盘子1从z移动到y  
第4次移动，将盘子3从x移动到z  
第5次移动，将盘子1从y移动到x  
第6次移动，将盘子2从y移动到z  
第7次移动，将盘子1从x移动到z



ps:汉诺塔问题的数学递归式子得到的时间复杂度为  
2的n次方-1

*经典的递归题目，要点在于两点。  
1.`HanoiExcute(char x, char y, char z, int n)`这个方法的目的，就是将x上的n个圆盘按原本 的顺序从x移动到z上，y作为辅助柱子。参数含义为x是起始柱子，y是辅助柱子，z是目标柱子，n为圆盘个数。   
递归的核心就是要定义好要递归的函数定义。  
2.`move(char a,  char b, int n)`这个方法的目的是，从a柱子上将编号为n的圆盘移动到b柱子上。参数含义为，a为起始柱子，b为目标柱子，n为圆盘编号*

***

### `tree`  树
### `diagram` 图
### `sorting` 排序查找
