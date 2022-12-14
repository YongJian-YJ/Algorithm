
public class Array_Shortest_Path extends Main {
    int num_Vertices = size; // max number of vertices in graph
    int pi[] = new int[size];

    // find a vertex with minimum distance
    int minDistance(int path_array[], Boolean S[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < num_Vertices; v++) {
            if (S[v] == false && path_array[v] <= min) {
                count++;
                min = path_array[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // print the array of distances (path_array)
    void printMinpath(int path_array[], int pi[]) {

        System.out.println(
                "Vertex# \t Minimum Distance from Source \t Predecessors \t Minimum Distance from Source to Vertex");
        for (int i = 1; i < num_Vertices; i++) {
            if (pi[i] == 0) {
                System.out.println(i + " \t\t\t " + path_array[i] + "\t\t\t\t" + pi[i] + "\t\t\t" + i + "<-0");
            } else {
                System.out.print(i + " \t\t\t " + path_array[i] + "\t\t\t\t" + pi[i] + "\t\t\t" + i + "<-");
                int temp = pi[i];
                while (temp != 0) {
                    System.out.print(temp + "<-");
                    temp = pi[temp];
                }
                System.out.print("0");
                System.out.println();
            }
            System.out.println();
        }
    }

    // Implementation of Dijkstra's algorithm for graph (adjacency matrix)
    void algo_dijkstra(int graph[][], int src_node) {
        int path_array[] = new int[num_Vertices]; // The output array. dist[i] will hold

        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        Boolean S[] = new Boolean[num_Vertices];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < num_Vertices; i++) {
            path_array[i] = Integer.MAX_VALUE;
            S[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[src_node] = 0;
        // now find shortest path for all vertices
        //the first for loop is to loop through the x axis of the matrix
        //the second for loop is to loop through the y axis of the matrix
        for (int counter = 0; counter < num_Vertices - 1; counter++) {
            count++;
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, S);
            // the current vertex u is processed
            S[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < num_Vertices; v++) {
                // if vertex v not in S then update it
                if (!S[v] && graph[u][v] != 0 && path_array[u] != Integer.MAX_VALUE && path_array[u]
                        + graph[u][v] < path_array[v]) {
                    count++;
                    path_array[v] = path_array[u] + graph[u][v];
                    pi[v] = u;
                }
            }
        }

        // print the path array
        // printMinpath(path_array, pi);

    }
}
