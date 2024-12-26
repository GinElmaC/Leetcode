## Leetcode560.和为k的子数组

![image-20241226232449771](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241226232449771.png)

这一题由于是求解子串相关问题，而子串是连续的一段非空数组序列，所以我们可以以**前缀和**为思路

那什么是前缀和呢？

![image-20241226232630723](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241226232630723.png)

**所以第一种方法：**

我们先求出原数组的前缀和数组pre，然后遍历前缀和数组，在pre[i]=k或者pre[i]-pre[j]=k的时候更新我们的答案。

```java
public int subarraySum(int[] nums, int k) {
        //前缀和方法，很慢但是通过了
	    int n = nums.length;
        int[] pre = new int[n];
    	//初始化前缀和数组
        pre[0] = nums[0];
        for(int i = 1;i<n;i++){
            pre[i] = pre[i-1]+nums[i];
        }

        int res = 0;
        for(int i = 0;i<n;i++){
            //pre[i]=k情况
            if(pre[i] == k){
                res++;
            }
            for(int j = i+1;j<n;j++){
                //pre[i]-pre[j]=k情况
                if(pre[j]-pre[i] == k){
                    res++;
                }
            }
        }
        return res;
    }
```

这种方法的时间复杂度是n(n^2)，我们需要优化他。

**方法二：前缀和+哈希**

![image-20241226233249268](C:\Users\WangYishuo\AppData\Roaming\Typora\typora-user-images\image-20241226233249268.png)

根据公式我们得到：对于以下标i结尾的位置，我们只需要得到前缀和为pre[i]-k的pre[j]的个数即可，所以我们用Map来记录每个前缀和出现的概率

```java
public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer>map = new HashMap<>();
        //为了防止我们的输入数组中以0开始
        //有一个用例是[0,0,0,0,0,0]，如果没有这一项，我们的结果会少
        map.put(0,1);
        //目前的和，其实就是pre[i]
        int s = 0;
        //维护结果
        int res = 0;
        for(int i = 0;i<n;i++){
            //更新和,s就是pre[i]
            s+=nums[i];
            //更新答案，获取前缀和为pre[i]-k的个数
            res+=map.getOrDefault(s-k,0);
            //更新map
            map.put(s,map.getOrDefault(s,0)+1);
        }
        return res;
}
```

