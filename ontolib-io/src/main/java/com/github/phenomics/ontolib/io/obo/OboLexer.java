package com.github.phenomics.ontolib.io.obo;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

import com.github.phenomics.ontolib.base.OntoLibRuntimeException;

import de.charite.compbio.ontolib.io.obo.parser.Antlr4OboLexer;

/**
 * Extends the generated Antlr4OboLexer to bail out at the first lexing error.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class OboLexer extends Antlr4OboLexer {

  public OboLexer(CharStream input) {
    super(input);
  }

  @Override
  public void recover(LexerNoViableAltException e) {
    throw new OntoLibRuntimeException("There was a problem with lexing OBO file.", e); // bail out
  }

}
