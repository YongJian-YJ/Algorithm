import java.util.*; 
import java.util.Random;
class entryGate{
    public static void main(String arg[])   { 
        //get number of vertices and edges
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of vertices");
        int noOfVertices = sc.nextInt();
        System.out.println("Please enter the number of edges");
        int noOfEdges = sc.nextInt();
        if(noOfEdges > noOfVertices*(noOfVertices-1)){
            System.out.println("Too many edges for this number of vertices; formula: N(N-1)");
            return;
        }

        int V = noOfVertices; 
        int source = 0;
        Random rand = new Random();
       

        // adjacency list representation of graph
        List<List<Node> > adj_list = new ArrayList<List<Node> >(); 


        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj_list.add(item); 
        } 
 
        // Add a set to check if there is any repeated edges
        Set<String> hash_Set = new HashSet<String>();


        //initialize the graph(Adjacency list)
        for(int i=0; i<noOfVertices; i++){
            int randomVertices = rand.nextInt(noOfVertices);
            int randomWeight = rand.nextInt(10);
            if(i!=randomVertices){
                adj_list.get(i).add(new Node(randomVertices, randomWeight));
                hash_Set.add(i+" "+randomVertices);
                System.out.printf("adj_list.get(%d).add(new Node(%d, %d));", i, randomVertices, randomWeight);
                System.out.println("");
                //adj_list.get(0).add(new Node(1, 5)); 
            }
            else{
                i--;    //if it is the same vertex, retry so that we can have the no. of edges we want
            }
        }

        //add additional edges - noOfEdges - noOfVertices as I have already added some of the required edges
        int remaining = noOfEdges - noOfVertices;
        while(remaining>0){
            int i = rand.nextInt(noOfVertices);
            int j = rand.nextInt(noOfVertices);
            if(hash_Set.contains(i+" "+j) || i==j){
                adj_list.get(i).add(new Node(j, rand.nextInt(noOfVertices)));
                remaining--;
            }
            else{
                continue;
            }
        }
        adj_list.get(0).add(new Node(4, 3));
        adj_list.get(1).add(new Node(0, 4));
        adj_list.get(2).add(new Node(1, 1));
        adj_list.get(3).add(new Node(4, 5));
        adj_list.get(4).add(new Node(1, 1));
        
        // call Dijkstra's algo method  
        Graph_pq dpq = new Graph_pq(V); 
        dpq.algo_dijkstra(adj_list, source); 
   
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node("+source+") to other nodes:"); 
        System.out.println("Node\t\t" + "Predecessor\t\t" + "Distance");
        for (int i = 0; i < dpq.dist.length; i++) 
            System.out.println(" " + i + " \t\t     "  + dpq.pi[i]+ " \t\t\t   "  + dpq.dist[i]);




            /* 
         // Input graph edges 
        adj_list.get(0).add(new Node(4, 3));
        adj_list.get(1).add(new Node(0, 4));
        adj_list.get(2).add(new Node(1, 1));
        adj_list.get(3).add(new Node(4, 5));
        adj_list.get(4).add(new Node(1, 1));
        */
    } 
} 
   