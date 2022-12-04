import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865_김정효 {
	static int n, k, max, bag[][], dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		bag = new int[n][2];
		dp = new int[k+1];
		max = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());	// 무게
			bag[i][1] = Integer.parseInt(st.nextToken());	// 가치
		}
		
		for (int i = 0; i < n; i++) {	// 물건
			for (int j = k; j-bag[i][0] >= 0; j--) {	// 무게k부터 k-물건무게까지
				// 현재 무게의 가치 = max(현재무게의 가치, (전체무게-물건i의 무게)=남은 무게의 가치 + 물건i의 가치)
				dp[j] = Math.max(dp[j], dp[j-bag[i][0]] + bag[i][1]);
			}
		}
		System.out.println(dp[k]);
	}
	
}
