package com.GinElmaC.code;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {

    }
    //2025.11.18:leetcode717,遇到1跳2步，遇到0跳1步，看最后一位0是否被跳过
    public static boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while(true){
            if(i == n){
                return false;
            }
            if(i == n-1){
                return true;
            }
            if(bits[i] == 1){
                i+=2;
            }else{
                i++;
            }
        }
    }
    //2025.11.19:leetcode2154，时间换空间使用遍历，空间换时间使用哈希表
    public static int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        for(int num:nums){
            if(original == num){
                original*=2;
            }
        }
        return original;
    }
}
