package D20250121;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
    public static int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] dp = new int[k+1];
        for(List<Integer> list:piles){
            for(int i = 1;i<list.size();i++){
                int mid = list.get(i-1)+list.get(i);
                list.set(i,mid);
            }
        }
        return 0;
    }
}
