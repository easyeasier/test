package wz.acm.str;

/**
 * Created by wangz on 17-11-5.
 */

/**
 * 求 给定字符的所有字符组合
 * 如 :
 * input = ['a','b','c']
 * output = a,b,c,ab,ac,bc,abc
 * <p>
 * 思路:
 * 1. 字符的长度范围是len =1 - len(input)
 * 2. 确定每次的字符长度len,然后确定起始字符,
 * 3. 当长度等于i的时候就是要回溯的时候了
 * 4. 计算其从1- len(input)的组合
 */
public class AllCombination {
    public static void main(String[] args) {
        allCombination("abcd");
    }

    public static void allCombination(String input) {
        int len = input.length();

        for (int tempLen = 1; tempLen <= len; tempLen++) {
            for (int index = 0; index <= len - 1; index++) {
                String tempStr = input.charAt(index) + "";
                comb(input, tempStr, tempLen, index + 1);
            }
        }
    }

    /**
     * @param input   初始的字符串
     * @param tempStr 已经组合的前缀字符串
     * @param tempLen 前缀字符串长度
     * @param index   当前字符字符串尾部所在的位置
     */
    public static void comb(String input, String tempStr, int tempLen, int index) {
        //
        if (tempStr.length() == tempLen) {
            System.out.println(tempStr);
            return;
        } else if (index > input.length() - 1) {
            return;
        } else {
            for (int i = index; i < input.length(); i++) {
                comb(input, tempStr + input.charAt(i), tempLen, i + 1);
            }
        }
    }
}
