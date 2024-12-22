import java.util.Arrays;

public class Q2 {
    public static void main(String[] args) {

    }
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int pre = Integer.MIN_VALUE;//用来记录前一个数
        int res = 0;
        for(int i:nums){
            i = Math.min(i+k,Math.max(pre+1,i-k));
            if(i>pre){
                res++;
                pre = i;
            }
        }
        return res;
    }
}
