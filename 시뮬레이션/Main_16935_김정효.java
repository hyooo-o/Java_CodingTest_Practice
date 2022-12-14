import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16935_김정효 {
	static int m, n, map[][], r, result[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			cycle(Integer.parseInt(st.nextToken()));	// 배열 돌리기
			map = new int[n][m];	// n, m 바꼈을 경우에 대비해서 배열 크기를 초기화
			copy();	// result를 map에 복사
		}
		print();	// 출력
	}

	private static void cycle(int c) {
		switch(c) {
			case 1:
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				five();
				break;
			case 6:
				six();
				break;
			default:
				break;
		}
	}

	private static void one() {
		result = new int[n][m];
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < m; j++) {
				result[n-i-1][j] = map[i][j];
				result[i][j] = map[n-i-1][j];
			}
		}
	}

	private static void two() {
		result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m/2; j++) {
				result[i][m-j-1] = map[i][j];
				result[i][j] = map[i][m-j-1];
			}
		}
	}

	private static void three() {
		// n, m 바꾸기
		int temp = n;
		n = m;
		m = temp;
		result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][m-1-j] = map[j][i];
			}
		}
	}

	private static void four() {
		int temp = n;
		n = m;
		m = temp;
		result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[n-1-i][j] = map[j][i];
			}
		}
	}
	
	private static void five() {
		result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 1
				if (i<n/2 && j<m/2)
					result[i][j+m/2] = map[i][j];
				// 4
				else if (i>=n/2 && j<m/2)
					result[i-n/2][j] = map[i][j];
				// 3
				else if (i>=n/2 && j>=m/2)
					result[i][j-m/2] = map[i][j];
				// 2
				else
					result[i+n/2][j] = map[i][j];
			}
		}
	}

	private static void six() {
		result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 1
				if (i<n/2 && j<m/2)
					result[i+n/2][j] = map[i][j];
				// 4
				else if (i>=n/2 && j<m/2)
					result[i][j+m/2] = map[i][j];
				// 3
				else if (i>=n/2 && j>=m/2)
					result[i-n/2][j] = map[i][j];
				// 2
				else
					result[i][j-m/2] = map[i][j];
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
	
	private static void copy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = result[i][j];
			}
		}
	}
}
