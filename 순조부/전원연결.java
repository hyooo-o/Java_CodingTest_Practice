import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 완료
public class 전원연결 {
	static int n, map[][], totalCnt, max, len;
	static List<int[]> list;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			list = new ArrayList<>();
			totalCnt = 0;
			max = 0;
			len = Integer.MAX_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i!=0 && i!=n-1 && j!=0 && j!=n-1 && map[i][j] == 1) {
						list.add(new int[] {i, j});
						totalCnt++;
					}
				}
			}
			
			subset(0, 0);
			sb.append("#").append(tc).append(" ").append(len).append("\n");
		}
		System.out.println(sb.toString());

	}

	// 코어 선택 (부분집합)
	private static void subset(int index, int cnt) {
		if (index == totalCnt) {
			if (max < cnt) {	// 코어 개수 최대
				max = cnt;
				len = count();
			}
			else if (max == cnt) {
				len = Math.min(len, count());
			}
			return;
		}
		
		int[] cur = list.get(index);
		int x = cur[0];
		int y = cur[1];
		for (int d = 0; d < 4; d++) {
			if (connect(x, y, d)) {
				set(x, y, d, 2);
				subset(index+1, cnt+1);
				set(x, y, d, 0);
			}
		}
		subset(index+1, cnt);
	}

	// 연결 확인
	private static boolean connect(int x, int y, int d) {
		int nx = x;
		int ny = y;
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if (nx<0 || ny<0 || nx>=n || ny>=n) break;
			if (map[nx][ny]!=0) return false;
		}
		return true;
	}

	// 전선 놓기
	private static void set(int x, int y, int d, int mark) {
		int nx = x;
		int ny = y;
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if (nx<0 || ny<0 || nx>=n || ny>=n) break;
			map[nx][ny] = mark;
		}
	}
	
	// 전체 맵의 전선 개수 세기
	private static int count() {
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 2) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
