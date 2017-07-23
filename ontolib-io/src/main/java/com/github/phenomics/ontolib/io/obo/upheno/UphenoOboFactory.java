package com.github.phenomics.ontolib.io.obo.upheno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.github.phenomics.ontolib.base.OntoLibRuntimeException;
import com.github.phenomics.ontolib.formats.upheno.UphenoRelationQualifier;
import com.github.phenomics.ontolib.formats.upheno.UphenoTerm;
import com.github.phenomics.ontolib.formats.upheno.UphenoTermRelation;
import com.github.phenomics.ontolib.io.obo.OboImmutableOntologyLoader;
import com.github.phenomics.ontolib.io.obo.OboOntologyEntryFactory;
import com.github.phenomics.ontolib.io.obo.Stanza;
import com.github.phenomics.ontolib.io.obo.StanzaEntry;
import com.github.phenomics.ontolib.io.obo.StanzaEntryAltId;
import com.github.phenomics.ontolib.io.obo.StanzaEntryComment;
import com.github.phenomics.ontolib.io.obo.StanzaEntryCreatedBy;
import com.github.phenomics.ontolib.io.obo.StanzaEntryCreationDate;
import com.github.phenomics.ontolib.io.obo.StanzaEntryDef;
import com.github.phenomics.ontolib.io.obo.StanzaEntryDisjointFrom;
import com.github.phenomics.ontolib.io.obo.StanzaEntryId;
import com.github.phenomics.ontolib.io.obo.StanzaEntryIntersectionOf;
import com.github.phenomics.ontolib.io.obo.StanzaEntryIsA;
import com.github.phenomics.ontolib.io.obo.StanzaEntryIsObsolete;
import com.github.phenomics.ontolib.io.obo.StanzaEntryName;
import com.github.phenomics.ontolib.io.obo.StanzaEntryRelationship;
import com.github.phenomics.ontolib.io.obo.StanzaEntrySubset;
import com.github.phenomics.ontolib.io.obo.StanzaEntrySynonym;
import com.github.phenomics.ontolib.io.obo.StanzaEntryType;
import com.github.phenomics.ontolib.io.obo.StanzaEntryUnionOf;
import com.github.phenomics.ontolib.ontology.data.ImmutableTermId;
import com.github.phenomics.ontolib.ontology.data.ImmutableTermSynonym;
import com.github.phenomics.ontolib.ontology.data.ImmutableTermXref;
import com.github.phenomics.ontolib.ontology.data.TermId;
import com.github.phenomics.ontolib.ontology.data.TermSynonym;
import com.github.phenomics.ontolib.ontology.data.TermSynonymScope;
import com.github.phenomics.ontolib.ontology.data.TermXref;
import com.google.common.collect.Lists;

/**
 * Factory class for constructing {@link UphenoTerm} and {@link UphenoTermRelation} objects from
 * {@link Stanza} objects for usage in {@link OboOntologyEntryFactory}.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
class UphenoOboFactory implements OboOntologyEntryFactory<UphenoTerm, UphenoTermRelation> {

  /**
   * Mapping from string representation of term Id to {@link TermId}.
   *
   * <p>
   * All occuring termIds must be previously registered into this map before calling any of this
   * object's functions. This happens in {@link OboImmutableOntologyLoader}.
   * </p>
   */
  private SortedMap<String, ImmutableTermId> termIds = null;

  /** Id of next relation. */
  private int nextRelationId = 1;

  @Override
  public void setTermIds(SortedMap<String, ImmutableTermId> termIds) {
    this.termIds = termIds;
  }

  @Override
  public UphenoTerm constructTerm(Stanza stanza) {
    final TermId id =
        termIds.get(this.<StanzaEntryId>getCardinalityOneEntry(stanza, StanzaEntryType.ID).getId());

    final String name =
        this.<StanzaEntryName>getCardinalityOneEntry(stanza, StanzaEntryType.NAME).getName();

    final List<TermId> altTermIds;
    final List<StanzaEntry> altEntryList = stanza.getEntryByType().get(StanzaEntryType.ALT_ID);
    if (altEntryList == null) {
      altTermIds = Lists.newArrayList();
    } else {
      altTermIds = altEntryList.stream().map(e -> termIds.get(((StanzaEntryAltId) e).getAltId()))
          .collect(Collectors.toList());
    }

    final StanzaEntryDef defEntry =
        this.<StanzaEntryDef>getCardinalityZeroOrOneEntry(stanza, StanzaEntryType.DEF);
    final String definition = (defEntry == null) ? null : defEntry.getText();

    final StanzaEntryComment commentEntry =
        this.<StanzaEntryComment>getCardinalityZeroOrOneEntry(stanza, StanzaEntryType.COMMENT);
    final String comment = (commentEntry == null) ? null : commentEntry.getText();

    final List<String> subsets;
    final List<StanzaEntry> subsetEntryList = stanza.getEntryByType().get(StanzaEntryType.SUBSET);
    if (subsetEntryList == null) {
      subsets = Lists.newArrayList();
    } else {
      subsets = subsetEntryList.stream().map(e -> ((StanzaEntrySubset) e).getName())
          .collect(Collectors.toList());
    }

    final List<TermSynonym> synonyms;
    final List<StanzaEntry> synonymEntryList = stanza.getEntryByType().get(StanzaEntryType.SYNONYM);
    if (synonymEntryList == null) {
      synonyms = Lists.newArrayList();
    } else {
      synonyms = synonymEntryList.stream().map(e -> {
        final StanzaEntrySynonym s = (StanzaEntrySynonym) e;

        final String value = s.getText();
        final TermSynonymScope scope = s.getTermSynonymScope();
        final String synonymTypeName = s.getSynonymTypeName();
        final List<TermXref> termXrefs = s.getDbXrefList().getDbXrefs().stream()
            .map(xref -> new ImmutableTermXref(termIds.get(xref.getName()), xref.getDescription()))
            .collect(Collectors.toList());

        return new ImmutableTermSynonym(value, scope, synonymTypeName, termXrefs);
      }).collect(Collectors.toList());
    }

    final StanzaEntryIsObsolete isObsoleteEntry = this.<
        StanzaEntryIsObsolete>getCardinalityZeroOrOneEntry(stanza, StanzaEntryType.IS_OBSOLETE);
    final boolean obsolete = (isObsoleteEntry == null) ? false : isObsoleteEntry.getValue();

    final StanzaEntryCreatedBy createdByEntry =
        this.<StanzaEntryCreatedBy>getCardinalityZeroOrOneEntry(stanza, StanzaEntryType.CREATED_BY);
    final String createdBy = (createdByEntry == null) ? null : createdByEntry.getCreator();

    final StanzaEntryCreationDate creationDateEntry = this.<
        StanzaEntryCreationDate>getCardinalityZeroOrOneEntry(stanza, StanzaEntryType.CREATION_DATE);
    final String creationDateStr =
        (creationDateEntry == null) ? null : creationDateEntry.getValue();

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    Date creationDate = null;
    if (creationDateStr != null) {
      try {
        creationDate = format.parse(creationDateStr);
      } catch (ParseException e) {
        throw new OntoLibRuntimeException("Problem parsing date string " + creationDateStr, e);
      }
    }

    return new UphenoTerm(id, altTermIds, name, definition, comment, subsets, synonyms, obsolete,
        createdBy, creationDate);
  }

  /**
   * Extract cardinality one entry (=tag) of type <code>type</code> from <code>stanza</code>.
   *
   * @param stanza {@link Stanza} to get {@link StanzaEntry} from.
   * @param type {@link StanzaEntryType} to use.
   * @return Resulting {@link StanzaEntry}, properly cast.
   */
  @SuppressWarnings("unchecked")
  protected <E extends StanzaEntry> E getCardinalityOneEntry(Stanza stanza, StanzaEntryType type) {
    final List<StanzaEntry> typeEntries = stanza.getEntryByType().get(type);
    if (typeEntries == null) {
      throw new OntoLibRuntimeException(
          type + " tag must have cardinality 1 but was null (" + stanza + ")");
    } else if (typeEntries.size() != 1) {
      throw new OntoLibRuntimeException(type + " tag must have cardinality 1 but was "
          + typeEntries.size() + " (" + stanza + ")");
    }
    return (E) typeEntries.get(0);
  }

  /**
   * Extract cardinality zero or one entry (=tag) of type <code>type</code> from
   * <code>stanza</code>.
   *
   * @param stanza {@link Stanza} to get {@link StanzaEntry} from.
   * @param type {@link StanzaEntryType} to use.
   * @return Resulting {@link StanzaEntry}, properly cast, or <code>null</code>.
   */
  @SuppressWarnings("unchecked")
  protected <E extends StanzaEntry> E getCardinalityZeroOrOneEntry(Stanza stanza,
      StanzaEntryType type) {
    final List<StanzaEntry> typeEntries = stanza.getEntryByType().get(type);
    if (typeEntries == null) {
      return null;
    } else if (typeEntries.size() != 1) {
      throw new OntoLibRuntimeException(type + " tag must have cardinality <= 1 but was "
          + typeEntries.size() + " (" + stanza + ")");
    } else {
      return (E) typeEntries.get(0);
    }
  }

  @Override
  public UphenoTermRelation constructTermRelation(Stanza stanza, StanzaEntryIsA stanzaEntry) {
    final TermId sourceId =
        termIds.get(this.<StanzaEntryId>getCardinalityOneEntry(stanza, StanzaEntryType.ID).getId());
    final TermId destId = termIds.get(stanzaEntry.getId());
    return new UphenoTermRelation(sourceId, destId, nextRelationId++, UphenoRelationQualifier.IS_A);
  }

  @Override
  public UphenoTermRelation constructTermRelation(Stanza stanza,
      StanzaEntryDisjointFrom stanzaEntry) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UphenoTermRelation constructTermRelation(Stanza stanza, StanzaEntryUnionOf stanzaEntry) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UphenoTermRelation constructTermRelation(Stanza stanza,
      StanzaEntryIntersectionOf stanzaEntry) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UphenoTermRelation constructTermRelation(Stanza stanza,
      StanzaEntryRelationship stanzaEntry) {
    throw new UnsupportedOperationException();
  }

}
