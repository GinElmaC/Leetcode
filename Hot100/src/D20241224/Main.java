package D20241224;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {100,4,200,3,1,2};
        System.out.println(longestConsecutive(nums));
    }
    //哈希3
    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int res = 1;
        for(int i:nums){
            set.add(i);
        }
        for(int i:set){
            if(!set.contains(i-1)){
                int curr = 1;
                while(set.contains(i+1)){
                    curr++;
                    i+=1;
                }
                res = Math.max(curr,res);
            }
        }
        return res;
        //思路：排序+动态规划，超时
//        Arrays.sort(nums);
//        int n = nums.length;
//        int[] dp = new int[n];
//        Arrays.fill(dp,1);
//        int res = 0;
//        for(int i = 1;i<n;i++){
//            for(int j = 0;j<i;j++){
//                if(nums[j]+1 == nums[i]){
//                    dp[i] = Math.max(dp[i],dp[j]+1);
//                    res = Math.max(dp[i],res);
//                }
//            }
//        }
//        return res;
    }
}
