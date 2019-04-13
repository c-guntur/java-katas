# Reflection - To - MethodHandles Recap

* MethodHandles.Lookup the methods on the class.
* Acquire a MethodHandle to invoke:
  * by **Method type** approach ::
    * Describe the method to create a MethodType
    * `find*()` method to extract a MethodHandle
  * by **Method signature** approach ::
    * Get a Method instance by name and arity
    * `unreflect()` the Method instance to extract a MethodHandle.
    
[<< Prev](page12.md) 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; 
[Next >>](page14.md) 