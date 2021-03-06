package stack.demo;

import stack.Stack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 栈应用之行编辑器
 * 内容：
 * #表示退格  @表示退行
 * 比如  whli##ilr#e（s#*s）
 * outcha@putchar(*s=#++)
 * 实际有效的是如下
 * while(*s)
 * putchar(*s++)
 * <p>
 * 思路：用栈存储输入，直到输入结束为止'\n'表示，则输出 ，期间如果遇到#则出栈 ，遇到@则清空栈
 */
public class LineEdit {
    public static void main(String[] args) throws IOException {
        Stack Stack = new Stack();
        Stack.InitStack();

        try {
            char ch = (char) System.in.read();
            while (ch != '\n') {
                if (ch == '#') {
                    Stack.Pop();
                } else if (ch == '@') {
                    Stack.ClearStack();
                } else {
                    Stack.push(ch);
                }
                ch = (char) System.in.read();
            }
            List<Character> list=new ArrayList<>();
            while(Stack.getTop()!=null){
                list.add((Character) Stack.Pop());
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
