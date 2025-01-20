package D20250119;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "22233";
        System.out.println(countTexts(str));
    }
    public static int countTexts(String pressedKeys) {
        int n = pressedKeys.length();
        int count = 0;
        List<Integer> list = new ArrayList<>();
        int start = 0;
        int res = 0;
        while(start != n){
            count = 1;
            if(start == 0){
                start++;
                continue;
            }
            while(start<n&&pressedKeys.charAt(start) == pressedKeys.charAt(start-1)){
                count++;
                start++;
            }
            list.add(count);
            start++;
        }
        for(int a:list){
            int x = 1;
            int sum = 1;
            for(int i = 0;i<a;i++){
                sum*=x;
                x++;
            }
            res+=sum;
        }
        return res;
    }
}
