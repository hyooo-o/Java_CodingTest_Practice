import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_김정효 {
	static int m, n, map[][], day;
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}
		// 토마토 익히기
		bfs();
//		print();
		// 전체 토마토 확인
		System.out.println(check()? day-1: -1);
	}
//	private static void print() {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	// 토마토 익히기
	private static void bfs() {
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx>=0 && ny>=0 && nx<n && ny<m && map[nx][ny]==0) {
						map[nx][ny] = 1;	// 토마토 익히기
						q.offer(new int[] {nx, ny});	// 다음 토마토 탐색
					}
				}
			}
			day++;
		}
	}

	// 토마토 다 익혔으면 전체 맵을 돌면서 0인 부분이 있는지 확인
	private static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
