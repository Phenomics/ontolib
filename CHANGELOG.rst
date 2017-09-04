=========
Changelog
=========

-----------------------
v0.3 (work in progress)
-----------------------

- Refactoring the code for object score I/O into ``ontolib-io`` package.
- Adding support for score distribution reading and writing to H2 database files.
- ``Ontology.getAncestorTermIds()`` now also resolves alternative term IDs.
- Fixing dependency on slf4j components in ``ontolib-core`` and ``ontolib-io``.
- Adding ``getPrimaryTermId()`` in ``Ontology``.

----
v0.2
----

- Making date parser for HPO annotation files more robust.
  It works now for positive and negative associations.
- Small bug fix in HPO OBO parser.
- Adding ``ontolib-cli`` package that allows score distribution precomputation from the command line.
- Removed some dead code.
- Added various tests, minor internal refactoring.
- Moved ``OntologyTerms`` into ``ontology.algo`` package.

----
v0.1
----

- Everything is new.
