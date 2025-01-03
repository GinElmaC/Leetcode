package D20250103;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;
        searchInsert(nums,target);
    }
    //leetcode1047简单
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        stack.push(s.charAt(0));
        for(int i = 1;i<n;i++){
            if(!stack.isEmpty()&&s.charAt(i) == stack.peek()){
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        int l = stack.size();
        for(int i = 0;i<l;i++){
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
    //leetcode239困难-滑动窗口的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        for(int i = 0;i<n;i++){
            //因为当滑动窗口中没有之前的历史最大值时，我们不应该继续保留那个最大值
            //如果我们的队列中储存的是数组中的值，那对于历史最大值该不该被弹出就很难办
            //所以我们的队列中储存数组下标。
            //我们需要的最大值是在[i-k+1,i]之间的
            //又因为我们的遍历是从左到右的，换言之，队列从头到尾的下标一定是单调递增的
            //所以我们只需要从deque的First进行判断是否在[i-k+1,i]之间即可。

            //判断First范围
            while(!deque.isEmpty()&&deque.peek()<i-k+1){
                deque.poll();
            }
            //我们的deque的First一定是最大的
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            //添加当前下标
            deque.offer(i);
            //更新我们的答案数组，将First对应的下标添加到我们的答案数组中即可
            if(i>=k-1){
                res[index] = nums[deque.peekFirst()];
                index++;
            }
        }
        return res;
    }
    //leetcode76困难-最小覆盖子串
    public static String minWindow(String s, String t) {
        //哈希表快方法
        //获取长度
        int n = s.length();
        int m = t.length();
        //构造由t组成的数组
        int[] need = new int[255];
        //构造目前遍历到字符串组成的数组
        int[] visit = new int[255];
        //获取一共需要字符
        int toneed = 0;
        //目前已经对应上了几种
        int found = 0;
        //最终的左端点
        int resleft = 0;
        //最终的右端点
        int resright = 0;
        //最小的长度
        int min = Integer.MAX_VALUE;
        //初始化由t组成的数组
        for(int i = 0;i<m;i++){
            need[(int)t.charAt(i)]++;
            //种类加一
            if(need[(int)t.charAt(i)] == 1){
                toneed++;
            }
        }
        int left = 0;
        int right = 0;
        while(right<n){
            //当目前遍历的窗口没有t字符串
            while(right<n && found != toneed){
                visit[(int)s.charAt(right)]++;
                //有一种字符满足了
                if(visit[(int)s.charAt(right)] == need[(int)s.charAt(right)]){
                    found++;
                }
                right++;
            }
            //当目前遍历的窗口有了t字符串
            while(left<=right && found == toneed){
                //更新最小窗口
                if(min>right-left+1){
                    min = right-left+1;
                    resleft = left;
                    resright = right;
                }
                if(visit[(int)s.charAt(left)] == need[(int)s.charAt(left)]){
                    found--;
                }
                visit[(int)s.charAt(left)]--;
                left++;
            }
        }
        return min == Integer.MAX_VALUE?"":s.substring(resleft,resright);
        //map方法，慢
//        int n = s.length();
//        int m = t.length();
//        if(n<m){
//            return "";
//        }
//        Map<Character,Integer> maps = new HashMap<>();
//        Map<Character,Integer> mapt = new HashMap<>();
//        for(int i = 0;i<m;i++){
//            mapt.put(t.charAt(i),mapt.getOrDefault(t.charAt(i),0)+1);
//        }
//
//        int left = 0;
//        int right = -1;
//        int min = Integer.MAX_VALUE;
//        int resleft = 0;
//        int resright = 0;
//        while(right<n){
//            right++;
//            if(right<n&&mapt.containsKey(s.charAt(right))){
//                maps.put(s.charAt(right),maps.getOrDefault(s.charAt(right),0)+1);
//            }
//            while(check(maps,mapt)&&left<=right){
//                if(right-left+1<min){
//                    min = right-left+1;
//                    resleft = left;
//                    resright = right;
//                }
//                if(left<n&&mapt.containsKey(s.charAt(left))){
//                    maps.put(s.charAt(left), maps.getOrDefault(s.charAt(left),0)-1);
//                }
//                left++;
//            }
//        }
//        return min == Integer.MAX_VALUE?"":s.substring(resleft,resright+1);
    }
    public static boolean check(Map<Character,Integer>maps,Map<Character,Integer>mapt){
        Iterator iter = mapt.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (maps.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
    //leetcode53中等-最大子数组和
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        for(int i = 1;i<n;i++){
            if(nums[i-1]>=0){
                int a = nums[i-1]+nums[i];
                nums[i] = a;
            }
            max = Math.max(max,nums[i]);
        }
        return max;
    }
    //leetcode56中等-合并区间
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        int n = intervals.length;
        int count = 0;
        for(int i = 1;i<n;i++){
            if(intervals[i-1][0] == intervals[i][0]){
                intervals[i][1] = Math.max(intervals[i][1],intervals[i-1][1]);
                intervals[i-1][0] = -1;
                continue;
            }
            if(intervals[i-1][0]<intervals[i][0]&&intervals[i-1][1]>=intervals[i][0]&&intervals[i-1][1]<=intervals[i][1]){
                intervals[i][0] = intervals[i-1][0];
                intervals[i-1][0] = -1;
                continue;
            }
            if(intervals[i-1][0]<intervals[i][0]&&intervals[i-1][1]>=intervals[i][1]){
                intervals[i][0] = intervals[i-1][0];
                intervals[i][1] = intervals[i-1][1];
                intervals[i-1][0] = -1;
            }
        }
        for(int i = 0;i<n;i++){
            if(intervals[i][0] != -1){
                count++;
            }
        }
        int[][] res = new int[count][2];
        int index = 0;
        for(int i = 0;i<n;i++){
            if(intervals[i][0] != -1){
                res[index][0] = intervals[i][0];
                res[index][1] = intervals[i][1];
                index++;
            }
        }
        return res;
    }
    //leetcode189中等-轮转数组
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if(k == 0){
            return;
        }
        int[] left = new int[n-k];
        int[] right = new int[k];
        for(int i = n-k;i<n;i++){
            right[i-n+k] = nums[i];
        }
        for(int i = 0;i<n-k;i++){
            left[i] = nums[i];
        }
        for(int i = 0;i<n;i++){
            if(i<k){
                nums[i] = right[i];
            }else{
                nums[i] = left[i-k];
            }
        }
    }
    //leetcode238中等-除自身以外数组的乘积
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] cns1 = new int[n];
        int[] cns2 = new int[n];
        cns1[0] = nums[0];
        cns2[n-1] = nums[n-1];
        for(int i = 1;i<n-1;i++){
            cns1[i] = cns1[i-1]*nums[i];
            cns2[n-1-i] = cns2[n-i]*nums[n-1-i];
        }
        nums[0] = cns2[1];
        nums[n-1] = cns1[n-2];
        for(int i = 1;i<n-1;i++){
            nums[i] = cns1[i-1]*cns2[i+1];
        }
        return nums;
    }
    //leetcode41困难-缺失的第一个正数
    public static int firstMissingPositive(int[] nums) {
        //题解快方法
        int n = nums.length;
        //将负数变成n+1
        for(int i = 0;i<n;i++){
            if(nums[i] <=0){
                nums[i] = n+1;
            }
        }
        //将<n的下标+1位置变成绝对值相同的负数
        for(int i = 0;i<n;i++){
            if(Math.abs(nums[i])<n+1){
                nums[Math.abs(nums[i])-1] = -Math.abs(nums[Math.abs(nums[i])-1]);
            }
        }
        //返回第一个不是负数的下标+1就是缺少的正整数
        for(int i = 0;i<n;i++){
            if(nums[i]>0){
                return i+1;
            }
        }
        return n+1;
        //不满足时间复杂度为o(n)，空间复杂度也大
//        int n = nums.length;
//        Arrays.sort(nums);
//        int start = 1;
//        for(int i = 0;i<n;i++){
//            if(nums[i] <=0){
//                continue;
//            }
//            if(i>0&&nums[i] == nums[i-1]){
//                continue;
//            }
//            if(nums[i] == start){
//                start++;
//            }else{
//                return start;
//            }
//        }
//        return start;
    }
    //leetcode35简单-搜索插入位置
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        if(nums[n-1]<target){
            return n;
        }
        if(nums[0]>target){
            return 0;
        }
        int left = 0;
        int right = n-1;
        while(left<right){
            int mid = (right+left)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(mid == left||mid == right){
                break;
            }
            if(nums[mid]<target){
                left = mid;
            }
            if(nums[mid]>target){
                right = mid;
            }
        }
        return right;
    }
}
