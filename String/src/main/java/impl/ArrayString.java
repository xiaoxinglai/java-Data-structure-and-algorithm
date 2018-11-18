package impl;

import Interface.IString;

/**
 * 定长顺序存储表示串
 * <p>
 * 用一组地址连续的存储单元存储串值的字符序列
 * 在串的定长顺序存储结构中，按照预定义的大小，为每个定义的串变量分配一个固定长度的存储区
 * 串的实际长度 可以在定长范围内随意
 * <p>
 * 定长数组，超出部分自动截断
 * <p>
 * <p>
 * 通俗的说，就是用字符数组来实现字符串
 * <p>
 * ps:
 * 串赋值，串比较,求串长,串连接，求子串(SubString) 这五种操作是最小操作，其他的串操作可以用这五种组合成
 *
 * 存储密度=串值所占的存储单位/实际分配的存储单位
 * 因此 数组型实现的串存储密度大于链表型存储实现的串
 *
 *
 *
 */

public class ArrayString implements IString {

    private final int MAXSTRLEN = 10;
    private char[] values;

    /**
     * 生成一个其值等于chars的串T
     *
     * @param chars
     * @return
     */
    public IString StrAssign(char[] chars) {
        int len = chars.length > MAXSTRLEN ? MAXSTRLEN : chars.length;
        values = new char[len];
        copyArray(values, chars);
        return this;
    }

    /**
     * 将串的内容复制给target
     *
     * @param
     * @return
     */
    public IString StrCopy(IString target) {
        target.StrAssign(this.values);
        return target;
    }

    /**
     * 若是串为空 则返回true 否则返回false
     *
     * @return
     */
    public Boolean StrEmpty() {
        if (StrLength() == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 如果本字符串比target大，则返回大于0 若是相等 返回0  若是不等 则返回-1
     *
     * @param target
     * @return
     */
    public int StrCompare(IString target) {
        char[] c2 = target.toArray();
        for (int i = 0; i < this.values.length && i < c2.length; i++) {
            if (this.values[i] != c2[i]) {
                return -1;
            }
        }
        return (this.values.length - c2.length);
    }

    /**
     * 返回串的长度 即元素个数
     *
     * @return
     */
    public int StrLength() {
        if (values != null) {
            return values.length;
        } else {
            return 0;
        }
    }


    /**
     * 将串清空为空串
     */
    public Boolean ClearString() {
        values = null;
        return true;
    }

    /**
     * 连接为新串，并返回新串
     */
    public IString Concat(IString s2) {
        int len = StrLength() + s2.StrLength() > MAXSTRLEN ? MAXSTRLEN : StrLength() + s2.StrLength();
        char[] charNews = new char[len];
        copyArray(charNews, this.values);
        char[] cs2 = s2.toArray();
        int limit = StrLength() + s2.StrLength() > MAXSTRLEN ? MAXSTRLEN - StrLength() : s2.StrLength();
        for (int i = 0; i < limit; i++) {
            charNews[StrLength() + i] = cs2[i];
        }
        IString newS = new ArrayString();
        newS.StrAssign(charNews);
        return newS;
    }

    /**
     * 返回串的第post个字符起，长度为len的子串
     */
    public IString SubString(int pos, int len) {
        if (pos < 0 || len + pos - 1 > StrLength()) {
            return null; //超出界限
        }
        char[] c = new char[len];
        for (int i = pos; i < len + pos; i++) {
            c[i - pos] = values[i - 1];
        }

        IString s = new ArrayString();
        s.StrAssign(c);
        return s;
    }

    /**
     * 若主串中存在和串T值相同的子串，则返回它在主串中第pos个位置之后的第一次出现的位置，否则返回0
     * 利用 compare subString 组合使用
     *
     * @param T   子串
     * @param pos 指定位置
     * @return
     */
    public int index(IString T, int pos) {

        if (pos + T.StrLength() > StrLength()) {
            return 0;
        }

        for (int i = pos; i < StrLength(); i++) {
            if (SubString(i, T.StrLength()).StrCompare(T) == 0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 用V替换所有在主串中出现的所有与T相等的不重叠的子串
     * <p>
     * 思路为 先找到相等的串，然后删除掉，再在原来的位置插入新的。
     * 因为如果直接替换元素，T V 若是长度不同，则会破坏原来数据的完整
     *
     * @param T
     * @return
     */
    public IString Replace(IString T, IString V) {

        IString news = this;
        int tag = news.index(T, 1);
        while (tag != 0) {
            news = news.StrDelete(tag, T.StrLength()).StrInsert(V, tag);
            tag = news.index(T, tag);
        }
        return news;
    }


    /**
     * 在主串的第pos个字符之前插入串T
     *
     * 要考虑到末尾插入的情况！！ 特殊情况！！
     * @param T
     * @param pos
     * @return
     */
    public IString StrInsert(IString T, int pos) {
        if (pos >= 1) {
            int len = StrLength() + T.StrLength() <= MAXSTRLEN ? StrLength() + T.StrLength() : MAXSTRLEN;
            char[] newc = new char[len];
            char[] inc = T.toArray();
            copyArray(newc, values);

            //在末尾插入
            if (StrLength() < pos && pos < MAXSTRLEN) {
                for (int i = 0; i < T.StrLength(); i++) {
                    newc[pos-1+i] = inc[i];

                }
            } else {
                //挪开位置
                for (int i = 0; i <= T.StrLength(); i++) {
                    newc[len - 1 - i] = newc[StrLength() - 1 - i];
                }
                //插入新值
                for (int i = 0; i < T.StrLength(); i++) {
                    newc[pos - 1 + i] = inc[i];
                }
            }

            IString news = new ArrayString();
            news.StrAssign(newc);
            return news;

        }

        return null;
    }

    /**
     * 从主串中删除第pos个字符起长度为len的子串
     *
     * @param pos
     * @param len
     * @return
     */
    public IString StrDelete(int pos, int len) {
        int leng = pos - 1 + len > StrLength() ? pos : StrLength() - len;
        char[] c = new char[leng];
        copyArray(c, SubString(1, pos - 1).toArray());
        IString s = new ArrayString();
        if (pos + len > StrLength()) {
            s.StrAssign(c);
            return s;
        } else {
            for (int i = 0; i <= leng - pos; i++) {
                c[pos - 1 + i] = values[pos + len + i - 1];
            }

            s.StrAssign(c);
            return s;
        }
    }

    public IString DestroyString() {
        return null;
    }

    public void print() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
        }
        System.out.println();
    }

    public char[] toArray() {
        char[] c = new char[values.length];
        copyArray(c, values);
        return c;
    }

    private void copyArray(char[] target, char[] source) {
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
    }

    public static void main(String[] args) {
        //赋值
        System.out.println("赋值");
        ArrayString as = new ArrayString();
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        as.StrAssign(chars);
        as.print();
        //拷贝
        System.out.println("拷贝");
        ArrayString as2 = new ArrayString();
        as.StrCopy(as2);
        as2.print();

        System.out.println("转为数组");
        System.out.println(as.toArray());
        char[] c2 = as.toArray();
        c2[2] = 'x';
        System.out.println(as.toArray());
        System.out.println(c2);

        System.out.println("求串长");
        System.out.println(as.StrLength());

        System.out.println("判空");
        System.out.println(as.StrEmpty());

        System.out.println("比较两个字符串");
        IString as3 = new ArrayString();
        as3.StrAssign(new char[]{'A', 'B'});
        System.out.println(as.StrCompare(as2));
        System.out.println(as.StrCompare(as3));


        System.out.println("连接 未截断");
        as.Concat(as2).print();
        System.out.println("连接 截断");

        IString as4 = new ArrayString();
        as4.StrAssign(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'});
        as.Concat(as4).print();

        System.out.println("截取子串 从第2个位置开始 截取4位");
        as4.SubString(2, 3).print();


        System.out.println("子串匹配");
        IString as5 = new ArrayString();
        as5.StrAssign(new char[]{'6', '7'});
        System.out.println(as4.index(as5, 1));


        System.out.println("插入新值");
        IString as6 = new ArrayString();
        as6.StrAssign(new char[]{'a', 'b', 'c'});
        as6.StrInsert(as5, 2).print();


        System.out.println("删除指定位置2开始 长度为3的子串");
        as4.print();
        as4.StrDelete(2, 3).print();
        as4.StrDelete(5, 2).print();


        System.out.println("子串替换");
        IString as7 = new ArrayString();
        as7.StrAssign(new char[]{'a', 'b', 'c', 'd', 'e', 'c', 'd'});

        IString T = new ArrayString();
        T.StrAssign(new char[]{'c', 'd'});

        IString V = new ArrayString();
        V.StrAssign(new char[]{'1', '2'});
        as7.Replace(T, V).print();
    }


}
