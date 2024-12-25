package D20241225;

public class Main {
    public static void main(String[] args) {

    }
    //双指针1
    //总算秒杀了一道简单题，从看到题目到写出来不超过1分钟
    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        while(right<n){
            if(nums[right] != 0){
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while(left<n) {
            nums[left] = 0;
            left++;
        }
    }
}
