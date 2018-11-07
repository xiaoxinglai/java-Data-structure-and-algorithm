package stack.demo;
import stack.Stack;
/**
 * 栈的应用之括号匹配的检验
 * 输入一系列括号 返回是否匹配
 * 比如 ([]()）返回匹配
 * [([][])]返回匹配
 *  [(] 返回不匹配
 *  [())返回不匹配
 *  (()]返回不匹配
 *
 *  原理 最需要匹配的那个括号永远在栈顶
 *
 */
public class ParenthesisMathing {
    public static void main(String[] args) {
        char[] demo1={'(','[',']','(',')',')'};
        char[] demo2={'[','(','[',']','[',']',')',']'};
        char[] demo3={'[','(',']'};
        char[] demo4={'[','(',')',')'};
        char[] demo5={'(','(',')',']'};
        System.out.println(Juge(demo1));
        System.out.println(Juge(demo2));
        System.out.println(Juge(demo3));
        System.out.println(Juge(demo4));
        System.out.println(Juge(demo5));
    }

    static String Juge(char[] demo){
        Stack Stack =new Stack();
        Stack.InitStack();
        for (int i = 0; i <demo.length; i++) {
            if (demo[i]=='('||demo[i]=='['){
                Stack.push(demo[i]);
            }

            if (demo[i]==')'||demo[i]==']'){
                if ((char) Stack.getTop()=='('&&demo[i]==')'){
                    Stack.Pop();
                }else if ((char) Stack.getTop()=='['&&demo[i]==']'){
                    Stack.Pop();
                }else {
                    return "匹配失败";
                }
            }


        }
        return "匹配成功";
    }
}
