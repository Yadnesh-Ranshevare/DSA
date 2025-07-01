import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju{
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

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }

    // topological sorting
    public static void topSort(ArrayList<Edge> graph[], int curr, boolean  vis[], Stack<Integer> stack){
        vis[curr] = true;

        for (int i = 0; i< graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(vis[e.dest] == false){
                topSort(graph, e.dest, vis, stack);
            }
        }
        stack.push(curr);
    }

    public static void dfs(ArrayList<Edge> graph[], int curr, boolean  vis[]){
        System.out.print(curr+" ");
        vis[curr] = true;

        for (int i = 0; i< graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(vis[e.dest] == false){
                dfs(graph, e.dest, vis);
            }
        }
    }

    public static void KosarajuAlgo(ArrayList<Edge> graph[]){
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[graph.length];

        for(int i = 0; i< graph.length; i++){
            if(!vis[i]){
                topSort(graph, i, vis, s);
            }
        }

        ArrayList<Edge> transpose[] = new ArrayList[graph.length];

        for (int i = 0; i< graph.length; i++){
            vis[i] = false;
            transpose[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i< graph.length; i++){
            for(int j = 0; j< graph[i].size(); j++){
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                dfs(transpose, curr, vis);
                System.out.println();
            }
        }

    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        KosarajuAlgo(graph);  
    }
}