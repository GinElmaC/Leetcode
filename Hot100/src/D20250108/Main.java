package D20250108;

public class Main {
    public static void main(String[] args) {

    }
    public static String largestGoodInteger(String num) {
        int n = num.length();
        int max = 0;
        String res = "";
        for(int i = 0;i<n-2;i++){
            max = Math.max(max,check(num,i));
            if(max == check(num,i)){
                res = num.substring(i,i+3);
            }
        }
        return res;
    }
    public static int check(String str,int left){
        String mid = str.substring(left,left+3);
        if(mid.charAt(0) != mid.charAt(1) || mid.charAt(1) != mid.charAt(2)){
            return -1;
        }
        return Integer.valueOf(mid);
    }
}
