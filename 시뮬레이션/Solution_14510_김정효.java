import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_14510_김정효 {
	static int N, arr[], day;
	static List<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			day = 0;
			list = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				day = Math.max(day, arr[i]);
			}
			// 최대 높이와의 차이를 구해서 list에 넣어주기
			for (int i = 0; i < N; i++) {
				if (day != arr[i]) {
					list.add(day-arr[i]);
				}
			}
			
			// 구하기
			if (list.size() != 0) {
				go();
			}
			// 나무 높이가 다 최대이면 day=0
			else day = 0;
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void go() {
		int odd = 0;	// 홀
		int even = 0;	// 짝
		
		for (int i = 0; i < list.size(); i++) {
			// 홀수끼리, 짝수끼리의 합
			odd += list.get(i) % 2;
			even += list.get(i) / 2;
		}
		
		while(true) {
			// 짝=홀 개수 or 짝홀 차=1 or 홀>짝
			// 홀수 2개 -> 짝수 1개로 교환 X 반대는 가능
			if (odd==even || Math.abs(odd-even)==1 || odd>even) {
				if (even>=odd) {
					day = even*2;
				} else {
					day = odd*2-1;
				}
				break;
			}
			// 짝수>홀수 => 짝수-1, 홀수+2
			if (even > odd) {
				even -= 1;
				odd += 2;
			}
		}
	}
}