import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * (출력)
 * 모두 녹아 없어지지는 데 걸린 시간
 * 다 녹기 한 시간 전 남아있는 치즈 칸 
 * 되고 있는 것 같긴 한데..
 * @author jeonghyo
 *
 */
public class Main_2636_김정효 {
	static int[][] map;;
	static Queue<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs();;;;
	}
	// 공기와 접촉한 치즈 dfs 계속 돌리기
	private static void dfs() {
		// 큐에서 하나씩 빼서 
		q.offer();;;;,
		
		
	}

}
