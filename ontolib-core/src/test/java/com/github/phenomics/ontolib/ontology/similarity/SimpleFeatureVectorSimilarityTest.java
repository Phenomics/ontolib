package com.github.phenomics.ontolib.ontology.similarity;

import static org.junit.Assert.assertEquals;

import com.github.phenomics.ontolib.ontology.data.ImmutableTermId;
import com.github.phenomics.ontolib.ontology.similarity.SimpleFeatureVectorSimilarity;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

public class SimpleFeatureVectorSimilarityTest {

  SimpleFeatureVectorSimilarity similarity;

  @Before
  public void setUp() {
    similarity = new SimpleFeatureVectorSimilarity();
  }

  @Test
  public void testQueries() {
    assertEquals("Simple feature vector similarity", similarity.getName());
    assertEquals(true, similarity.isSymmetric());
    assertEquals("{}", similarity.getParameters());
  }

  @Test
  public void testComputeSimilarities() {
    assertEquals(1.0,
        similarity.computeScore(
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000008"),
                ImmutableTermId.constructWithPrefix("HP:0000009")),
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000008"))),
        0.01);
    assertEquals(1.0,
        similarity.computeScore(
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000008"),
                ImmutableTermId.constructWithPrefix("HP:0000009")),
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000008"),
                ImmutableTermId.constructWithPrefix("HP:0000010"))),
        0.01);
    assertEquals(0.0,
        similarity.computeScore(
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000009")),
            Lists.newArrayList(ImmutableTermId.constructWithPrefix("HP:0000008"),
                ImmutableTermId.constructWithPrefix("HP:0000010"))),
        0.01);
  }

}
