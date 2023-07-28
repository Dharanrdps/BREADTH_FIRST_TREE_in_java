class Node {
    int data;
    Node next;

    public Node(int d) {
        data = d;
        next = null;
    }
}

class Queue {
    Node front, rear;

    public Queue() {
        this.front = this.rear = null;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public int dequeue() {
        if (front == null) {
            return -1; // A value to represent an empty queue (assuming vertices in the graph are non-negative)
        }
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

class Graph {
    int V; // Number of vertices
    Node[] adjList; // Adjacency list representation

    public Graph(int V) {
        this.V = V;
        adjList = new Node[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = null;
        }
    }

    // Function to add an edge to the graph
    public void addEdge(int src, int dest) {
        Node newNode = new Node(dest);
        newNode.next = adjList[src];
        adjList[src] = newNode;

        // For an undirected graph, add the reverse edge as well
        newNode = new Node(src);
        newNode.next = adjList[dest];
        adjList[dest] = newNode;
    }

    // Breadth-First Search traversal from a given source vertex
    public void BFS(int source) {
        boolean[] visited = new boolean[V];
        Queue queue = new Queue();

        visited[source] = true;
        queue.enqueue(source);

        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            System.out.print(current+ " ");

            Node temp = adjList[current];
            while (temp != null) {
                int neighbor = temp.data;
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.enqueue(neighbor);
                }
                temp = temp.next;
            }
        }
    }

     
}

public class Main {
    public static void main(String[] args) {
        // Create a graph with 6 vertices (0 to 5)
        Graph graph = new Graph(4);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
       graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("BFS Traversal starting from vertex 0:");
        graph.BFS(1); // Should print "0 1 2 3 4 5"

      
    }
}
