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
				cycle(order[i]);
				// 돌린 결과를 map에 복사
				copy(map, change);
			}
			// 이 순서에 대한 최솟값을 구해 전체 최솟값 구하기
			last = Math.min(last, min());
			// map을 원래 배열로 돌려 놓기 (origin 배열을 map으로 복사)
			copy(map, origin);
			copy(change, map);
			return;
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
		int start_x = list[index][0] - list[index][2];	// r-s
		int start_y = list[index][1] - list[index][2];	// c-s
		int end_x = list[index][0] + list[index][2]; 		// r+s
		int end_y = list[index][1] + list[index][2];		// c+s
		int count = Math.min(end_x-start_x, end_y-start_y) / 2;
		// 하우상좌
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, 1, 0, -1};
		
		// 돌릴 배열 개수
		for (int i = 0; i < count; i++) {
			// 돌릴 배열의 시작 좌표
			int x = start_x + i -1;
			int y = start_y + i -1;
			// 시작 좌표의 값 저장
			int temp = map[x][y];
			// 변하는 x, y 값
			int cur_x = start_x + i -1;
			int cur_y = start_y + i -1;
			for (int d = 0; d < 4; d++) {
				int nx = cur_x + dx[d];
				int ny = cur_y + dy[d];
				while (nx>=x && nx<end_x - i && ny>=y && ny<end_y - i) {
					change[cur_x][cur_y] = map[nx][ny];
					cur_x = nx;
					cur_y = ny;
					nx = cur_x + dx[d];
					ny = cur_y + dy[d];
				}
			}
			// 모든 이동이 다 끝났으면 마지막 값 이동시키기
			// 처음 시작 좌표 값을 오른쪽으로 한 칸 이동
			change[x][y+1] = temp;
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
	
	private static void copy(int[][] ori, int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ori[i][j] = arr[i][j];
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
