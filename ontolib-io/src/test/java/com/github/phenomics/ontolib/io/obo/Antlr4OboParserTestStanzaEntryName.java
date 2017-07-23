package com.github.phenomics.ontolib.io.obo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.phenomics.ontolib.io.obo.StanzaEntry;
import com.github.phenomics.ontolib.io.obo.StanzaEntryName;
import com.github.phenomics.ontolib.io.obo.StanzaEntryType;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.InstanceStanzaKeyValueContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.KeyValueNameContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.TermStanzaKeyValueContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboParser.TypedefStanzaKeyValueContext;

public class Antlr4OboParserTestStanzaEntryName extends Antlr4OboParserTestBase {

  @Test
  public void testParseNoModifierNoCommentAsTermKeyValue() {
    final String text = "name: Term Name\n";
    final Antlr4OboParser parser = buildParser(text);
    final TermStanzaKeyValueContext ctx = parser.termStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsInstanceKeyValue() {
    final String text = "name: Term Name\n";
    final Antlr4OboParser parser = buildParser(text);
    final InstanceStanzaKeyValueContext ctx = parser.instanceStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsTypedefKeyValue() {
    final String text = "name: Term Name\n";
    final Antlr4OboParser parser = buildParser(text);
    final TypedefStanzaKeyValueContext ctx = parser.typedefStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsKeyValueName() {
    final String text = "name: Term Name\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueNameContext ctx = parser.keyValueName();
    final StanzaEntryName stanzaEntry = (StanzaEntryName) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertEquals("Term Name", stanzaEntry.getName());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseModifierNoCommentAsKeyValueName() {
    final String text = "name: Term Name {key=value}\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueNameContext ctx = parser.keyValueName();
    final StanzaEntryName stanzaEntry = (StanzaEntryName) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertEquals("Term Name", stanzaEntry.getName());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierCommentAsKeyValueName() {
    final String text = "name: Term Name ! comment\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueNameContext ctx = parser.keyValueName();
    final StanzaEntryName stanzaEntry = (StanzaEntryName) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertEquals("Term Name", stanzaEntry.getName());
    assertNull(stanzaEntry.getTrailingModifier());
    assertEquals("comment", stanzaEntry.getComment().toString());
  }

  @Test
  public void testParseModifierCommentAsKeyValueName() {
    final String text = "name: Term Name {key=value} ! comment\n";
    final Antlr4OboParser parser = buildParser(text);
    final KeyValueNameContext ctx = parser.keyValueName();
    final StanzaEntryName stanzaEntry = (StanzaEntryName) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.NAME, stanzaEntry.getType());
    assertEquals("Term Name", stanzaEntry.getName());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertEquals("comment", stanzaEntry.getComment());
  }

}
