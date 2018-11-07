package stack.demo;

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
                    System.out.println("到达终点，坐标为:" + "(" + spath.x + "," + spath.y + ")");
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
