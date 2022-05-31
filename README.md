# Ancient Roman Firefighters

This is a proof-of-concept application.
 * https://en.wikipedia.org/wiki/History_of_firefighting 
 * https://en.wikipedia.org/wiki/Vigiles

## Prompt

You have been tasked with organizing a firefighting force for Ancient Rome known as the Vigiles Urbani or “watchmen of the city”.

Optimize for overall distance traveled among all firefighters.

## Given

### City

The City is organized in a grid of squares where each square represents a Building and roads run between all of them. The size of the city can vary and the coordinates are 0-indexed. It knows its own dimensions and contains references to all buildings as well as FireDispatch.

### CityNode

CityNode is a class that allows us to reference a location in the city.

### Building

A Building knows its location, information about whether or not it’s burning, and can toggle its own burning status.

### FireStation

The FireStation is a special building. It is fireproof and has a random location in the city.

## To Do

### FireDispatch

FireDispatch is constructed with a reference to its city. It is responsible for initializing and dispatching the firefighters.
  
### Firefighter

A Firefighter knows their own location and how far they have traveled throughout their lifetime. In order for a fire to be put out, a Firefighter must be present.

## Running

If Maven is installed:

 Run `mvn compile test`. 

If Maven is not installed:

 Run `./mvnw compile test`.

All tests should complete successfully. 

Example output:

```console
$ mvn compile test
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.github.mckelvym:vigiles.urbani >-----------------
[INFO] Building vigiles.urbani 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ vigiles.urbani ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ vigiles.urbani ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ vigiles.urbani ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ vigiles.urbani ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ vigiles.urbani ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory vigiles.urbani/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:testCompile (default-testCompile) @ vigiles.urbani ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ vigiles.urbani ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.github.mckelvym.vigiles.urbani.scenarios.BasicScenariosTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.03 s - in com.github.mckelvym.vigiles.urbani.scenarios.BasicScenariosTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.878 s
[INFO] Finished at: 2022-05-30T22:42:58-04:00
[INFO] ------------------------------------------------------------------------
```