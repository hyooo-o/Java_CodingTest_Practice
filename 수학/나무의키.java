import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 완료
public class 나무의키 {
	static int n, h[], max, day;
	static List<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			h = new int[n];
			list = new ArrayList<Integer>();
			day = 0;
			max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, h[i]);
			}
			
			for (int i = 0; i < n; i++) {
				if (h[i] != 0) {
					list.add(max-h[i]);
				}
			}
			
			if (list.size() != 0) go();
			
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void go() {
		int odd = 0;	// 홀
		int even = 0;	// 짝
		
		for (int i = 0; i < n; i++) {
			odd += list.get(i) % 2;
			even += list.get(i) / 2;
		}
		
		while(true) {
			if (Math.abs(odd-even)==1 || odd==even || odd > even) {
				if (even >= odd) day = even*2;
				else day = odd*2-1;
				break;
			}
			
			if (even > odd) {
				even -= 1;
				odd += 2;
			}
		}
		
	}

}
