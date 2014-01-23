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
public class GraphFactory<V>
{

    /**
     * Create an empty graph.
     */
    static public <V> Graph<V> createGraph() 
    {
        return new HashGraph<V>();
    }

}
