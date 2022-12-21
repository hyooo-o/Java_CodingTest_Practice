import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17406_김정효 {
	static int m, n, map[][], k, list[][], change[][], order[];
	static boolean visit[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		change = new int[n][m];
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				change[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new int[k][3];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
			list[i][2] = Integer.parseInt(st.nextToken());
		}
		order = new int[k];
		visit = new boolean[k];
		// 순열으로 list 순서 정하기
		perm(0);
		System.out.println(result());
	}

	private static void perm(int count) {
		// k개의 순서를 다 정하면
		if (count == k) {
			// 배열 돌리기
			cycle();
			// 돌린 결과를 map에 복사
			copy();
		}
		
		for (int i = 0; i < k; i++) {
			if (visit[i]) continue;
			order[count] = i;
			visit[i] = true;
			perm(count+1);
			visit[i] = false;
		}
	}

	private static void cycle() {
		int start_r = list[][];
		int start_c = list[][];
		int end_r = list[][]; 
		int end_c = list[][];
		int count = Math.min(end_r-start_r, end_c-start_c) / 2;
		// 하우상좌
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, 1, 0, -1};
		
		// 돌릴 배열 개수
		for (int i = 0; i < count; i++) {
			int x = start_r + i;
			int y = start_c + i;
			int temp = map[x][y];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				while (nx>=0 && nx<n && ny>=0 && ny<m) {
					change[x][y] = map[nx][ny];
					x = nx;
					y = ny;
					nx = x + dx[d];
					ny = y + dy[d];
				}
			}
			change[start_r][start_c+1] = temp;
		}
	}
	
	private static int result() {
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
	
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void copy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = change[i][j];
			}
		}
	}
}
