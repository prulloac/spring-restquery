![GitHub release (latest SemVer pre-release)](https://img.shields.io/github/v/release/prulloac/spring-restquery?include_prereleases&sort=semver)
[![Build Status](https://img.shields.io/travis/prulloac/spring-restquery/develop?logo=travis)](https://travis-ci.org/prulloac/spring-restquery)

[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=prulloac_spring-restquery&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=prulloac_spring-restquery)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=prulloac_spring-restquery&metric=code_smells)](https://sonarcloud.io/dashboard?id=prulloac_spring-restquery)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=prulloac_spring-restquery&metric=bugs)](https://sonarcloud.io/dashboard?id=prulloac_spring-restquery)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=prulloac_spring-restquery&metric=sqale_index)](https://sonarcloud.io/dashboard?id=prulloac_spring-restquery)

[![codecov](https://codecov.io/gh/prulloac/spring-restquery/branch/develop/graph/badge.svg)](https://codecov.io/gh/prulloac/spring-restquery)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=prulloac_spring-restquery&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=prulloac_spring-restquery)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8bc978a93b0544ec8511efe83c870fdc)](https://www.codacy.com/manual/pablo.ulloac/spring-restquery?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=prulloac/spring-restquery&amp;utm_campaign=Badge_Grade)

### RestQuery Specification

The RestQuery Specification creates Specification queries for any given entity.

Supported operations:
-   Logical operations: AND, OR, NOT

-   Comparison operations:
    -   Basic: =, <>, isNull, isNotNull, in 
    -   Numeric: <, >, <=, >=
    -   String: equals, like, startsWith, endsWith
    -   Date and Time: before, after, between
    
The implementation goes like this: 
-   A given string will be interpreted as a collection of _where_ clauses for a specific entity to be queried. (ie, "name = John and age > 20"). 

-   This creates a graph of predicates-to-be nodes. (Nodes that will later be translated into predicates for the specification).

-   The nodes will be translated to create a specification query.

-   In order to prevent any latency because of the query creation, queries will be stored in a cache 
for 10 minutes since last cache read. 

-   Queries can be identified in said cache by its entity.

-   Rest assured, only the query will be stored in the cache, not the results, so the results can be processed on-demand

### *RestQuerySpecificationRepository

TODO readme
