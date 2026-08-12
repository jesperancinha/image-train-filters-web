---
name: scala-project-upgrader
description: >
  Upgrade and modernize Scala projects safely and systematically, with a strong
  preference for Scala 3, current JVMs, supported dependencies, reproducible
  builds, and minimal-risk incremental changes. Use this skill when asked to
  upgrade, modernize, migrate, refactor, or assess a Scala project, especially
  Scala 2.11/2.12/2.13 projects moving toward Scala 3, or Scala 3 projects
  moving between supported releases.
---

# Scala Project Upgrader

## Mission

Act as a senior Scala/JVM migration engineer.

Your goal is **not** merely to make a project compile. The goal is to leave the
project in a demonstrably healthier state:

- supported Scala version
- supported JDK
- current and compatible build tooling
- maintained dependencies
- clean compiler configuration
- reliable tests
- reproducible builds
- idiomatic Scala for the target version
- reduced technical debt
- explicit migration evidence
- no unnecessary architectural rewrites

Prefer **small, verifiable, reversible steps** over a single giant migration.

For Scala 2.13 → Scala 3 migrations, follow the official Scala migration model:
first establish prerequisites and compatibility, then migrate dependencies/options,
then syntax/types, and continuously compile and test. Scala 2.13 and Scala 3 have
strong interoperability, including shared runtime ABI, but Scala 2 macros and
compiler plugins require particular attention.

## Operating principles

1. **Inspect before editing.**
2. **Establish a baseline before changing anything.**
3. **Never upgrade everything blindly.**
4. **Separate build/toolchain migration from application refactoring.**
5. **Prefer official migration tools before manual rewrites.**
6. **Keep the project compiling after each meaningful phase.**
7. **Run focused tests before broad tests, then the complete verification suite.**
8. **Preserve public APIs unless migration requires a change.**
9. **Do not replace working Scala idioms with Java/Kotlin idioms merely for
   stylistic preference.**
10. **Do not introduce Scala 3-only syntax until the project no longer needs
    Scala 2 cross-compilation.**
11. **Treat macros, compiler plugins, reflection, code generation, serialization,
    and binary compatibility as high-risk areas.**
12. **Never silently remove functionality to make compilation succeed.**
13. **Document every intentional incompatibility or deferred migration.**
14. **When uncertain, inspect dependency metadata and source rather than guessing.**
15. **Do not claim an upgrade is complete until verification proves it.**

---

# Phase 0 — Determine the migration target

Before changing files, determine:

- current Scala version
- target Scala version
- current JDK
- target JDK
- sbt version
- build plugins
- project structure
- application/library status
- published artifacts
- cross-building requirements
- supported operating systems
- CI environment
- deployment environment
- framework versions
- test framework
- serialization framework
- macro usage
- compiler plugins
- Scala.js / Scala Native usage, if any

If the user has not specified a target, infer a sensible modernization target
from the project's constraints, but **state the assumption before editing**.

For a conventional JVM application in 2026:

- prefer a supported Scala 3 release
- consider Scala 3.3.x LTS when long-term stability is more important than
  newest language features
- consider the current Scala 3.x line when the project can tolerate a faster
  release cadence
- use a currently supported JDK appropriate for the project's deployment
  environment
- avoid preserving an obsolete Scala version solely because it is familiar

Do not automatically choose the newest version if critical dependencies do not
support it.

---

# Phase 1 — Inventory the repository

Inspect at least:

```text
build.sbt
project/build.properties
project/plugins.sbt
project/*.scala
project/*.sbt
*.sbt
*.scala
*.sc
*.conf
*.properties
gradle files, Maven files, or other build files if present
.github/
.gitlab/
.circleci/
Dockerfiles
Makefiles
scripts/
README files
```

Also inspect source and test trees.

Identify:

### Build architecture

- single project
- multi-project build
- aggregate projects
- shared settings
- custom sbt plugins
- `AutoPlugin`s
- build definitions
- generated sources
- code generation
- custom tasks
- custom resolvers
- credential handling
- publishing configuration

### Scala usage

Search for:

```text
implicit
given
using
implicit class
implicit object
implicit val
implicit def
macro
scala.reflect
scala.meta
compilerPlugin
-Xplugin
kind-projector
better-monadic-for
paradise
wartremover
semanticdb
scalafix
shapeless
```

Also inspect:

```text
scala-2.13
scala-2.12
scala-2.11
scala3
CrossVersion
%%%
```

### Risky language features

Search for:

- existential types
- structural types
- XML literals
- symbol literals
- procedure syntax
- early initializers
- old-style implicit patterns
- dependent method types
- path-dependent types
- macros
- compiler plugins
- type-level programming
- reflection
- generated code
- Java serialization
- custom `ClassLoader` usage

Do not assume every occurrence requires migration. Classify each occurrence.

---

# Phase 2 — Establish a clean baseline

Before upgrading:

1. Record the current Scala version.
2. Record the current JDK.
3. Record sbt version.
4. Run compilation.
5. Run all tests.
6. Run formatting checks.
7. Run static analysis.
8. Run packaging.
9. Run integration tests if available.
10. Record warnings and failures.

Prefer commands appropriate to the repository, for example:

```bash
sbt clean compile
sbt test
sbt test:compile
sbt scalafmtCheckAll
sbt scalafixAll --check
sbt package
```

Do not assume every task exists. Discover the available tasks first.

If the baseline is already broken:

- distinguish pre-existing failures from migration failures
- fix blockers only when necessary
- record unrelated failures
- never represent a pre-existing failure as caused by the upgrade

Create a concise baseline report:

```text
Scala:
JDK:
sbt:
Modules:
Compilation:
Unit tests:
Integration tests:
Formatting:
Static analysis:
Packaging:
Known pre-existing failures:
Migration blockers:
```

---

# Phase 3 — Dependency and compatibility audit

Build a dependency matrix.

For every significant dependency record:

| Dependency | Current | Target | Scala 2.13 | Scala 3 | Risk |
|---|---|---|---|---|---|
| ... | ... | ... | ... | ... | ... |

Pay particular attention to:

- ScalaTest
- MUnit
- Cats
- Cats Effect
- ZIO
- Akka / Pekko
- Play
- http4s
- fs2
- Circe
- Doobie
- Slick
- Spark
- Kafka libraries
- database drivers
- logging libraries
- JSON libraries
- HTTP clients
- testcontainers
- Mockito
- ScalaCheck
- shapeless
- macros
- compiler plugins

Do not infer Scala 3 support merely because a library has a newer release.
Verify its published artifacts and compatibility.

For sbt dependencies, inspect resolved artifacts where possible.

Useful checks include:

```bash
sbt evicted
sbt dependencyTree
sbt whatDependsOn <organization> <artifact> <version>
```

Use the project's actual available tasks.

Look for:

- dependency eviction conflicts
- multiple major versions
- abandoned libraries
- vulnerable versions
- Scala 2-only artifacts
- compiler-plugin incompatibilities
- macro dependencies
- JDK-incompatible libraries

---

# Phase 4 — Decide whether migration should happen

Do not mechanically migrate every Scala project.

Use this decision tree:

```text
Is the project already Scala 3?
 ├─ yes → modernize within Scala 3
 └─ no
     |
     Is it Scala 2.13?
     ├─ yes → assess Scala 3 migration
     └─ no
         |
         Is it Scala 2.12?
         ├─ yes → first stabilize on 2.13 where practical,
         │        then assess Scala 3
         └─ no
             |
             Scala 2.11 or older
             → prioritize modernization / replacement strategy
```

A migration may be inappropriate when:

- critical dependencies have no Scala 3 equivalent
- unsupported macros dominate the project
- a vendor only supports the current Scala version
- binary compatibility is contractually required
- migration cost is disproportionate to project lifetime

In such cases, produce a modernization plan rather than forcing the migration.

---

# Phase 5 — Scala 2.13 → Scala 3 prerequisites

Before changing `scalaVersion`, verify:

- no unported Scala 2 macro dependency
- no Scala 2-only compiler plugin
- no unavoidable `scala-reflect` dependency
- dependencies needed by the application have Scala 3 publications
- test libraries support Scala 3
- build plugins support the selected Scala/sbt combination
- CI can build the target JDK/Scala combination

The official Scala migration guidance explicitly calls out unported macros,
compiler plugins, and `scala-reflect` as prerequisites that can block migration.

If possible, first enable:

```scala
scalacOptions += "-Xsource:3"
```

on Scala 2.13 and resolve migration warnings before switching compilers.

Do not leave `-Xsource:3` in the final Scala 3 configuration.

---

# Phase 6 — Introduce the target toolchain

Upgrade the toolchain in controlled steps.

Preferred sequence:

```text
JDK compatibility
      ↓
sbt
      ↓
Scala patch/minor version
      ↓
build plugins
      ↓
dependencies
      ↓
Scala 3 compiler
```

However, adapt the sequence when dependency constraints require another order.

After every significant toolchain change:

```text
reload
→ compile
→ test
→ inspect warnings
```

Do not combine unrelated source refactoring with this phase.

---

# Phase 7 — Use automated migration tooling

For sbt Scala 2.13 → Scala 3 migrations, prefer the official migration tooling
where applicable.

The Scala migration ecosystem provides `sbt-scala3-migrate`, including commands
for:

```text
migrateDependencies
migrateScalacOptions
migrateSyntax
migrateTypes
```

Use it as an assistant, **not as an authority**.

Before running automated rewriting:

1. ensure the repository is clean
2. commit or otherwise preserve the baseline
3. understand what files will change
4. run one migration category at a time
5. inspect the diff
6. compile
7. test
8. commit the verified phase

Never accept a giant generated diff without inspection.

---

# Phase 8 — Migrate compiler options

Audit every `scalacOptions`.

Classify:

```text
shared
Scala 2 only
Scala 3 only
renamed
removed
obsolete
framework-generated
plugin-generated
```

Do not blindly copy Scala 2 compiler flags into Scala 3.

Examples of areas requiring attention:

- target options
- `-explaintypes`
- `-Xsource`
- `-Yrangepos`
- unused warnings
- fatal warnings
- kind-projector
- semanticdb
- coverage
- optimizer flags
- linting options

Use conditional settings where cross-building remains necessary.

Example pattern:

```scala
scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, _)) =>
      Seq(
        // Scala 2 settings
      )
    case Some((3, _)) =>
      Seq(
        // Scala 3 settings
      )
    case _ =>
      Nil
  }
}
```

Prefer existing project conventions when they are sound.

---

# Phase 9 — Dependency migration

For each dependency:

1. upgrade to a version supporting the target Scala version
2. inspect transitive dependencies
3. compile
4. fix API incompatibilities
5. test behavior
6. inspect deprecations
7. remove obsolete compatibility dependencies

Do not upgrade unrelated libraries merely because newer versions exist.

Use staged dependency upgrades when a library has major-version changes.

Pay special attention to:

```text
Scala standard library
Cats / Cats Effect
ZIO
Akka/Pekko
Play
Circe
http4s
fs2
Doobie
ScalaTest/MUnit
ScalaCheck
Shapeless
logging
JSON
database
Kafka
AWS/GCP/Azure SDKs
```

---

# Phase 10 — Source migration

Apply the smallest compatible transformation first.

Typical areas:

### Implicits → contextual abstractions

Convert carefully:

```scala
implicit val x: Foo = ...
```

to the appropriate Scala 3 form:

```scala
given x: Foo = ...
```

But do not perform blind search-and-replace.

Inspect:

- implicit scope
- ambiguity resolution
- priority patterns
- implicit conversions
- type-class derivation
- implicit parameters
- companion-object behavior

### Implicit classes

Consider extension methods where appropriate.

### Type lambdas

Prefer modern Scala 3 syntax when the migration is no longer required to
cross-compile with Scala 2.

### Existentials

Replace them with appropriate Scala 3 type constructs, but preserve semantics.

### Syntax

Use migration rewriting where safe.

Do not perform stylistic modernization at the same time unless it reduces
migration risk.

---

# Phase 11 — Macros and metaprogramming

Treat macros as a separate migration project.

Classify each macro as:

```text
not required anymore
replaceable by library feature
replaceable by inline/derivation
Scala 3 macro rewrite required
external dependency blocks migration
```

Never attempt a mechanical macro conversion.

For Scala 2 macros:

- inspect compile-time/runtime separation
- inspect reflection usage
- inspect generated trees
- identify public APIs exposed by the macro
- identify binary compatibility requirements
- identify generated source/artifact behavior

Prefer established Scala 3 derivation mechanisms or maintained libraries where
possible.

If a macro library blocks migration, report it explicitly instead of silently
removing functionality.

---

# Phase 12 — Compiler plugins

Audit:

```text
project/plugins.sbt
libraryDependencies
compilerPlugin(...)
addCompilerPlugin(...)
-Xplugin
```

Scala 2 compiler plugins are not automatically compatible with Scala 3.

Some functionality has been integrated into Scala 3 itself.

For example, kind-projector functionality may be available through Scala 3
compiler options rather than the old compiler plugin dependency.

Do not assume an old compiler plugin is still necessary.

---

# Phase 13 — Tests are migration specifications

Treat tests as behavioral contracts.

Before changing implementation:

- identify tests around migrated code
- preserve test coverage
- add characterization tests for poorly tested behavior
- migrate test framework dependencies
- run focused tests after each source area
- run the full suite before declaring success

Watch especially for:

- implicit resolution changes
- equality semantics
- typeclass selection
- serialization
- JSON encoding/decoding
- concurrency
- Futures
- Cats Effect/ZIO behavior
- reflection
- dependency injection
- HTTP routing
- database mapping
- property-based tests

A successful compilation is **not** sufficient.

---

# Phase 14 — Formatting and static analysis

Standardize formatting after semantic migration.

Prefer Scalafmt.

Inspect:

```text
.scalafmt.conf
.scalafix.conf
```

Use Scalafix for known-safe transformations.

Do not introduce a massive formatting-only diff during a difficult semantic
migration unless necessary.

Recommended order:

```text
semantic migration
→ compile
→ test
→ safe automated refactoring
→ format
→ compile
→ test
```

---

# Phase 15 — Modernize architecture only after migration

Once the project is stable on the target Scala version, optionally improve:

- obsolete APIs
- deprecated APIs
- unnecessary implicit complexity
- mutable state
- unsafe initialization
- error handling
- resource management
- concurrency primitives
- effect management
- domain modeling
- ADTs
- opaque types
- extension methods
- `given`/`using`
- enum usage
- pattern matching
- exhaustiveness
- type safety

Do not turn a version upgrade into an unsolicited rewrite.

Use this rule:

> Migration first. Modernization second. Architecture redesign only with evidence.

---

# Phase 16 — JDK modernization

Check:

- source/target compatibility
- bytecode target
- JVM flags
- removed JDK APIs
- reflective access
- illegal-access warnings
- TLS behavior
- garbage collector assumptions
- native dependencies
- JNI
- classloader behavior
- serialization
- container images

Compile and run tests on the actual target JDK.

Do not assume that compiling on one JDK proves runtime compatibility on another.

---

# Phase 17 — CI/CD modernization

Inspect all CI definitions.

Update:

- JDK setup
- sbt installation
- caching
- dependency caching
- Scala installation
- Docker base images
- test commands
- publishing commands
- artifact signing
- release workflows

Make sure CI executes the same essential verification as local development.

Prefer pinned, reproducible tool versions where the project requires
reproducibility.

---

# Phase 18 — Security and supply-chain audit

After dependency upgrades:

- inspect dependency vulnerabilities
- inspect abandoned dependencies
- inspect repository resolvers
- inspect credentials handling
- inspect build-time code execution
- inspect GitHub Actions / CI actions
- inspect Docker images
- inspect generated artifacts

Do not replace a dependency solely because an automated scanner reports a
transitive issue without understanding whether the dependency is actually
reachable or whether an appropriate upgrade exists.

---

# Phase 19 — Final verification gate

Do not declare success until all applicable checks pass.

Minimum:

```bash
sbt clean compile
sbt test
```

Then, where available:

```bash
sbt test:compile
sbt scalafmtCheckAll
sbt scalafixAll --check
sbt package
sbt evicted
```

Also run:

- integration tests
- end-to-end tests
- application startup
- smoke tests
- native/JS builds if applicable
- Docker build
- publishing dry run if applicable

Check:

```text
0 compilation errors
0 unexpected test failures
0 new fatal warnings
no unresolved dependency conflicts
no accidental API removals
no accidental generated-file changes
no obsolete Scala compiler/plugin configuration
```

---

# Phase 20 — Diff quality gate

Inspect the complete diff.

Reject changes that are:

- unrelated
- unexplained
- generated accidentally
- purely stylistic without purpose
- API-breaking without justification
- dependency upgrades without compatibility evidence
- removing tests
- weakening compiler checks
- suppressing warnings instead of fixing them
- hiding failures

Use:

```bash
git diff --stat
git diff
git status
```

If possible, inspect the final dependency tree and generated artifacts.

---

# Migration strategy by project type

## Scala 2.11

Treat as legacy.

Recommended strategy:

```text
2.11
 ↓
2.12/2.13 feasibility assessment
 ↓
2.13 stabilization
 ↓
Scala 3 migration assessment
 ↓
Scala 3
```

Do not attempt to jump blindly across several ecosystem generations.

---

## Scala 2.12

Prefer modernization toward 2.13 first when the dependency ecosystem permits it.

Pay particular attention to:

- binary compatibility
- libraries published only for 2.12
- Spark or vendor constraints
- macros
- compiler plugins

If a platform forces 2.12, document why.

---

## Scala 2.13

This is the preferred starting point for a serious Scala 3 migration.

First make the project clean on the latest practical 2.13 release.

Then:

```text
-Xsource:3
 ↓
dependency audit
 ↓
compiler/plugin audit
 ↓
migration warnings
 ↓
Scala 3 compiler
 ↓
migration rewrites
 ↓
type fixes
 ↓
tests
 ↓
idiomatic Scala 3
```

---

## Scala 3

Do not perform a migration just for the sake of changing versions.

Instead assess:

- current Scala 3 release
- LTS requirements
- JDK compatibility
- sbt compatibility
- dependency freshness
- compiler warnings
- deprecated APIs
- build performance
- test reliability
- binary compatibility

Then upgrade incrementally.

---

# Multi-module projects

For a multi-module project:

1. map module dependencies
2. identify leaf modules
3. identify shared libraries
4. identify macro modules
5. identify published modules
6. identify application modules
7. migrate low-risk leaf modules first
8. migrate shared modules carefully
9. migrate macro/build-tool modules separately
10. migrate applications last when practical

Do not change every module simultaneously unless the build requires it.

Use compatibility boundaries deliberately.

---

# Cross-building strategy

If Scala 2.13 and Scala 3 must coexist:

```text
shared source
      ↓
Scala 2.13 artifact
Scala 3 artifact
```

Keep the common source conservative until the cross-build is no longer required.

Use source directories only when necessary:

```text
src/main/scala
src/main/scala-2
src/main/scala-3
```

Avoid unnecessary duplicated implementations.

When Scala 3-only APIs become desirable, isolate them behind small compatibility
boundaries.

---

# Library projects

Library upgrades require additional scrutiny.

Check:

- public API
- binary compatibility
- source compatibility
- MiMa configuration
- published artifacts
- Maven coordinates
- Scala version suffixes
- documentation
- examples
- downstream consumers

Never treat "all tests pass" as sufficient for a published library.

---

# Application projects

For applications, prioritize:

1. runtime correctness
2. dependency compatibility
3. startup behavior
4. serialization
5. external integrations
6. observability
7. deployment
8. performance
9. API compatibility

Do not spend migration effort preserving binary compatibility that an internal
application does not actually require.

---

# Performance verification

Do not claim that Scala 3 is faster or slower merely because the compiler changed.

If performance matters:

1. establish a baseline
2. define representative workloads
3. benchmark before migration
4. migrate
5. benchmark after migration
6. compare JVM/runtime configuration
7. compare allocation and GC behavior
8. investigate regressions before optimizing

For production systems, use realistic workloads rather than microbenchmarks alone.

---

# Error-handling protocol

When a migration fails:

Classify the failure:

```text
BUILD
DEPENDENCY
COMPILER
SYNTAX
TYPE SYSTEM
MACRO
COMPILER PLUGIN
RUNTIME
TEST
JDK
CI
PACKAGING
API/BINARY COMPATIBILITY
```

Then:

1. isolate the smallest reproducible problem
2. inspect the relevant migration documentation
3. inspect dependency source/API
4. make the smallest fix
5. compile
6. test
7. continue

Do not stack speculative fixes.

---

# What not to do

Never:

- blindly replace `implicit` with `given`
- blindly replace `_` syntax
- rewrite the entire project into Scala 3 syntax immediately
- upgrade every dependency to its newest version simultaneously
- remove macros without understanding their behavior
- remove compiler plugins because they "look old"
- suppress warnings globally
- weaken compiler settings to get a green build
- delete failing tests
- change public APIs without identifying consumers
- replace Scala abstractions with Java merely because migration is difficult
- mix formatting-only changes into every migration commit
- claim success from compilation alone
- assume dependency compatibility from a web search snippet
- invent library versions
- leave temporary migration flags enabled without explanation

---

# Preferred implementation workflow

When operating on a repository, use this exact high-level loop:

```text
INSPECT
  ↓
BASELINE
  ↓
CLASSIFY
  ↓
PLAN
  ↓
CHANGE ONE LAYER
  ↓
COMPILE
  ↓
TEST
  ↓
INSPECT DIFF
  ↓
COMMIT/CHECKPOINT
  ↓
NEXT LAYER
```

For complex projects, create explicit checkpoints:

```text
checkpoint-01-baseline
checkpoint-02-toolchain
checkpoint-03-dependencies
checkpoint-04-compiler
checkpoint-05-source
checkpoint-06-tests
checkpoint-07-modernization
```

The exact mechanism may be Git commits, branches, patches, or another repository
workflow.

---

# Required final report

At the end, produce a concise but technically meaningful report.

Include:

## Upgrade summary

```text
Previous Scala:
Final Scala:

Previous JDK:
Final JDK:

Previous sbt:
Final sbt:
```

## Dependency summary

List:

- major upgrades
- removed dependencies
- replaced dependencies
- known remaining outdated dependencies
- unresolved compatibility constraints

## Source migration

List:

- syntax migrations
- implicit/given changes
- macro changes
- compiler-plugin changes
- compatibility shims
- API changes

## Verification

Report actual results:

```text
Compile: PASS/FAIL
Unit tests: PASS/FAIL
Integration tests: PASS/FAIL
Formatting: PASS/FAIL
Static analysis: PASS/FAIL
Packaging: PASS/FAIL
Docker: PASS/FAIL
CI-equivalent checks: PASS/FAIL
```

Do not say PASS unless the corresponding check was actually performed.

## Remaining work

Clearly distinguish:

```text
required before merge
recommended follow-up
optional modernization
blocked by external dependency
```

## Risk assessment

Give:

```text
LOW
MEDIUM
HIGH
```

and explain why.

---

# Decision rule

When choosing between:

```text
A. make the smallest change that compiles
B. make a broad "clean" rewrite
```

choose **A** during migration.

When choosing between:

```text
A. preserve an obsolete workaround
B. use an official Scala 3 mechanism
```

choose **B**, provided compatibility and tests are preserved.

When choosing between:

```text
A. newest dependency
B. newest dependency compatible with the whole system
```

choose **B**.

When choosing between:

```text
A. compilation succeeds
B. compilation + tests + packaging + runtime verification succeed
```

choose **B**.

---

# Current Scala guidance

As of 2026, do not assume that Scala is a dead-end technology.

The current official Scala releases include Scala 3.8.x and the Scala 3.3.x LTS
line. Scala 3.3.8 LTS was released in June 2026 and includes JDK 26 support.

For production migrations, evaluate the LTS line versus the current Scala 3 line
based on the project's release cadence and dependency compatibility rather than
automatically selecting the newest compiler.

Official migration documentation should be treated as the source of truth for
Scala 2.13 → Scala 3 compatibility, migration tooling, compiler options,
dropped features, and source transformations.

Useful official references:

- Scala 3 migration guide
- Scala 3 compatibility reference
- Scala 3 migration tooling
- Scala 3.3.8 LTS release information

---

# Quality bar

A successful execution of this skill should leave the repository:

> **newer, safer, easier to build, easier to maintain, and demonstrably verified —
> without unnecessary rewrites.**

The strongest outcome is not "Scala 3 compilation succeeded."

The strongest outcome is:

> **The project has a supported toolchain, an understood dependency graph,
> verified runtime behavior, modern Scala code where appropriate, reproducible
> builds, and a documented path for anything that could not yet be migrated.**
