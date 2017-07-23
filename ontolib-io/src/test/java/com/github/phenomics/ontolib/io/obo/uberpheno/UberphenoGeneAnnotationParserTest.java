package com.github.phenomics.ontolib.io.obo.uberpheno;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.phenomics.ontolib.formats.uberpheno.UberphenoGeneAnnotation;
import com.github.phenomics.ontolib.io.base.TermAnnotationParserException;
import com.github.phenomics.ontolib.io.obo.uberpheno.UberphenoGeneAnnotationParser;
import com.github.phenomics.ontolib.io.utils.ResourceUtils;

public class UberphenoGeneAnnotationParserTest {

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();

  private File uberphenoGeneAnnotationHeadFile;

  @Before
  public void setUp() throws IOException {
    uberphenoGeneAnnotationHeadFile = tmpFolder.newFile("HSgenes_crossSpeciesPhenoAnnotation.txt");
    ResourceUtils.copyResourceToFile("/HSgenes_crossSpeciesPhenoAnnotation.txt",
        uberphenoGeneAnnotationHeadFile);
  }

  @Test
  public void testParseHpoDiseaseAnnotationHead()
      throws IOException, TermAnnotationParserException {
    final UberphenoGeneAnnotationParser parser =
        new UberphenoGeneAnnotationParser(uberphenoGeneAnnotationHeadFile);

    // Read and check first record.
    final UberphenoGeneAnnotation firstRecord = parser.next();
    assertEquals(
        "UberphenoGeneAnnotation [entrezGeneId=90639, entrezGeneSymbol=COX19, termDescription=Hyperphosphatemia, uberphenoTermId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0002905], evidenceDescription=Cox19 (MGI:1915283/MOUSE)]",
        firstRecord.toString());

    // Read remaining records and check count.
    int count = 1;
    while (parser.hasNext()) {
      parser.next();
      count += 1;
    }
    assertEquals(9, count);

    parser.close();
  }

}
