package D20250110;

public class Main {
    public static void main(String[] args) {
        String str1 = "abcabc";
        String word2 = "abc";
        System.out.println(validSubstringCount(str1,word2));
    }
    //leetcode3297/3298困难-统计重新排列后包含另一个字符串的字符串数目
    public static long validSubstringCount(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] cns = new int[26];
        //cns(word2)是has(word1)的前缀
        for(int i = 0;i<n2;i++){
            cns[word2.charAt(i)-'a']++;
        }
        long res = 0;
        int[] has = new int[26];
        int left = 0;
        int right = 0;
        while(right<n1){
            while(right<n1&&!check(cns,has)){
                has[word1.charAt(right)-'a']++;
                right++;
            }
            while(left<right&&check(cns,has)){
                //如果当前位置符合要求，则right向右移动同样满足
                res+=(n1-right+1);
                has[word1.charAt(left)-'a']--;
                left++;
            }
        }
        return res;
    }
    public static boolean check(int[] cns,int[] has){
        for(int i = 0;i<26;i++){
            if(cns[i]>has[i]){
                return false;
            }
        }
        return true;
    }
    //leetcode416中等-分割等和子集
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int a:nums){
            sum+=a;
        }
        if(sum%2 != 0){
            return false;
        }
        sum/=2;
        int[] dp = new int[sum+1];
        for(int i = 0;i<n;i++){
            for(int j = sum;j>=nums[i];j--){
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum] == sum;
    }
    //leetcode115困难-不同的子序列
    public static int numDistinct(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int[] has = new int[26];
        int[] cns = new int[26];
        for(int i = 0;i<n2;i++){
            cns[t.charAt(i)-'a']++;
        }
        long res = 0;
        for(int i = 0;i<n1;i++){
            has[s.charAt(i)-'a']++;
            if(check115(has,cns)){
                res++;
            }
        }
        long s1 = (long)Math.pow(10,9)+7;
        return (int)(res%s1);
    }
    public static boolean check115(int[] has,int[] cns){
        for(int i = 0;i<26;i++){
            if(has[i]<cns[i]){
                return false;
            }
        }
        return true;
    }
}
