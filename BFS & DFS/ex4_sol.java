import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ex4_sol {
	static int n, map[][];
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#").append(tc).append(" ").append(findMinRoad()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// BFS + 방문관리(비용)
	// 방문처리 : 같은 곳에 더 적은 비용으로 오게 될 경우는 갈 수 있도록 함
	private static int findMinRoad() {
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		// 해당 위치에 먼저 지나간 경로의 최소 비용
		int[][] visit = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		// 큐 생성
		Queue<Point> q = new ArrayDeque<>();
		// 시작점(너비 0) 큐에 넣기
		q.offer(new Point(0, 0));
		// 시작점 방문처리 (비용 0)
		visit[0][0] = 0;
		// 큐가 비어 있을 때까지 반복 (모든 가능한 경우)
		while(!q.isEmpty()) {
			// 큐에서 처리할 정점 꺼내기
			Point p = q.poll();
			// 현 정점의 인접정점 처리 (4방향의 정점)
			for (int d = 0; d < 4; d++) {
				// 다음 정점이 경계를 벗어나면 진행하지 않음
				int nx = p.r + dx[d];
				int ny = p.c + dy[d];
				if (nx<0 || ny<0 || nx>=n || ny>=n) continue;
				// 현 정점과의 높이 차를 이용하여 가중치 비용 계산
				int ncost = visit[p.r][p.c];
				
				if (map[p.r][p.c] == map[nx][ny]) ncost += 1;
				else if (map[p.r][p.c] < map[nx][ny]) ncost += (map[nx][ny]-map[p.r][p.c])*2;
				// 다음 인접정점의 기존 비용 > 현정점을 거쳐 다음 인접정점으로 가는 비용
				if (visit[nx][ny] > ncost) {
					// 다음 인접정점을 큐에 넣기
					q.offer(new Point(nx, ny));
					// 방문처리 (비용)
					visit[nx][ny] = ncost;
				}
			}
		}
		return visit[n-1][n-1];
	}

	
}
