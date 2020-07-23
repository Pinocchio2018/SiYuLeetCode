package siyu.leetcode;

/**
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 */
public class No154 {

    public int minArray(int[] numbers) {
        for (int i = 0; i <numbers.length-1 ; i++) {
            if(numbers[i]>numbers[i+1]){
                return numbers[i+1];
            }
        }
        return numbers[0];
    }
}
