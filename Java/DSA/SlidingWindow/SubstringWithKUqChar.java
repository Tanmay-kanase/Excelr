import java.util.HashMap;
import java.util.Map;

public class SubstringWithKUqChar {

    public static int substruniqchark(String s, int k) {
        int start = 0;
        int maxLen = 0;

        for(int end = 0; end < s.length(); end++){
            char ch = s.charAt(end);
            map.put(ch , map.getOrDefault(ch , 0 ) + 1);

            while(map.size() > k){
                
            }
        }
    }

    public static void main(String[] args) {
        String str = "aabacbebebe";
        System.out.println("Coutn : " + substruniqchark(str, 3));
    }
}
