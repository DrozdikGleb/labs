package algorithms.firstyear.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task5 {
    FastScanner in;
    PrintWriter out;
    boolean[] used;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    ArrayList<ArrayList<Integer>> gt = new ArrayList<>();
    ArrayList<Integer> order = new ArrayList<>();
    int[] component;

    public static void main(String[] arg) {
        new task5().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        component = new int[n];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
            gt.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            g.get(f).add(s);
            gt.get(s).add(f);
        }

        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs1(i);
            }
        }
        int k = 0;
        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            int v = order.get(n - 1 - i);
            if (!used[v]) {
                dfs2(v, k);
                k++;
            }
        }
        out.write(String.valueOf(k) + "\n");
        for (int i = 0; i < n; i++) {
            out.write(String.valueOf(component[i] + 1) + " ");
        }
        out.close();
    }

    public void dfs1(int v) {
        if (used[v]) {
            return;
        }
        used[v] = true;
        for (int i = 0; i < g.get(v).size(); i++) {
            dfs1(g.get(v).get(i));

        }
        order.add(v);
    }

    public void dfs2(int v, int k) {
        if (used[v]) {
            return;
        }
        used[v] = true;
        component[v] = k;
        for (int i = 0; i < gt.get(v).size(); i++)
            dfs2(gt.get(v).get(i), k);
    }

    public void run() {
        try {
            in = new FastScanner(new File("strong.in"));
            out = new PrintWriter(new File("strong.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
