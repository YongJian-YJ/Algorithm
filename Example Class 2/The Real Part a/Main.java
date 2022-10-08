
//main testing
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;
import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class Main {
  static int size;
  static int edges;
  static int count;

  public static void main(String[] args) throws IOException {
    for (size = 4; size <= 300; size++) {
      edges = (size - 1);
      count = 0; // 64, 86, 108 ,another file:11,
      // System.out.println("Please enter the number of vertices: ");
      // Scanner scan = new Scanner(System.in);
      // size = scan.nextInt();
      // System.out.println("Please enter the number of edges: ");
      // edges = scan.nextInt();
      // if (edges > (size * size) - size) {
      // int tmp = (size * size) - size;
      // System.out.println("The max number of edges is " + tmp);
      // System.out.println("Your edges is too large");
      // return;
      // }

      Random rand = new Random();
      int upperbound = 10;
      int graph[][] = new int[size][size];
      System.out.println("");

      ArrayList<Integer> h = new ArrayList<Integer>();
      ArrayList<Integer> nodeAlrConnectedtoSource = new ArrayList<Integer>();
      ArrayList<Integer> nodeNotConenctedtoSource = new ArrayList<Integer>();
      ArrayList<String> allNodes = new ArrayList<String>();
      int totalEdges = 0;
      // initialize
      for (int y = 0; y < size; y++) {
        for (int z = 0; z < size; z++) {
          if (y != z) {
            allNodes.add(y + "," + z);
          }
        }
      }

      // add all vertices to the nodeNotConnectedtoSource
      for (int ui = 1; ui < size; ui++) {
        nodeNotConenctedtoSource.add(ui);
      }

      long start = System.nanoTime();
      // from line 33 to 77
      // create connection for first row of matrix - worst case is having only 1 edge
      // connected directly to source
      int edgesToInitialize = 1;
      for (int u = 0; u < edgesToInitialize; u++) {
        int random = rand.nextInt(size - 1) + 1;
        int weightInitial = rand.nextInt(upperbound - 1) + 1;
        if (!h.contains(random)) {

          graph[0][random] = weightInitial;
          allNodes.remove(0 + "," + random);
          totalEdges++;
          h.add(random);
          nodeAlrConnectedtoSource.add(random);
          nodeNotConenctedtoSource.remove(Integer.valueOf(random));
          edges--;
        } else {
          u--;
        }
      }

      // create connection for each vertices that has not been connected to source,
      // without needing to connect directly
      while (true) {
        int index_for_left = (int) (Math.random() * nodeAlrConnectedtoSource.size());
        int index_for_right = (int) (Math.random() * nodeNotConenctedtoSource.size());
        int right_value = nodeNotConenctedtoSource.get(index_for_right);
        int left_value = nodeAlrConnectedtoSource.get(index_for_left);

        if (graph[left_value][right_value] == 0 && left_value != right_value) {

          graph[left_value][right_value] = rand.nextInt(upperbound - 1) + 1;
          allNodes.remove(left_value + "," + right_value);
          totalEdges++;
          nodeAlrConnectedtoSource.add(right_value);
          nodeNotConenctedtoSource.remove(Integer.valueOf(right_value));
          edges--;
        }

        // termination case
        if (edges <= 0 || nodeNotConenctedtoSource.size() == 0)
          break;
      }

      // create random connection until edges is 0
      while (edges > 0) {
        int index = (int) (Math.random() * allNodes.size());
        String str = allNodes.get(index);
        int left_value = Integer.parseInt(str.substring(0, str.indexOf(",")));
        int right_value = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.length()));

        if (graph[left_value][right_value] == 0 && left_value != right_value) {

          graph[left_value][right_value] = rand.nextInt(upperbound - 1) + 1;
          totalEdges++;
          allNodes.remove(left_value + "," + right_value);
          edges--;
        }
      }

      System.out.println("Total edges: " + totalEdges);

      Array_Shortest_Path g = new Array_Shortest_Path();
      g.algo_dijkstra(graph, 0);
      long end = System.nanoTime();
      long elapsedTime = end - start;
      System.out.println("Number of vertices: " + size);
      System.out.println("In nanoseconds : " + elapsedTime);
      double elapsedTimeInSecond = (double) elapsedTime / 1000000000;
      System.out.println("In seconds: " + elapsedTimeInSecond);
      /*
       * System.out.println("The weighted value for each node: ");
       * for (int i = 0; i < size; i++) {
       * for (int j = 0; j < size; j++) {
       * System.out.print(graph[i][j] + " ");
       * }
       * System.out.println();
       * }
       */
      System.out.println("Number of count: " + count);
      File csvFile = new File("TimeanalysisOneGraphDifferent|V|.csv");
      FileWriter fileWriter = new FileWriter(csvFile, true);
      // fileWriter.append("Number of vertices" + "," + "Number of edges" + "," +
      // "Time in nanoseconds" + "," + "Time in seconds");
      fileWriter.append((System.getProperty("line.separator")));
      fileWriter.append(size + "," + totalEdges + "," + elapsedTime + "," + elapsedTimeInSecond + "," + count);
      fileWriter.close();
      // scan.close();
    }
    // //System.out.println("AdditionalEdge: "+additionalEdge);
    // System.out.println("The weighted value for each node: ");
    // for(int i=0;i<size;i++) {
    // for(int j=0;j<size;j++) {
    // System.out.print(graph[i][j]+" ");
    // }
    // System.out.println();
    // }
    // Array_Shortest_Path g = new Array_Shortest_Path();
    // g.algo_dijkstra(graph, 0);

    // scan.close();

  }

}