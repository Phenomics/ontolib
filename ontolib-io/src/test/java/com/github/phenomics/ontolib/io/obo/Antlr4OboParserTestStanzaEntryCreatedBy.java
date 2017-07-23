package com.github.phenomics.ontolib.io.obo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.phenomics.ontolib.io.obo.StanzaEntry;
import com.github.phenomics.ontolib.io.obo.StanzaEntryCreatedBy;
import com.github.phenomics.ontolib.io.obo.StanzaEntryType;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.KeyValueCreatedByContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.TermStanzaKeyValueContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.TypedefStanzaKeyValueContext;

public class Antlr4OboParserTestStanzaEntryCreatedBy extends Antlr4OboParserTestBase {

  @Test
  public void testParseNoModifierNoCommentAsTermKeyValue() {
    final String text = "created_by: creator\n";
    final Antlr4OboParser parser = buildParser(text);
    final TermStanzaKeyValueContext ctx = parser.termStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsTypedefKeyValue() {
    final String text = "created_by: creator\n";
    final Antlr4OboParser parser = buildParser(text);
    final TypedefStanzaKeyValueContext ctx = parser.typedefStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsKeyValueCreatedBy() {
    final String text = "created_by: creator\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueCreatedByContext ctx = parser.keyValueCreatedBy();
    final StanzaEntryCreatedBy stanzaEntry =
        (StanzaEntryCreatedBy) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertEquals("creator", stanzaEntry.getCreator());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseModifierNoCommentAsKeyValueCreatedBy() {
    final String text = "created_by: creator {key=value}\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueCreatedByContext ctx = parser.keyValueCreatedBy();
    final StanzaEntryCreatedBy stanzaEntry =
        (StanzaEntryCreatedBy) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertEquals("creator", stanzaEntry.getCreator());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierCommentAsKeyValueCreatedBy() {
    final String text = "created_by: creator ! comment\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueCreatedByContext ctx = parser.keyValueCreatedBy();
    final StanzaEntryCreatedBy stanzaEntry =
        (StanzaEntryCreatedBy) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertEquals("creator", stanzaEntry.getCreator());
    assertNull(stanzaEntry.getTrailingModifier());
    assertEquals("comment", stanzaEntry.getComment().toString());
  }

  @Test
  public void testParseModifierCommentAsKeyValueCreatedBy() {
    final String text = "created_by: creator {key=value} ! comment\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueCreatedByContext ctx = parser.keyValueCreatedBy();
    final StanzaEntryCreatedBy stanzaEntry =
        (StanzaEntryCreatedBy) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.CREATED_BY, stanzaEntry.getType());
    assertEquals("creator", stanzaEntry.getCreator());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertEquals("comment", stanzaEntry.getComment());
  }

}
