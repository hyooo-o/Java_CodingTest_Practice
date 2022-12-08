import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16926_김정효 {
	static int m, n, map[][], r, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		cnt = Math.min(n, m) / 2;	// 그룹 수
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cycle();	// 회전
		print();	// 출력
	}

	private static void cycle() {
		// 우하좌상
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		// 전체 회전 수
		for (int k = 0; k < r; k++) {
			// 그룹 개수만큼 반시계방향으로 돌리기
			for (int i = 0; i < cnt; i++) {
				int x = i;
				int y = i;
				int temp = map[i][i];	// 시작점
				// 4방향 돌리기
				int d = 0;
				while (d < 4) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					// 주의! : 그룹에 따라 범위가 유동적임!
					if (nx>=i && ny>=i && nx<n-i && ny<m-i) {
						map[x][y] = map[nx][ny];	// 반시계방향
						x = nx;
						y = ny;
					}
					else d++;
				}
				// 한 그룹 다 돌면 시작점도 옮겨주기
				map[i+1][i] = temp;
			}
		}
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
