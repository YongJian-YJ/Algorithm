//main testing
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;
public class Main {
  static int size;
  static int edges;
  public static void main(String[] args) 
    { 
        //example graph is given below
    System.out.println("Please enter the number of vertices: ");
    Scanner scan = new Scanner(System.in);
    size = scan.nextInt();
    System.out.println("Please enter the number of edges: ");
    edges= scan.nextInt();
    Random rand = new Random();
    int upperbound = 10;
    int count=0;
    
    
        int graph[][] = new int[size][size];
    System.out.println("");
        Random ran = new Random();
    ArrayList<Integer> h = new ArrayList<Integer>(5);
    //HashSet<Integer> hashSet = new HashSet<Integer>();

    //create graph
    int totalEdges = 0;
    int edgesToInitialize = (int)(edges*40.0/100.0);
    //(int) edges/2;
    //initialize first row with 40% of the edges
    for(int u=0; u<edgesToInitialize; u++){
      int random = rand.nextInt(size-1)+1;
      int weightInitial = rand.nextInt(upperbound-1)+1;
      if(!h.contains(random)){
        graph[0][random]= weightInitial;
        totalEdges++;
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
        totalEdges++;
        int random_vertice = rand.nextInt(h.size()-1)+1;    
        graph[h.get(random_vertice-1)][x] = rand.nextInt(upperbound-1)+1;
      }
    }

    int remainingEdges = edges - edgesToInitialize - additionalEdge + 1;

    

        for(int i=0;i<remainingEdges-1;i++) {
      int x = rand.nextInt(size);
      int j = rand.nextInt(size);
      int weight = ran.nextInt(upperbound-1)+1;
      
      //check if the edge is already in the graph by using the hashset
      //System.out.println(graph[x][j]);
      if(graph[x][j]==0){
        totalEdges++;
        graph[x][j] = weight;
        
      }
      else{
        i--;
      }
      
    }

  
    System.out.println("Total edges: "+totalEdges);


  
    //System.out.println("AdditionalEdge: "+additionalEdge);
        System.out.println("The weighted value for each node: ");
        for(int i=0;i<size;i++) {
          for(int j=0;j<size;j++) {
            System.out.print(graph[i][j]+" ");
          }
          System.out.println();
        }
        Array_Shortest_Path g = new Array_Shortest_Path(); 
        g.algo_dijkstra(graph, 0); 

    scan.close();
    } 
}