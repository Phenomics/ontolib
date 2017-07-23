package com.github.phenomics.ontolib.ontology.data;

import com.github.phenomics.ontolib.graph.data.DirectedGraph;

/**
 * Interface for implementation of the <b>visitor pattern</b> for ontology terms (identified by
 * their {@link TermId}).
 *
 * @param <V> vertex type of graph, see {@link DirectedGraph} for requirements on vertex type
 * @param <E> edge type to use in the graph, also see {@link DirectedGraph} for details
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public interface TermVisitor<O extends Ontology<?, ?>> {

  /**
   * Algorithms using <code>TermVisitor</code> will call this function for each visited term.
   *
   * @param ontology Ontology that the visitor is applied to.
   * @param termId ID of currently visited term.
   * @return {@code true} when to continue iteration and {@code false} otherwise.
   */
  boolean visit(O ontology, TermId termId);

}
