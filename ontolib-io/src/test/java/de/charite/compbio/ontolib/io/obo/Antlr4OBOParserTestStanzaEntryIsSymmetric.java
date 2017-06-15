package de.charite.compbio.ontolib.io.obo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.KeyValueIsSymmetricContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.TypedefStanzaKeyValueContext;

public class Antlr4OBOParserTestStanzaEntryIsSymmetric extends Antlr4OBOParserTestBase {

  @Test
  public void testParseNoModifierNoCommentAsTypedefKeyValue() {
    final String text = "is_symmetric: true\n";
    final Antlr4OBOParser parser = buildParser(text);
    final TypedefStanzaKeyValueContext ctx = parser.typedefStanzaKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_SYMMETRIC, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsKeyValueReplacedBy() {
    final String text = "is_symmetric: true\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsSymmetricContext ctx = parser.keyValueIsSymmetric();
    final StanzaEntryIsSymmetric stanzaEntry = (StanzaEntryIsSymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_SYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseModifierNoCommentAsKeyValueReplacedBy() {
    final String text = "is_symmetric: true {key=value}\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsSymmetricContext ctx = parser.keyValueIsSymmetric();
    final StanzaEntryIsSymmetric stanzaEntry = (StanzaEntryIsSymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_SYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierCommentAsKeyValueReplacedBy() {
    final String text = "is_symmetric: true ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsSymmetricContext ctx = parser.keyValueIsSymmetric();
    final StanzaEntryIsSymmetric stanzaEntry = (StanzaEntryIsSymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_SYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertEquals("comment", stanzaEntry.getComment().toString());
  }

  @Test
  public void testParseModifierCommentAsKeyValueReplacedBy() {
    final String text = "is_symmetric: true {key=value} ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueIsSymmetricContext ctx = parser.keyValueIsSymmetric();
    final StanzaEntryIsSymmetric stanzaEntry = (StanzaEntryIsSymmetric) getListener().getValue(ctx);

    assertEquals(StanzaEntryType.IS_SYMMETRIC, stanzaEntry.getType());
    assertEquals(true, stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertEquals("comment", stanzaEntry.getComment());
  }

}
