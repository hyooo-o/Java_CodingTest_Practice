import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 백준 게리맨더링과 유사
public class ex5 {
	static int n, map[][], _min, man[], sum;
	static boolean select[], visit[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			man = new int[n];
			select = new boolean[n];
			_min = Integer.MAX_VALUE;
			
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
			sb.append("#").append(tc).append(" ").append(_min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subset(int index) {
		if (index == n) {
//			print();
			// 연결 확인
			if (check(true) && check(false)) {
				_min = Math.min(_min, gap());
			}
			return;
		}
		// 그룹1
		select[index] = true;
		subset(index+1);
		// 그룹2
		select[index] = false;
		subset(index+1);
		
	}
//
//	private static void print() {
//		for (int i = 0; i < n; i++) {
//			if (select[i]) {
//				System.out.print(i+" ");
//			}
//		}
//		System.out.println();
//		System.out.println("------------------");
//	}

	private static boolean check(boolean flag) {
		visit = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		
		// 그룹의 시작점 찾기
		for (int i = 0; i < n; i++) {
			if (select[i] == flag) {
				visit[i] = true;
				q.offer(i);
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			// 연결되어 있는 수가 select==flag 인지 확인
			for (int i = 0; i < n; i++) {
				if (select[i] != flag || visit[i]) {
					continue;
				}
				if (map[cur][i] == 1) {
					visit[i] = true;
					q.offer(i);						
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (select[i] == flag && !visit[i]) return false;
		}
		return true;
	}

	private static int gap() {
		int a = 0;
		int b = 0;
		
		for (int i = 0; i < n; i++) {
			if (select[i]) {
				a += man[i];
				continue;
			}
			b += man[i];
		}
		return Math.abs(a-b);
	}
}
