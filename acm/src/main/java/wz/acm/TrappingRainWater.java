package wz.acm;

/**
 * Created by wangz on 17-6-21.
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(nums));
    }

    /**
     *  1.能盛水的区间都是中间低的.
     *  2. 求值恩都是两边中第二高的(也就是短板)与区间内的值相间的面值累加之和
     *  3.如果是左边高就从左往右
     *  4.如果是右边高则从右往左
     *
     *  5.最重要的一点,左边高,则要从右边走,右边高则从左边走,始终让短板在走的一端
     * @param nums
     * @return
     */
    public static int trap(int[] nums){
        int len = nums.length;
        int left = 0,right = len -1;
        int secHigh = 0;
        int area = 0;
        while(left<right){
            if(nums[left]<nums[right]){
                secHigh = Math.max(secHigh,nums[left]);
                area += secHigh - nums[left];
                left++;
            }else{
                secHigh = Math.max(secHigh,nums[right]);
                area+= secHigh - nums[right];
                right--;
            }
        }

        return area;
    }
}
