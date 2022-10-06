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
    if(edges>(size*size)-size){
      int tmp = (size*size)-size;
      System.out.println("The max number of edges is "+tmp);
      System.out.println("Your edges is too large");
      return;
    }
    Random rand = new Random();
    int upperbound = 10; 
    int graph[][] = new int[size][size];
    System.out.println("");
    int[] array = new int[size*(size*(size+1))]; 
    int sizeOfArray=-1;

    ArrayList<Integer> h = new ArrayList<Integer>();
    ArrayList<Integer> nodeAlrConnectedtoSource = new ArrayList<Integer> (); 
    ArrayList<Integer> nodeNotConenctedtoSource = new ArrayList<Integer> (); 
    ArrayList<String> allNodes = new ArrayList<String> ();
    int totalEdges = 0;
    for(int y=0; y<size; y++)
    {
      for(int z=0; z<size; z++)
      {
        if(y!=z){
          allNodes.add(y+","+z);
        }
      }
    }
    for(int ui=0; ui<size; ui++){
      nodeNotConenctedtoSource.add(ui);
    }
  
    //from line 33 to 77
   //create connection for first row of matrix - worst case is having only 1 edge connected directly to source
   int edgesToInitialize = 1;
   for(int u=0; u<edgesToInitialize; u++){
     int random = rand.nextInt(size-1)+1;
     int weightInitial = rand.nextInt(upperbound-1)+1;
     if(!h.contains(random)){
       graph[0][random]= weightInitial;
       allNodes.remove(0+","+random);
       array[++sizeOfArray]=0;
       array[++sizeOfArray]=random;
       totalEdges++;
       h.add(random);
       nodeAlrConnectedtoSource.add(random);
       nodeNotConenctedtoSource.remove(Integer.valueOf(random));
       edges--;
     }
     else{
       u--;
     }
   }
    
    //create connection for each vertices that has not been connected to source, without needing to connect directly
    while(true){
      int index_for_left = (int)(Math.random() * nodeAlrConnectedtoSource.size());
      int index_for_right = (int)(Math.random() * nodeNotConenctedtoSource.size());
      int right_value = nodeNotConenctedtoSource.get(index_for_right);
      int left_value = nodeAlrConnectedtoSource.get(index_for_left);

      if(graph[left_value][right_value]==0 && left_value!=right_value){
        graph[left_value][right_value]=rand.nextInt(upperbound-1)+1;
        allNodes.remove(left_value+","+right_value);
        array[++sizeOfArray]=left_value;
        array[++sizeOfArray]=right_value;
        totalEdges++;
        nodeAlrConnectedtoSource.add(right_value);
        nodeNotConenctedtoSource.remove(Integer.valueOf(right_value));
        edges--;
      }

      //termination case
      if(edges<=0 || nodeNotConenctedtoSource.size()==0) break;
    }

    //create random connection until edges is 0
    while(edges>0){
      int index = (int)(Math.random() * allNodes.size());
      String str = allNodes.get(index);
      int left_value = Integer.parseInt(str.substring( 0, str.indexOf(",")));
      int right_value = Integer.parseInt(str.substring(str.indexOf(",")+1, str.length()));
  
      if(graph[left_value][right_value]==0 && left_value!=right_value){
        graph[left_value][right_value]=rand.nextInt(upperbound-1)+1;
        totalEdges++;
        array[++sizeOfArray]=left_value;
        array[++sizeOfArray]=right_value;
        allNodes.remove(left_value+","+right_value);
        edges--;
      }   
    }
        //end code here - matrix is generated

        System.out.println("Total edges: "+totalEdges);
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
              System.out.print(graph[i][j]+" ");
            }
            System.out.println();
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
          int randtmp = rand.nextInt(10-1)+1;
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