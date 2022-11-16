import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2112_김정효 {
	static int map[][], copy[][], _min, d, w, k;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[d][w];
			copy = new int[d][w];
			_min = Integer.MAX_VALUE;
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					copy[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (k == 1) {
				_min = 0;
			} else {
				drug(0, 0);
			}
			
			sb.append("#").append(tc).append(" ").append(_min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void drug(int index, int cnt) {	// cnt: 약품 사용 횟수
		// 성능 검사를 통과한다면 최솟값 구하기
		if (check()) {
			_min = Math.min(_min, cnt);
			return;
		}
		
		if (index == d) return;		// 마지막까지 다 탐색했으면 리턴
		if (_min <= cnt) return;	// 현재의 최솟값보다 약품 더 사용하는 건 의미 없으므로 리턴
		
		// 사용X
		drug(index+1, cnt);
		// A약품 사용
		Arrays.fill(map[index], 0);
		drug(index+1, cnt+1);
		// B약품 사용
		Arrays.fill(map[index], 1);
		drug(index+1, cnt+1);
		// 배열 원상복귀
		map[index] = Arrays.copyOf(copy[index], w);
	}

	// 성능검사
	private static boolean check() {
		for (int i = 0; i < w; i++) {
			int cnt = 1;
			boolean pass = false;
			for (int j = 1; j < d; j++) {
				// 지금 약품 == 이전 약품 => 통과 개수 +1
				if (map[j][i] == map[j-1][i]) cnt++;
				// 지금 약품 != 이전 약품 => 통과 개수 다시 1
				else cnt = 1;
				// 통과개수에 도달하면 종료하고 다음 셀 확인
				if (cnt == k) {
					pass = true;
					break;
				}
			}
			if (!pass) return false;
		}
		// 모든 셀이 성능검사에서 통과했다면 true
		return true;
	}

}
