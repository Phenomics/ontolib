package com.github.phenomics.ontolib.ontology.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ImmutableOntologyTest extends ImmutableOntologyTestBase {

  @Test
  public void test() {
    assertEquals("{}", ontology.getMetaInfo().toString());
    assertEquals(
        "ImmutableDirectedGraph [edgeLists={ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001]=ImmutableVertexEdgeList [inEdges=[], outEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], id=1], ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], id=2], ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], id=3]]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002]=ImmutableVertexEdgeList [inEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], id=1]], outEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=4]]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003]=ImmutableVertexEdgeList [inEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], id=2]], outEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=5]]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004]=ImmutableVertexEdgeList [inEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], id=3]], outEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=6]]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005]=ImmutableVertexEdgeList [inEdges=[ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=4], ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=5], ImmutableEdge [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=6]], outEdges=[]]}, edgeCount=6]",
        ontology.getGraph().toString());
    assertEquals(
        "{ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001]=TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], altTermIds=[], name=term1, definition=some definition 1, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002]=TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], altTermIds=[], name=term2, definition=some definition 2, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003]=TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], altTermIds=[], name=term3, definition=some definition 3, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004]=TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], altTermIds=[], name=term4, definition=some definition 4, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005]=TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], altTermIds=[], name=term5, definition=some definition 5, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]]}",
        ontology.getTermMap().toString());
    assertEquals(
        "{1=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], id=1], 2=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], id=2], 3=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], id=3], 4=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=4], 5=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=5], 6=TestTermRelation [source=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], dest=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], id=6]}",
        ontology.getRelationMap().toString());
    assertTrue(ontology.isRootTerm(id5));
    assertEquals(
        "[ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005]]",
        ontology.getAllTermIds().toString());
    assertEquals(
        "[TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], altTermIds=[], name=term1, definition=some definition 1, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], altTermIds=[], name=term2, definition=some definition 2, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], altTermIds=[], name=term3, definition=some definition 3, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], altTermIds=[], name=term4, definition=some definition 4, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]], TestTerm [termId=ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005], altTermIds=[], name=term5, definition=some definition 5, comment=null, subsets=[], termSynonyms=[], obsolete=false, createdBy=null, creationDate=null, xrefs=[]]]",
        ontology.getTerms().toString());
    assertEquals(5, ontology.countAllTerms());
    assertEquals(
        "[ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000001], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000005]]",
        ontology.getNonObsoleteTermIds().toString());
    assertEquals("[]", ontology.getObsoleteTermIds().toString());
    assertEquals(
        "[ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000002], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000003], ImmutableTermId [prefix=ImmutableTermPrefix [value=HP], id=0000004]]",
        ontology.getParentTermIds(id1).toString());
  }


  /** The example graph has id1->id2, id1->id3, id1->id4, id2->id5, id4->id5 */
  @Test
  public void testPathExists() {
    assertTrue(ontology.existsPath(id1,id2));
    assertFalse(ontology.existsPath(id2,id1));
    assertTrue(ontology.existsPath(id1,id3));
    assertFalse(ontology.existsPath(id3,id1));
    assertTrue(ontology.existsPath(id1,id4));
    assertFalse(ontology.existsPath(id4,id1));
    assertTrue(ontology.existsPath(id1,id5));
    assertFalse(ontology.existsPath(id5,id1));
    assertTrue(ontology.existsPath(id2,id5));
    assertFalse(ontology.existsPath(id5,id2));
    assertTrue(ontology.existsPath(id4,id5));
    assertFalse(ontology.existsPath(id5,id4));
  // test that a term cannot have a path to itself.
    assertFalse(ontology.existsPath(id5,id5));

  }


}
