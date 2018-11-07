package stack.demo;

import stack.Stack;

/**
 * 迷宫求解
 * 从当前入口出发，顺某一方向前进，若则走通，则继续走，否则沿原路退回，换一个方向继续走，直到所有的可能的通路都走过为止
 * 需要一个后进先出的结构来保存入口到当前位置的路径，这个结构就是栈
 *
 * 衍生问题：迷宫最短路径   自动生成迷宫
 */
public class MazePath {

    private static int[][] mg = null;//creatMG();//初始化时候生成迷宫
    private static Stack<SElemType> stack = new Stack();

    //迷宫的坐标
    static class Seat {
        int x; //横坐标
        int y; //纵坐标

        public Seat(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //栈的元素
    static class SElemType {
        Seat seat;
        int di;//从此通道块走向下一通道块的方向  0 1 2 3 表示右 下 左 上
    }


    public static void main(String[] args) {

        SElemType sElemType = new SElemType();//第一个栈元素
        sElemType.seat = new Seat(0, 1); //入口坐标为0 1
        sElemType.di = 0;//表示初始向右走
        stack.InitStack();

        do {
            if (Pass(sElemType.seat)) { //如果该位置可以通过
                stack.push(sElemType);  //放入到栈顶中
                if (IsEnd(sElemType.seat)) { //判断是否到达终点
                    System.out.println("恭喜到达终点,终点坐标为"+sElemType.seat.x+","+sElemType.seat.y);
                    System.out.println("该路线的路径为:");
                    for (SElemType elemType : stack.getAll()) {
                        System.out.println("("+elemType.seat.x+","+elemType.seat.y+")");
                    }
                    return; //到达终点 停止
                }
                //进入下一位置  以及方向是否维持原方向
                sElemType = next(sElemType, Boolean.TRUE); //表示从当前位置 维持原来的方向
            } else {
                //不能通过的 获取上一步位置
                sElemType = stack.getTop();
                //进入下一位置  以及方向变换一次 不维持原来的方向
                sElemType = next(sElemType, Boolean.FALSE);
            }
        } while (!stack.StackEmpty());


    }


    //向指定方向走 且需要判断是否越界，且需要判断是否是来过的位置 是的话 需要换一个方向
    private static SElemType next(SElemType sElemType, Boolean isKeep) {
        //维持原来的方向更新坐标   0 1 2 3 表示右 下 左 上
        if (isKeep) {
            if (sElemType.di == 0) { //向右更新坐标

                int y = sElemType.seat.y + 1;
                if (y > 9) {//越界
                    //需要更换方向
                    return next(sElemType, Boolean.FALSE);
                }
                //创建新的栈元素
                return getsElemType(sElemType, y, sElemType.seat.x);
            }
            SElemType x = getsElemType(sElemType);
            if (x != null) return x;

        } else {
            //排除掉原来的方向和之前来过的方向 更新坐标
            sElemType.di++; //换一个方向
            if (sElemType.di <= 3) {
                SElemType x = getsElemType(sElemType);
                if (x != null) return x;
            } else {
                System.out.println("已经到达该路线的终点了，该坐标是" + sElemType.seat.x + "," + sElemType.seat.y);
                //退一步 所有方向都试过了才能退栈
                stack.Pop();
                //然后返回前一个栈的格子
                return stack.getTop();
            }

        }


        return sElemType;
    }

    private static SElemType getsElemType(SElemType sElemType) {
        if (sElemType.di == 1) {//往下走
            int x = sElemType.seat.x + 1;
            if (x > 5) {
                return next(sElemType, Boolean.FALSE); //越界 换方向
            }
            int y = sElemType.seat.y;
            if (isRecord(x, y)) {
                return next(sElemType, Boolean.FALSE); //该位置来过 换一个方向
            } else {
                return getsElemType(sElemType, y, x);
            }

        }
        if (sElemType.di == 2) {//往左走
            int y = sElemType.seat.y - 1;
            if (y < 0) {
                return next(sElemType, Boolean.FALSE); //越界 换方向
            }
            int x = sElemType.seat.x;
            if (isRecord(x, y)) {
                return next(sElemType, Boolean.FALSE);//该位置来过 换一个方向
            } else {
                return getsElemType(sElemType, y, x);
            }
        }
        if (sElemType.di == 3) { //往上走
            int x = sElemType.seat.x - 1;
            if (x < 0) {
                return next(sElemType, Boolean.FALSE); //越界 换方向
            }
            int y = sElemType.seat.y;
            if (isRecord(x, y)) {
                return next(sElemType, Boolean.FALSE);//该位置来过 换一个方向
            } else {
                return getsElemType(sElemType, y, x);
            }
        }
        //退一步 所有方向都试过了才能退栈
        return stack.Pop();
    }

    private static SElemType getsElemType(SElemType sElemType, int y, int x) {
        SElemType sElemTypeNew = new SElemType();
        sElemTypeNew.seat = new Seat(x, y);
        sElemTypeNew.di = 0;//表示初始向右走
        return sElemTypeNew;
    }


    private static Boolean isRecord(int x, int y) {
        if (stack.getAll() == null) {
            return Boolean.FALSE;
        }
        //看栈里面是有存在这个坐标
        for (SElemType sElemType : stack.getAll()) {
            if (sElemType==null||sElemType.seat == null) {
                break;
            }
            if (sElemType.seat.x == x && sElemType.seat.y == y) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private static Boolean Pass(Seat seat) {
        //同时也要判断是不是走过的
        if (mg[seat.x][seat.y] == 1&&!isRecord(seat.x , seat.y)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private static Boolean IsEnd(Seat seat) {

        if (seat.x == 4 && seat.y == 4) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }


//    public static int[][] creatMG() {
//        //创建一个迷宫
//        int mg[][] = new int[5][10];
//        //创建正确的路径，值为1表示可以通过
//        mg[0][1] = 1;
//        mg[1][1] = 1;
//        mg[2][1] = 1;
//        mg[2][2] = 1;
//        mg[3][2] = 1;
//        mg[3][3] = 1;
//        mg[3][4] = 1;
//        mg[4][4] = 1;
//
//        //创建干扰路径
//        mg[0][2] = 1;
//        mg[0][3] = 1;
//        mg[0][4] = 1;
//        mg[1][4] = 1;
//        mg[1][5] = 1;
//        mg[1][6] = 1;
//        mg[2][6] = 1;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(mg[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        return mg;
//    }
}





