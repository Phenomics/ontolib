package com.github.phenomics.ontolib.ontology.similarity;

import java.util.Map;

import com.github.phenomics.ontolib.ontology.data.Term;
import com.github.phenomics.ontolib.ontology.data.TermId;
import com.github.phenomics.ontolib.ontology.data.TermRelation;

/**
 * Implementation of pairwise Jiang similarity.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 * @author <a href="mailto:sebastian.koehler@charite.de">Sebastian Koehler</a>
 */
final class PairwiseJiangSimilarity<T extends Term, R extends TermRelation>
    implements
      PairwiseSimilarity {

  /** Internally used pairwise Resnik similarity. */
  private final PairwiseResnikSimilarity<T, R> pairwiseResnik;

  /**
   * Construct with inner {@link PairwiseResnikSimilarity}.
   *
   * @param pairwiseResnik Inner {@link PairwiseResnikSimilarity} to use.
   */
  public PairwiseJiangSimilarity(PairwiseResnikSimilarity<T, R> pairwiseResnik) {
    this.pairwiseResnik = pairwiseResnik;
  }

  @Override
  public double computeScore(TermId query, TermId target) {
    final Map<TermId, Double> termToIc = pairwiseResnik.getTermToIc();
    final double resnikScore = this.pairwiseResnik.computeScore(query, target);
    return (1.0 / (termToIc.get(query) + termToIc.get(target) - (2 * resnikScore) + 1));
  }

}
