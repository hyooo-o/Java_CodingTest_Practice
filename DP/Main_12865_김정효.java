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
		dp = new int[n];
		max = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());	// 무게
			bag[i][1] = Integer.parseInt(st.nextToken());	// 가치
		}
		
		select(k, 1);
		System.out.println(dp[n-1]);
	}
	/**
	 * 부분집합으로 배낭에 넣을 물건 선택
	 * @param w 무게
	 * @param v 가치
	 * @param index 인덱스
	 */
	private static void select(int w, int index) {
		// 모든 물건에 대해 선택/미선택 완료하면 최대 가치 구하기
		if (index == n) {
			return;
		}
		
		// 무게 초과면 리턴
		if (w-bag[index][0] >= 0) {
			if (dp[index] < dp[index-1]+bag[index][1]) {
				// 선택
				dp[index] =  dp[index-1] + bag[index][1];
				select(w-bag[index][0], index+1);
			}
			// 미선택
			select(w, index+1);
		}
		else {
			if (dp[index] < dp[index-1]) {
				dp[index] = dp[index-1];
			}
			select(w, index+1);
		}
	}

}
