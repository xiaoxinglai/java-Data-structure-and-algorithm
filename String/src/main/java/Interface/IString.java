package Interface;


import impl.ArrayString;

/**
 * 串 是由零个或者多个字符组成的有限序列
 * 串中字符的数目n称为串的长度   零个字符的串称为空串 它的长度为零
 * 串中任意个连续字符字符组成的子序列称为该串的子串  包含子串的串相应的称为主串
 * 通常称字符在序列中的序号为该字符在串中的位置  子串的位置则是以该子串的第一个字符在主串中的位置来表示 （ps:第一个字符的位置是1 不是零）
 * 两个串相等的前提是 两个串的长度相等 且对应位置的各个字符也都相等
 * 由一个或多个空格组成的串 也称为空格串，不等于空串，长度为空格的数目
 *
 * 串的存储结构与线性表相似，但是基本操作不同，串的基本操作是以串的整体作为操作对象，线性表是以单个元素作为操作对象
 * 例如 在串中查询某个子串，求取某个子串，在串的某个位置插入一个子串 以及删除一个子串等
 *
 * 以下操作中：
 * 串赋值，串比较,求串长,串连接，求子串 这五种操作是最小操作，其他的串操作可以用这五种组合成
 *
 *
 */
public interface IString {

    /**
     * 生成一个其值等于chars的串T
     * @param chars
     * @return
     */
    IString StrAssign(char[]  chars);

    /**
     * 将串的内容复制给target
     * @param
     * @return
     */
    IString StrCopy(IString target);

    /**
     * 若是串为空 则返回true 否则返回false
     * @return
     */
    Boolean StrEmpty();

    /**
     * 如果本字符串比target大，则返回大于0 若是相等 返回0  若是不等 则返回-1
     * @param target
     * @return
     */
    int StrCompare(IString target);

    /**
     * 返回串的长度 即元素个数
     * @return
     */
    int StrLength();

    /**
     * 将串清空为空串
     */
    Boolean ClearString();

    /**
     * 连接为新串，并返回新串
     */
    IString Concat(IString s2);

    /**
     * 返回串的第post个字符起，长度为len的子串
     */
    IString SubString(int pos,int len);

    /**
     * 若主串中存在和串T值相同的子串，则返回它在主串中第pos个位置之后的第一次出现的位置，否则返回0
     *
     * @param T 子串
     * @param pos 指定位置
     * @return
     */
    int index(IString T,int pos);

    /**
     * 用V替换所有在主串中出现的所有与T相等的不重叠的子串
     * @param T
     * @return
     */
    IString Replace(IString T,IString V);

    /**
     * 在主串的第pos个字符之前插入串T
     * @param T
     * @param pos
     * @return
     */
    IString StrInsert(IString T,int pos);

    /**
     * 从主串中删除第pos个字符起长度为len的子串
     * @param pos
     * @param len
     * @return
     */
    IString StrDelete(int pos,int len);

    /**
     * 销毁
     */
    IString DestroyString();

    /**
     * 打印自己
     */
    void print();

    /**
     * 转为数组
     */
    char[] toArray();


}
