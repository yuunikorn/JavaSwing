package edu.union.adt.graph;

import java.util.*;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).
 *
 * The API is based on one from
 *     http://introcs.cs.princeton.edu/java/home/
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Aaron G. Cass
 * @version 1
 */
public class GraphZickb<V> implements Graph<V>{
    private ArrayList<ArrayList<Boolean>> adj_matrix; //list of lists contains 'from' side of edges, lists contain 'to' side
    private ArrayList<V> nodes; //contains node values

    /**
     * Create an empty graph.
     */
    public GraphZickb() {
      nodes = new ArrayList<V>();
      adj_matrix = new ArrayList<ArrayList<Boolean>>();
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices() {
        return adj_matrix.size(); //the number of vertices in the graph is the length of the ArrayList that holds them
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges() {
        int edge_count = 0; //initialize a temporary counter

        for(ArrayList<Boolean> edge_list : adj_matrix) { //iterate through the list of lists
            for(Boolean edge : edge_list) { //iterate through each list
                if(edge) { //and edge exists if the value is true, doesn't if false
                    edge_count++;
                }
            }
        }

        return edge_count;
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex) {
        int degree = 0;

        int index_of_vertex = this.getIndexOf(vertex);

        if(index_of_vertex == -1) {
            throw new RuntimeException("That node does not exist.");
        }

        ArrayList<Boolean> from_edge_list = adj_matrix.get(index_of_vertex);

        //determine the number of edges going from the vertex
        for (boolean edge : from_edge_list) {
            if(edge) {
                degree++;
            }
        }

        /*//determine the number of edges going to the vertex, implemented but not desired, left in case of future use
        for (int i = 0; i < adj_matrix.size(); i++) {
            if(adj_matrix.get(i).get(index_of_vertex)) {
                degree++;
            }
        }*/

        return degree;
    }

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to) {
        //ensures that both vertices exist
        if(!(this.contains(from))) {
            this.addVertex(from);
        }
        if(!(this.contains(to))) {
            this.addVertex(to);
        }

        int index_of_from = this.getIndexOf(from);
        int index_of_to = this.getIndexOf(to);

        adj_matrix.get(index_of_from).set(index_of_to, true); //set the value at this location in the adjacency matrix to true
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex) {
        if(!this.contains(vertex)) {
            if(this.numVertices() == 0){
                nodes.add(vertex); //add the value of the vertex
                ArrayList<Boolean> empty_edge_list_to_add = new ArrayList<Boolean>(); //create an emptyty edge list
                empty_edge_list_to_add.add(false); //set the edge between a node and itself to false by default
                adj_matrix.add(empty_edge_list_to_add); //add this edge list to the adjacency matrix
            } else {
                nodes.add(vertex); //add the value of the vertex
                for(ArrayList<Boolean> edge_list : adj_matrix) { //iterate through the lists of edges
                    edge_list.add(false); //set new addition to the list of edges to false
                }
                ArrayList<Boolean> edge_list_to_add = new ArrayList<Boolean>(); //create a temporary list of edges to add to the adjacency matrix
                for(int i = 0; i < this.numVertices()+1; i++) { //add the correct number of falses standing in for non existent edges
                    edge_list_to_add.add(false);
                }
                adj_matrix.add(edge_list_to_add); //add the temp list to the adjacency matrix
            }
        }
    }

    /**
     * @return an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices() {
        return nodes;
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from) {
        ArrayList<V> adjacent_vertices;

        if(!this.contains(from)) {
            adjacent_vertices = new ArrayList<V>();
        } else {
            int index_of_from = this.getIndexOf(from);
            adjacent_vertices = (ArrayList<V>)this.nodes.clone();
            ArrayList<Boolean> from_edge_list = adj_matrix.get(index_of_from);
            for (int i = adjacent_vertices.size()-1; i >= 0; i--) {
                if(!from_edge_list.get(i)){
                    adjacent_vertices.remove(i);
                }
            }
        }

        return adjacent_vertices;
    }

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex) {
        return nodes.contains(vertex);
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to) {
        if(!this.contains(from) || !this.contains(to)) {
            return false;
        } else {
            int index_of_from = this.getIndexOf(from);
            int index_of_to = this.getIndexOf(to);

            return adj_matrix.get(index_of_from).get(index_of_to);
        }
    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     *
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString() {
        String graph_string = "";
        boolean first_vertex = true;

        for (int i = 0; i < nodes.size(); i++) {
            first_vertex = true;
            String string_to_add = "";
            string_to_add += nodes.get(i);
            string_to_add += ":";

            ArrayList<Boolean> working_edge_list = adj_matrix.get(i);

            //loop over the ArrayList of edges to add all existent edges
            for (int j = 0; j < working_edge_list.size(); j++) {
                boolean thrown = false;

                if(working_edge_list.get(j) && first_vertex) {
                    string_to_add += " ";
                    string_to_add += nodes.get(j);
                    first_vertex = false;
                } else if(working_edge_list.get(j)) {
                    string_to_add += ",";
                    string_to_add += " ";
                    string_to_add += nodes.get(j);
                }
            }
            string_to_add += "\n";
            graph_string += string_to_add;
        }
        return graph_string;
    }

    /**
     * Tells whether the graph is empty.
     *
     * @return true iff the graph is empty. A graph is empty if it has
     * no vertices and no edges.
     */
    public boolean isEmpty(){
        return nodes.isEmpty(); //check if the list of nodes is empty26
    }

    /**
     * Removes and vertex from the graph.  Also removes any edges
     * connecting from the edge or to the edge.
     *
     * <p>Postconditions:
     *
     * <p>If toRemove was in the graph:
     * <ul>
     * <li>numVertices = numVertices' - 1
     * <li>toRemove is no longer a vertex in the graph
     * <li>for all vertices v: toRemove is not in adjacentTo(v)
     * </ul>
     *
     * @param toRemove the vertex to remove.
     */
    public void removeVertex(V toRemove){
        int index_to_remove = this.getIndexOf(toRemove); //get the index of the vertex to be removed

        if(index_to_remove != -1){ //ensure that vertex exists in the graph
            nodes.remove(index_to_remove); //remove it from the nodes list

            adj_matrix.remove(index_to_remove); //remove its from list

            for(ArrayList to_list : adj_matrix){
                to_list.remove(index_to_remove); //remove it from all to lists
            }
        }
    }

    /**
     * Removes an edge from the graph.
     *
     * <p>Postcondition: If from and to were in the graph and (from,
     * to) was an edge in the graph, then numEdges = numEdges' - 1
     */
    public void removeEdge(V from, V to){
        if(this.hasEdge(from, to)) { //ensure both vertices and the edge are in the graph
            int index_of_from = this.getIndexOf(from); //get the from vertex index
            int index_of_to = this.getIndexOf(to); //get the to vertex index

            adj_matrix.get(index_of_from).set(index_of_to, false); //set the matrix entry for this edge to false
        }
    }

    /**
     * Tells whether there is a path connecting two given vertices.  A
     * path exists from vertex A to vertex B iff A and B are in the
     * graph and there exists a sequence x_1, x_2, ..., x_n where:
     *
     * <ul>
     * <li>x_1 = A
     * <li>x_n = B
     * <li>for all i from 1 to n-1, (x_i, x_{i+1}) is an edge in the graph.
     * </ul>
     *
     * It therefore follows that, if vertex A is in the graph, there
     * is a path from A to A.
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true iff there is a path from 'from' to 'to' in the graph.
     */
    public boolean hasPath(V from, V to){
        if(this.contains(from) && this.contains(to)){ //ensure vertices are in graph
            Iterator<V> iterator;

            try {
                iterator = this.getPath(from, to).iterator(); //attempt to get path
            } catch (IllegalArgumentException e) {
                return false; //if error is thrown return false
            }

            return iterator.hasNext(); //if there is a path, return true
        }
        return false; //if not return false
    }

    /**
     * Gets the length of the shortest path connecting two given
     * vertices.  The length of a path is the number of edges in the
     * path.
     *
     * <ol>
     * <li>If from = to, shortest path has length 0
     * <li>Otherwise, shortest path length is length of the shortest
     * possible path connecting from to to.
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return the length of the shortest path from 'from' to 'to' in
     * the graph.  If there is no path, returns Integer.MAX_VALUE
     */
    public int pathLength(V from, V to){
        if(this.contains(from) && this.contains(to)){ //ensure vertices are in graph
            Iterator<V> iterator;

            try {
                iterator = this.getPath(from, to).iterator(); //attempt to get path
            } catch (IllegalArgumentException e) {
                return Integer.MAX_VALUE; //if error is thrown return false
            }
            int counter = -1;

            while(iterator.hasNext()){
                counter++;
                iterator.next();
            }

            return counter; //if there is a path, return its length
        }
        return Integer.MAX_VALUE; //if not return MAX_INT
    }

    /**
     * Returns the vertices along the shortest path connecting two
     * given vertices.  The vertices should be given in the order x_1,
     * x_2, x_3, ..., x_n, where:
     *
     * <ol>
     * <li>x_1 = from
     * <li>x_n = to
     * <li>for all i from 1 to n-1: (x_i, x_{i+1}) is an edge in the graph.
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return an Iterable collection of vertices along the shortest
     * path from 'from' to 'to'.  The Iterable should include the
     * source and destination vertices.
     */
    public Iterable<V> getPath(V from, V to){
        if(this.contains(from) && this.contains(to)){ //ensure both vertices are in the graph
            Map<V, Boolean> visited = new HashMap<V, Boolean>(); //to store which vertices have been visited
            Map<V, V> previous = new HashMap<V, V>(); //to store each vertex's previous vertex

            ArrayList<V> path = new ArrayList<V>(); //to store the shortest path
            Queue<V> visitation_queue= new LinkedList<V>(); //to store the order vertices will be visited

            V current_vertex = from; //visit the from vertex first
            visitation_queue.add(from);
            visited.put(current_vertex, true);

            while(!visitation_queue.isEmpty()){ //if the queue is empty, no more nodes to visit
                current_vertex = visitation_queue.remove(); //remove the visited vertex
                if(!current_vertex.equals(to)){ //if equals to to we are done
                    for(V vertex : this.adjacentTo(current_vertex)){ //otherwise explore adjacent vertices
                        if(!visited.containsKey(vertex)){
                            visitation_queue.add(vertex);
                            visited.put(vertex, true);
                            previous.put(vertex, current_vertex);
                        }
                    }
                }
            }

            if(!current_vertex.equals(to)){ //throw an error if here is no path
                throw new IllegalArgumentException("There is no path between the two argument vertices.");
            }

            for(V vertex = to; vertex != null; vertex = previous.get(vertex)){
                path.add(vertex); //build the path
            }

            Collections.reverse(path); //reverse it cuz its backwards
            return path;

        } else {
            throw new IllegalArgumentException("One or both nodes is not in the graph."); //throw an error if the vertices are not in the graph
        }

    }

    /**
     *  Tells whether the passed object is an equivalent graph. This means
     *  that it has the same vertices and edges, regardless of the types of those
     *  vertices and edges.
     *
     *  @param o the graph to compare to
     *  @return true if the passed object is equal to this graph, false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Graph) {
            Graph g = (Graph) o;
            if (this.toString().equals(g.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Returns the index of the passed vertex in the nodes list and therefore in the matrix.
     *
     *  @param vertex the vertex to get the index
     *  @return the integer index of the vertex, -1 otherwise
     */
    private int getIndexOf(V vertex){
        if(this.contains(vertex)){
            return nodes.indexOf(vertex);
        } else {
            return -1;
        }
    }
}
