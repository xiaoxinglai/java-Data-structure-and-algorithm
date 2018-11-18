package demo;

/**
 * 串的模式匹配算法
 */
public class StringIndex {

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int index(String S, String T, int pos) {

        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕
        //双循环 时间复杂度是n*n  最坏情况下 每次匹配到最后一个字符时候不等，这样遍历了两层循环  ，最优情况下是n+m
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        for (int j = pos; j < S.length(); j++) {
            int tag = 0;
            for (int i = j; i - j < t.length; i++) {
                if (s[i] != t[i - j]) {
                    tag = 1;
                    break;
                }
            }
            if (tag == 0) {
                return j;
            }
        }

        return 0;
    }

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int indextwo(String S, String T, int pos) {
        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕

        int i = pos;
        int j = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                //重置指针，从新匹配
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == t.length) {
            return (i - j);
        } else {
            return 0;
        }
        //这个时间复杂度 最差仍然为n*n 等于每次快循环结束时候都要重置指针
    }


    public static void main(String[] args) {
        String s = "ab12cde";
        String t = "12";
        System.out.println(index(s, t, 2));
        System.out.println(indextwo(s,t,2));
    }
}
