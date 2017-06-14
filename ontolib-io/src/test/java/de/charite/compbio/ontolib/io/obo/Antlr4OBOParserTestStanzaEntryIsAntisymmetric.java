package de.charite.compbio.ontolib.io.obo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.KeyValueIsAntisymmetricContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.TypedefStanzaKeyValueContext;

public class Antlr4OBOParserTestStanzaEntryIsAntisymmetric extends Antlr4OBOParserTestBase {

  @Test
  public void testParseNoModifierNoCommentAsTypedefKeyValue() {
    final String text = "is_antisymmetric: true\n";
    final Antlr4OBOParser parser = buildParser(text);
    final TypedefStanzaKeyValueContext ctx = parser.typedefStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_ANTISYMMETRIC, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsKeyValueIsAntisymmetric() {
    final String text = "is_antisymmetric: true\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsAntisymmetricContext ctx = parser.keyValueIsAntisymmetric();
    final StanzaEntryIsAntisymmetric stanzaEntry =
        (StanzaEntryIsAntisymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_ANTISYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseModifierNoCommentAsKeyValueIsAntisymmetric() {
    final String text = "is_antisymmetric: true {key=value}\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsAntisymmetricContext ctx = parser.keyValueIsAntisymmetric();
    final StanzaEntryIsAntisymmetric stanzaEntry =
        (StanzaEntryIsAntisymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_ANTISYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierCommentAsKeyValueIsAntisymmetric() {
    final String text = "is_antisymmetric: true ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsAntisymmetricContext ctx = parser.keyValueIsAntisymmetric();
    final StanzaEntryIsAntisymmetric stanzaEntry =
        (StanzaEntryIsAntisymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_ANTISYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertEquals("comment", stanzaEntry.getComment().toString());
  }

  @Test
  public void testParseModifierCommentAsKeyValueIsAntisymmetric() {
    final String text = "is_antisymmetric: true {key=value} ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsAntisymmetricContext ctx = parser.keyValueIsAntisymmetric();
    final StanzaEntryIsAntisymmetric stanzaEntry =
        (StanzaEntryIsAntisymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_ANTISYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertEquals("comment", stanzaEntry.getComment());
  }

}