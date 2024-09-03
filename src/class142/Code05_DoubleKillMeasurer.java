package class142;

// 倍杀测量者
// 测试链接 : https://www.luogu.com.cn/problem/P4926

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code05_DoubleKillMeasurer {

	public static int MAXN = 1001;

	public static int MAXM = 5001;

	public static int MAXK = 10;

	public static double sml = 1e-6;

	// 链式前向星
	public static int[] head = new int[MAXN];

	public static int[] next = new int[MAXM];

	public static int[] to = new int[MAXM];

	public static int[] op = new int[MAXM];

	public static double[] weight = new double[MAXM];

	public static double[] ktimes = new double[MAXM];

	public static int cnt;

	// spfa
	public static double[] dist = new double[MAXN];

	public static int[] update = new int[MAXN];

	public static int MAXQ = 1000001;

	public static int[] queue = new int[MAXQ];

	public static int h, t;

	public static boolean[] enter = new boolean[MAXN];

	public static int n, m1, m2;

	public static void prepare() {
		cnt = 1;
		Arrays.fill(head, 0, n + 1, 0);
	}

	public static void clear() {
		h = t = 0;
		Arrays.fill(dist, 0, n + 1, -1e9);
		Arrays.fill(update, 0, n + 1, 0);
		Arrays.fill(enter, 0, n + 1, false);
	}

	public static void addEdge(int u, int v, int o, double w, double k) {
		next[cnt] = head[u];
		to[cnt] = v;
		op[cnt] = o;
		weight[cnt] = w;
		ktimes[cnt] = k;
		head[u] = cnt++;
	}

	public static boolean spfa(int s, double limit) {
		clear();
		dist[s] = 0;
		update[s] = 1;
		queue[t++] = s;
		enter[s] = true;
		while (h < t) {
			int u = queue[h++];
			enter[u] = false;
			int v, o;
			double w, k;
			for (int ei = head[u]; ei > 0; ei = next[ei]) {
				v = to[ei];
				o = op[ei];
				k = ktimes[ei];
				if (o == 1) {
					w = Math.log(k - limit);
				} else if (o == 2) {
					w = -Math.log(k + limit);
				} else {
					w = weight[ei];
				}
				// 注意这里，变大才更新
				if (dist[v] < dist[u] + w) {
					dist[v] = dist[u] + w;
					if (!enter[v]) {
						if (++update[v] > n) {
							return true;
						}
						queue[t++] = v;
						enter[v] = true;
					}
				}
			}
		}
		return false;
	}

	public static double compute() {
		double ans = 0, l = 0, r = MAXK, mid;
		while (r - l >= sml) {
			mid = (l + r) / 2;
			if (spfa(0, mid)) {
				ans = mid;
				l = mid + sml;
			} else {
				r = mid - sml;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		in.nextToken();
		m1 = (int) in.nval;
		in.nextToken();
		m2 = (int) in.nval;
		prepare();
		for (int i = 1, op, u, v, k; i <= m1; i++) {
			in.nextToken();
			op = (int) in.nval;
			in.nextToken();
			u = (int) in.nval;
			in.nextToken();
			v = (int) in.nval;
			in.nextToken();
			k = (int) in.nval;
			addEdge(v, u, op, 0, k);
		}
		for (int i = 1, u, w; i <= m2; i++) {
			in.nextToken();
			u = (int) in.nval;
			in.nextToken();
			w = (int) in.nval;
			addEdge(0, u, 0, Math.log(w), 0);
			addEdge(u, 0, 0, -Math.log(w), 0);
		}
		for (int i = 1; i <= n; i++) {
			addEdge(0, i, 0, 0, 0);
		}
		double ans = compute();
		if (ans == 0) {
			out.println("-1");
		} else {
			out.println(ans);
		}
		out.flush();
		out.close();
		br.close();
	}

}