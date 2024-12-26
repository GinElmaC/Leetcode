package D20241226;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums= {1,2,1,2,1};
        //           1,3,4,6,7
        int k = 3;
        System.out.println(subarraySum(nums,k));
    }
    //双指针-接雨水
    public static int trap(int[] height) {
        int n = height.length;
        int[] leftmax = new int[n];
        leftmax[0] = height[0];
        for(int i = 1;i<n;i++){
            leftmax[i] = Math.max(leftmax[i-1],height[i]);
        }

        int[] rightmax = new int[n];
        rightmax[n-1] = height[n-1];
        for(int i = n-2;i>=0;i--){
            rightmax[i] = Math.max(rightmax[i+1],height[i]);
        }

        int res = 0;
        for(int i = 0;i<n;i++){
            res+=Math.min(leftmax[i],rightmax[i])-height[i];
        }
        return res;
    }
    //滑动窗口-找到字符串中所有字母的异位词
    public static List<Integer> findAnagrams(String s, String p) {
        //方法二：
        int S = s.length();
        int P = p.length();

        int left = 0;
        int right = left+P-1;

        if(P>S){
            return new ArrayList<>();
        }

        int[] sch = new int[26];
        int[] pch = new int[26];
        for(int i = 0;i<P;i++){
            pch[p.charAt(i)-'a']++;
        }
        for(int i = left;i<=right;i++){
            sch[s.charAt(i)-'a']++;
        }

        List<Integer> res=  new ArrayList<>();
        while(right<S){
            if(check(sch,pch)){
                res.add(left);
            }

            sch[s.charAt(left)-'a']--;
            left++;

            right++;
            if(right == S){
                break;
            }
            sch[s.charAt(right)-'a']++;
        }
        return res;
        //方法一：太慢
//        int S = s.length();
//        int P = p.length();
//        if(P>S){
//            return new ArrayList<>();
//        }
//        int left = 0;
//        int right = left+P-1;
//
//        char[] ch = p.toCharArray();
//        Arrays.sort(ch);
//        p = new String(ch);
//
//        List<Integer> list = new ArrayList<>();
//        while(right<S){
//            char[] now = new char[P];
//            for(int i = left;i<=right;i++){
//                now[i-left] = s.charAt(i);
//            }
//            Arrays.sort(now);
//            String n = new String(now);
//            if(n.equals(p)){
//                list.add(left);
//            }
//            left++;
//            right++;
//        }
//        return list;
    }
    public static boolean check(int[] a,int[] b){
        for(int i = 0;i<a.length;i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
    //子串-和为k的子数组
    public static int subarraySum(int[] nums, int k) {
        //快方法
        int n = nums.length;
        //用于统计
        Map<Integer,Integer>map = new HashMap<>();
        //为了防止我们的输入数组中以0开始
        //有一个用例是[0,0,0,0,0,0]，如果没有这一项，我们的结果会少
        map.put(0,1);
        //目前的和
        int s = 0;
        //维护结果
        int res = 0;
        for(int i = 0;i<n;i++){
            //更新和
            s+=nums[i];
            //更新答案
            //假如现在s小于k，那我们从map中得到的就是0
            res+=map.getOrDefault(s-k,0);
            map.put(s,map.getOrDefault(s,0)+1);
        }
        return res;
        //前缀和方法，很慢但是通过了
//        int n = nums.length;
//        int[] pre = new int[n];
//        pre[0] = nums[0];
//        for(int i = 1;i<n;i++){
//            pre[i] = pre[i-1]+nums[i];
//        }
//
//        int res = 0;
//        for(int i = 0;i<n;i++){
//            if(pre[i] == k){
//                res++;
//            }
//            for(int j = i+1;j<n;j++){
//                if(pre[j]-pre[i] == k){
//                    res++;
//                }
//            }
//        }
//        return res;
    }
}
