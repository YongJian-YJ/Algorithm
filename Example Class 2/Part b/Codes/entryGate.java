import java.util.*; 

class entryGate{
    static int size;
    static int edges;
    public static void main(String arg[])   { 
        Scanner sc = new Scanner(System.in);

    //Generate Matrix from line 9 to line 62
    System.out.println("Please enter the number of vertices: ");
    Scanner scan = new Scanner(System.in);
    size = scan.nextInt();
    System.out.println("Please enter the number of edges: ");
    edges= scan.nextInt();

    Random rand = new Random();
    int upperbound = 10;    
    int graph[][] = new int[size][size];
    ArrayList<Integer> h = new ArrayList<Integer>(5);
   
    //create graph
    int edgesToInitialize = (int)(size*40.0/100.0);
    for(int u=0; u<edgesToInitialize; u++){
      int random = rand.nextInt(size-1)+1;
      int weightInitial = rand.nextInt(upperbound-1)+1;
      if(!h.contains(random)){
        graph[0][random]= weightInitial;
        h.add(random);
      }
      else{
        u--;
      }
    }

    int additionalEdge = 0;

    for(int x=0; x<size; x++){
      if(graph[0][x]==0){
        //if it is 0, establish a connection
        additionalEdge++;
        int random_vertice = rand.nextInt(h.size()-1)+1;    
        graph[h.get(random_vertice-1)][x] = rand.nextInt(upperbound-1)+1;
      }
    }

    int remainingEdges = edges - edgesToInitialize - additionalEdge + 1;

      //create connection for remaining no. of edges
      for(int i=0;i<remainingEdges-1;i++) {
      int x = rand.nextInt(size);
      int j = rand.nextInt(size);
      int weight = rand.nextInt(upperbound-1)+1;
      
      //check if the edge is already in the graph
      if(graph[x][j]==0){
        graph[x][j] = weight;
      }
      else{
        i--;
      }
    }
        //end code here - matrix is generated

        
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
              System.out.print(graph[i][j]+" ");
            }
            System.out.println();
          }

        //store the matrix into (array)
        int[] array = new int[size*4]; 
        int sizeOfArray=-1;
        for(int p=0; p<size; p++){
          for(int q=0; q<size; q++){
            if(graph[p][q]!=0){
              array[++sizeOfArray] = p;
              array[++sizeOfArray] = q;
            }
          }
        }
        sizeOfArray = sizeOfArray+1;

        int V = size; 
        int source = 0;
        int oldSize = sizeOfArray;
        // adjacency list representation of graph
        List<List<Node> > adj_list = new ArrayList<List<Node> >(); 

        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj_list.add(item); 
        } 
        
        
        // Input graph edges 
        sizeOfArray = -1;
        while(true){
          int first = array[++sizeOfArray];
          int second = array[++sizeOfArray];
          int randtmp = rand.nextInt(20-1)+1;
          System.out.printf("adj_list.get(%d).add(new Node(%d,%d)", first, second, randtmp);
          System.out.println("");
            adj_list.get(first).add(new Node(second, randtmp)); 
            if(sizeOfArray>=oldSize-1){
                break;
            }
        }
            
        // call Dijkstra's algo method  
        Graph_pq dpq = new Graph_pq(V); 
        dpq.algo_dijkstra(adj_list, source); 
   
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node("+source+") to other nodes:"); 
        System.out.println("Node\t\t" + "Predecessor\t\t" + "Distance");
        for (int i = 0; i < dpq.dist.length; i++) 
            System.out.println(" " + i + " \t\t     "  + dpq.pi[i]+ " \t\t\t   "  + dpq.dist[i]);
    } 
} 