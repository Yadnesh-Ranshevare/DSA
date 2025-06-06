import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS{
    static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i< graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1));
        
        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 0));
        
        graph[2].add(new Edge(2, 1));
        
        graph[3].add(new Edge(3, 4));
        
        graph[4].add(new Edge(4, 3));
    }

    public static void bfs(ArrayList<Edge> graph[], int V, int start, boolean vis[]){
        Queue<Integer> q = new LinkedList<>();
        

        q.add(start);

        while(!q.isEmpty()){
            int curr = q.remove();
            if(vis[curr] == false){
                System.out.print(curr + " ");
                vis[curr] = true;
                for(int i =0; i < graph[curr].size(); i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }

        
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        boolean vis[] = new boolean[V];

        for(int i = 0; i< V;i++){
            if(vis[i] == false){
                bfs(graph, V, i,vis);
            }
        }
    }
}