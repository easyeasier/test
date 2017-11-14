package wz.acm.str;

import java.util.ArrayList;
import java.util.List;

/**
 * 求数组的全排列组合
 * <p>
 * Created by wangz on 17-6-25.
 */
public class Permutations {
    public static void main(String[] args) {
        String input = "abc";
        permute(input).forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    /**
     * 求全排列组合
     * e.g  [1,2,3]  --->>> [1,2,3],[1,3,2],[2,1,3]等六种组合
     * 采用回溯的方法，每次从0开始添加元素，直到长度==length  ；（如1,2,3）
     * 然后回溯，去除当前一个，存入下一个（如1,2） -->> (1,3) ;(1)  --> (2)
     *
     * @param input 输入字符串
     * @return
     */
    public static List<List<Character>> permute(String input) {
        List<List<Character>> ret = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return ret;
        }
        List<Character> tempList = new ArrayList<>();
        backtrack(input, ret, tempList);         //每一次递归，必须从0开始，因为只是0的位置不同，仍然需要添加到templist中
        return ret;
    }

    /**
     * 回溯，当tempList.length == nums.length时，表明当前一次排列已经完成，添加到ret中
     * 回溯到上次排列的位置，继续for循环，取下一个应该放入的位置
     *
     * @param input
     * @param ret
     * @param tempList
     */
    private static void backtrack(String input, List<List<Character>> ret, List<Character> tempList) {
        if (tempList.size() == input.length()) {
            //不能直接add(tempList),在回溯后会更改tempList的值。这也是保护 不可变类 常用的方式，同样，get的时候，可以返回副本予以保护
            //addAll是可以的，因为是添加的副本
            ret.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            if (tempList.contains(input.charAt(i))) {
                continue;
            }
            tempList.add(input.charAt(i));

            backtrack(input, ret, tempList);
            tempList.remove(tempList.size() - 1);//当回溯回来时，去掉已经进行完的元素，进行下一次循环
        }
    }
}
