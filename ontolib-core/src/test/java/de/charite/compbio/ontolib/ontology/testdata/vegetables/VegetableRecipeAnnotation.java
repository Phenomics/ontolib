package de.charite.compbio.ontolib.ontology.testdata.vegetables;

import com.google.common.collect.ComparisonChain;

import de.charite.compbio.ontolib.ontology.data.TermAnnotation;
import de.charite.compbio.ontolib.ontology.data.TermId;

/**
 * Annotate that a vegetable is used in a recipe.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class VegetableRecipeAnnotation implements TermAnnotation {

  private static final long serialVersionUID = 1L;

  private TermId termId;
  private String label;

  public VegetableRecipeAnnotation(TermId termId, String label) {
    this.termId = termId;
    this.label = label;
  }

  @Override
  public TermId getTermId() {
    return termId;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public int compareTo(TermAnnotation o) {
    if (!(o instanceof VegetableRecipeAnnotation)) {
      throw new RuntimeException("Cannot compare " + o + " to " + this);
    }
    VegetableRecipeAnnotation that = (VegetableRecipeAnnotation) o;

    return ComparisonChain.start().compare(this.termId, that.termId).compare(this.label, that.label)
        .result();
  }

  @Override
  public String toString() {
    return "VegetableTermAnnotation [termId=" + termId + ", label=" + label + "]";
  }

}
