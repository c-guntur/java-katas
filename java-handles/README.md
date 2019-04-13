# Method Reflection and Unsafe alternates Kata

<img align="center" src="docs/davinci.png" alt="DaVinci Machine Project" title="DaVinci Machine Project" hspace="20" width="480"/>


## Table of Contents
* [What is a Code Kata](#WhatIsACodeKata)
  * [How does one go about with this kata?](#HowToSolveKata)
  * [Mission](#Mission)
  * [Requirements](#Requirements)
* [Project Structure](#ProjectStructure)
* [Java Reflection and recent alternates](#Reflection)
* [Sun Unsafe and recent alternates](#Unsafe)
* [Solutions to the Katas](#Solutions)
* [Take Away](#TakeAway)

Many Java libraries and frameworks currently use Reflection and Unsafe APIs. 
With the newer modular Java some of these important tools of our trade may become 
incompatible and/or may not work as desired. In addition several enterprise applications 
also rely on Core Reflection, (if not the use of Unsafe APIs). 


## <a name="WhatIsACodeKata"></a>What is a Code-Kata

A code-kata is a coding exercise that builds muscle memory by a practice of programming to arrive 
at a known solution.

### <a name="HowToSolveKata"></a>How does one go about with this code kata?

The essence of the exercise (presentation material and code kata) is to demonstrate the alternates 
for the current usage patterns for some of the simpler and more common usages of Java Reflection 
API and the sun.misc.Unsafe among applications, libraries and frameworks. An explanation of the 
newer Handles API will be followed with code that allows for a comparison between using both styles.

This set of code katas rely on fixing broken tests. The tests may have multiple solutions, the 
intent is to learn and experiment. 

The project contains several JUnit tests that fail with a message `"Test Failure - Fix all TODOs. "`. 

<span style=“color:green;”>**Here are simple steps to use this kata**</span>:

1. Run the test class(es).
1. One or more tests will fail with the test failure message, phrased above.
1. Fix the failing tests by taking hints from the `TODO`s.
1. Repeat above steps until all tests pass.
1. Check the solutions to see if there are other ways to solve. (Remember, the solution may be less performant than yours)
1. Rinse and repeat (delete and checkout again, then back to Step 1) to build muscle memory.

Each test class has two types of test methods:

* **Solved** test methods show how an invocation/access can be achieved with the traditional calls.

* **Unsolved/failing** test methods provide TODO hints that will allow the kata-taker to manually solve the exercise to 
achieve the same with MethodHandles/VarHandles.

### <a name="Mission"></a>Mission
> ***Your mission**, should you choose to accept it, will be to fix the JUnit tests to achieve the 
same results with the Handles API as the solved one(s) do with traditional reflection. This 
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
| |       |____constructors
| |       |____methods
| |       |____variables
| |
| |____main                    <------------------- Shared ErrorMessages & DemoClass
| | |____java
| |   |____none
| |     |____cvg
| |
| |____solutions               <------------------- Solutions 
| | |____java
| |   |____none
| |     |____cvg
| |       |____constructors
| |       |____methods
| |       |____variables
```


## <a name="Reflection"></a>Java Reflection and recent alternates

There are a few JUnit tests with an aim to practice method access alternates to Reflection.

### Tests Included

#### Constructor invocation

1. ##### [DefaultConstructorInvocationTest.java](src/test/java/none/cvg/constructors/DefaultConstructorInvocationTest.java)

   This test aims at using MethodHandles to invoke a **default constructor** on a class in order to create a new instance. 

1. ##### [ParameteredConstructorInvocationTest.java](src/test/java/none/cvg/constructors/ParameteredConstructorInvocationTest.java) 

   This test aims at using MethodHandles to invoke a **constructor with a parameter** on a class in order to create a new instance. 

#### Method invocation

1. ##### [PublicMethodInvocationTest.java](src/test/java/none/cvg/methods/PublicMethodInvocationTest.java)

   This test aims at using MethodHandles to invoke a **public method** on a class. 

1. ##### [PackageProtectedMethodInvocationTest.java](src/test/java/none/cvg/methods/PackageProtectedMethodInvocationTest.java)

   This test aims at using MethodHandles to invoke a **package-protected (default-access) method** on a class. 

1. ##### [ProtectedMethodInvocationTest.java](src/test/java/none/cvg/methods/ProtectedMethodInvocationTest.java)

   This test aims at using MethodHandles to invoke a **protected method** on a class. 

1. ##### [PrivateMethodInvocationTest.java](src/test/java/none/cvg/methods/PrivateMethodInvocationTest.java)

   This test aims at using MethodHandles to invoke a **private method** on a class. 

1. ##### [PublicStaticMethodInvocationTest.java](src/test/java/none/cvg/methods/PublicStaticMethodInvocationTest.java)

   This test aims at using MethodHandles to invoke a **public static method** on a class. 

## <a name="Unsafe"></a>sun.misc.Unsafe and recent alternates

There are a few JUnit tests with an aim to practice variable access alternates to Unsafe.

### Tests Included

1. #### Get variable values

      ##### [GetterTest.java](src/test/java/none/cvg/variables/GetterTest.java)
   
      This test aims at showing the differences between traditional reflection/Unsafe usage and the Handles API. 
      
      In each of the below: **The traditional access test passes**, solve the alternate.

      1. `public` variables.
      1. `private` variables.
      1. `one-dimensional array` variables.
      1. `two-dimensional array` variables.

1. #### Compare and Set variable values

      ##### [CompareAndSet.java](src/test/java/none/cvg/variables/CompareAndSetTest.java)

      This test aims at showing the differences between traditional reflection/Unsafe usage and the Handles API. 

      In each of the below: **The traditional access test passes**, solve the alternate.

      1. Using `AtomicReference` - **Solved**.
      1. Using `AtomicReferenceFieldUpdater` - **Solved**.
      1. Using `Unsafe` - **Solved**.
      1. Using `VarHandle` - ***Unsolved***, use the `TODO`s to fix.
      
1. #### Var Handles restrictions that were possible with Unsafe

      ##### [VarHandlesForbiddenUnsafeFeaturesTest.java](src/test/java/none/cvg/variables/VarHandlesForbiddenUnsafeFeaturesTest.java)

      This test aims at highlighting functionality available with Reflection/Unsafe that no longer are available using VarHandles.

      1. Modify a `private final` variable using traditional calls - **Solved**.
      1. Cannot modify a `private final` variable using VarHandles - ***Unsolved***, use the `TODO`s to fix.
      1. Modify a `public static final` constant using traditional calls - **Solved**.
      1. Cannot modify a `public static final` constant using VarHandles - ***Unsolved***, use the `TODO`s to fix.
      
      
## <a name="Solutions"></a>Solutions

Solutions for each test:

Kata Test | Solution
------------ | -------------
[DefaultConstructorInvocationTest.java](src/test/java/none/cvg/constructors/DefaultConstructorInvocationTest.java) | [SDefaultConstructorInvocationTest.java](src/solutions/java/none/cvg/constructors/SDefaultConstructorInvocationTest.java)
[ParameteredConstructorInvocationTest.java](src/test/java/none/cvg/constructors/ParameteredConstructorInvocationTest.java) | [SParameteredConstructorInvocationTest.java](src/solutions/java/none/cvg/constructors/SParameteredConstructorInvocationTest.java)
---- | ----
[PublicMethodInvocationTest.java](src/test/java/none/cvg/methods/PublicMethodInvocationTest.java) | [SPublicMethodInvocationTest.java](src/solutions/java/none/cvg/methods/SPublicMethodInvocationTest.java)
[PackageProtectedMethodInvocationTest.java](src/test/java/none/cvg/methods/PackageProtectedMethodInvocationTest.java) | [SPackageProtectedMethodInvocationTest.java](src/solutions/java/none/cvg/methods/SPackageProtectedMethodInvocationTest.java)
[ProtectedMethodInvocationTest.java](src/test/java/none/cvg/methods/ProtectedMethodInvocationTest.java) | [SProtectedMethodInvocationTest.java](src/solutions/java/none/cvg/methods/SProtectedMethodInvocationTest.java)
[PrivateMethodInvocationTest.java](src/test/java/none/cvg/methods/PrivateMethodInvocationTest.java) | [SPrivateMethodInvocationTest.java](src/solutions/java/none/cvg/methods/SPrivateMethodInvocationTest.java)
[PublicStaticMethodInvocationTest.java](src/test/java/none/cvg/methods/PublicStaticMethodInvocationTest.java) | [SPublicStaticMethodInvocationTest.java](src/solutions/java/none/cvg/methods/SPublicStaticMethodInvocationTest.java)
---- | ----
[GetterTest.java](src/test/java/none/cvg/variables/GetterTest.java) | [SGetterTest.java](src/solutions/java/none/cvg/variables/SGetterTest.java)
[CompareAndSetTest.java](src/test/java/none/cvg/variables/CompareAndSetTest.java) | [SCompareAndSetTest.java](src/solutions/java/none/cvg/variables/SCompareAndSetTest.java)
[VarHandlesForbiddenUnsafeFeaturesTest.java](src/test/java/none/cvg/variables/VarHandlesForbiddenUnsafeFeaturesTest.java) | [SVarHandlesForbiddenUnsafeFeaturesTest.java](src/solutions/java/none/cvg/variables/VarHandlesForbiddenUnsafeFeaturesTest.java)
    

## <a name="TakeAway"></a>Take Away

The key take-away from this kata is a solid understanding of the 
simpler and more common usages of Core Reflection API and Unsafe API alongside the newer Handles 
API both in similarity and in certain cases, how they differ.

Who knows if your next open source/enterprise contribution is with helping out a library, framework 
or an enterprise application in converting to the newer APIs ?