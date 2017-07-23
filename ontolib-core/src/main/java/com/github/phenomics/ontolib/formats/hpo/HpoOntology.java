package com.github.phenomics.ontolib.formats.hpo;

import java.util.Collection;

import com.github.phenomics.ontolib.graph.data.ImmutableDirectedGraph;
import com.github.phenomics.ontolib.graph.data.ImmutableEdge;
import com.github.phenomics.ontolib.ontology.data.ImmutableOntology;
import com.github.phenomics.ontolib.ontology.data.TermId;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

/**
 * Implementation of the HPO as an {@link ImmutableOntology}.
 *
 * <p>
 * Similarity computations should be performed with the "phenotypic abnormality" sub ontology that
 * can be retrieved through {@link #getPhenotypicAbnormalitySubOntology()}.
 * </p>
 *
 * @see HpoFrequency
 * @see HpoFrequencyTermIds
 * @see HpoModeOfInheritanceTermIds
 * @see HpoSubOntologyRootTermIds
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 * @author <a href="mailto:sebastian.koehler@charite.de">Sebastian Koehler</a>
 */
public final class HpoOntology extends ImmutableOntology<HpoTerm, HpoTermRelation> {

  /** Serial UId for serialization. */
  private static final long serialVersionUID = 1L;

  /** "Phenotypic abnormality" sub ontology. */
  private final ImmutableOntology<HpoTerm, HpoTermRelation> phenotypicAbnormalitySubOntology;

  /**
   * Constructor.
   *
   * @param metaInfo {@link ImmutableSortedMap} with meta information.
   * @param graph Graph with the ontology's topology.
   * @param rootTermId {@link TermId} of the root term.
   * @param nonObsoleteTermIds {@link Collection} of {@link TermId}s of non-obsolete terms.
   * @param obsoleteTermIds {@link Collection} of {@link TermId}s of obsolete terms.
   * @param termMap Mapping from {@link TermId} to HPO term.
   * @param relationMap Mapping from numeric edge identifier to {@link HpoTermRelation}.
   */
  public HpoOntology(ImmutableSortedMap<String, String> metaInfo,
      ImmutableDirectedGraph<TermId, ImmutableEdge<TermId>> graph, TermId rootTermId,
      Collection<TermId> nonObsoleteTermIds, Collection<TermId> obsoleteTermIds,
      ImmutableMap<TermId, HpoTerm> termMap, ImmutableMap<Integer, HpoTermRelation> relationMap) {
    super(metaInfo, graph, rootTermId, nonObsoleteTermIds, obsoleteTermIds, termMap, relationMap);
    // Construct "phenotypic abnormality sub ontology" on construction.
    this.phenotypicAbnormalitySubOntology = (ImmutableOntology<HpoTerm,
        HpoTermRelation>) super.subOntology(HpoSubOntologyRootTermIds.PHENOTYPIC_ABNORMALITY);
  }

  /** @return "Phenotypic abnormality" sub ontology. */
  public ImmutableOntology<HpoTerm, HpoTermRelation> getPhenotypicAbnormalitySubOntology() {
    return phenotypicAbnormalitySubOntology;
  }

  @Override
  public String toString() {
    return "HpoOntology [getMetaInfo()=" + getMetaInfo() + ", getGraph()=" + getGraph()
        + ", getTermMap()=" + getTermMap() + ", getRelationMap()=" + getRelationMap()
        + ", getRootTermId()=" + getRootTermId() + ", getAllTermIds()=" + getAllTermIds()
        + ", getTerms()=" + getTerms() + ", getNonObsoleteTermIds()=" + getNonObsoleteTermIds()
        + ", getObsoleteTermIds()=" + getObsoleteTermIds() + "]";
  }

}
