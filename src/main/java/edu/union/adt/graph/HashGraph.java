package edu.union.adt.graph;
import java.util.HashMap;
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
 * @author Duri Abdurahman Duri
 * @version 1
 */
public class HashGraph<V> implements Graph<V>
{
    HashMap<V, HashMap<V,Boolean>> graph;
    int count = 0;
    /**
     * Create an empty graph.
     */
    public HashGraph() 
    {
        graph  =  new HashMap<V, HashMap<V,Boolean>>();
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
        return graph.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
        return count;
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex)
    {
        if(graph.containsKey(vertex)){
            return graph.get(vertex).size();
        }
        else{
            throw new RuntimeException("Vertex does not exist in this Graph");
        }       
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
    public void addEdge(V from, V to)
    {
        if(!graph.containsKey(from)){
            this.addVertex(from);
        }
        if(!graph.containsKey(to)){
            this.addVertex(to);
        }
        if(graph.get(from).get(to) == null){
            graph.get(from).put(to, true);
        }
        this.count++;
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex)
    {
        if(!graph.containsKey(vertex)){
            graph.put(vertex, new HashMap<V, Boolean>());
        }
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return (Iterable<V>)graph.keySet();
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
    public Iterable<V> adjacentTo(V from)
    {
        if(!graph.containsKey(from)) return null;
        return (Iterable<V>) graph.get(from).keySet();
    }

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex)
    {
        return graph.containsKey(vertex);
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
    public boolean hasEdge(V from, V to)
    {
        if(!graph.containsKey(from)){
            return false;
        }
        if (graph.get(from).get(to) == null){
            return false;
        }
        else{
            return graph.get(from).get(to);
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
    public String toString()
    {
        String result = "";
        Boolean firstIt = true;
        for(V vertex: this.getVertices()){
            result = result + vertex+":";
            firstIt = true;
            for(V adjVertex: graph.get(vertex).keySet()){
                if(firstIt){
                    result = result + " " + adjVertex;
                    firstIt = false;
                }else{
                    result=result+" ,"+adjVertex;
                }
            }
            result=result+"\n";

        }
        return result;
    }


    /**
     * @return the boolean equivalence whether this Graph is identical
     * to the one passed into the argument.
     */
    public boolean equals(Object other){
        if(other == null) return false;
        if(!(other instanceof HashGraph)) return false;
        if(this == other) return true;
        HashGraph otherGraph = (HashGraph)other;
        return this.toString().equals(otherGraph.toString());
    }
}
