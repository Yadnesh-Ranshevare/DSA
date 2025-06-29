import java.util.ArrayList;

public class BellmanFord{
    static class Edge{
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int wt){
            this.src = s;
            this.dest = d;
            this.wt = wt;
        }
    }

    public static class Pair implements Comparable<Pair>{
        int node;
        int dis;
        public Pair(int node, int dis){
            this.node = node;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dis - p2.dis;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i< graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, -4));

        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));
        
        graph[4].add(new Edge(4, 1, -1));
    }

    public static void BellmanFordAlgo(ArrayList<Edge> graph[], int src, int V){
        int dis[] = new int[V];
        for(int i =0; i< V ; i++){
            if(i != src){
                dis[i] = Integer.MAX_VALUE;
            }
        }

        for(int i= 0; i< V-1; i++){
            for(int j = 0; j<V; j++){
                for(int k = 0; k< graph[j].size(); k++){
                    Edge e = graph[j].get(k);

                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if(dis[u]!= Integer.MAX_VALUE && dis[u] + wt < dis[v]){
                        dis[v] = dis[u] + wt;
                    }
                }
            }
        }

        for(int j = 0; j<V; j++){
            for(int k = 0; k< graph[j].size(); k++){
                Edge e = graph[j].get(k);

                int u = e.src;
                int v = e.dest;
                int wt = e.wt;

                if(dis[u]!= Integer.MAX_VALUE && dis[u] + wt < dis[v]){
                    System.out.println("cycle detected");
                }
            }
        }

        for(int i =0; i<V;i++){
            System.out.print(dis[i]+ " ");
        }
    }



    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        BellmanFordAlgo(graph, 0, V);

    }
}