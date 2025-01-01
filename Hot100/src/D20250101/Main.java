package D20250101;

public class Main {
    public static void main(String[] args) {

    }
    //每日一题
    public static String convertDateToBinary(String date) {
        String[] strs = date.split("-");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(String str:strs){
            int a = Integer.valueOf(str);
            String b = Integer.toBinaryString(a);
            sb.append(b);
            if(count != 2){
                sb.append("-");
            }
            count++;
        }
        return sb.toString();
    }
}
