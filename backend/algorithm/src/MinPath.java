import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinPath {
    public static void main(String[] args) {
        try {
            new MinPath().service();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void service() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] inputArr = input.split(" ");
        int V = Integer.parseInt(inputArr[0]);
        int E = Integer.parseInt(inputArr[1]);
        int K = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        List<List<Integer>> weightGraph = new ArrayList<List<Integer>>();

        for(int i = 0; i < V+1; i++) {
            List<Integer> list = new ArrayList<Integer>();
            List<Integer> weightList = new ArrayList<Integer>();

            graph.add(list);
            weightGraph.add(weightList);
        }
        for(int i = 0; i < E; i++) {
            input = br.readLine();
            inputArr = input.split(" ");
            int u = Integer.parseInt(inputArr[0]);
            int v = Integer.parseInt(inputArr[1]);
            int w = Integer.parseInt(inputArr[2]);

            graph.get(u).add(v);
            weightGraph.get(u).add(w);
        }

        int[] distances = dijstra(graph, weightGraph, K);
        for(int i = 1; i < distances.length; i++) {
            if(distances[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distances[i]);
            }
        }

        br.close();
    }

    private int[] dijstra(List<List<Integer>> graph, List<List<Integer>> weightGraph, int K) {
        int len = graph.size();
        int[] check = new int[len];
        int[] distances = new int[len];
        for(int i = 0; i < len; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Point> pq = new PriorityQueue<Point>((u, v) -> (u.w - v.w));
        distances[K] = 0;
        pq.offer(new Point(K, distances[K]));

        while(pq.size() != 0) {
            Point node = pq.poll();
            int cv = node.v;
            int cw = node.w;
            if(check[cv] == 1) continue;
            check[cv] = 1;


            List<Integer> childs = graph.get(cv);
            List<Integer> weightChilds = weightGraph.get(cv);
            for(int i = 0; i < childs.size(); i++) {
                int nv = childs.get(i);
                int nw = weightChilds.get(i);

                if(check[nv] == 1) continue;

                if(distances[nv] > distances[cv] + nw) {
                    distances[nv] = distances[cv] + nw;
                    pq.offer(new Point(nv, distances[nv]));
                }
            }
        }
        return distances;
    }
}

class Point {
    public int v, w;

     public Point(int v, int w) {
         this.v = v; this.w = w;
     }
}