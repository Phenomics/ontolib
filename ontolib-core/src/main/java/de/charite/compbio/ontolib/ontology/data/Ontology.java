package de.charite.compbio.ontolib.ontology.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Interface for most ontology implementations to implement.
 *
 * <p>
 * This interface adds functions for easy access to ancestors to {@link MinimalOntology}.
 * </p>
 *
 * @param <T> {@link Term} sub class this <code>Ontology</code> uses
 * @param <R> {@link TermRelation} sub class this <code>Ontology</code> uses.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 * @author <a href="mailto:sebastian.koehler@charite.de">Sebastian Koehler</a>
 */
public interface Ontology<T extends Term, R extends TermRelation>
    extends
      MinimalOntology<T, R>,
      Serializable {

  // TODO: consolidate naming

  /**
   * Return all the {@link TermId}s of all ancestors from {@code termId}.
   * 
   * @param termId The {@link TermId} to query ancestor {@link TermId}s for.
   * @param includeRoot Whether or not to include the root.
   * @return {@link Set} of {@link TermId}s of the ancestors of {@code termId} (including itself).
   */
  Set<TermId> getAncestors(TermId termId, boolean includeRoot);

  /**
   * Return all the {@link TermId}s of all ancestors from {@code termId}, including root.
   * 
   * @param termId The {@link TermId} to query ancestor {@link TermId}s for.
   * @return {@link Set} of {@link TermId}s of the ancestors of {@code termId} (including itself),
   *         including root.
   */
  default Set<TermId> getAncestors(TermId termId) {
    return getAncestors(termId, true);
  }

  /**
   * Return all the {@link TermId}s of all ancestors from {@code termIds}.
   *
   * @param termIds {@link Collection} of {@link TermId}s to gather all parents except for the root.
   * @param includeRoot Whether or not to include the root's {@link TermId}
   * @return {@link Set} of {@link TermId}s including all {@link TermId}s from {@code termIds},
   *         including all ancestors.
   */
  Set<TermId> getAllAncestorTermIds(Collection<TermId> termIds, boolean includeRoot);

  /**
   * Return all the {@link TermId}s of all ancestors from {@code termIds}, including root.
   *
   * @param termIds {@link Collection} of {@link TermId}s to gather all parents except for the root.
   * @return {@link Set} of {@link TermId}s including all {@link TermId}s from {@code termIds},
   *         including all ancestors, including root.
   */
  default Set<TermId> getAllAncestorTermIds(Collection<TermId> termIds) {
    return getAllAncestorTermIds(termIds, true);
  }

}
