package com.github.phenomics.ontolib.ser;

import com.github.phenomics.ontolib.base.OntoLibException;

/**
 * Thrown on problems with data serialization and deserialization.
 *
 * @author <a href="mailto:manuel.holtgrewe@charite.de">Manuel Holtgrewe</a>
 */
public class SerializationException extends OntoLibException {

  /** Serial UID for serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   *
   * @param message Exception message.
   * @param cause Underlying {@link Throwable}.
   */
  public SerializationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor.
   *
   * @param message Exception message.
   */
  public SerializationException(String message) {
    super(message);
  }

}
