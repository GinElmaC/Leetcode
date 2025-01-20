package D20250120;

public class Main {
    public static void main(String[] args) {

    }
    //每日一题
    public static int findClosestNumber(int[] nums) {
        int mindiff = Math.abs(nums[0]);
        int res = nums[0];
        for(int a:nums){
            if(mindiff == Math.abs(a)){
                res = Math.max(res,a);
            }else if(mindiff>Math.abs(a)){
                mindiff = Math.abs(a);
                res = a;
            }
        }
        return res;
    }
}
