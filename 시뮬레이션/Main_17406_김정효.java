import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17406_김정효 {
	static int m, n, map[][], k, list[][], change[][], order[], last, origin[][];
	static boolean visit[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		change = new int[n][m];
		map = new int[n][m];
		origin = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				origin[i][j] = change[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new int[k][3];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());	// r
			list[i][1] = Integer.parseInt(st.nextToken());	// c
			list[i][2] = Integer.parseInt(st.nextToken());	// s
		}
		order = new int[k];
		visit = new boolean[k];
		last = Integer.MAX_VALUE;
		// 순열으로 list 순서 정하기
		perm(0);
		System.out.println(last);
	}

	private static void perm(int count) {
		// k개의 순서를 다 정하면
		if (count == k) {
			for (int i = 0; i < k; i++) {
				// 배열 돌리기
				cycle(i);
				// 돌린 결과를 map에 복사
				copy(change);
			}
			// 이 순서에 대한 최솟값을 구해 전체 최솟값 구하기
			last = Math.min(last, min());
			// map을 원래 배열로 돌려 놓기 (origin 배열을 map으로 복사)
			copy(origin);
		}
		
		for (int i = 0; i < k; i++) {
			if (visit[i]) continue;
			order[count] = i;
			visit[i] = true;
			perm(count+1);
			visit[i] = false;
		}
	}

	private static void cycle(int index) {
		// r c s
		int start_r = list[index][0] - list[index][2];	// r-s
		int start_c = list[index][1] - list[index][2];	// c-s
		int end_r = list[index][0] + list[index][2]; 		// r+s
		int end_c = list[index][1] + list[index][2];		// c+s
		int count = Math.min(end_r-start_r, end_c-start_c) / 2;
		// 하우상좌
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, 1, 0, -1};
		
		// 돌릴 배열 개수
		for (int i = 0; i < count; i++) {
			int x = start_r + i -1;
			int y = start_c + i -1;
			int temp = map[x][y];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				while (nx>=start_r + i -1 && nx<end_r - i && ny>=start_c + i -1 && ny<end_c - i) {
					change[x][y] = map[nx][ny];
					x = nx;
					y = ny;
					nx = x + dx[d];
					ny = y + dy[d];
				}
			}
			change[start_r + i -1][start_c + i] = temp;
		}
	}
	
	private static int min() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				sum += map[i][j];
			}
			result = Math.min(sum, result);
		}
		return result;
	}
	
	private static void copy(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = arr[i][j];
			}
		}
	}
	
//	private static void print() {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
}
