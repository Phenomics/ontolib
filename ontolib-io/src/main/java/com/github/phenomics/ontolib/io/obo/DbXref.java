package com.github.phenomics.ontolib.io.obo;

/**
 * Representation of the <code>dbXref</code> from the OBO file.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class DbXref {

  /** The dbXref name. */
  private final String name;

  /** The dbXref description, <code>null</code> if missing. */
  private final String description;

  /**
   * The {@link TrailingModifier}, <code>null</code> if missing.
   */
  private final TrailingModifier trailingModifier;

  /**
   * Constructor.
   *
   * @param name The dbXref name.
   * @param description The dbXref description, <code>null</code> if missing.
   * @param trailingModifier The trailing modifier, <code>null</code> if missing.
   */
  public DbXref(String name, String description, TrailingModifier trailingModifier) {
    this.name = name;
    this.description = description;
    this.trailingModifier = trailingModifier;
  }

  /**
   * Query for the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Query for description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Query for trailing modifier.
   *
   * @return the trailingModifier
   */
  public TrailingModifier getTrailingModifier() {
    return trailingModifier;
  }

  @Override
  public String toString() {
    return "DbXref [name=" + name + ", description=" + description + ", trailingModifier="
        + trailingModifier + "]";
  }

}
