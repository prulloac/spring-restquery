[![Build Status](https://travis-ci.com/prulloac/spring-extras.svg?branch=develop)](https://travis-ci.com/prulloac/spring-extras)
[![codecov](https://codecov.io/gh/prulloac/spring-extras/branch/develop/graph/badge.svg)](https://codecov.io/gh/prulloac/spring-extras)
![GitHub release (latest SemVer pre-release)](https://img.shields.io/github/v/release/prulloac/spring-extras?include_prereleases&sort=semver)



 This repository contains my personal extras to be used among the spring framework

---

## Table of contents
1. [Springdata Extras](#springdata-extras)
2. [Versionable entities framework](#versionable-entities-framework)

---

## Springdata Extras

The idea of this module is to provide more functionalities to the already robust spring-data library.

### RestQuery Specification

The RestQuery Specification creates Specification queries for any given entity.

Supported operations:
- Logical operations: AND, OR, NOT
- Comparison operations:
    * Basic: =, <>, isNull, isNotNull, in 
    * Numeric: <, >, <=, >=
    * String: equals, like, startsWith, endsWith
    * Date and Time: before, after, between
    
The implementation goes like this: 
* A given string will be interpreted as a collection of _where_ clauses for a specific entity to be queried. (ie, "name = John and age > 20"). 
* This creates a graph of predicates-to-be nodes. (Nodes that will later be translated into predicates for the specification).
* The nodes will be translated to create a specification query.
* In order to prevent any latency because of the query creation, queries will be stored in a cache 
for 10 minutes since last cache read. 
* Queries can be identified in said cache by its entity.
* Rest assured, only the query will be stored in the cache, not the results, so the results can be processed on-demand

### *RestQuerySpecificationRepository

TODO readme

---
## Versionable entities framework

TODO readme 

