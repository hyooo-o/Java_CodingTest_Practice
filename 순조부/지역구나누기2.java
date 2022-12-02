import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 지역구나누기2 {
	static int n, map[][], man[], result;
	static boolean select[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			man = new int[n];
			select = new boolean[n];
			result = Integer.MAX_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				man[i] = Integer.parseInt(st.nextToken());
			}
			subset(0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subset(int index) {
		if (index == n) {
			// 각 그룹 연결되어 있는지 확인
			if (check(true) && check(false)) {
				result = Math.min(result, gap());
			}
			return;
		}
		// 그룹 나누기
		select[index] = true;
		subset(index+1);
		select[index] = false;
		subset(index+1);
	}
	
	private static boolean check(boolean flag) {
		boolean[] visit = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			if (select[i]==flag && !visit[i]) {
				q.offer(i);
				visit[i]=true;
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < n; i++) {
				if (select[i]==flag && map[cur][i]==1 && !visit[i]) {
					q.offer(i);
					visit[i]=true;
				}
			}
		}
		
		// 그룹에 속하지만 연결되지 않은 마을이라면
		for (int i = 0; i < n; i++) {
			if (select[i]==flag && !visit[i]) {
				return false;
			}
		}
		return true;
	}

	private static int gap() {
		int a = 0;
		int b = 0;
		
		for (int i = 0; i < n; i++) {
			if (select[i]) a += man[i];
			else b += man[i];
		}
		return Math.abs(a-b);
	}
}
