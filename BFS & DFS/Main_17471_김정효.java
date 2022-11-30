import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471_김정효 {
	static int n, map[][], man[], min;
	static boolean select[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		man = new int[n];
		map = new int[n][n];
		select = new boolean[n];
		min = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			man[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][a-1] = 1;
			}
		}
		
		subset(0);
		System.out.println(min==Integer.MAX_VALUE? -1: min);
	}
	
	private static void print() {
	for (int i = 0; i < n; i++) {
		if (select[i]) {
			System.out.print(i+" ");
		}
	}
	System.out.println();
	System.out.println("------------------");
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
