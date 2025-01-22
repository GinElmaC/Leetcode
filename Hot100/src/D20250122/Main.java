package D20250122;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {9,8,7,6,5,1,2,3,4};
        System.out.println(maxCoins(numbers));
    }
    //leetcode每日一题，简单的排序，我每次取第二大的就行，bob每次取最小的，ali每次取最大的
    public static int maxCoins(int[] piles) {
        int n = piles.length;
        int res = 0;
        Arrays.sort(piles);
        int count = n/3;
        int start = n-2;
        while(count>0){
            res+=piles[start];
            start-=2;
            count--;
        }
        return res;
    }
}
