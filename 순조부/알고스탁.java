import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 완료
public class 알고스탁 {
	static int ms, ma, n, l, prices[][], max;
	static class Stock{
		int price, benefit;

		public Stock(int price, int benefit) {
			this.price = price;
			this.benefit = benefit;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ms = Integer.parseInt(st.nextToken());
			ma = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			prices = new int[n][l+1];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < l+1; j++) {
					prices[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#").append(tc).append(" ").append(getBenefit()-(ms+l*ma)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int getBenefit() {
		int money = ms;		// 현재 보유 현금
		for (int i = 0; i < l; i++) {
			// 해당 달에 이득 되는 종목별 주식의 가격, 차액 구하기
			ArrayList<Stock> stock = stockList(i);
			// 부분집합으로 구매하기
			max = 0; // 차익
			subset(0, stock, money, 0);
			money += max;
			money += ma;
		}
		return money;
	}

	//조합 구하기
	private static void subset(int index, ArrayList<Stock> list, int money, int benefit) {
		if (index == list.size()) {
			max = Math.max(max, benefit);
			return;
		}
		
		Stock s = list.get(index);
		int cnt = money/s.price;
		while(cnt >= 0) {
			subset(index+1, list, money-s.price*cnt, benefit+s.benefit*cnt);
			cnt--;
		}
	}
	
	// 해당 달에 이득되는 주식 리스트
	private static ArrayList<Stock> stockList(int month) {
		ArrayList<Stock> stock = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			if (prices[i][month] < prices[i][month+1]) {
				stock.add(new Stock(prices[i][month], prices[i][month+1]-prices[i][month]));
			}
		}
		return stock;
	}
	
}
