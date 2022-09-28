package matrixandPriorityArray;

import java.util.Scanner;
import java.util.Random;
public class Main {
	static int size;
	public static void main(String[] args) 
    { 
        //example graph is given below
		System.out.println("Please enter the size of the matrix: ");
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int upperbound = 10;
		size = scan.nextInt();
        int graph[][] = new int[size][size]; 
        
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		if(i==j) {
        			graph[i][j]=0;
        		}
        		else {
        			graph[i][j]=rand.nextInt(upperbound);  
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
