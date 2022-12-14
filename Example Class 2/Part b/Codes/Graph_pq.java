import java.util.*; 
class Graph_pq extends entryGate{ 
    int dist[]; int pi[];
    Set<Integer> visited; 
    PriorityQueue<Node> pqueue; 
    int V; // Number of vertices 
    List<List<Node> > adj_list; 
    //class constructor
    public Graph_pq(int V) { 
        count++;
        this.V = V; 
        dist = new int[V];
        pi = new int[V];       //add pi[] to store predecessor
        visited = new HashSet<Integer>(); 
        pqueue = new PriorityQueue<Node>(V, new Node()); 
    } 
   
    // Dijkstra's Algorithm implementation 
    public void algo_dijkstra(List<List<Node> > adj_list, int src_vertex) 
    { 
        this.adj_list = adj_list; 
   
        for (int i = 0; i < V; i++) 
            dist[i] = Integer.MAX_VALUE; 
   
        // first add source vertex to PriorityQueue 
        pqueue.add(new Node(src_vertex, 0)); 
        
   
        // Distance to the source from itself is 0 
        dist[src_vertex] = 0; 

        //keep looping as long as the answer slot is not filled up
        while (visited.size() != V) { 
 
            // u is removed from PriorityQueue and has min distance  
            int u = pqueue.remove().node; 
            
   
            // add node to finalized list (visited)
            visited.add(u); 
            count++;
            graph_adjacentNodes(u);
        } 
    } 
    
  // this methods processes all neighbours of the just visited node 
    private void graph_adjacentNodes(int u)   { 
        int newDistance = -1; 
   
        // process all neighbouring nodes of u 
        //adj_list.get(u).size() stores number of neighbouring nodes
        for (int i = 0; i < adj_list.get(u).size(); i++) { 
            Node v = adj_list.get(u).get(i); 
            
          
   
            //  proceed only if current node is not in 'visited'
            // dist[v.node] if has not been dealt with yet is 2147483647
            if (!visited.contains(v.node) & v.cost < dist[v.node]) { 
                count++;
                newDistance = dist[u] + v.cost; 
                dist[v.node] = newDistance;
                pi[v.node] = u;        
   
                // Add the current vertex to the PriorityQueue 
                pqueue.add(new Node(v.node, dist[v.node])); 
                count++;
            } 
        } 
    } 
}

