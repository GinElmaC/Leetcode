import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 对于2024.12.22的每日一题，直接使用暴力枚举即可解决，题目给的数据太小了
 * 时间复杂度：o(Nlogn)
 * 空间复杂度：o(N)
 */
public class EveryDay {
    public static void main(String[] args) {
        int lo = 12;
        int hi = 15;
        int k = 2;
        System.out.println(getKth(lo,hi,k));
    }
    public static int getKth(int lo, int hi, int k) {
        int n = hi-lo+1;
        int[][] cns= new int[n][2];
        for(int i = 0;i<n;i++){
            cns[i][0] = change(i+lo);
            cns[i][1] = i+lo;
        }
        Arrays.sort(cns, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        return cns[k-1][1];
    }
    public static int change(int a){
        int count = 0;
        while(a != 1){
            if(a%2 == 0){
                a/=2;
                count++;
            }else{
                a = 3*a+1;
                count++;
            }
        }
        return count;
    }
    //717
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
}
