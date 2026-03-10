This repository contains a small prototype built to explore the project described in the JetBrains build process visualization internship.

## Build Process Dependency Prototype

The goal of the prototype is to explore how a change in one module can trigger rebuilds in other modules that depend on it. The prototype models modules as a dependency graph and identifies affected modules using graph traversal.

Example project structure:
```
main
 ├─ ui
 │   └─ app
 └─ auth
     └─ service
         └─ tests

metrics
 └─ analytics

```
## Implementation
The modules and dependencies are are modeled as a directed graph.  
A → B means "A depends on B"

For efficient traversal the prototype stores reverse dependencies:  
B → A


When a module changes:
All modules that depend on it (directly or indirectly) must also be rebuilt.

1. Mark the module as directly changed
2. Traverse the reverse dependency graph using Breadth First Search
3. For every discovered module:
   - mark it as affected
   - record the module that caused its rebuild
4. Continue until no new modules are discovered

This produces a list of affected modules and explanations for why each module is rebuilt.

Output contains:
- A list of affected modules
- Rebuild resons

Example output when "main" is changed:  
Affected modules:
- app
- tests
- ui
- auth
- service
- main
Rebuild reasons: 
- main is changed directly
- ui depends on main
- auth depends on main
- app depends on ui
- service depends on auth
- tests depends on service

## Project Structure

ModuleGraph.java
Stores module dependencies using a reverse dependency graph, includes graph helper methods.

BuildAnalyzer.java
Traverses the graph and determines affected modules via BFS.

Main.java
Demonstration via example module structure.


## Future improvements

Linked lists/sets ?? to make the output guraanteed in order?

- Use `LinkedHashMap` and `LinkedHashSet` to preserve deterministic output order.
- Add support for visualizing rebuild cascades as a dependency tree.
- Integrate build status tracking such as:
[compiling] service
[waiting]       app
[skipped]  database
