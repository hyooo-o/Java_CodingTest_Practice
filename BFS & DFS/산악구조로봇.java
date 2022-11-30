import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 산악구조로봇 {
	static int n, min, map[][], dp[][];
	static boolean visit[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			dp = new int[n][n];
			visit = new boolean[n][n];
			
			for (int i = 0; i <n; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			dp[0][0] = 0;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			go(0, 0);
			sb.append("#").append(tc).append(" ").append(dp[n-1][n-1]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void go(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx>=0 && ny>=0 && nx<n && ny<n && !visit[nx][ny]) {
				int sum = 0;
				// 전==후
				if (map[x][y] == map[nx][ny]) {
					sum = 1;
				}
				// 전 < 후
				else if (map[x][y] < map[nx][ny]) {
					sum = (map[nx][ny]-map[x][y])*2;
				}
				// 저장되어 있는 값보다 지금이 더 최소라면
				if (dp[nx][ny] > dp[x][y] + sum) {
					// 최솟값으로 업데이트
					dp[nx][ny] = dp[x][y] + sum;
					// 여긴 다시 방문 안해도 되니깐 방문처리
					visit[nx][ny] = true;
					// 다음으로
					go(nx, ny);
					// 다 돌고 돌아오면 방문 취소
					visit[nx][ny] = false;
				}
			}
		}
	}
	
}
