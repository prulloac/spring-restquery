#Spring-extras

 This repository contains my personal extras to be used among the spring framework

---

## Table of contents
1. [Springdata Extras](#springdata-extras)
2. [Versionable entities framework](#versionable-entities-framework)

---

## Springdata Extras

The idea behing this module is to provide more functionalities to the already robust springdata library.

### RestQuery Specification

The RestQuery Specification creates Specification querys for any given entity, using a fluent DSL which 
supports the following SQL operations:

- Logical operations: AND, OR, NOT
- Comparison operations:
    * Basic: =, <>, isNull, isNotNull 
    * Numeric: <, >, <=, >=
    * String: equals, like, startsWith, endsWith (all with corresponding ignoreCase cases)
    * Date and Time: TBD
    
The implementation is simply put like this: a given string will be interpreted as a collection of _where_ 
clauses for a specific entity to be queried (ie, "name = John and age > 20"). This creates a graph of 
predicates-to-be nodes (Nodes that will later be translated into predicates for the specification). In order 
to prevent any memory overhead or latency because of the query creation, queries will be stored in a cache 
for 10 minutes and each query can be easily identified for it's corresponding entity. (Rest assured, only 
the query will be stored in the cache, not the results, so the results can be processed on-demand)

---
## Versionable entities framework

The purpose is to 

### Purpose