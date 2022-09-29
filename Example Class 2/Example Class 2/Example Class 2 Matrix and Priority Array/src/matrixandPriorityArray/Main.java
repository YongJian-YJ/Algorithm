package matrixandPriorityArray;

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
        
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		if(i==j) {
        			graph[i][j]=0;
        		}
        		else {
        			if(count==edges) {
            			break;
            		}
        			graph[i][j]=rand.nextInt(upperbound-1)+1;
        			count++;
        		}
        		
        	}
        }
        System.out.println("The weighted value for each node: ");
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		System.out.print(graph[i][j]+" ");
        	}
        	System.out.println();
        }
        Array_Shortest_Path g = new Array_Shortest_Path(); 
        g.algo_dijkstra(graph, 0); 
    } 
}
