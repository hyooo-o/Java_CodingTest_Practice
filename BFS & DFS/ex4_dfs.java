import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ex4_dfs {
	static int n, map[][], dp[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static boolean visit[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			dp = new int[n][n];
			visit = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			dp[0][0] = 0;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			check(0, 0);
			sb.append("#").append(tc).append(" ").append(dp[n-1][n-1]).append("\n");
		}
		System.out.println(sb.toString());
	}

//	private static void check() {
//		Queue<int[]> q = new LinkedList<>();
//		boolean[][] visit = new boolean[n][n];
//		q.offer(new int[] {0, 0});
//		
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int x = cur[0];
//			int y = cur[1];
//			for (int d = 0; d < 4; d++) {
//				int sum = 0;
//				int nx = x + dx[d];
//				int ny = y + dy[d];
//				if (nx>=0 && ny>=0 && nx<n && ny<n && !visit[nx][ny]) {
//					// 전 > 현
//					if (map[x][y] > map[nx][ny]) {
//						sum = 0;
//					}
//					// 전 == 현
//					else if (map[x][y] == map[nx][ny]) {
//						sum = 1;
//					}
//					// 전 < 현
//					else if (map[x][y] < map[nx][ny]) {
//						sum = (map[nx][ny] - map[x][y])*2;
//					}
//					
//					if (dp[nx][ny] > dp[x][y]+sum) {
//						dp[nx][ny] = dp[x][y]+sum;
//						visit[nx][ny] = true;
//						q.offer(new int[] {nx, ny});
//					}
//				}
//			}
//		}
//	}
	
	private static void check(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int sum = 0;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx>=0 && ny>=0 && nx<n && ny<n && !visit[nx][ny]) {
				// 전 > 현
				if (map[x][y] > map[nx][ny]) {
					sum = 0;
				}
				// 전 == 현
				else if (map[x][y] == map[nx][ny]) {
					sum = 1;
				}
				// 전 < 현
				else if (map[x][y] < map[nx][ny]) {
					sum = (map[nx][ny] - map[x][y])*2;
				}
				
				if (dp[nx][ny] > dp[x][y]+sum) {
					dp[nx][ny] = dp[x][y]+sum;
					visit[nx][ny] = true;
					check(nx, ny);
					visit[nx][ny] = false;
				}
			}
		}
	}
}
