package class119;

// 测试链接 : https://www.luogu.com.cn/problem/P1967

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code04 {

	public static int MAXN = 10001;

	public static int MAXM = 50001;

	public static int LIMIT = 21;

	public static int[][] edges = new int[MAXM][3];

	public static int[] father = new int[MAXN];

	public static int power;

	public static int cnt;

	public static int[] head = new int[MAXN];

	public static int[] next = new int[MAXM << 1];

	public static int[] to = new int[MAXM << 1];

	public static int[] weight = new int[MAXM << 1];

	public static int[] deep = new int[MAXN];

	public static int[][] stjump = new int[MAXN][LIMIT];

	public static int[][] stweight = new int[MAXN][LIMIT];

	public static boolean[] visited = new boolean[MAXN];

	public static int log2(int n) {
		int ans = 0;
		while ((1 << ans) <= (n >> 1)) {
			ans++;
		}
		return ans;
	}

	public static void build(int n) {
		power = log2(n);
		cnt = 1;
		for (int i = 1; i <= n; i++) {
			father[i] = i;
		}
		Arrays.fill(head, 1, n + 1, 0);
		Arrays.fill(visited, 1, n + 1, false);
	}

	public static void kruskal(int n, int m) {
		Arrays.sort(edges, 1, m + 1, (a, b) -> b[2] - a[2]);
		for (int i = 1, a, b, fa, fb; i <= m; i++) {
			a = edges[i][0];
			b = edges[i][1];
			fa = find(a);
			fb = find(b);
			if (fa != fb) {
				father[fa] = fb;
				addEdge(a, b, edges[i][2]);
				addEdge(b, a, edges[i][2]);
			}
		}
	}

	public static int find(int i) {
		if (i != father[i]) {
			father[i] = find(father[i]);
		}
		return father[i];
	}

	public static void addEdge(int u, int v, int w) {
		next[cnt] = head[u];
		to[cnt] = v;
		weight[cnt] = w;
		head[u] = cnt++;
	}

	public static void dfs(int u, int w, int f) {
		visited[u] = true;
		if (f == 0) {
			deep[u] = 1;
			stjump[u][0] = u;
			stweight[u][0] = Integer.MAX_VALUE;
		} else {
			deep[u] = deep[f] + 1;
			stjump[u][0] = f;
			stweight[u][0] = w;
		}
		for (int p = 1; (1 << p) <= deep[u]; p++) {
			stjump[u][p] = stjump[stjump[u][p - 1]][p - 1];
			stweight[u][p] = Math.min(stweight[u][p - 1], stweight[stjump[u][p - 1]][p - 1]);
		}
		for (int e = head[u]; e != 0; e = next[e]) {
			if (!visited[to[e]]) {
				dfs(to[e], weight[e], u);
			}
		}
	}

	public static int lca(int a, int b) {
		if (find(a) != find(b)) {
			return -1;
		}
		if (deep[a] < deep[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		int ans = Integer.MAX_VALUE;
		for (int p = power; p >= 0; p--) {
			if (deep[stjump[a][p]] >= deep[b]) {
				ans = Math.min(ans, stweight[a][p]);
				a = stjump[a][p];
			}
		}
		if (a == b) {
			return ans;
		}
		for (int p = power; p >= 0; p--) {
			if (stjump[a][p] != stjump[b][p]) {
				ans = Math.min(ans, Math.min(stweight[a][p], stweight[b][p]));
				a = stjump[a][p];
				b = stjump[b][p];
			}
		}
		ans = Math.min(ans, Math.min(stweight[a][0], stweight[b][0]));
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int n = (int) in.nval;
		build(n);
		in.nextToken();
		int m = (int) in.nval;
		for (int i = 1; i <= m; i++) {
			in.nextToken();
			edges[i][0] = (int) in.nval;
			in.nextToken();
			edges[i][1] = (int) in.nval;
			in.nextToken();
			edges[i][2] = (int) in.nval;
		}
		kruskal(n, m);
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				dfs(i, 0, 0);
			}
		}
		in.nextToken();
		int q = (int) in.nval;
		for (int i = 1, a, b; i <= q; i++) {
			in.nextToken();
			a = (int) in.nval;
			in.nextToken();
			b = (int) in.nval;
			out.println(lca(a, b));
		}
		out.flush();
		out.close();
		br.close();
	}

}
