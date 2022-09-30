//main testing
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
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
    
    
    int graph[][] = new int[size][size];
    System.out.println("");

    ArrayList<Integer> h = new ArrayList<Integer>(5);
    Set<Integer> nodeAlrConnectedtoSource = new HashSet<Integer> (); 
    Set<Integer> nodeNotConenctedtoSource = new HashSet<Integer> (size); 

    for(int ui=0; ui<size; ui++){
      nodeNotConenctedtoSource.add(ui);
    }
  
    //from line 33 to 77
    //create connection for first row of matrix
    int edgesToInitialize = (int)(size*40.0/100.0);
    for(int u=0; u<edgesToInitialize; u++){
      int random = rand.nextInt(size-1)+1;
      int weightInitial = rand.nextInt(upperbound-1)+1;
      if(!h.contains(random)){
        graph[0][random]= weightInitial;
        h.add(random);
        nodeAlrConnectedtoSource.add(random);
        nodeNotConenctedtoSource.remove(random);
        edges--;
      }
      else{
        u--;
      }
    }
    
    //create connection for each vertices that has not been connected to source, without needing to connect directly
    while(true){
      Integer[] arrayNumbers = nodeAlrConnectedtoSource.toArray(new Integer[nodeAlrConnectedtoSource.size()]);
      Integer[] arrayNumbers2 = nodeNotConenctedtoSource.toArray(new Integer[nodeNotConenctedtoSource.size()]);
      int left_value = arrayNumbers[rand.nextInt(nodeAlrConnectedtoSource.size())];
      int right_value = arrayNumbers2[rand.nextInt(nodeNotConenctedtoSource.size())];
      if(graph[left_value][right_value]==0 || left_value!=right_value){
        graph[left_value][right_value]=rand.nextInt(upperbound-1)+1;
        nodeAlrConnectedtoSource.add(right_value);
        edges--;
        nodeNotConenctedtoSource.remove(right_value);
      }
      //termination case
      if(edges<=0 || nodeNotConenctedtoSource.size()==0) break;
    }

    //create random connection until edges is 0
    while(edges>0){
      int left_value = rand.nextInt(size-1)+1;
      int right_value = rand.nextInt(size-1)+1;

      if(graph[left_value][right_value]==0 || left_value!=right_value){
        graph[left_value][right_value]=rand.nextInt(upperbound-1)+1;
        edges--;
      }   
    }
    

    //System.out.println("Total edges: "+totalEdges);

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