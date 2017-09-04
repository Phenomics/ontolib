package com.github.phenomics.ontolib.ontology.data;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Implementation of immutable {@link Dbxref}s.
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class ImmutableDbxref implements Dbxref {

  /** Name of the dbxref. */
  private final String name;

  /** Description of the dbxref. */
  private final String description;

  /** Trailing modifier information. */
  private final ImmutableMap<String, String> trailingModifiers;

  /**
   * Construct with the given values.
   *
   * @param name Name of the dbxref.
   * @param description Description of the dbxref, {@code null} if not given.
   * @param trailingModifiers Trailing modifiers {@code null} if not given.
   */
  public ImmutableDbxref(String name, String description, Map<String, String> trailingModifiers) {
    this.name = name;
    this.description = description;
    if (trailingModifiers != null) {
      this.trailingModifiers = ImmutableMap.copyOf(trailingModifiers);
    } else {
      this.trailingModifiers = null;
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Map<String, String> getTrailingModifiers() {
    return trailingModifiers;
  }

  @Override
  public String toString() {
    return "ImmutableDbxref [name=" + name + ", description=" + description + ", trailingModifiers="
        + trailingModifiers + "]";
  }

}
