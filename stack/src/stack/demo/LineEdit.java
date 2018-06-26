package stack.demo;

import stack.stack;

import java.io.IOException;
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
public class LineEdit  {
    public static void main(String[] args) throws IOException {
        stack stack = new stack();
        stack.InitStack();

        try {
            char ch = (char) System.in.read();


        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            throw e;
        }



    }
}
