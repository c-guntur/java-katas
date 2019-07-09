# java-optional

# Java Optional API - A Code kata

<img align="center" src="docs/DukeOptional.png" alt="Java Duke Optional Logo" title="Java Optional" hspace="20" height="420"/>

## Table of Contents
* [What is a Code Kata](#WhatIsACodeKata)
  * [How does one go about with this kata?](#HowToSolveKata)
  * [Mission](#Mission)
  * [Requirements](#Requirements)
* [Project Structure](#ProjectStructure)
* [Java Time API](#JavaTime)
* [Solutions to the Katas](#Solutions)
* [Take Away](#TakeAway)

Quite often, we have to test if a field or variable is `null` or has a value. 
Conditional checks in traditional java relied on an if condition. 
Failure to check and assumption of existence of a value can lead to the 
ever-so-popular `NullPointerException`.

Sir Charles Anthony Richard Hoare (Tony Hoare), creator of the *null reference* 
rued the creation of null in 2009:

>I call it my billion-dollar mistake. It was the invention of the null reference in 1965. 
At that time, I was designing the first comprehensive type system for references in an 
object oriented language (ALGOL W). My goal was to ensure that all use of references 
should be absolutely safe, with checking performed automatically by the compiler. 
But I couldn't resist the temptation to put in a null reference, simply because it was 
so easy to implement. This has led to innumerable errors, vulnerabilities, and 
system crashes, which have probably caused a billion dollars of pain and damage in 
the last forty years.

Optional was added in Java 8 as a possible solution to errant null issues. An Optional wrapper
over a field or variable ensures there is a check or a deliberate attempt to get an value from 
the wrapper. The kata will help us better understand the features of `java.util.Optional`

## <a name="WhatIsACodeKata"></a>What is a Code-Kata

A code-kata is a coding exercise that builds muscle memory by a practice of programming to arrive 
at a known solution.

### <a name="HowToSolveKata"></a>How does one go about with this code kata?

The essence of the exercise (presentation material and code kata) is to demonstrate the  
usage patterns for dates and times.

This set of code katas rely on fixing broken tests. The tests may have multiple solutions, the 
intent is to learn and experiment. 

The project contains several JUnit tests that fail as `org.opentest4j.AssertionFailedError`. 

<span style=“color:green;”>**Here are simple steps to use this kata**</span>:

1. Run the test class(es).
1. One or more tests will fail with the test failure message.
1. Fix the failing tests by taking hints from the `TODO`s.
1. Repeat above steps until all tests pass.
1. Check the solutions to see if there are other ways to solve. 
(Remember, the solution may be less performant than yours)
1. Rinse and repeat (delete and checkout again, then back to Step 1) to build muscle memory.

### <a name="Mission"></a>Mission
> ***Your mission**, should you choose to accept it, will be to fix the JUnit tests. This 
message will self-destruct in* **NaN** *minutes*

### <a name="Requirements"></a>Requirements
How to prepare for coding along

This kata is developed as a Java maven project. Ensure that you have:

1. Apache Maven 3.3.x or above. _Tested with Apache Maven 3.5.0_.
    Link: https://maven.apache.org/download.cgi

1. JDK 11. _Tested with OpenJDK 11_
    Link: http://jdk.java.net/11/

1. Your favorite Java IDE. _IntelliJ IDEA Ultimate was used to develop this kata_.
 
## <a name="ProjectStructure"></a>Project Structure

The structure of the project:
```
|____pom.xml
|____README.md
|
|____src
| |
| |____test                    <------------------- Kata Tests
| | |____java
| |   |____none
| |     |____cvg
| |       |____optional
| |
| |____solutions               <------------------- Solutions 
| | |____java
| |   |____none
| |     |____cvg
| |       |____optional
```

## <a name="JavaTime"></a>Java Time API

The JUnit tests listed below are setup to utilize the Java Optional API features.

### Tests Included

#### Java Optional

1. ##### [Test1OptionalCreationAndFetchingValues](src/test/java/none/cvg/optional/Test1OptionalCreationAndFetchingValues.java)

   The tests in this class show creation of and fetching value from an Optional. 

1. ##### [Test2OptionalConditionalFetching.java](src/test/java/none/cvg/optional/Test2OptionalConditionalFetching.java) 

   The tests in this class show conditional checks and alternate actions when fetching values from an Optional. 

1. ##### [Test3StreamsAndOptionals.java](src/test/java/none/cvg/optional/Test3StreamsAndOptionals.java)

   The tests in this class show the usage of Optional in Java Stream API. 

      
## <a name="Solutions"></a>Solutions

Solutions for each test:

Kata Test | Solution
------------ | -------------
[Test1OptionalCreationAndFetchingValues](src/test/java/none/cvg/optional/Test1OptionalCreationAndFetchingValues.java) | [STest1OptionalCreationAndFetchingValues.java](src/solutions/java/none/cvg/optional/STest1OptionalCreationAndFetchingValues.java)
[Test2OptionalConditionalFetching](src/test/java/none/cvg/optional/Test2OptionalConditionalFetching.java) | [STest2OptionalConditionalFetching.java](src/solutions/java/none/cvg/optional/STest2OptionalConditionalFetching.java)
[Test3StreamsAndOptionals](src/test/java/none/cvg/optional/Test3StreamsAndOptionals.java) | [STest3StreamsAndOptionals.java](src/solutions/java/none/cvg/optional/STest3StreamsAndOptionals.java)
    

## <a name="TakeAway"></a>Take Away

The key take-away from this kata is a solid understanding of the Java Optional API.
