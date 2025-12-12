package com.GinElmaC.code;

import java.util.*;

public class Code {
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
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        if(n == 1){
            return 1;
        }
        int left = 0;
        while(true){
            if(left>=n){
                return res;
            }
            Map<Character, Integer> map = new HashMap<>();
            int right = left;
            while(right<n && !map.containsKey(s.charAt(right))){
                map.put(s.charAt(right), 0);
                right++;
            }
            res = Math.max(res, right - left);
            left++;
        }
    }

    public static void main(String[] args) {
        //numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
        List<List<String>> data = Arrays.asList(
                Arrays.asList("MESSAGE", "1", "id0 id1"),
                Arrays.asList("MESSAGE", "5", "id2"),
                Arrays.asList("MESSAGE", "6", "ALL"),
                Arrays.asList("OFFLINE", "5", "2")
        );
        countMentions(3,data);
    }
    public static int[] countMentions(int numberOfUsers, List<List<String>> events) {
        //status[i][count,time]标识id为i的用户被提及的数量、是否在线、下次上线的时间戳
        int[][] status = new int[numberOfUsers][2];
        events.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int a1 = Integer.parseInt(o1.get(1));
                int a2 = Integer.parseInt(o2.get(1));
                return a1==a2?o2.get(0).charAt(0)-o1.get(0).charAt(0):a1-a2;
            }
        });
        int n = events.size();
        int timestarp = 0;
        for(int i = 0;i<n;i++){
            List<String> msg = events.get(i);
            timestarp = Integer.parseInt(msg.get(1));
            switch(msg.get(0)){
                case "MESSAGE":
                    //全体存活添加
                    if(Objects.equals(msg.get(2), "HERE")){
                        for(int m = 0;m<numberOfUsers;m++){
                            if(status[m][1]<=timestarp){
                                status[m][0]++;
                            }
                        }
                    }else if(Objects.equals(msg.get(2), "ALL")){
                        for(int m = 0;m<numberOfUsers;m++){
                            status[m][0]++;
                        }
                    }else{
                        //单独加
                        String ids = msg.get(2);
                        String[] s = ids.split(" ");
                        for(String s1:s){
                            status[Integer.parseInt(s1.substring(2))][0]++;
                        }
                    }
                    break;
                case "OFFLINE":
                    //更新离线时间
                    String id = msg.get(2);
                    String time = msg.get(1);
                    status[Integer.parseInt(id)][1] = Integer.parseInt(time)+60;
                    break;
            }
        }
        int[] res = new int[numberOfUsers];
        for(int i = 0;i<numberOfUsers;i++){
            res[i] = status[i][0];
        }
        return res;
    }
}
