package com.github.phenomics.ontolib.formats.hpo;

import com.github.phenomics.ontolib.ontology.data.ImmutableTermId;
import com.github.phenomics.ontolib.ontology.data.TermId;

/**
 * Utility class with TermIds into the "frequency" sub ontology.
 *
 * @see HpoFrequency
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 * @author <a href="mailto:sebastian.koehler@charite.de">Sebastian Koehler</a>
 */
public final class HpoFrequencyTermIds {

  /** {@link TermId} for "always present (100% of the cases)". */
  public static final TermId ALWAYS_PRESENT = ImmutableTermId.constructWithPrefix("HP:0040280");

  /** {@link TermId} for "very frequent (80-99% of the cases)". */
  public static final TermId VERY_FREQUENT = ImmutableTermId.constructWithPrefix("HP:0040281");

  /** {@link TermId} for "frequent (30-79% of the cases)". */
  public static final TermId FREQUENT = ImmutableTermId.constructWithPrefix("HP:0040282");

  /** {@link TermId} for "occasional (5-29% of the cases)". */
  public static final TermId OCCASIONAL = ImmutableTermId.constructWithPrefix("HP:0040283");

  /** {@link TermId} for "excluded (1-4% of the cases)". */
  public static final TermId VERY_RARE = ImmutableTermId.constructWithPrefix("HP:0040284");

  /** {@link TermId} for "excluded (0% of the cases)". */
  public static final TermId EXCLUDED = ImmutableTermId.constructWithPrefix("HP:0040285");

}