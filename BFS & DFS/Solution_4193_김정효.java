import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4193_김정효 {
	static int map[][], _min, n, a, b, c, d;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Node> q = new ArrayDeque<>();
	
	static class Node{
		int x, y, cnt;
		Node(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visit = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			_min = Integer.MAX_VALUE;
			q.clear();
			
			if (a==c && b==d) {
				_min = 0;
			}
			if (_min != 0) {
				bfs();
			}
			
			sb.append("#").append(tc).append(" ").append(_min==Integer.MAX_VALUE? -1: _min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs() {
		visit[a][b] = true;
		q.offer(new Node(a, b, 0));
		
		while(!q.isEmpty()) {
			// 하나씩 꺼내서 도착지 확인 후, 도착지가 아니면 그 다음으로 갈 수 있는지 확인
			Node no = q.poll();
			
			// 도착지 => 성공
			if(no.x==c && no.y==d) {
				_min = no.cnt;
				return;
			}
			// 도착지가 아님
			for(int d = 0; d < 4; d++) {
				int nx = no.x+dx[d];
				int ny = no.y+dy[d];
				// 범위
				if (nx<0 || ny<0 || nx>=n || ny>=n) continue;
				// 장애물, 방문했음
				if (map[nx][ny]==1 || visit[nx][ny]) continue;
				// 소용돌이 2초
				if (map[nx][ny] == 2 && no.cnt%3 != 2) {
					q.offer(new Node(no.x, no.y, no.cnt+1));
				} else {	// 정상 이동 가능
					visit[nx][ny] = true;
					q.offer(new Node(nx, ny, no.cnt+1));
				}
			}
		}
	}

}
