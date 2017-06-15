package de.charite.compbio.ontolib.io.obo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.HeaderKeyValueContext;
import de.charite.compbio.ontolib.io.obo.parser.Antlr4OBOParser.KeyValueFormatVersionContext;

public class Antlr4OBOParserTestStanzaEntryFormatVersion extends Antlr4OBOParserTestBase {

  @Test
  public void testParseNoModifierNoCommentAsHeaderKeyValue() {
    final String text = "format-version: 1.2\n";
    final Antlr4OBOParser parser = buildParser(text);
    final HeaderKeyValueContext ctx = parser.headerKeyValue();
    final StanzaEntry stanzaEntry = (StanzaEntry) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.FORMAT_VERSION, stanzaEntry.getType());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierNoCommentAsKeyValueFormatVersion() {
    final String text = "format-version: 1.2\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueFormatVersionContext ctx = parser.keyValueFormatVersion();
    final StanzaEntryFormatVersion stanzaEntry =
        (StanzaEntryFormatVersion) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.FORMAT_VERSION, stanzaEntry.getType());
    assertEquals("1.2", stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseModifierNoCommentAsKeyValueFormatVersion() {
    final String text = "format-version: 1.2 {key=value}\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueFormatVersionContext ctx = parser.keyValueFormatVersion();
    final StanzaEntryFormatVersion stanzaEntry =
        (StanzaEntryFormatVersion) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.FORMAT_VERSION, stanzaEntry.getType());
    assertEquals("1.2", stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertNull(stanzaEntry.getComment());
  }

  @Test
  public void testParseNoModifierCommentAsKeyValueFormatVersion() {
    final String text = "format-version: 1.2 ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueFormatVersionContext ctx = parser.keyValueFormatVersion();
    final StanzaEntryFormatVersion stanzaEntry =
        (StanzaEntryFormatVersion) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.FORMAT_VERSION, stanzaEntry.getType());
    assertEquals("1.2", stanzaEntry.getValue());
    assertNull(stanzaEntry.getTrailingModifier());
    assertEquals("comment", stanzaEntry.getComment().toString());
  }

  @Test
  public void testParseModifierCommentAsKeyValueFormatVersion() {
    final String text = "format-version: 1.2 {key=value} ! comment\n";
    final Antlr4OBOParser parser = buildParser(text);
    final KeyValueFormatVersionContext ctx = parser.keyValueFormatVersion();
    final StanzaEntryFormatVersion stanzaEntry =
        (StanzaEntryFormatVersion) getOuterListener().getValue(ctx);

    assertEquals(StanzaEntryType.FORMAT_VERSION, stanzaEntry.getType());
    assertEquals("1.2", stanzaEntry.getValue());
    assertEquals("TrailingModifier [keyValue=[KeyValue [key=key, value=value]]]",
        stanzaEntry.getTrailingModifier().toString());
    assertEquals("comment", stanzaEntry.getComment());
  }

}
