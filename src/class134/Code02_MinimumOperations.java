package class134;

// 全变成1的最少操作次数
// 测试链接 : https://www.luogu.com.cn/problem/P2962

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code02_MinimumOperations {

	public static int MAXN = 37;

	public static int[][] mat = new int[MAXN][MAXN];

	public static int[] op = new int[MAXN];

	public static int n, ans;

	public static void prepare() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				mat[i][j] = 0;
			}
			mat[i][i] = 1;
			mat[i][n + 1] = 1;
			op[i] = 0;
		}
	}

	// 高斯消元解决异或方程组模版
	public static void gauss(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (j < i && mat[j][j] == 1) {
					continue;
				}
				if (mat[j][i] == 1) {
					swap(i, j);
					break;
				}
			}
			if (mat[i][i] == 1) {
				for (int j = 1; j <= n; j++) {
					if (i != j && mat[j][i] == 1) {
						for (int k = i; k <= n + 1; k++) {
							mat[j][k] ^= mat[i][k];
						}
					}
				}
			}
		}
	}

	public static void swap(int a, int b) {
		int[] tmp = mat[a];
		mat[a] = mat[b];
		mat[b] = tmp;
	}

	public static void dfs(int i, int num) {
		if (num >= ans) { // 剪枝
			return;
		}
		if (i == 0) {
			ans = num;
		} else {
			if (mat[i][i] == 1) {
				int cur = mat[i][n + 1];
				for (int j = i + 1; j <= n; j++) {
					cur ^= mat[i][j] * op[j];
				}
				dfs(i - 1, num + cur);
			} else {
				op[i] = 0;
				dfs(i - 1, num);
				op[i] = 1;
				dfs(i - 1, num + 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		prepare();
		in.nextToken();
		int m = (int) in.nval;
		for (int i = 1, u, v; i <= m; i++) {
			in.nextToken();
			u = (int) in.nval;
			in.nextToken();
			v = (int) in.nval;
			mat[u][v] = 1;
			mat[v][u] = 1;
		}
		gauss(n);
		int sign = 1;
		for (int i = 1; i <= n; i++) {
			if (mat[i][i] == 0) {
				sign = 0;
				break;
			}
		}
		if (sign == 1) {
			ans = 0;
			for (int i = 1; i <= n; i++) {
				ans += mat[i][n + 1];
			}
		} else {
			ans = n;
			dfs(n, 0);
		}
		out.println(ans);
		out.flush();
		out.close();
		br.close();
	}

}