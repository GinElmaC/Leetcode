package D20241229;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] str=  {"ABC","ACB","ABC","ACB","ACB"};
        System.out.println(rankTeams(str));
    }
    public static long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        int res = 0;
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0;i<n;i++){
            for(int j = i+2;j<n;j++){
                list.add(new int[]{i,j,nums[i]*nums[j]});
            }
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        for(int i = 1;i<list.size();i++){
            if(list.get(i-1)[2] == list.get(i)[2]){
                if(check(list.get(i-1),list.get(i))){
                    res++;
                }
            }
        }
        return res;
    }
    public static boolean check(int[] a,int[] b){
        if(b[0]-a[0]>1&&a[1]-b[0]>1&&b[1]-a[1]>1){
            return true;
        }
        return false;
    }
    public static String answerString(String word, int numFriends) {
        if(numFriends == 1){
            return word;
        }
        int n = word.length();
        char[] ch = word.toCharArray();
        int maxindex = 0;
        for(int i = 0;i<n;i++){
            if((int)ch[i]>(int)ch[maxindex]){
                maxindex = i;
            }else if((int)ch[i] == (int)ch[maxindex]){
                int a = i;
                int b = maxindex;
                while(a<n&&b<n&&ch[a] == ch[b]){
                    a++;
                    b++;
                }
                if(a == n){
                    continue;
                }
                if(b == n){
                    maxindex = i;
                    continue;
                }
                if(ch[a]>ch[b]){
                    maxindex = i;
                    continue;
                }
            }
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(maxindex<n&&n-count!=numFriends-1){
            sb.append(ch[maxindex]);
            maxindex++;
            count++;
        }
        return sb.toString();
    }
    public static int countGoodArrays(int n, int m, int k) {
        long res = 0;
        //相邻
        res+=(n-k)*m;
        //不相邻
        long mid = m*(m-1);
        for(int i = 0;i<k;i++){
            res+=mid;
            mid*=k;
        }
        long count = (long) (res%(Math.pow(10,9)+7));
        return (int)count;
    }
    //今日每日一题
    //总体思路：一共26个字母，m个排名，创建二维数组，更新数据后自定义排序
    public static String rankTeams(String[] votes) {
        int n = votes.length;
        int m = votes[0].length();
        int[][] cns = new int[26][m+1];
        for(int i = 0;i<26;i++){
            cns[i][m] = i;
        }
        for(String str:votes){
            for(int i = 0;i<m;i++){
                cns[str.charAt(i)-'A'][i]++;
            }
        }
        Arrays.sort(cns, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for(int i = 0;i<m;i++){
                    if(o1[i] == o2[i]){
                        continue;
                    }else{
                        return o2[i]-o1[i];
                    }
                }
                return o2[0]-o1[0];
            }
        });
        char[] res = new char[m];
        for(int i = 0;i<m;i++){
            res[i] = (char)(cns[i][m]+'A');
        }
        return new String(res);
    }
}
