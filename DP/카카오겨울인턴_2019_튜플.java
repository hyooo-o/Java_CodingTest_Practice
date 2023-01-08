import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class 카카오겨울인턴_2019_튜플 {
	public static void main(String[] args) {
		solution("{{2}, {3, 5}}");	// test
	}
	
	public static int[] solution(String s) {
        int[] answer = {};
        List<List<Integer>> list = new ArrayList<>();
        char[] c = new char[s.length()-2];
        for (int i = 1; i < s.length()-1; i++) {
			c[i-1] = s.charAt(i);
		}
        
        int cnt = 0;
        while(cnt < c.length) {
        	if (c[cnt] == '{') {
        		cnt++;
        		List<Integer> arr = new ArrayList<>();
        		while(c[cnt] != '}') {
        			if (c[cnt]-'0' > 0) arr.add(c[cnt]-'0');
        			cnt++;
        		}
        		list.add(arr);
        	}
        	cnt++;
        }
        return answer;
    }
}
