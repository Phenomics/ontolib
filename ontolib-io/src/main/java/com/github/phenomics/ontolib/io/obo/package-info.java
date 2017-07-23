/**
 * Generic code for parsing OBO files.
 *
 * <p>
 * The facade classes here are {@link com.github.phenomics.ontolib.io.obo.OboParser} and
 * {@link com.github.phenomics.ontolib.io.obo.OboImmutableOntologyLoader} classes.
 * </p>
 *
 * <p>
 * {@link com.github.phenomics.ontolib.io.obo.OboParser} either generates a
 * {@link com.github.phenomics.ontolib.io.obo.OboFile} object with the full information from the OBO
 * file or you can use {@link com.github.phenomics.ontolib.io.obo.OboParseResultListener} to get the
 * header and all OBO stanzas as {@link com.github.phenomics.ontolib.io.obo.Header} and
 * {@link com.github.phenomics.ontolib.io.obo.Stanza} objects as they are loaded from the OBO file.
 * The first variant might be more convenient but requires storing the full OBO information in
 * memory while the second does not allow for simple loop-style processing but is potentially memory
 * saving.
 * </p>
 *
 * <p>
 * {@link com.github.phenomics.ontolib.io.obo.OboImmutableOntologyLoader} allows you to directly build
 * an {@link com.github.phenomics.ontolib.ontology.data.ImmutableOntology} from the stream of
 * {@link com.github.phenomics.ontolib.io.obo.Stanza} events. For each ontology to load (e.g., GO, HPO
 * etc.), there is a {@link com.github.phenomics.ontolib.io.obo.OboOntologyEntryFactory}
 * implementation that has to be passed into the loader for construction of the concrete
 * {@link com.github.phenomics.ontolib.ontology.data.Term} and
 * {@link com.github.phenomics.ontolib.ontology.data.TermRelation} implementations.
 * </p>
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
package com.github.phenomics.ontolib.io.obo;
