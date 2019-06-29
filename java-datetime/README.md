# javasig-datetime

<img align="center" src="docs/DukeTime.png" alt="Java Duke Date Time Logo" title="Java Duke Date Time" hspace="20" width="480"/>

## Table of Contents
* [What is a Code Kata](#WhatIsACodeKata)
  * [How does one go about with this kata?](#HowToSolveKata)
  * [Mission](#Mission)
  * [Requirements](#Requirements)
* [Project Structure](#ProjectStructure)
* [Java Time API](#JavaTime)
* [Solutions to the Katas](#Solutions)
* [Take Away](#TakeAway)

Most, if not all Java developers need to rely on Java date and time libraries. 

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
| |       |____datetime
| |
| |____main                    <------------------- Shared ErrorMessages & DemoClass
| | |____java
| |   |____none
| |     |____cvg
| |       |____datetime
| |
| |____solutions               <------------------- Solutions 
| | |____java
| |   |____none
| |     |____cvg
| |       |____datetime
```

## <a name="JavaTime"></a>Java Time API

The JUnit tests listed below are setup to utilize the Java Time API features.

### Tests Included

#### Java Date Time

1. ##### [Test1InstantAndDateInteropTest](src/test/java/none/cvg/datetime/Test1InstantAndDateInteropTest.java)

   The tests in this class show interoperability between `java.util.Date` and the newer `java.time.Instant`. 

1. ##### [Test2LocalAndZonedDateTimesTest.java](src/test/java/none/cvg/datetime/Test2LocalAndZonedDateTimesTest.java) 

   The tests in this class show the usage of `java.time.LocalDate`, `java.time.LocalTime`, `java.time.LocalDateTime` and `java.time.ZonedDateTime`. 
   In addition, this class introduces a `java.time.Clock` that is extremely handy in writing tests. 

1. ##### [Test3PeriodsAndDurationsTest.java](src/test/java/none/cvg/datetime/Test3PeriodsAndDurationsTest.java)

   The tests in this class show the usage of DateTime ranges: Period, Duration tests. 

1. ##### [Test4DateTimePartialsTest.java](src/test/java/none/cvg/datetime/Test4DateTimePartialsTest.java)

   The tests in this class show the usage of DateTime partials: Month, MonthDay, Year, YearMonth and DayOfWeek tests. 

1. ##### [Test5StreamsInDateTimeTest.java](src/test/java/none/cvg/datetime/Test5StreamsInDateTimeTest.java)

   The tests in this class show the usage of DateTime in Java `stream()` lazy iterations. 

      
## <a name="Solutions"></a>Solutions

Solutions for each test:

Kata Test | Solution
------------ | -------------
[Test1InstantAndDateInteropTest](src/test/java/none/cvg/datetime/Test1InstantAndDateInteropTest.java) | [STest1InstantAndDateInteropTest.java](src/solutions/java/none/cvg/datetime/STest1InstantAndDateInteropTest.java)
[Test2LocalAndZonedDateTimesTest](src/test/java/none/cvg/datetime/Test2LocalAndZonedDateTimesTest.java) | [STest2LocalAndZonedDateTimesTest.java](src/solutions/java/none/cvg/datetime/STest2LocalAndZonedDateTimesTest.java)
[Test3PeriodsAndDurationsTest](src/test/java/none/cvg/datetime/Test3PeriodsAndDurationsTest.java) | [STest3PeriodsAndDurationsTest.java](src/solutions/java/none/cvg/datetime/STest3PeriodsAndDurationsTest.java)
[Test4DateTimePartialsTest](src/test/java/none/cvg/datetime/Test4DateTimePartialsTest.java) | [STest4DateTimePartialsTest.java](src/solutions/java/none/cvg/datetime/STest4DateTimePartialsTest.java)
[Test5StreamsInDateTimeTest](src/test/java/none/cvg/datetime/Test5StreamsInDateTimeTest.java) | [STest5StreamsInDateTimeTest.java](src/solutions/java/none/cvg/datetime/STest5StreamsInDateTimeTest.java)
    

## <a name="TakeAway"></a>Take Away

The key take-away from this kata is a solid understanding of the Java Time API.
