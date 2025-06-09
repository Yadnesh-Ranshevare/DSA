import java.util.ArrayList;

public class CycleDetectionInDirectedGraph {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }
    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));

        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 0));
    }  

    public static boolean isCyclePresent(ArrayList<Edge> graph[], boolean vis[], int curr, boolean rec[]){
        vis[curr] = true;
        rec[curr] = true;

        for(int i = 0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(rec[e.dest]){
                return  true;
            }else if(!vis[e.dest]){
                if(isCyclePresent(graph, vis, e.dest, rec)){
                    return true;
                }
            }
        }
        rec[curr] = false;
        return  false;
    }
    
    
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        boolean vis[] = new boolean[V];
        boolean rec[] = new boolean[V];

        for(int i = 0; i< V; i++){
            if(!vis[i]){
                if(isCyclePresent(graph, vis, i, rec)){
                    System.out.println("true");
                    break;
                }
            }
        }
    }
}
