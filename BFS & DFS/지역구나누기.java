import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 지역구나누기 {
	static int n, map[][], man[], min;
	static List<int[]> list;
	static boolean select[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			list = new ArrayList<int[]>();
			select = new boolean[n];
			man = new int[n];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==1) {
						list.add(new int[] {i, j});
					}
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				man[i] = Integer.parseInt(st.nextToken());
			}
			
			subset(0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subset(int index) {
		if (index == n) {
			// 연결 확인
			if (check(true) && check(false)) {
				// 최소값 구하기
				min = Math.min(min, gap());
			}
			return;
		}
		
		select[index] = true;
		subset(index+1);
		select[index] = false;
		subset(index+1);
	}

	private static boolean check(boolean flag) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[n];
		
		// 각 그룹의 시작점 찾기
		for (int i = 0; i < n; i++) {
			if (select[i] == flag) {
				visit[i] = true;
				q.offer(i);
				break;
			}
		}

		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < n; i++) {
				// 그룹에 해당하지 않거나 방문했다면 패쓰
				if (select[i]!=flag || visit[i]) continue;
				// 그룹에 해당하지 않으면서 방문안했고, 인접해 있다면
				if (map[cur][i]==1) {
					visit[i] = true;
					q.offer(i);	// i와 연결된 마을 찾기
				}
			}
		}
		
		// 연결된 마을을 다 확인했는데 그룹에서 아직 방문하지 않은 마을이 있다면? => 인접한 마을이 아니므로 false
		for (int i = 0; i < n; i++) {
			if (select[i]==flag && !visit[i]) {
				return false;
			}
		}
		return true;
	}

	private static int gap() {
		int t = 0;
		int f = 0;
		
		for (int i = 0; i < n; i++) {
			if (select[i]) t += man[i];
			else f+= man[i];
		}
		
		return Math.abs(t-f);
	}
}
