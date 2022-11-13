import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_김정효 {
	static int b, s[], _min;
	static boolean visit[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			_min = Integer.MAX_VALUE;
			s = new int[n];
			visit = new boolean[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				s[i] = Integer.parseInt(st.nextToken());
				if (s[i] == b) {	// 직원 1명의 키가 b와 같다면 최솟값을 업데이트
					_min = s[i];
				}
			}
			// b와 키가 같은 직원이 없다면 부분집합 돌리기
			if (_min == Integer.MAX_VALUE) {
				subset(0, 0);
			}
			sb.append("#").append(tc).append(" ").append(_min-b).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subset(int i, int sum) {
		// 합계가 b이상이면서 현재 최솟값(_min)보다 작으면 최솟값 업데이트
		if (sum >= b && sum < _min) {
			_min = Math.min(_min, sum);
			return;
		}
		// 위의 조건에 해당하지 않으면서 s의 모든 원소 탐색했다면 return
		if (i == s.length) {
			return;
		}
		
		visit[i] = true;
		subset(i+1, sum+s[i]);
		visit[i] = false;
		subset(i+1, sum);
	}

}
