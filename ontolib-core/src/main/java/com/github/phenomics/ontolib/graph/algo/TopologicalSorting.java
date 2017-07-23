package com.github.phenomics.ontolib.graph.algo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.phenomics.ontolib.graph.data.DirectedGraph;
import com.github.phenomics.ontolib.graph.data.Edge;

/**
 * Topological sorting for {@link DirectedGraph}s using the <b>visitor pattern</b>.
 *
 * @param <V> vertex type of graph, see {@link DirectedGraph} for requirements on vertex type
 * @param <E> edge type to use in the graph, also see {@link DirectedGraph} for details
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public final class TopologicalSorting<V extends Comparable<V>, E extends Edge<V>,
    G extends DirectedGraph<V, E>> implements GraphVertexAllIteration<V, E, G> {

  @Override
  public void startForward(G g, VertexVisitor<V, E> visitor) {
    final NeighborSelector<V, E> neighborSelector = new ForwardNeighborSelector<V, E>();
    startImpl(g, visitor, neighborSelector);
  }

  @Override
  public void startReverse(G g, VertexVisitor<V, E> visitor) {
    final NeighborSelector<V, E> neighborSelector = new ReverseNeighborSelector<V, E>();
    startImpl(g, visitor, neighborSelector);
  }

  /**
   * Implementation of Tarjan's algorithm for topological sorting.
   *
   * @param g {@link DirectedGraph} to iterate
   * @param visitor {@link VertexVisitor} to use for notifying about reaching a vertex
   * @param selector {@link NeighborSelector} to use for selecting the next neighbor
   */
  public void startImpl(G g, VertexVisitor<V, E> visitor, NeighborSelector<V, E> selector) {
    final Set<V> tmpMarked = new HashSet<V>();

    // Collect unmarked vertices
    final Set<V> unmarked = new HashSet<V>();
    final Iterator<V> vertexIterator = g.vertexIterator();
    while (vertexIterator.hasNext()) {
      unmarked.add(vertexIterator.next());
    }

    // Perform visiting
    while (!unmarked.isEmpty()) {
      final V v = unmarked.iterator().next();
      startFromImpl(g, unmarked, tmpMarked, v, visitor, selector);
    }
  }

  /**
   * Tarjan's <code>visit()</code>.
   *
   * @param g {@link DirectedGraph} to traverse
   * @param unmarked Unmarked vertices
   * @param tmpMarked Temporarily marked vertices
   * @param v Vertex to start from
   * @param selector {@link NeighborSelector} to select neighbors with
   */
  private void startFromImpl(G g, Set<V> unmarked, Set<V> tmpMarked, V v,
      VertexVisitor<V, E> visitor, NeighborSelector<V, E> selector) {
    if (tmpMarked.contains(v)) {
      throw new GraphNotDagException("Graph is not a DAG");
    }
    if (unmarked.contains(v)) {
      tmpMarked.add(v);
      Iterator<V> nextVertices = selector.nextFrom(g, v);
      while (nextVertices.hasNext()) {
        startFromImpl(g, unmarked, tmpMarked, nextVertices.next(), visitor, selector);
      }
      unmarked.remove(v);
      tmpMarked.remove(v);
      if (!visitor.visit(g, v)) {
        return;
      }
    }
  }

}
