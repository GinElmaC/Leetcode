package D20250104;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String str = ")()())";
        System.out.println(longestValidParentheses(str));
    }
    //leetcode54中等-螺旋矩阵
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int[][] cns = new int[m][n];
        for(int i = 0;i<m*n;i++){
            if(check(x,y,n,m)||cns[y][x] == 1){
                x-=dir[count][0];
                y-=dir[count][1];
                count++;
                if(count == 4){
                    count = 0;
                }
                x+=dir[count][0];
                y+=dir[count][1];
                i--;
                continue;
            }
            list.add(matrix[y][x]);
            cns[y][x] = 1;
            x+=dir[count][0];
            y+=dir[count][1];
        }
        return list;
    }
    public static boolean check(int x,int y,int n,int m){
        if(x>=n||x<0||y>=m||y<0){
            return true;
        }
        return false;
    }
    //leetcode48中等-旋转图像
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] cns = new int[n][m];
        int x = 0;
        int y = 0;
        for(int i = 0;i<m;i++){
            for(int j = n-1;j>=0;j--){
                cns[y][x] = matrix[j][i];
                x++;
            }
            x = 0;
            y++;
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                matrix[i][j] = cns[i][j];
            }
        }
    }
    //leetcode240中等-搜索二维矩阵||
    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = m-1;
        int y = 0;
        while(y<n&&matrix[y][left]<=target){
            if(matrix[y][right]<target){
                y++;
                continue;
            }
            if(matrix[y][left] == target){
                return true;
            }
            if(matrix[y][right] == target){
                return true;
            }
            left = 0;
            right = m-1;
            while(left<right){
                int mid = (right+left)/2;
                if(matrix[y][mid] == target){
                    return true;
                }
                if(mid == left||mid == right){
                    if(matrix[y][left] == target||matrix[y][right] == target){
                        return true;
                    }
                    break;
                }
                if(matrix[y][mid]<target){
                    left = mid;
                }
                if(matrix[y][mid]>target){
                    right = mid;
                }
            }
            left = 0;
            right = m-1;
            y++;
        }
        return false;
    }
    //leetcode70简单-爬楼梯
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    //leetcode739中等-每日温度
    public static int[] dailyTemperatures(int[] temperatures) {
        //更快的
        int n = temperatures.length;
        int[] ans = new int[n];
        ans[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && temperatures[j] <= temperatures[i] && ans[j] != 0) {
                j += ans[j];
            }
            if (j < n && temperatures[j] > temperatures[i]) {
                ans[i] = j - i;
            }
        }
        return ans;
        //单调栈
//        Stack<Integer> stack = new Stack<>();
//        int n = temperatures.length;
//        int[] res = new int[n];
//        for(int i = 0;i<n;i++){
//            if(stack.empty()){
//                stack.push(i);
//                continue;
//            }
//            if(!stack.empty() && temperatures[i]<=temperatures[stack.peek()]){
//                stack.push(i);
//            }
//            if(!stack.empty() && temperatures[i]>temperatures[stack.peek()]){
//                while(!stack.empty() && temperatures[i]>temperatures[stack.peek()]){
//                    res[stack.peek()] = i-stack.peek();
//                    stack.pop();
//                }
//                stack.push(i);
//            }
//        }
//        return res;
        //暴力，超时
//        int n = temperatures.length;
//        int[] res = new int[n];
//        for(int i = 0;i<n;i++){
//            for(int j = i+1;j<n;j++){
//                if(temperatures[j]>temperatures[i]){
//                    res[i] = j-i;
//                    break;
//                }
//            }
//        }
//        return res;
    }
    //leetcode34中等-在排序数组中查找元素的第一个和最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n == 0 || nums[n-1]<target || nums[0]>target){
            return new int[]{-1,-1};
        }
        int left = 0;
        int right = n-1;
        while(left<=right){
            int mid = (right+left)/2;
            if(nums[mid] == target){
                left = mid;
                break;
            }
            if(nums[mid]>target){
                right = mid-1;
            }
            if(nums[mid]<target){
                left = mid+1;
            }
        }
        if(nums[left] != target){
            return new int[]{-1,-1};
        }
        int x = left;
        int y = left;
        while(x>=0 && nums[x] == target){
            x--;
            if(x<0 || nums[x] != target){
                x++;
                break;
            }
        }
        while(y<n && nums[y] == target){
            y++;
            if(y>=n || nums[y] != target){
                y--;
                break;
            }
        }
        return new int[]{x,y};
    }
    //leetcode33中等-搜索旋转排序数组
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(left<=right){
            int mid = (right+left)/2;
            if(nums[mid] == target){
                return mid;
            }
            //左侧有序
            if(nums[mid]>=nums[left]){
                if(nums[mid]>target && nums[left]<=target){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            //右侧有序
            if(nums[mid]<=nums[right]){
                if(nums[mid]<target && nums[right]>=target){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }
    //leetcode153中等-寻找旋转排序数组中的最小值
    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        int res = Integer.MAX_VALUE;
        while(left <= right){
            int mid = (right+left)/2;
            //左侧有序
            if(nums[mid]>=nums[left]){
                res = Math.min(nums[left],res);
                left = mid+1;
            }
            //右侧有序
            if(nums[mid]<=nums[right]){
                res = Math.min(nums[mid],res);
                right = mid-1;
            }
        }
        return res;
    }
    //leetcode4困难-寻找两个正序数组中的中位数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //下方函数传入的k代表要得到第k大的数，当数组总长度为奇数是，left和right的值都一样，得出的值也一样，当数组长度为偶数时，left与right相差为1；
        int left = (m+n+1)/2;
        int right = (m+n+2)/2;

        int numa =  getKth(nums1,0,m-1,nums2,0,n-1,left);
        int numb =  getKth(nums1,0,m-1,nums2,0,n-1,right);
        return (numa+numb)*0.5;
    }
    public static int getKth(int[] nums1,int start1,int end1,int[] nums2,int start2,int end2,int k){
        //计算出两个剩余数组的长度,end和start为下标索引
        int len1 = end1-start1+1;
        int len2 = end2-start2+1;

        //保证len1<len2，也就是当数组空的时候一定是len1空了；
        if(len1>len2){
            return getKth(nums2,start2,end2,nums1,start1,end1,k);
        }
        //当其中一个数组长度为0时，直接从另一个数组开头找当前第k大
        if(len1 == 0){
            return nums2[start2+k-1];
        }
        if(k == 1){//当k=1时，证明数组总长度为奇数，这是中位数就是各数组start位置的数中小的那一个
            return Math.min(nums1[start1],nums2[start2]);
        }
        //我们要排除k/2个元素，要比较每个数组第k/2的元素，所以要比较的下标索引为start+k/2-1,但是为了确保索引不超出范围，要和len进行比较
        int i = start1+Math.min(len1,k/2)-1;
        int j = start2+Math.min(len2,k/2)-1;
        if(nums1[i]>nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }else{
            return getKth(nums1,i+1,end1,nums2,start2,end2,k-(i-start1+1));
        }
    }
    //leetcode121简单-买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0;i<n;i++){
            buy = Math.min(buy,prices[i]);
            res=  Math.max(res,prices[i]-buy);
        }
        return res;
    }
    //leetcode55中等-跳跃游戏|
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int maxcover = nums[0];
        //每一步尽量挑最远的跳
        for(int i = 0;i<n;i++){
            if(maxcover>=i){
                maxcover = Math.max(maxcover,i+nums[i]);
            }
            if(maxcover>=n-1){
                return true;
            }
        }
        return false;
    }
    //leetcode45中等-跳跃游戏||
    public static int jump(int[] nums) {
        int n = nums.length;
        int count = 0;
        int[] dp = new int[n];
        Arrays.fill(dp,n+1);
        dp[0] = 0;
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<=nums[i]+i;j++){
                if(j > n-1){
                    break;
                }
                dp[j] = Math.min(dp[j],dp[i]+1);
            }
        }
        return dp[n-1];
    }
    //leetcode763中等-划分字母区间
    public static List<Integer> partitionLabels(String s) {
        //思路：获取字符串当中每个字符的最远出现位置
        int[] has = new int[26];
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        //更新每个字符出现的最远位置
        for(int i = 0;i<n;i++){
            has[s.charAt(i)-'a'] = i;
        }
        //用来获取我们的区间长度
        int left = 0;
        int right = 0;
        for(int i = 0;i<n;i++){
            right = Math.max(right,has[s.charAt(i)-'a']);
            //也就是遍历到了我们的区间末尾
            if(i == right){
                res.add(right-left+1);
                left = right+1;
            }
        }
        return res;
    }
    //leetcode198中等-打家劫舍
    public static int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1;i<n;i++){
            if(i == 1){
                dp[i] = Math.max(nums[i],nums[i-1]);
                continue;
            }
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }
    //leetcode32困难-最长有效括号
    public static int longestValidParentheses(String s) {
        //动态规划
        int n = s.length();
        int[] dp = new int[n+1];
        int res = 0;
        for(int i = 0;i<n;i++){
            if(s.charAt(i) == ')'){
                if(i-1-dp[i]>=0&&s.charAt(i-1-dp[i])=='('){
                    dp[i+1] = dp[i]+2+dp[i-1-dp[i]];
                }
            }
            res = Math.max(res,dp[i+1]);
        }
        return res;
    }
}
